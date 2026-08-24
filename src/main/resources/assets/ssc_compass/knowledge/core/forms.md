# 幻形者诅咒（SSC/SSCA）形态与变形指令

> **数据库更新时间：2026-08-24**（数据来源：SSC 原版 1.10.0 源码 lang 与 SSCA 8.0.0-beta.2 源码 `SscAddonForms.register()` / `FormIdentifiers` / `zh_cn.json`，依照代码逐条核对）

本页给出所有形态的准确 ID、官方名，以及如何变形、为什么改血量上限/饱食度会「看起来没生效」。变形需 OP / 作弊模式。

## 变形指令

- 原版内置形态与附属(SSCA)形态都是普通形态，用：
  - `/shape_shifter_curse set_form @s <形态ID>`：直接设置形态（无变身特效）。
  - `/shape_shifter_curse transform_to_form @s <形态ID>`：变身到形态（带变身特效）。
- `set_dynamic_form` / `transform_to_dynamic_form` 只用于第三方数据包定义的「动态形态」；SSC 原版与 SSCA 官方形态都不是动态形态，不要用它们。
- 形态ID 必须带命名空间：原版是 `shape-shifter-curse:xxx`，附属(SSCA)是 `my_addon:xxx`。
- 变形前先确认形态ID 与玩家想要的形态一致（可先用 get_form_status 读当前形态，看它真实的 ID 格式作参考）。若 set_form 提示找不到该形态，多半是 ID 或命名空间写错了。

## 人类形态（两种，变之前务必问清楚）

玩家说「变回人类 / 变成人」时有两种，先问是哪一种：
- 开书前的普通人类：`shape-shifter-curse:original_before_enable`（官方名「原版(开启模组内容前)」）。
- 开书后的幻形者人类：`shape-shifter-curse:original_shifter`（官方名「幻形者初始形态」）。

## 原版形态 ID → 官方名（命名空间 shape-shifter-curse）

阶段 0=初始，1/2=中间阶段，3=永久（终）形态。
- 蝙蝠：`bat_0`/`bat_1`/`bat_2`/`bat_3` = 蝙蝠形态(0/1/2/永久阶段)
- 美西螈：`axolotl_0`/`axolotl_1`/`axolotl_2`/`axolotl_3` = 美西螈形态(...)
- 豹猫：`ocelot_0`/`ocelot_1`/`ocelot_2`/`ocelot_3` = 豹猫形态(...)
- 使魔红狐：`familiar_fox_0`/`familiar_fox_1`/`familiar_fox_2`/`familiar_fox_3` = 使魔红狐形态(...)
- 雪狐：`snow_fox_0`/`snow_fox_1`/`snow_fox_2`/`snow_fox_3` = 雪狐形态(...)
- 胡狼（阿努比斯之狼）：`anubis_wolf_0`/`anubis_wolf_1`/`anubis_wolf_2`/`anubis_wolf_3` = 胡狼形态(...)
- 蜘蛛：`spider_0`/`spider_1`/`spider_2`/`spider_3` = 蜘蛛形态(...)
- 悦灵：`allay_sp` = 悦灵形态(sp)
- 野猫：`feral_cat_sp` = 野猫形态(sp)

## 附属(SSCA)形态 ID → 官方名（命名空间 my_addon）

必须严格使用官方名，不要自己翻译或改名（例如 `familiar_fox_red` 官方叫「使魔(Red)」，不能叫「红色使魔」）。注意 formID 不带 `form_` 前缀（`form_` 只是 origin 图层名）：
- `my_addon:familiar_fox_sp` = SP使魔
- `my_addon:familiar_fox_red` = 使魔(Red)
- `my_addon:familiar_fox_mancianima` = 契灵
- `my_addon:upgrade_familiar_fox` = 进化使魔
- `my_addon:snow_fox_sp` = SP雪狐
- `my_addon:axolotl_sp` = SP美西螈
- `my_addon:upgrade_axolotl` = 进化美西螈
- `my_addon:axolotl_fluorescent` = 荧光幼灵
- `my_addon:axolotl_aling` = 阿澪
- `my_addon:anubis_wolf_sp` = 冥裁者
- `my_addon:wild_cat_sp` = 野猫(SP)
- `my_addon:allay_sp` = SP悦灵
- `my_addon:fallen_allay_sp` = SP堕落悦灵
- `my_addon:golden_sandstorm_sp` = 金沙岚(SP)
- `my_addon:bat_desmodus` = 吸血蝙蝠
- `my_addon:bat_parasitic_fruit` = 寄生果蝠
- `my_addon:ocelot_wind_spirit` = 风灵
- `my_addon:ocelot_nova` = 朔望
- `my_addon:spider_moon_weaver` = 月织蛛
- `my_addon:spider_salticidae` = 跳蛛
- `my_addon:wild_cat_nightmare` = 食梦魔
- `my_addon:snow_fox_frostspine` = 寒棘狐

（注意区分：原版的 `shape-shifter-curse:allay_sp` 是「悦灵形态(sp)」，SSCA 的 `my_addon:allay_sp` 是「SP悦灵」，两者不同。）

## 生命上限（max_health）为什么「改不动」

很多形态通过「属性修饰符」给玩家生命上限加了负修正（例如使魔系 SP使魔/使魔Red/契灵/进化使魔 都是 -6，即 -3 颗心）。`/attribute @s minecraft:generic.max_health base set X` 改的是「基础值」，最终上限 = X + 形态修正。所以：
- 在使魔系形态里 `base set 30`，实际上限 = 30-6 = 24（12 颗心），不是 15 颗心，很容易被误会成「没生效」。
- `base set` 只改上限外框、不会回血，当前血量不变，看起来更像「没变」。
真正改到目标值的办法：① 把命令数值补上修正量（想在使魔系得到 30，就 `base set 36`）；② 或先变回人类/无修正形态再 `base set`；③ 改完用 get_player_status 读实际上限确认，再补血把血条填满。改完记得如实告诉玩家「当前形态有 -N 的生命上限修正」。

## 补饱食度为什么「补不上」

部分形态免疫 `minecraft:saturation` 药水效果（使魔SP/使魔Red/契灵、以及点了「buff免疫」天赋的进化使魔，其免疫表里含 saturation），所以 `/effect give @s minecraft:saturation` 在这些形态里无效。

给这些免疫形态补饱食度，应改用「压缩能量药水」（自定义药水 `shape-shifter-curse:feed_potion`，效果是喂食/补饱食度，免疫形态不挡它）——**优先给滞留版**：
- 滞留版（优先）：`/give @s minecraft:lingering_potion{Potion:"shape-shifter-curse:feed_potion"}`（扔出后产生停留云雾，站进去回饱食度/能量）。
- 喷溅版：`/give @s minecraft:splash_potion{Potion:"shape-shifter-curse:feed_potion"}`。
- 饮用版：`/give @s minecraft:potion{Potion:"shape-shifter-curse:feed_potion"}`。
- SSCA 还有「无限压缩能量药水」独立物品（可反复使用）：饮用 `ssc_addon:infinite_energy_potion`、喷溅 `ssc_addon:infinite_energy_potion_splash`、滞留 `ssc_addon:infinite_energy_potion_lingering`。

给玩家补饱食度的流程：若当前形态不免疫 saturation，可直接用 saturation；若免疫（使魔系），优先 give 压缩能量药水滞留版。补完用 get_player_status 确认，别谎称已补满。
