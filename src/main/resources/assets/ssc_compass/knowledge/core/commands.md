<!-- compass: mod=ssc_addon version=8.0.0-magic-dev.3-ssc-1.9.2,8.0.0-magic-dev.4 -->

## 形态法力恢复 set_mana 与魔法书法力的区别
/ssc_addon set_mana <目标玩家> <非负整数> 设置代码明确支持的形态资源：雪狐资源、SP悦灵法力、吸血蝙蝠雾血、冥裁者灵魂能量和 SSC 原生 ManaComponent，均按各自上限限制。例：/ssc_addon set_mana @s 999999。
它不会设置月尘魔法书 NBT 的 Mana、寄生果蝠种子量或契灵共鸣抗性，也不代表所有能量系统已补满。契灵抗性用 /ssc_addon resistance get [玩家]、set <值> [玩家]、add <增量> [玩家] 单独管理。恢复速度和条件随形态而异，不能说所有资源每 tick 都自动回复。
来源：command/SscAddonCommands.java:setMana、resistanceGet/Set/Add。

## 月尘魔法书经验指令 moon_dust_spellbook exp addon
/ssc_addon moon_dust_spellbook exp addon <正整数>：给执行者主手持有的月尘魔法书增加经验，主手不是书时检查副手；不接受玩家选择器。需要 OP 2。amount=1 表示 1.0 经验，内部 ExpTen 增加 10；溢出会拒绝。
此命令只加经验，不代替注魔台升级，不直接设等级、不补法力。法术施放经验和书等级是另一套机制。
来源：command/SscAddonCommands.java:addSpellbookExp、spell/SpellbookData.java。

## 法术冷却重置 moon_dust_spellbook cd reset 与旧命令
/ssc_addon moon_dust_spellbook cd reset [目标玩家]，省略目标时作用于执行玩家；控制台必须指定目标。需要 OP 2。
旧命令 /ssc_addon reset_spell_cd [目标玩家] 仍可用。它们清除目标的服务端共享法术冷却、所佩戴书内卷轴的冷却 NBT 和施法公共间隔。不强制中断正在进行的施法，也不清除所有形态技能冷却。
来源：command/SscAddonCommands.java:resetSpellCd。

## SSCA 管理、技能、调试与白名单指令
需 OP 2：/ssc_addon skill <形态> <技能> [玩家]；block <玩家> <形态> <技能>；unblock <玩家> <形态> <技能>；list_blocks <玩家>；evolution unlock_all|reset [玩家]；mancianima_assault reset|lock|status [玩家]；mark_owner <实体>；reload。
技能形态参数包括 snow_fox、anubis_wolf、allay、axolotl、wild_cat、familiar_fox、familiar_fox_red。不是每个形态能力都有管理调用入口。朔望技能由内部 action 触发，没有 nova primary|secondary 命令。
/ssc_addon give_unanalyzed_scroll <法术路径> [等级1..5] 给予属于本存档研究规则的未解析卷轴，需要 OP 2，不会公开符文解法。
普通玩家可用 /ssc_addon my_whitelist 打开自己的白名单 GUI、palette export、palette apply <分享码>。当前没有 /ssc_addon whitelist add/remove/list/clear 这组命令，不要编造。
/ssc_addon debug form|mana|anim 是当前注册的调试入口。get_book <ID> [语言]、list_books [语言]、reload_books 管理书籍，需 OP 2；Compass 只说明指令，不泄露故事内容。
/ssc_addon reload 重载附属配置；/reload 用于服务器数据包。两者不能混为一谈。
来源：command/SscAddonCommands.java 的注册树。
