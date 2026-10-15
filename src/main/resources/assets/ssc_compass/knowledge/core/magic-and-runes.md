<!-- compass: mod=ssc_addon version=8.0.0-magic-dev.3-ssc-1.9.2,8.0.0-magic-dev.4 -->

## 月尘魔法书等级、槽位、经验与法力上限
月尘魔法书 ssc_addon:moon_dust_spellbook 的数据保存在书物品 NBT，施法时需要佩戴到对应饰品位。书为 Lv1..3；卷轴槽分别 3/5/7；基础法力上限分别 100/200/300；法阵槽分别 1/3/6。
默认 Lv1→2 需要600.0经验，Lv2→3需要900.0。内部以 ExpTen 保存十分之一经验，旧 Exp 会迁移。Lv3经验继续积累，默认每600.0经验增加50法力上限，加成最多600；不含其它加成时满档900。通用法阵还能提供上限和经验效率加成，不能把基础300当最终上限。服务器平衡数据可以覆盖精通参数。
来源：spell/SpellbookData.java、SpellCastManager.java。装备与界面操作可检索“月尘魔法书”“注魔台”。

## 法术施放、渐进扣蓝、打断、净化与冷却
法术默认数值与各级倍率来自 data/ssc_addon/spells/*.json，最终伤害、耗蓝、读条、CD 还受书/卷轴、形态、耐久、法阵、符文和服务器配置影响。base 数值不能直接当最终实战数值。
书施法准备期间每2 tick逐步结算已锁定的总耗蓝，完成时结清尾数。取消或打断的结算受具体中断规则影响，不要声称所有法术起手一次性扣满或可无限免费蓄力。净化会强制中断相关施法，并进入对应完整冷却；实际剩余时间以客户端同步/HUD为准。
get_form_status 没有读取书内卷轴与服务端共享 CD，不能用形态资源表推算全部法术状态。
来源：SpellManaPayment、SpellCastingRules、SpellChannelManager、PurifiedEffect、SpellNumbers。

## 符文研究、存档差异与普通卷轴和“改”卷轴
符文语言与研究规则属于服务器存档，不能照搬另一个世界的解法。研究成功后的方案由服务端登记，物品 NBT 是显示镜像；复制或篡改 NBT 不等于获得有效方案。
基础环用于研究和识别；外环有真实增强时才是“XXX 改”。外环为空，即使保留基础环或旧 RuneScheme 键，也应显示和使用普通卷轴。低于增强启用档位时不应用该增强，不能只凭键存在就判定“改”。
Compass 可以解释符文通用效果、代价、稳定性、负载、能力适配与研究方法，不公开某个法术的固定符文顺序或配方。
来源：research/WorldRuneState、RuneLanguage、RuneBuildEvaluator、RuneLayout、RuneScheme、SlottedFormationTransaction。

## 符文增强的收益、代价和共鸣
增幅、蓄积、分裂、转换、恢复等语义按法术实际支持的能力生效。强化常同时增加耗蓝、CD或负载；不支持的伤害/治疗/弹体能力不会凭空添加。稳定性与负载限制需要在研究界面诊断。
通用共鸣组合包括增幅+蓄积、源+蓄积、分裂+转换、恢复+融合，它们要求对应的法术能力，分别涉及爆发、弹体、压制或治疗收益并带CD/耗蓝/负载代价。这些是通用增强语义，不是具体法术的解法。
来源：research/RuneResonance、RuneCapabilities、RuneModifiers、RuneSummary。

## 白名单 GUI、友方保护与服务端开关
开启 whitelistEnabled 时：白名单为空通常保护所有玩家、满足主人条件的驯服宠物与带主人标记的召唤物；名单非空时只保护名单条目、白名单主人的相关宠物/召唤物，施法者始终保护自己。添加单独生物条目也会让名单变为非空。
关闭该服务端开关时，攻击技能不再因白名单跳过目标（施法者自身仍保护）；强化/治疗默认排除敌对、Monster和未驯服的部分 Angerable 生物。不同攻击仍可能有额外过滤。
用 /ssc_addon my_whitelist 管理自己的名单；友军标记物品可以记录指定生物。不要把 Minecraft 服务器登录白名单命令当作技能白名单。
来源：util/WhitelistUtils.java、SSCAddonConfig、SscAddonCommands。
