"""Rebuild checked-in knowledge from local SSC release jars and SSCA checkouts.

No network, lore, loot tables, world rune seeds or solved spell/rune recipes.
The normal Gradle build uses the checked-in snapshot and needs no sibling repo.
"""
import argparse
import hashlib
import json
from pathlib import Path
import re
import subprocess
import zipfile

ROOT = Path(__file__).resolve().parents[1]
OUTPUT = ROOT / "src/main/resources/assets/ssc_compass/knowledge/generated"


def digest(data):
    return hashlib.sha256(data).hexdigest()


def props(path):
    return dict(re.findall(r"^\s*(\w+)\s*=\s*(.*?)\s*$", path.read_text("utf-8"), re.M))


def generate(label, mod, version, files, lang, provenance):
    hashes = {}
    sections = {key: [] for key in ("forms", "powers", "items", "recipes", "spells")}

    def read(name):
        data = files[name]
        hashes[name] = digest(data)
        return json.loads(data)

    def tr(value):
        if isinstance(value, dict):
            value = value.get("translate", value.get("text", ""))
        return str(lang.get(value, value)) if value else ""

    def add(kind, title, body, source):
        sections[kind].append(f"## {title}\n\n{body}\n\n来源：{source}；{provenance}。数值是出厂默认，服务器配置与数据包优先。\n")

    prefix = "data/shape-shifter-curse/" if mod == "shape-shifter-curse" else "data/my_addon/"
    used_powers = set()
    for name in sorted(files):
        if not name.startswith(prefix + "origins/form_") or not name.endswith(".json"):
            continue
        obj = read(name)
        namespace = name.split("/")[1]
        path = Path(name).stem.removeprefix("form_")
        form_id = f"{namespace}:{path}"
        codex = f"codex.form.{namespace}.{path}"
        title = tr(obj.get("name")) or lang.get(f"origin.{namespace}.form_{path}.name") or lang.get(codex + ".name") or path
        powers = list(dict.fromkeys(obj.get("powers", [])))
        used_powers.update(powers)
        body = f"形态 ID：{form_id}。Origin 数据 ID：{namespace}:form_{path}，两者不能混用。\n"
        codex_name = lang.get(codex + ".name", "").strip()
        if codex_name and codex_name != title:
            title += " / " + codex_name
        # SSCA codex mechanics fields also contain backstory paragraphs. Use powers there.
        fields = [("pros", "能力与操作"), ("cons", "限制与弱点"), ("equip", "建议装备")] if mod == "shape-shifter-curse" else []
        for suffix, heading in fields:
            value = lang.get(codex + "." + suffix, "").strip()
            if value:
                body += f"{heading}：{value}\n"
        if mod == "ssc_addon":
            for power in powers:
                power_ns, power_path = power.split(":", 1)
                power_file = f"data/{power_ns}/powers/{power_path}.json"
                if power_file not in files:
                    continue
                info = read(power_file)
                if info.get("hidden", False):
                    continue
                power_base = f"power.{power_ns}.{power_path}"
                power_title = tr(info.get("name")) or lang.get(power_base + ".name", power)
                desc = tr(info.get("description")) or lang.get(power_base + ".description", "")
                if desc:
                    body += f"{power_title}：{desc}\n"
        body += "挂载能力：" + "、".join(powers) if powers else "无 Origin 能力（临时/初始形态请结合状态工具判断）。"
        add("forms", f"{title} {form_id}", body, name)

    for power_id in sorted(used_powers):
        namespace, path = power_id.split(":", 1)
        name = f"data/{namespace}/powers/{path}.json"
        if name not in files:
            continue  # Some powers belong to the dependency, not this snapshot.
        obj = read(name)
        base = f"power.{namespace}.{path}"
        title = tr(obj.get("name")) or lang.get(base + ".name", path)
        desc = tr(obj.get("description")) or lang.get(base + ".description", "")
        body = f"能力 ID：{power_id}；类型：{obj.get('type', '未知')}。\n{desc}\n"
        for key in ("min", "max", "start_value", "cooldown", "tick_rate", "condition", "modifier", "modifiers",
                    "food_modifier", "food_modifiers", "saturation_modifier", "saturation_modifiers", "always_edible", "recipe"):
            if key in obj:
                body += key + "：" + json.dumps(obj[key], ensure_ascii=False, separators=(",", ":")) + "\n"
        add("powers", f"{title} {power_id}", body, name)

    for key, value in sorted(lang.items()):
        match = re.fullmatch(r"(item|block|effect)\.([\w-]+)\.([\w/]+)", key)
        if not match or any(word in key for word in ("story", "written_book")):
            continue
        kind, namespace, path = match.groups()
        if namespace not in ("ssc_addon", "my_addon", "shape-shifter-curse"):
            continue
        body = f"注册 ID：{namespace}:{path}；类别：{kind}。\n"
        for suffix in ("tooltip", "description"):
            if key + "." + suffix in lang:
                body += lang[key + "." + suffix] + "\n"
        add("items", f"{value} {namespace}:{path}", body, "assets/*/lang/zh_cn.json")

    for name in sorted(files):
        if re.match(r"data/(ssc_addon|shape-shifter-curse)/recipes/.*\.json$", name):
            obj = read(name)
            encoded = json.dumps(obj, ensure_ascii=False, separators=(",", ":"))
            if any(word in encoded for word in ("written_book", "story_books", "SlottedSpellRecipes", "rune_scheme")):
                continue
            result = obj.get("result", {})
            result_id = result if isinstance(result, str) else result.get("item", "")
            item_key = "item." + result_id.replace(":", ".")
            block_key = "block." + result_id.replace(":", ".")
            title = lang.get(item_key, lang.get(block_key, result_id))
            add("recipes", f"{title} 配方 {Path(name).stem}", encoded + "\n需以 query_recipe 查询服务器实时注册配方；未显示的 Apoli 自定义合成可能是能力型配方。", name)
        elif name.startswith("data/ssc_addon/spells/") and name.endswith(".json"):
            obj = read(name)
            path = Path(name).stem
            base = f"spell.ssc_addon.{path}"
            title = lang.get(base + ".name", path)
            desc = lang.get(base + ".description", "")
            body = f"法术 ID：ssc_addon:{path}。{desc}\n默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：\n"
            body += json.dumps(obj, ensure_ascii=False, separators=(",", ":"))
            add("spells", f"{title} ssc_addon:{path}", body, name)

    result = {}
    for kind, parts in sections.items():
        if parts:
            result[f"{label}-{kind}.md"] = f"<!-- compass: mod={mod} version={version} -->\n\n" + "\n".join(parts)
    return result, {"mod": mod, "version": version, "provenance": provenance,
                    "counts": {k: len(v) for k, v in sections.items()}, "inputs_sha256": hashes}


def jar_snapshot(path, label):
    with zipfile.ZipFile(path) as jar:
        meta = json.loads(jar.read("fabric.mod.json"))
        files = {n: jar.read(n) for n in jar.namelist() if n.endswith(".json") and n.startswith(("data/shape-shifter-curse/", "assets/shape-shifter-curse/lang/"))}
    lang = json.loads(files["assets/shape-shifter-curse/lang/zh_cn.json"])
    docs, manifest = generate(label, meta["id"], meta["version"], files, lang, f"SSC {meta['version']} 发布 JAR")
    manifest["jar_sha256"] = digest(path.read_bytes())
    return docs, manifest


def addon_snapshot(path, label):
    properties = props(path / "gradle.properties")
    resources = path / "src/main/resources"
    files = {p.relative_to(resources).as_posix(): p.read_bytes() for p in resources.rglob("*.json")
             if any(part in ("data", "lang") for part in p.relative_to(resources).parts)
             and not any(part in ("story_books", "loot_tables", "advancements") for part in p.parts)}
    lang = {}
    for name, data in sorted(files.items()):
        if name.endswith("/lang/zh_cn.json"):
            lang.update(json.loads(data))
    commit = subprocess.check_output(["git", "-c", "safe.directory=" + path.resolve().as_posix(), "-C", str(path), "rev-parse", "HEAD"], text=True).strip()
    docs, manifest = generate(label, "ssc_addon", properties["mod_version"], files, lang,
                              f"SSCA {properties['mod_version']} 源码 {commit[:12]}，SSC {properties['ssc_version']}")
    manifest["commit"] = commit
    manifest["ssc_version"] = properties["ssc_version"]
    manifest["inputs_sha256"].update({n: digest(d) for n, d in files.items() if n.endswith("/lang/zh_cn.json")})
    java_root = path / "src/main/java/net/jackcooper/shapeShifterCurseAddon"
    def java(name):
        p = java_root / name
        manifest["inputs_sha256"][p.relative_to(path).as_posix()] = digest(p.read_bytes())
        return re.sub(r"//[^\n]*|/\*.*?\*/", "", p.read_text("utf-8"), flags=re.S)
    constants = {key: f"{ns}:{value}" for key, ns, value in re.findall(
        r'(\w+)\s*=\s*new Identifier\("([^"]+)",\s*"([^"]+)"\)', java("util/FormIdentifiers.java"))}
    ring = {f"{ns}:{value}": f"{tns}:{target}" for ns, value, tns, target in re.findall(
        r'registerUpgrade\("([^"]+)",\s*"([^"]+)",\s*"([^"]+)",\s*"([^"]+)"\)', java("SpUpgradeItem.java"))}
    stone = {}
    for ns, value, target in re.findall(
        r'playerFormID.equals\(new Identifier\("([^"]+)",\s*"([^"]+)"\)\)\)\s*\{\s*targetFormId\s*=\s*([^;]+);', java("EvolutionStoneItem.java")):
        literal = re.search(r'new Identifier\("([^"]+)",\s*"([^"]+)"\)', target)
        stone[f"{ns}:{value}"] = f"{literal[1]}:{literal[2]}" if literal else constants[target.strip().split(".")[-1]]
    assert len(ring) == 11 and len(stone) == 11, "Evolution layout changed; review extraction before publishing"
    def form_name(identifier):
        ns, value = identifier.split(":")
        return lang.get(f"origin.{ns}.form_{value}.name", lang.get(f"codex.form.{ns}.{value}.name", identifier))
    parts = []
    for start in sorted(ring.keys() | stone.keys()):
        text = f"起点：{form_name(start)} {start}。\n"
        if start in ring:
            text += f"月髓十字环（月髓环）：→ {form_name(ring[start])} {ring[start]}，要求诅咒之月实际夜晚。\n"
        if start in stone:
            text += f"进化石：→ {form_name(stone[start])} {stone[start]}，不要求诅咒之月夜。\n"
        text += "如果当前起点属于SSCA进化路线，必须满足路线与全部分支解锁门控；普通原版起点不受该路线门控。两种道具的分支不能互换。\n"
        text += "已是SP形态时不要在诅咒之月乱用月髓环：代码存在爆炸和致死惩罚；特定临时形态的免费回退单独处理。\n"
        text += f"来源：SpUpgradeItem.UPGRADE_MAP、EvolutionStoneItem.finishUsing、EvolutionManager.canUpgradeFoxEvolve；SSCA {properties['mod_version']}。\n"
        parts.append(f"## {form_name(start)} {start} 月髓环与进化石进化路线\n\n{text}")
    docs[f"{label}-evolution.md"] = f"<!-- compass: mod=ssc_addon version={properties['mod_version']} -->\n\n" + "\n".join(parts)
    manifest["counts"]["evolution"] = len(parts)
    return docs, manifest


def wiki_snapshot(path):
    commit = subprocess.check_output(["git", "-C", str(path), "rev-parse", "HEAD"], text=True).strip()
    docs, hashes = {}, {}
    for source in sorted((path / "docs/ssc").rglob("*.md")):
        relative = source.relative_to(path).as_posix()
        text = source.read_text("utf-8")
        hashes[relative] = digest(source.read_bytes())
        text = re.sub(r"\A---\n.*?\n---\n", "", text, flags=re.S)
        text = re.sub(r"<EChart\b.*?/>", "", text, flags=re.S)
        text = re.sub(r"<ModelViewer\b.*?/>", "", text, flags=re.S)
        text = re.sub(r"```(?:mermaid|jsx|tsx).*?```", "", text, flags=re.S)
        text = re.sub(r"<!--.*?-->", "", text, flags=re.S)
        text = re.sub(r"^import .*?$", "", text, flags=re.M)
        text = re.sub(r"^:::[^\n]*$", "", text, flags=re.M)
        if source.stem == "cursed-moon":
            text = text.replace("已经过中午、尚未到午夜", "在游戏内18:00之后、次日05:00之前")
            text = text.replace("23000` tick（23:00）", "23000` tick（次日05:00）")
        title = re.search(r"^# (.+)$", text, re.M).group(1)
        headings = list(re.finditer(r"^(#{1,3}) (.+)$", text, re.M))
        parts, parents = [], []
        for i, heading in enumerate(headings):
            level = len(heading.group(1))
            parents = parents[:level - 1]
            parents.append(heading.group(2))
            end = headings[i + 1].start() if i + 1 < len(headings) else len(text)
            body = text[heading.end():end].strip()
            if not body:
                continue
            url = f"https://github.com/LynxCatTheThird/SSC-wiki/blob/{commit}/{relative}"
            parts.append("## " + " · ".join(parents) + "\n\n" + body + "\n\n"
                         + f"来源：SSC 详细 Wiki《{title}》，{url}；SSC 1.10.0 基线；"
                         + "LynxCatTheThird/SSC-wiki contributors；CC-BY-NC-SA-4.0；已移除交互图表并拆分标题。\n")
        docs["wiki110-" + source.stem + ".md"] = "<!-- compass: mod=shape-shifter-curse version=1.10.0 -->\n\n" + "\n".join(parts)
    return docs, {"version": "1.10.0", "commit": commit, "license": "CC-BY-NC-SA-4.0", "inputs_sha256": hashes}


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    for key in ("ssc192", "ssc110", "addon192", "addon110"):
        parser.add_argument("--" + key, type=Path, required=True)
    parser.add_argument("--check", action="store_true", help="Compare without writing")
    parser.add_argument("--wiki", type=Path, required=True, help="Local clone of LynxCatTheThird/SSC-wiki")
    args = parser.parse_args()
    all_docs, manifests = {}, {}
    for key, loader in (("ssc192", jar_snapshot), ("ssc110", jar_snapshot), ("addon192", addon_snapshot), ("addon110", addon_snapshot)):
        docs, manifest = loader(getattr(args, key), key)
        all_docs.update(docs)
        manifests[key] = manifest
    docs, manifest = wiki_snapshot(args.wiki)
    all_docs.update(docs)
    manifests["wiki110"] = manifest
    all_docs["manifest.json"] = json.dumps(manifests, ensure_ascii=False, indent=2) + "\n"
    if args.check:
        assert set(all_docs) == {p.name for p in OUTPUT.iterdir() if p.is_file()}, "Snapshot file set differs"
        for name, content in all_docs.items():
            assert (OUTPUT / name).read_text("utf-8") == content, f"Snapshot is stale: {name}"
    else:
        OUTPUT.mkdir(parents=True, exist_ok=True)
        for name, content in all_docs.items():
            (OUTPUT / name).write_text(content, "utf-8", newline="\n")
    license_path = OUTPUT.parent / "licenses/CC-BY-NC-SA-4.0.txt"
    license_text = (args.wiki / "LICENSES/CC-BY-NC-SA-4.0.txt").read_text("utf-8")
    if args.check:
        assert license_path.read_text("utf-8") == license_text
    else:
        license_path.parent.mkdir(parents=True, exist_ok=True)
        license_path.write_text(license_text, "utf-8")
    print(json.dumps({k: v.get("counts", {"pages": len(v["inputs_sha256"])}) for k, v in manifests.items()}, ensure_ascii=False))


if __name__ == "__main__":
    main()
