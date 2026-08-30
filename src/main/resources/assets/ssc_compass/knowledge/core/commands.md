# 幻形者诅咒（SSC）与附属（SSCA）指令大全

> **数据库更新时间：2026-08-24**（SSCA 指令依照代码 `SscAddonCommands.java` 注册表逐条核对，与 8.0.0-beta.2 一致；SSC 原版指令以 1.10.0 为准）

本页列出 SSC 原版与 SSCA 附属的所有游戏指令、作用与权限，以及如何恢复法力值 mana。命令语法均以《我的世界》1.20.1 为准。

## 恢复法力值 mana 的方法

在 SSCA 里，「能量」「法力」「mana」指的是同一样东西——玩家说「把我的能量补满」「回满法力」「恢复 mana」都等同于同一个操作。不同形态里它的叫法不同（雪狐是「资源」、悦灵是「法力 mana」、阿努比斯之狼是「灵魂能量」、契灵有「共鸣抗性」等），可以这样恢复：

- 用指令一次性设满（需 OP / 作弊模式）：`/ssc_addon set_mana <目标> <数值>`。它会把目标玩家的所有法力/能量资源一次性设为该数值（超过各自上限时自动取上限）。想给自己补满就用一个很大的数，例如：`/ssc_addon set_mana @s 999999`。
- 法力也会随时间**自动回复**（每 tick 有回复量）；部分形态在获得能力或复活时会自动补满法力。
- 契灵的「共鸣抗性」单独管理：`/ssc_addon resistance set <数值> [玩家]` 设置、`/ssc_addon resistance add <增量> [玩家]` 增减、`/ssc_addon resistance get [玩家]` 查询。

## 让 Compass 帮你恢复 mana（作弊模式）

如果你想让 Compass 直接帮你恢复法力：先在 Compass 设置里开启「作弊模式」（仅 OP 可开），然后对 Compass 说「帮我恢复/补满法力」，它会执行 `/ssc_addon set_mana @s 999999` 为你补满。若未开作弊或没有 OP 权限，出于安全无法执行（这是客户端强制校验，无法通过对话绕过）。

## SSCA 附属指令 /ssc_addon

多数需要 OP 权限（等级 2）：

- `/ssc_addon set_mana <目标> <数值>`：设置目标所有法力/能量资源（**恢复 mana 就用这个**）。
- `/ssc_addon resistance get|set <值>|add <增量> [玩家]`：契灵「共鸣抗性」的查询 / 设置 / 增减。
- `/ssc_addon skill <形态> <技能> [玩家]`：手动触发某形态的技能。形态可选 snow_fox / anubis_wolf / allay / axolotl / wild_cat / familiar_fox / familiar_fox_red。
- `/ssc_addon block <玩家> <形态> <技能>` / `/ssc_addon unblock <玩家> <形态> <技能>` / `/ssc_addon list_blocks <玩家>`：封禁 / 解封 / 查看某玩家被封禁的技能。
- `/ssc_addon evolution unlock_all|reset [玩家]`：SSCA 进化加点全解锁 / 重置。
- `/ssc_addon mancianima_assault reset|lock|status [玩家]`：契灵「敲钟袭击」每日冷却的重置 / 锁定 / 查询。
- `/ssc_addon mark_owner <目标>`：把目标生物标记为执行者的宠物（写入主人 UUID 标签）。
- `/ssc_addon get_book <书籍ID> [语言]` / `/ssc_addon list_books [语言]` / `/ssc_addon reload_books`：给予 / 列出 / 重载剧情书（语言填 zh_cn 或 en_us）。
- `/ssc_addon reload`：重载 SSCA 配置。
- `/ssc_addon debug form|mana|anim`：调试当前形态 / 法力 / 动画日志。

无需 OP：

- `/ssc_addon my_whitelist`：打开自助白名单 GUI（只作用于自己）。
- `/ssc_addon palette export`：导出当前形态配色为分享码；`/ssc_addon palette apply <分享码>`：应用配色分享码（只作用于自己）。
- 朔望形态的主 / 次技能已改为内部自定义 action 触发（`ssc_addon:nova_charge` / `ssc_addon:nova_leap`），不再提供 `nova primary|secondary` 指令。

## SSC 原版指令 /shape_shifter_curse

需要 OP（等级 2）：

- `/shape_shifter_curse set_form <目标> <形态>`：直接设置形态（无变身特效）。
- `/shape_shifter_curse transform_to_form <目标> <形态>`：变身到形态（带变身特效）。
- `/shape_shifter_curse set_dynamic_form` / `transform_to_dynamic_form` / `set_sub_form` / `transform_to_sub_form <目标> <形态>`：分别用于动态形态、子形态的设置 / 变身。
- `/shape_shifter_curse jump_to_next_cursed_moon`：直接跳到下一个诅咒之月。
- `/shape_shifter_curse world_time set <时间>` / `add <时间>`：设置 / 增加世界时间。

无需 OP（等级 0）：

- `/shape_shifter_curse keep_original_skin <true|false>`：是否保留玩家原皮肤。
- `/shape_shifter_curse set_form_color <enable> [颜色参数...]`：设置形态配色。
- `/shape_shifter_curse form_color menu|save|load|delete|list|config|to_chat|set_color_from_string ...`：配色的菜单 / 存档槽保存 / 读取 / 删除 / 列表 / 分享等。
- `/shape_shifter_curse patron_info`：查看赞助者信息。
- `/shape_shifter_curse debug ...`：清除玩家形态 / 皮肤 / 随从 / 法力数据等调试子命令。
