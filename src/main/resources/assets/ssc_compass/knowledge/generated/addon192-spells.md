<!-- compass: mod=ssc_addon version=8.0.0-magic-dev.3-ssc-1.9.2 -->

## 羊了个羊 ssc_addon:beep_sheep

法术 ID：ssc_addon:beep_sheep。丢出一颗鸡蛋式抛物线的咩弹，
命中小型生物将其变成羊，
期间无法攻击、无法破坏与放置方块；
Boss类与生命上限超过50点的
生物免疫。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"basic_1","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":700,"cooldown_floor_ticks":60,"base_cast_time_ticks":0,"mana_cost":20,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"summon","exp_mode":0,"levels":[{"rarity":"white"},{"mana_cost_multiplier":1.2,"cooldown_multiplier":0.8571,"rarity":"green"},{"mana_cost_multiplier":1.44,"cooldown_multiplier":0.7143,"rarity":"blue"},{"mana_cost_multiplier":1.73,"cooldown_multiplier":0.6429,"rarity":"purple"},{"mana_cost_multiplier":2.07,"cooldown_multiplier":0.5714,"rarity":"orange"}]}

来源：data/ssc_addon/spells/beep_sheep.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 伙伴共鸣 ssc_addon:companion_resonance

法术 ID：ssc_addon:companion_resonance。强化周围自己的宠物与召唤物：
伤害提升、迅捷与抗性随等级增强。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"intermediate_1","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":500,"cooldown_floor_ticks":30,"base_cast_time_ticks":0,"mana_cost":15,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"summon","exp_mode":0,"levels":[{"rarity":"white"},{"mana_cost_multiplier":1.2,"cooldown_multiplier":0.9,"rarity":"green"},{"mana_cost_multiplier":1.44,"cooldown_multiplier":0.8,"rarity":"blue"},{"mana_cost_multiplier":1.72,"cooldown_multiplier":0.7,"rarity":"purple"},{"mana_cost_multiplier":2.07,"cooldown_multiplier":0.6,"rarity":"orange"}]}

来源：data/ssc_addon/spells/companion_resonance.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 腐蚀之雾 ssc_addon:corrupt_mist

法术 ID：ssc_addon:corrupt_mist。周身散布跟随自己的腐蚀雾气，
范围内敌人不断中毒并被缓速
（持续随等级延长）。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"advanced_1","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":400,"cooldown_floor_ticks":60,"base_cast_time_ticks":0,"mana_cost":28,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"curse","exp_mode":0,"levels":[{"rarity":"white"},{"speed_multiplier":1.15,"mana_cost_multiplier":1.2,"rarity":"green"},{"speed_multiplier":1.25,"mana_cost_multiplier":1.44,"cooldown_multiplier":0.875,"rarity":"blue"},{"speed_multiplier":1.4,"mana_cost_multiplier":1.73,"cooldown_multiplier":0.775,"rarity":"purple"},{"speed_multiplier":1.5,"mana_cost_multiplier":2.07,"cooldown_multiplier":0.6,"rarity":"orange"}]}

来源：data/ssc_addon/spells/corrupt_mist.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 诅咒标记 ssc_addon:curse_mark

法术 ID：ssc_addon:curse_mark。诅咒目标：其受到的伤害增加、
造成的伤害减少（高等级更强）；
可用月华治愈净化。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"intermediate_1","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":300,"cooldown_floor_ticks":60,"base_cast_time_ticks":0,"mana_cost":15,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"curse","exp_mode":0,"levels":[{"rarity":"white"},{"speed_multiplier":1.1,"mana_cost_multiplier":1.2,"cooldown_multiplier":0.9167,"rarity":"green"},{"speed_multiplier":1.2,"mana_cost_multiplier":1.44,"cooldown_multiplier":0.8333,"rarity":"blue"},{"speed_multiplier":1.3,"mana_cost_multiplier":1.73,"cooldown_multiplier":0.75,"rarity":"purple"},{"speed_multiplier":1.4,"mana_cost_multiplier":2.07,"cooldown_multiplier":0.6667,"rarity":"orange"}]}

来源：data/ssc_addon/spells/curse_mark.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 死亡是一切的终点 ssc_addon:death_finale

法术 ID：ssc_addon:death_finale。原地蓄力13秒，可转动视角；
完成时对13格球形范围内非白名单
生物造成法术伤害。
危险玩家会看到红色屏幕边缘预警。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"element":"void","base_damage":5000,"base_cooldown_ticks":3600,"base_cast_time_ticks":260,"spell_tier":"special","interrupt_mode":3,"mana_cost":300,"exp_mode":0,"cooldown_floor_ticks":0,"levels":[{"rarity":"red","damage_multiplier":1.0,"cooldown_multiplier":1.0,"mana_cost_multiplier":1.0,"speed_multiplier":1.0}]}

来源：data/ssc_addon/spells/death_finale.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 领域 ssc_addon:domain

法术 ID：ssc_addon:domain。原地蓄力15秒展开持续40秒的领域，
领域内外的进出与攻击均被封锁。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"element":"space","base_damage":0,"base_cooldown_ticks":3600,"base_cast_time_ticks":300,"spell_tier":"custom","interrupt_mode":3,"mana_cost":300,"exp_mode":0,"levels":[{"rarity":"red","damage_multiplier":1.0,"cooldown_multiplier":1.0,"mana_cost_multiplier":1.0}]}

来源：data/ssc_addon/spells/domain.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 恐惧低语 ssc_addon:dread_whisper

法术 ID：ssc_addon:dread_whisper。向准星方向发出锥形恐惧波，
范围内敌人被虚弱、缓速并击退。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"intermediate_2","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":400,"cooldown_floor_ticks":60,"base_cast_time_ticks":0,"mana_cost":18,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"curse","exp_mode":0,"levels":[{"rarity":"white"},{"speed_multiplier":1.15,"mana_cost_multiplier":1.2,"cooldown_multiplier":0.875,"rarity":"green"},{"speed_multiplier":1.25,"mana_cost_multiplier":1.44,"cooldown_multiplier":0.75,"rarity":"blue"},{"speed_multiplier":1.4,"mana_cost_multiplier":1.73,"cooldown_multiplier":0.625,"rarity":"purple"},{"speed_multiplier":1.5,"mana_cost_multiplier":2.07,"cooldown_multiplier":0.5,"rarity":"orange"}]}

来源：data/ssc_addon/spells/dread_whisper.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 爆裂魔法 ssc_addon:explosion

法术 ID：ssc_addon:explosion。锁定128格内落点，蓄力35秒后
在64格球形范围引发无差别爆炸。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"element":"fire","base_damage":1000,"base_cooldown_ticks":3600,"base_cast_time_ticks":700,"spell_tier":"custom","interrupt_mode":3,"mana_cost":300,"exp_mode":0,"cooldown_floor_ticks":0,"levels":[{"rarity":"red","damage_multiplier":1.0,"cooldown_multiplier":1.0,"mana_cost_multiplier":1.0,"speed_multiplier":1.0}]}

来源：data/ssc_addon/spells/explosion.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 火球术 ssc_addon:fire_bolt

法术 ID：ssc_addon:fire_bolt。朝准星射出一枚火球，命中
造成法术伤害并点燃目标。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"basic_1","interrupt_mode":0,"base_damage":5.0,"base_cooldown_ticks":200,"cooldown_floor_ticks":15,"base_cast_time_ticks":0,"mana_cost":15,"solo_damage_multiplier":0.5,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"fire","exp_mode":2,"levels":[{"rarity":"white"},{"damage_multiplier":1.25,"cooldown_multiplier":0.8,"speed_multiplier":1.25,"mana_cost_multiplier":1.1333334,"rarity":"green"},{"damage_multiplier":1.25,"cooldown_multiplier":0.6,"speed_multiplier":1.25,"mana_cost_multiplier":1.2666667,"rarity":"blue"},{"damage_multiplier":1.5,"cooldown_multiplier":0.45,"speed_multiplier":1.5,"mana_cost_multiplier":1.4,"rarity":"purple"},{"damage_multiplier":1.8,"cooldown_multiplier":0.3,"speed_multiplier":1.5,"mana_cost_multiplier":1.5333334,"rarity":"orange"}]}

来源：data/ssc_addon/spells/fire_bolt.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 烈焰新星 ssc_addon:flame_nova

法术 ID：ssc_addon:flame_nova。以自身为圆心爆发出球形火焰，
范围内敌人受到伤害、被击退并短暂着火。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"intermediate_2","interrupt_mode":3,"base_damage":4.0,"base_cooldown_ticks":240,"cooldown_floor_ticks":15,"base_cast_time_ticks":0,"mana_cost":20,"solo_damage_multiplier":0.5,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"fire","exp_mode":2,"levels":[{"rarity":"white"},{"damage_multiplier":1.25,"speed_multiplier":1.125,"mana_cost_multiplier":1.25,"rarity":"green"},{"damage_multiplier":1.25,"cooldown_multiplier":0.75,"speed_multiplier":1.25,"mana_cost_multiplier":1.5625,"rarity":"blue"},{"damage_multiplier":1.5,"cooldown_multiplier":0.75,"speed_multiplier":1.375,"mana_cost_multiplier":1.9531,"rarity":"purple"},{"damage_multiplier":1.5,"cooldown_multiplier":0.5,"speed_multiplier":1.5,"mana_cost_multiplier":2.4414,"rarity":"orange"}]}

来源：data/ssc_addon/spells/flame_nova.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 霜甲术 ssc_addon:frost_armor

法术 ID：ssc_addon:frost_armor。凝聚寒霜护盾，为自己提供
伤害吸收（黄心），持续 20 秒。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"basic_2","interrupt_mode":0,"base_damage":8.0,"base_cooldown_ticks":500,"cooldown_floor_ticks":60,"base_cast_time_ticks":0,"mana_cost":25,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"ice","exp_mode":0,"levels":[{"rarity":"white"},{"damage_multiplier":1.5,"cooldown_multiplier":0.9333,"mana_cost_multiplier":1.2,"rarity":"green"},{"damage_multiplier":2.0,"cooldown_multiplier":0.8667,"mana_cost_multiplier":1.44,"rarity":"blue"},{"damage_multiplier":2.5,"cooldown_multiplier":0.8,"mana_cost_multiplier":1.73,"rarity":"purple"},{"damage_multiplier":3.0,"cooldown_multiplier":0.7333,"mana_cost_multiplier":2.07,"rarity":"orange"}]}

来源：data/ssc_addon/spells/frost_armor.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 冰霜新星 ssc_addon:frost_nova

法术 ID：ssc_addon:frost_nova。以自身为圆心爆发寒气，
范围内敌人受到伤害并被缓速，
护甲 -50%（持续随等级）。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"basic_2","interrupt_mode":0,"base_damage":3.0,"base_cooldown_ticks":400,"cooldown_floor_ticks":15,"base_cast_time_ticks":0,"mana_cost":35,"solo_damage_multiplier":0.5,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"ice","exp_mode":2,"levels":[{"rarity":"white"},{"damage_multiplier":1.25,"cooldown_multiplier":0.9,"speed_multiplier":1.125,"mana_cost_multiplier":1.25,"rarity":"green"},{"damage_multiplier":1.5,"cooldown_multiplier":0.81,"speed_multiplier":1.25,"mana_cost_multiplier":1.5625,"rarity":"blue"},{"damage_multiplier":1.75,"cooldown_multiplier":0.729,"speed_multiplier":1.375,"mana_cost_multiplier":1.9531,"rarity":"purple"},{"damage_multiplier":2.0,"cooldown_multiplier":0.6,"speed_multiplier":1.5,"mana_cost_multiplier":2.4414,"rarity":"orange"}]}

来源：data/ssc_addon/spells/frost_nova.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 冰锥 ssc_addon:frost_spike

法术 ID：ssc_addon:frost_spike。朝准星射出一枚冰锥，命中造成法术伤害。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"basic_1","interrupt_mode":0,"base_damage":6.0,"base_cooldown_ticks":200,"cooldown_floor_ticks":15,"base_cast_time_ticks":0,"mana_cost":15,"solo_damage_multiplier":0.5,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"ice","exp_mode":2,"levels":[{"rarity":"white"},{"damage_multiplier":1.25,"cooldown_multiplier":0.8,"speed_multiplier":1.25,"mana_cost_multiplier":1.25,"rarity":"green"},{"damage_multiplier":1.5,"cooldown_multiplier":0.6,"speed_multiplier":1.4167,"mana_cost_multiplier":1.5625,"rarity":"blue"},{"damage_multiplier":1.6875,"cooldown_multiplier":0.45,"speed_multiplier":1.5833,"mana_cost_multiplier":1.9531,"rarity":"purple"},{"damage_multiplier":1.8333,"cooldown_multiplier":0.3,"speed_multiplier":1.75,"mana_cost_multiplier":2.4414,"rarity":"orange"}]}

来源：data/ssc_addon/spells/frost_spike.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 冰锥齐射 ssc_addon:ice_barrage

法术 ID：ssc_addon:ice_barrage。扇形散射三枚冰锥，每枚可穿刺
多个敌人（穿刺数随等级提升）。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"basic_2","interrupt_mode":0,"base_damage":4.0,"base_cooldown_ticks":300,"cooldown_floor_ticks":15,"base_cast_time_ticks":0,"mana_cost":30,"solo_damage_multiplier":0.5,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"ice","exp_mode":2,"levels":[{"rarity":"white"},{"damage_multiplier":1.25,"cooldown_multiplier":0.8333,"speed_multiplier":1.25,"mana_cost_multiplier":1.2,"rarity":"green"},{"damage_multiplier":1.5625,"cooldown_multiplier":0.6667,"speed_multiplier":1.4167,"mana_cost_multiplier":1.44,"rarity":"blue"},{"damage_multiplier":1.9531,"cooldown_multiplier":0.6,"speed_multiplier":1.5833,"mana_cost_multiplier":1.728,"rarity":"purple"},{"damage_multiplier":2.4414,"cooldown_multiplier":0.5333,"speed_multiplier":1.75,"mana_cost_multiplier":2.0736,"rarity":"orange"}]}

来源：data/ssc_addon/spells/ice_barrage.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 月华治愈 ssc_addon:lunar_mend

法术 ID：ssc_addon:lunar_mend。回复生命并净化负面效果
（负面效果等级越高，消耗的净化次数越多；
等级越高净化次数越多）。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"intermediate_1","interrupt_mode":3,"base_damage":5.0,"base_cooldown_ticks":240,"cooldown_floor_ticks":20,"base_cast_time_ticks":0,"mana_cost":20,"solo_damage_multiplier":0.5,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"lunar","exp_mode":0,"levels":[{"rarity":"white"},{"damage_multiplier":1.4,"mana_cost_multiplier":1.2,"rarity":"green"},{"damage_multiplier":1.8,"mana_cost_multiplier":1.44,"cooldown_multiplier":0.75,"rarity":"blue"},{"damage_multiplier":2.2,"mana_cost_multiplier":1.73,"cooldown_multiplier":0.75,"rarity":"purple"},{"damage_multiplier":2.6,"mana_cost_multiplier":2.07,"cooldown_multiplier":0.5,"rarity":"orange"}]}

来源：data/ssc_addon/spells/lunar_mend.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 月相 ssc_addon:lunar_phase

法术 ID：ssc_addon:lunar_phase。按住紫色描边标记准星附近的玩家或生物，
松手锁定后蓄力1.5秒（双方围圈连线），
蓄满按百分比交换生命，
每方最多变化50点生命，双方保底1点。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"custom","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":400,"cooldown_floor_ticks":80,"base_cast_time_ticks":30,"mana_cost":25,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"lunar","exp_mode":0,"levels":[{"rarity":"white"},{"mana_cost_multiplier":1.2,"cooldown_multiplier":0.9167,"rarity":"green"},{"mana_cost_multiplier":1.44,"cooldown_multiplier":0.8333,"rarity":"blue"},{"mana_cost_multiplier":1.73,"cooldown_multiplier":0.75,"rarity":"purple"},{"mana_cost_multiplier":2.07,"cooldown_multiplier":0.6667,"rarity":"orange"}]}

来源：data/ssc_addon/spells/lunar_phase.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 月幕 ssc_addon:lunar_veil

法术 ID：ssc_addon:lunar_veil。以自身为圆心展开月光辉幕，
范围内的玩家与驯服宠物获得抗性与缓降。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"intermediate_2","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":500,"cooldown_floor_ticks":40,"base_cast_time_ticks":0,"mana_cost":25,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"lunar","exp_mode":0,"levels":[{"rarity":"white"},{"speed_multiplier":1.1875,"mana_cost_multiplier":1.2,"cooldown_multiplier":0.9,"rarity":"green"},{"speed_multiplier":1.375,"mana_cost_multiplier":1.44,"cooldown_multiplier":0.8,"rarity":"blue"},{"speed_multiplier":1.5625,"mana_cost_multiplier":1.73,"cooldown_multiplier":0.7,"rarity":"purple"},{"speed_multiplier":1.75,"mana_cost_multiplier":2.07,"cooldown_multiplier":0.6,"rarity":"orange"}]}

来源：data/ssc_addon/spells/lunar_veil.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 陨火术 ssc_addon:meteor

法术 ID：ssc_addon:meteor。按住施法键显示瞄准圈，松开召唤陨火
砸向目标区域，造成伤害并点燃；
准星需指向方块。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"advanced_1","interrupt_mode":3,"base_damage":8.0,"base_cooldown_ticks":400,"cooldown_floor_ticks":15,"base_cast_time_ticks":0,"mana_cost":30,"solo_damage_multiplier":0.5,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"fire","exp_mode":2,"levels":[{"rarity":"white"},{"damage_multiplier":1.25,"cooldown_multiplier":0.88,"speed_multiplier":1.167,"mana_cost_multiplier":1.2,"rarity":"green"},{"damage_multiplier":1.5,"cooldown_multiplier":0.7744,"speed_multiplier":1.333,"mana_cost_multiplier":1.44,"rarity":"blue"},{"damage_multiplier":1.625,"cooldown_multiplier":0.6815,"speed_multiplier":1.5,"mana_cost_multiplier":1.728,"rarity":"purple"},{"damage_multiplier":1.75,"cooldown_multiplier":0.6,"speed_multiplier":1.667,"mana_cost_multiplier":2.0736,"rarity":"orange"}]}

来源：data/ssc_addon/spells/meteor.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 月光箭 ssc_addon:moonlight_arrow

法术 ID：ssc_addon:moonlight_arrow。朝准星射出一支凝聚月光的箭矢，
对亡灵生物伤害 +50%。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"basic_1","interrupt_mode":0,"base_damage":4.0,"base_cooldown_ticks":200,"cooldown_floor_ticks":15,"base_cast_time_ticks":0,"mana_cost":12,"solo_damage_multiplier":0.5,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"lunar","exp_mode":2,"levels":[{"rarity":"white"},{"damage_multiplier":1.25,"speed_multiplier":1.2,"cooldown_multiplier":0.85,"mana_cost_multiplier":1.25,"rarity":"green"},{"damage_multiplier":1.5,"speed_multiplier":1.4,"cooldown_multiplier":0.7,"mana_cost_multiplier":1.5625,"rarity":"blue"},{"damage_multiplier":1.75,"speed_multiplier":1.6,"cooldown_multiplier":0.6,"mana_cost_multiplier":1.9531,"rarity":"purple"},{"damage_multiplier":2.0,"speed_multiplier":1.8,"cooldown_multiplier":0.5,"mana_cost_multiplier":2.4414,"rarity":"orange"}]}

来源：data/ssc_addon/spells/moonlight_arrow.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 口袋空间 ssc_addon:pocket_space

法术 ID：ssc_addon:pocket_space。引导12秒后进入卷轴绑定的独立空间；
1级内部为16×16×16格，
每级边长增加6格。北侧出口可返回原位置。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"special_2","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":1200,"cooldown_floor_ticks":160,"base_cast_time_ticks":100,"mana_cost":30,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":1.0,"element":"space","exp_mode":0,"levels":[{"rarity":"white"},{"cooldown_multiplier":0.875,"mana_cost_multiplier":1.2,"rarity":"green"},{"cooldown_multiplier":0.75,"mana_cost_multiplier":1.4333,"rarity":"blue"},{"cooldown_multiplier":0.625,"mana_cost_multiplier":1.7333,"rarity":"purple"},{"cooldown_multiplier":0.5,"mana_cost_multiplier":2.0667,"rarity":"orange"}]}

来源：data/ssc_addon/spells/pocket_space.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 空间跳跃 ssc_addon:space_blink

法术 ID：ssc_addon:space_blink。按住施法键显示落点预览，
松开向准星方向瞬移
（距离随等级提升）。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"instant","interrupt_mode":0,"base_damage":0.0,"base_cooldown_ticks":320,"cooldown_floor_ticks":40,"base_cast_time_ticks":0,"mana_cost":30,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"space","exp_mode":0,"levels":[{"rarity":"white"},{"speed_multiplier":1.25,"mana_cost_multiplier":1.25,"cooldown_multiplier":0.875,"rarity":"green"},{"speed_multiplier":1.56,"mana_cost_multiplier":1.56,"cooldown_multiplier":0.75,"rarity":"blue"},{"speed_multiplier":1.95,"mana_cost_multiplier":1.95,"cooldown_multiplier":0.625,"rarity":"purple"},{"speed_multiplier":2.44,"mana_cost_multiplier":2.44,"cooldown_multiplier":0.5,"rarity":"orange"}]}

来源：data/ssc_addon/spells/space_blink.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 空间归途 ssc_addon:space_recall

法术 ID：ssc_addon:space_recall。蓄力8秒后传送回重生点
（床/重生锚）；受伤会打断蓄力。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"special_1","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":1200,"cooldown_floor_ticks":160,"base_cast_time_ticks":0,"mana_cost":60,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"space","exp_mode":0,"levels":[{"rarity":"white"},{"mana_cost_multiplier":1.2,"cooldown_multiplier":0.875,"rarity":"green"},{"mana_cost_multiplier":1.44,"cooldown_multiplier":0.75,"rarity":"blue"},{"mana_cost_multiplier":1.72,"cooldown_multiplier":0.625,"rarity":"purple"},{"mana_cost_multiplier":2.07,"cooldown_multiplier":0.5,"rarity":"orange"}]}

来源：data/ssc_addon/spells/space_recall.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 空间漫步 ssc_addon:space_stride

法术 ID：ssc_addon:space_stride。获得缓降与跳跃提升，持续探索途中使用。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"basic_2","interrupt_mode":0,"base_damage":0.0,"base_cooldown_ticks":400,"cooldown_floor_ticks":40,"base_cast_time_ticks":0,"mana_cost":12,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"space","exp_mode":0,"levels":[{"rarity":"white"},{"mana_cost_multiplier":1.25,"rarity":"green"},{"mana_cost_multiplier":1.56,"cooldown_multiplier":0.85,"rarity":"blue"},{"mana_cost_multiplier":1.95,"cooldown_multiplier":0.85,"rarity":"purple"},{"mana_cost_multiplier":2.44,"cooldown_multiplier":0.7,"rarity":"orange"}]}

来源：data/ssc_addon/spells/space_stride.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 召唤月灵 ssc_addon:summon_lunar_spirit

法术 ID：ssc_addon:summon_lunar_spirit。召唤月灵跟随并协战
（1至2级1只，3至4级2只，5级3只，
持续 30 秒以上）。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"intermediate_1","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":800,"cooldown_floor_ticks":30,"base_cast_time_ticks":0,"mana_cost":35,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"summon","exp_mode":0,"levels":[{"rarity":"white"},{"mana_cost_multiplier":1.2,"cooldown_multiplier":0.9063,"rarity":"green"},{"mana_cost_multiplier":1.44,"cooldown_multiplier":0.8125,"rarity":"blue"},{"mana_cost_multiplier":1.73,"cooldown_multiplier":0.7188,"rarity":"purple"},{"mana_cost_multiplier":2.07,"cooldown_multiplier":0.625,"rarity":"orange"}]}

来源：data/ssc_addon/spells/summon_lunar_spirit.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 虚空吞噬 ssc_addon:void_devour

法术 ID：ssc_addon:void_devour。按住施法键显示瞄准圈，松开在落点
爆发虚无吞噬，造成伤害并致盲；
准星需指向方块。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"advanced_1","interrupt_mode":3,"base_damage":6.0,"base_cooldown_ticks":300,"cooldown_floor_ticks":15,"base_cast_time_ticks":0,"mana_cost":22,"solo_damage_multiplier":0.5,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"void","exp_mode":2,"levels":[{"rarity":"white"},{"damage_multiplier":1.17,"mana_cost_multiplier":1.2,"cooldown_multiplier":0.8833,"speed_multiplier":1.1,"rarity":"green"},{"damage_multiplier":1.33,"mana_cost_multiplier":1.44,"cooldown_multiplier":0.7667,"speed_multiplier":1.2,"rarity":"blue"},{"damage_multiplier":1.5,"mana_cost_multiplier":1.72,"cooldown_multiplier":0.65,"speed_multiplier":1.3,"rarity":"purple"},{"damage_multiplier":1.67,"mana_cost_multiplier":2.07,"cooldown_multiplier":0.5333,"speed_multiplier":1.4,"rarity":"orange"}]}

来源：data/ssc_addon/spells/void_devour.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。

## 虚空侵蚀 ssc_addon:void_erosion

法术 ID：ssc_addon:void_erosion。以自身为圆心爆发虚无波，
范围内敌人陷入挖掘疲劳与虚弱
（高等级更强范围更大）。
默认配置（base 字段是计算基值，不等同最终实战值；levels 为各等级修正）：
{"spell_tier":"intermediate_2","interrupt_mode":3,"base_damage":0.0,"base_cooldown_ticks":400,"cooldown_floor_ticks":60,"base_cast_time_ticks":0,"mana_cost":20,"solo_damage_multiplier":1.0,"solo_cooldown_multiplier":2.0,"solo_cast_time_multiplier":2.0,"element":"void","exp_mode":0,"levels":[{"rarity":"white"},{"speed_multiplier":1.25,"mana_cost_multiplier":1.25,"cooldown_multiplier":0.9,"rarity":"green"},{"speed_multiplier":1.56,"mana_cost_multiplier":1.56,"cooldown_multiplier":0.8,"rarity":"blue"},{"speed_multiplier":1.95,"mana_cost_multiplier":1.95,"cooldown_multiplier":0.7,"rarity":"purple"},{"speed_multiplier":2.44,"mana_cost_multiplier":2.44,"cooldown_multiplier":0.6,"rarity":"orange"}]}

来源：data/ssc_addon/spells/void_erosion.json；SSCA 8.0.0-magic-dev.3-ssc-1.9.2 源码 1036d7480b85，SSC 1.9.2。数值是出厂默认，服务器配置与数据包优先。
