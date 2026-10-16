"""Check optional integrations against actual Minecraft 1.21.1 mod JARs, without launching a game."""
import argparse
import io
import json
from pathlib import Path
import zipfile
from check_ssc_api import members


def classes(path):
    found = {}
    def read(data):
        with zipfile.ZipFile(data) as jar:
            for name in jar.namelist():
                if name.endswith(".class"):
                    found[name[:-6].replace("/", ".")] = jar.read(name)
                elif name.startswith("META-INF/jars/") and name.endswith(".jar"):
                    read(io.BytesIO(jar.read(name)))
    read(path)
    return found


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--ssc", type=Path, required=True)
    parser.add_argument("--ssca", type=Path, required=True)
    parser.add_argument("--trinkets", type=Path, required=True)
    args = parser.parse_args()
    sources = {name: classes(getattr(args, name)) for name in ("ssc", "ssca", "trinkets")}
    checks = 0
    def expect(source, owner, symbol, descriptor=None):
        nonlocal checks
        data = sources[source][owner]
        assert any(name == symbol and flags & 1 and (descriptor is None or desc == descriptor)
                   for name, desc, flags in members(data)), (source, owner, symbol, descriptor)
        checks += 1
    ssc = "net.onixary.shapeShifterCurseFabric."
    addon = "net.jackcooper.shapeShifterCurseAddon."
    stack = "Lnet/minecraft/class_1799;"
    player = "Lnet/minecraft/class_1657;"
    lookup = "Lnet/minecraft/class_7225$class_7874;"
    expect("ssc", ssc + "player_form.utils.FormUtils", "getPlayerForm", "(" + player + ")Lnet/onixary/shapeShifterCurseFabric/player_form/IForm;")
    expect("ssc", ssc + "player_form.IForm", "getFormID")
    expect("ssc", ssc + "mana.ManaUtils", "getManaComponent")
    for method in ("getManaTypeID", "getMana", "getMaxMana"):
        expect("ssc", ssc + "mana.ManaComponent", method)
    expect("ssc", "org.ladysnake.cca.api.v3.component.ComponentKey", "get")
    apoli = "io.github.apace100.apoli."
    for method in ("KEY", "getPowers"):
        expect("ssc", apoli + "component.PowerHolderComponent", method)
    for method in ("getValue", "getMax"):
        expect("ssc", apoli + "power.VariableIntPower", method)
    for method in ("getRemainingTicks", "cooldownDuration"):
        expect("ssc", apoli + "power.CooldownPower", method)
    for method in ("doesApply", "getFoodModifiers", "getSaturationModifiers", "doesMakeAlwaysEdible"):
        expect("ssc", apoli + "power.ModifyFoodPower", method)
    expect("ssc", apoli + "util.modifier.Modifier", "apply", "(Lnet/minecraft/class_1297;D)D")
    for method in ("getEquippedBook", "getSelectedSlot"):
        expect("ssca", addon + "client.SpellcastClient", method)
    for method in ("getLevel", "getSlotCount", "getMaxMana", "getMana", "getExp"):
        expect("ssca", addon + "spell.SpellbookData", method, "(" + stack + ")I")
    expect("ssca", addon + "spell.SpellbookData", "getScroll", "(" + lookup + stack + "I)" + stack)
    expect("ssca", addon + "spell.SpellbookData", "getCooldownRemaining", "(" + stack + "ILnet/minecraft/class_1937;)J")
    for method in ("getSpell", "getLevel"):
        expect("ssca", addon + "spell.ScrollData", method)
    expect("ssca", addon + "spell.Spell", "getManaCost", "()I")
    expect("ssca", addon + "evolution.RegEvolutionComponent", "EVOLUTION")
    for method in ("getRoute", "getPoints", "getEvoLevel", "isUnlocked"):
        expect("ssca", addon + "evolution.EvolutionComponent", method)
    for method in ("INSTANCE", "all", "getRoute", "getRouteByStartForm"):
        expect("ssca", addon + "evolution.EvolutionRegistry", method)
    for field in ("nodes", "enabled", "startForm", "displayNameKey"):
        expect("ssca", addon + "evolution.EvolutionRoute", field)
    for field in ("id", "prereqs", "cost", "branch", "autoUnlock", "nameKey"):
        expect("ssca", addon + "evolution.EvolutionNode", field)
    expect("trinkets", "dev.emi.trinkets.api.TrinketsApi", "getTrinketComponent", "(Lnet/minecraft/class_1309;)Ljava/util/Optional;")
    expect("trinkets", "dev.emi.trinkets.api.TrinketComponent", "getAllEquipped")
    print(f"Compass 1.21.1 optional API checks PASS: {checks} checks; SSC Unofficial, Apoli Legacy, CCA 6, SSCA and Trinkets. No game launched.")


if __name__ == "__main__":
    main()
