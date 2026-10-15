<!-- compass: mod=ssc_addon version=8.0.0-magic-dev.4 -->

## form_allay_sp_amethyst_shard_from_block my_addon:form_allay_sp_amethyst_shard_from_block

能力 ID：my_addon:form_allay_sp_amethyst_shard_from_block；类型：apoli:item_on_item。



来源：data/my_addon/powers/form_allay_sp_amethyst_shard_from_block.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 水晶食性 my_addon:form_allay_sp_amethyst_shard_only

能力 ID：my_addon:form_allay_sp_amethyst_shard_only；类型：apoli:prevent_item_use。
你只能食用紫水晶碎片，其他食物对你来说难以下咽。


来源：data/my_addon/powers/form_allay_sp_amethyst_shard_only.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_attack_heal my_addon:form_allay_sp_attack_heal

能力 ID：my_addon:form_allay_sp_attack_heal；类型：apoli:self_action_on_hit。

cooldown：10


来源：data/my_addon/powers/form_allay_sp_attack_heal.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_beacon_active my_addon:form_allay_sp_beacon_active

能力 ID：my_addon:form_allay_sp_beacon_active；类型：apoli:resource。

min：0
max：1
start_value：0


来源：data/my_addon/powers/form_allay_sp_beacon_active.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_beacon_effect my_addon:form_allay_sp_beacon_effect

能力 ID：my_addon:form_allay_sp_beacon_effect；类型：apoli:action_over_time。

condition：{"type":"apoli:resource","resource":"my_addon:form_allay_sp_beacon_active","comparison":"==","compare_to":1}


来源：data/my_addon/powers/form_allay_sp_beacon_effect.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_beacon_mana_tick my_addon:form_allay_sp_beacon_mana_tick

能力 ID：my_addon:form_allay_sp_beacon_mana_tick；类型：apoli:action_over_time。

condition：{"type":"apoli:resource","resource":"my_addon:form_allay_sp_beacon_active","comparison":"==","compare_to":1}


来源：data/my_addon/powers/form_allay_sp_beacon_mana_tick.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_digging_speed_down my_addon:form_allay_sp_digging_speed_down

能力 ID：my_addon:form_allay_sp_digging_speed_down；类型：apoli:modify_break_speed。

modifier：{"operation":"multiply_base","value":-0.45}


来源：data/my_addon/powers/form_allay_sp_digging_speed_down.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 应急食品 my_addon:form_allay_sp_emergency_food

能力 ID：my_addon:form_allay_sp_emergency_food；类型：apoli:action_over_time。
消耗50点魔力恢复1点饱食度（0.5个鸡腿），并暂停魔力回复3.5秒。
condition：{"type":"apoli:and","conditions":[{"type":"apoli:food_level","comparison":"==","compare_to":0},{"type":"apoli:resource","resource":"my_addon:form_allay_sp_mana_resource","comparison":">=","compare_to":50}]}


来源：data/my_addon/powers/form_allay_sp_emergency_food.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_fly_hunger my_addon:form_allay_sp_fly_hunger

能力 ID：my_addon:form_allay_sp_fly_hunger；类型：apoli:exhaust。

condition：{"type":"apoli:power_active","power":"my_addon:form_allay_sp_levitate"}


来源：data/my_addon/powers/form_allay_sp_fly_hunger.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Group Heal my_addon:form_allay_sp_group_heal

能力 ID：my_addon:form_allay_sp_group_heal；类型：apoli:multiple。
Charge for 3 seconds to heal all living entities within 20 blocks for 20 HP. Consumes all mana. Healed entities display heart particles. 30s cooldown.


来源：data/my_addon/powers/form_allay_sp_group_heal.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_health my_addon:form_allay_sp_health

能力 ID：my_addon:form_allay_sp_health；类型：apoli:attribute。

modifier：{"name":"Form ocelot0 health reduction","attribute":"minecraft:generic.max_health","value":-10.0,"operation":"addition"}


来源：data/my_addon/powers/form_allay_sp_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_hero_of_village my_addon:form_allay_sp_hero_of_village

能力 ID：my_addon:form_allay_sp_hero_of_village；类型：apoli:action_on_entity_use。



来源：data/my_addon/powers/form_allay_sp_hero_of_village.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_jukebox_heal my_addon:form_allay_sp_jukebox_heal

能力 ID：my_addon:form_allay_sp_jukebox_heal；类型：apoli:action_over_time。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:and","conditions":[{"type":"apoli:block","block":"minecraft:jukebox"},{"type":"apoli:block_state","property":"has_record","value":true}]},"radius":3,"shape":"cube","comparison":">=","compare_to":1}


来源：data/my_addon/powers/form_allay_sp_jukebox_heal.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_levitate my_addon:form_allay_sp_levitate

能力 ID：my_addon:form_allay_sp_levitate；类型：shape-shifter-curse:levitate。



来源：data/my_addon/powers/form_allay_sp_levitate.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_levitate_speed_up my_addon:form_allay_sp_levitate_speed_up

能力 ID：my_addon:form_allay_sp_levitate_speed_up；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:power_active","power":"my_addon:form_allay_sp_levitate"}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":0.16,"name":"Inc speed when flying"}


来源：data/my_addon/powers/form_allay_sp_levitate_speed_up.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Mana my_addon:form_allay_sp_mana

能力 ID：my_addon:form_allay_sp_mana；类型：apoli:multiple。
Custom mana resource for Allay SP form.


来源：data/my_addon/powers/form_allay_sp_mana.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_melee_damage_down my_addon:form_allay_sp_melee_damage_down

能力 ID：my_addon:form_allay_sp_melee_damage_down；类型：apoli:modify_damage_dealt。

modifier：{"operation":"multiply_total","value":-0.5}


来源：data/my_addon/powers/form_allay_sp_melee_damage_down.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_mob_glow_in_range my_addon:form_allay_sp_mob_glow_in_range

能力 ID：my_addon:form_allay_sp_mob_glow_in_range；类型：apoli:multiple。



来源：data/my_addon/powers/form_allay_sp_mob_glow_in_range.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## SP Allay Portable Beacon my_addon:form_allay_sp_portable_beacon

能力 ID：my_addon:form_allay_sp_portable_beacon；类型：apoli:simple。
Allows the SP Allay to use a Beacon as a portable buff source.


来源：data/my_addon/powers/form_allay_sp_portable_beacon.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_prevent_digging_when_fly my_addon:form_allay_sp_prevent_digging_when_fly

能力 ID：my_addon:form_allay_sp_prevent_digging_when_fly；类型：apoli:modify_break_speed。

condition：{"type":"apoli:creative_flying"}
modifier：{"operation":"multiply_base","value":-0.8}


来源：data/my_addon/powers/form_allay_sp_prevent_digging_when_fly.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_projectile_damage_up my_addon:form_allay_sp_projectile_damage_up

能力 ID：my_addon:form_allay_sp_projectile_damage_up；类型：apoli:multiple。



来源：data/my_addon/powers/form_allay_sp_projectile_damage_up.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Purification my_addon:form_allay_sp_purify

能力 ID：my_addon:form_allay_sp_purify；类型：apoli:multiple。
Charge for 1.5 seconds to clear all status effects from yourself and unprotected living entities within 20 blocks. Interrupt their spell casts with full cooldowns and interrupt skills such as Vortex Impact and Group Heal. Other Allays' Purification charges continue.


来源：data/my_addon/powers/form_allay_sp_purify.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_resonant_core_trinket my_addon:form_allay_sp_resonant_core_trinket

能力 ID：my_addon:form_allay_sp_resonant_core_trinket；类型：apoli:multiple。



来源：data/my_addon/powers/form_allay_sp_resonant_core_trinket.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_scale my_addon:form_allay_sp_scale

能力 ID：my_addon:form_allay_sp_scale；类型：shape-shifter-curse:scale。



来源：data/my_addon/powers/form_allay_sp_scale.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_slipperiness my_addon:form_allay_sp_slipperiness

能力 ID：my_addon:form_allay_sp_slipperiness；类型：apoli:modify_slipperiness。

modifier：{"operation":"multiply_total","value":0.3}


来源：data/my_addon/powers/form_allay_sp_slipperiness.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_slow_falling my_addon:form_allay_sp_slow_falling

能力 ID：my_addon:form_allay_sp_slow_falling；类型：apoli:modify_falling。

condition：{"type":"apoli:or","conditions":[{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:fall_flying"}]},{"type":"apoli:and","conditions":[{"type":"apoli:sneaking","inverted":true},{"type":"apoli:fall_flying","inverted":true},{"type":"apoli:on_block","inverted":true}]}]}


来源：data/my_addon/powers/form_allay_sp_slow_falling.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_sound my_addon:form_allay_sp_sound

能力 ID：my_addon:form_allay_sp_sound；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:enable_random_sound"},{"type":"shape-shifter-curse:chance","chance":0.3}]}


来源：data/my_addon/powers/form_allay_sp_sound.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_sound_hurt my_addon:form_allay_sp_sound_hurt

能力 ID：my_addon:form_allay_sp_sound_hurt；类型：apoli:self_action_when_hit。

condition：{"type":"shape-shifter-curse:chance","chance":0.8}


来源：data/my_addon/powers/form_allay_sp_sound_hurt.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_sound_key my_addon:form_allay_sp_sound_key

能力 ID：my_addon:form_allay_sp_sound_key；类型：apoli:active_self。

cooldown：5


来源：data/my_addon/powers/form_allay_sp_sound_key.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Totem Guard my_addon:form_allay_sp_totem

能力 ID：my_addon:form_allay_sp_totem；类型：apoli:simple。
Right-click with Totem of Undying to activate. Prevents death of nearby allies.


来源：data/my_addon/powers/form_allay_sp_totem.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_walk_on_powder_snow my_addon:form_allay_sp_walk_on_powder_snow

能力 ID：my_addon:form_allay_sp_walk_on_powder_snow；类型：shape-shifter-curse:powder_snow_walker。



来源：data/my_addon/powers/form_allay_sp_walk_on_powder_snow.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 凋零酿造 my_addon:form_anubis_wolf_sp_craft_wither_potion

能力 ID：my_addon:form_anubis_wolf_sp_craft_wither_potion；类型：apoli:item_on_item。
手持药水与凋零玫瑰（一个在光标一个在背包格），点击合成 1 瓶凋零药水（可叠 3）。凋零药水仅你能饮用，喝下（32t 蓄力）获得固定凋零 II、持续 20 秒。


来源：data/my_addon/powers/form_anubis_wolf_sp_craft_wither_potion.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 凋零酿造 my_addon:form_anubis_wolf_sp_craft_wither_potion_alt

能力 ID：my_addon:form_anubis_wolf_sp_craft_wither_potion_alt；类型：apoli:item_on_item。
手持药水与凋零玫瑰（一个在光标一个在背包格），点击合成 1 瓶凋零药水（可叠 3）。凋零药水仅你能饮用，喝下（32t 蓄力）获得固定凋零 II、持续 20 秒。


来源：data/my_addon/powers/form_anubis_wolf_sp_craft_wither_potion_alt.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 死亡领域 my_addon:form_anubis_wolf_sp_death_domain

能力 ID：my_addon:form_anubis_wolf_sp_death_domain；类型：my_addon:fail_aware_active_self。
蓄力2秒后，将周围16格半径的圆柱范围内所有方块暂时变为灵魂沙。领域内的敌对生物受到缓慢I、凋零I并被削减15%的生命值上限。领域持续15秒。
cooldown：1000
condition：{"type":"apoli:sneaking","inverted":true}


来源：data/my_addon/powers/form_anubis_wolf_sp_death_domain.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sp_death_sentence my_addon:form_anubis_wolf_sp_death_sentence

能力 ID：my_addon:form_anubis_wolf_sp_death_sentence；类型：apoli:modify_damage_dealt。

modifier：{"operation":"multiply_total","value":0.2,"name":"Death Sentence"}


来源：data/my_addon/powers/form_anubis_wolf_sp_death_sentence.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sp_health my_addon:form_anubis_wolf_sp_health

能力 ID：my_addon:form_anubis_wolf_sp_health；类型：apoli:attribute。

modifier：{"name":"SP Anubis Wolf Health","attribute":"minecraft:generic.max_health","value":4.0,"operation":"addition"}


来源：data/my_addon/powers/form_anubis_wolf_sp_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sp_soul_energy my_addon:form_anubis_wolf_sp_soul_energy

能力 ID：my_addon:form_anubis_wolf_sp_soul_energy；类型：apoli:resource。

min：0
max：100
start_value：0


来源：data/my_addon/powers/form_anubis_wolf_sp_soul_energy.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sp_soul_sand_heal my_addon:form_anubis_wolf_sp_soul_sand_heal

能力 ID：my_addon:form_anubis_wolf_sp_soul_sand_heal；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:skill_disabled","form":"anubis_wolf","skill":"soul_sand_heal"},{"type":"apoli:on_block","block_condition":{"type":"apoli:or","conditions":[{"type":"apoli:block","block":"minecraft:soul_sand"},{"type":"apoli:block","block":"minecraft:soul_soil"}]}}]}


来源：data/my_addon/powers/form_anubis_wolf_sp_soul_sand_heal.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sp_soul_scrutiny my_addon:form_anubis_wolf_sp_soul_scrutiny

能力 ID：my_addon:form_anubis_wolf_sp_soul_scrutiny；类型：apoli:multiple。



来源：data/my_addon/powers/form_anubis_wolf_sp_soul_scrutiny.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sp_summon_wolf_on_hit my_addon:form_anubis_wolf_sp_summon_wolf_on_hit

能力 ID：my_addon:form_anubis_wolf_sp_summon_wolf_on_hit；类型：apoli:action_on_hit。



来源：data/my_addon/powers/form_anubis_wolf_sp_summon_wolf_on_hit.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sp_summon_wolf_when_hit my_addon:form_anubis_wolf_sp_summon_wolf_when_hit

能力 ID：my_addon:form_anubis_wolf_sp_summon_wolf_when_hit；类型：apoli:action_when_hit。



来源：data/my_addon/powers/form_anubis_wolf_sp_summon_wolf_when_hit.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 冥狼裁庭 my_addon:form_anubis_wolf_sp_summon_wolves

能力 ID：my_addon:form_anubis_wolf_sp_summon_wolves；类型：my_addon:fail_aware_active_self。
嚎叫1.5秒后召唤冥狼仆从。冥狼攻击目标时附加凋零效果，攻击已凋零的目标时治愈施法者。死亡领域激活时，召唤数量翻倍且冥狼获得增强属性。
cooldown：600
condition：{"type":"apoli:sneaking","inverted":true}


来源：data/my_addon/powers/form_anubis_wolf_sp_summon_wolves.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sp_undead_neutral my_addon:form_anubis_wolf_sp_undead_neutral

能力 ID：my_addon:form_anubis_wolf_sp_undead_neutral；类型：apoli:simple。



来源：data/my_addon/powers/form_anubis_wolf_sp_undead_neutral.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 凋零共生 my_addon:form_anubis_wolf_sp_wither_frenzy

能力 ID：my_addon:form_anubis_wolf_sp_wither_frenzy；类型：apoli:multiple。
亡灵体质让你与凋零共存：凋零对你的伤害减少20%且伤害间隔延长40%；凋零期间你与冥狼的伤害随持续时间阶梯递增（每2秒+10%，最高+30%）。你或冥狼攻击时会消耗自身凋零时间（每次5秒）传染给目标（不足则消耗全部，等级继承），目标调零累加上限15秒；凋零状态下击杀额外获得10点灵魂能量。


来源：data/my_addon/powers/form_anubis_wolf_sp_wither_frenzy.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 凋零追猎 my_addon:form_anubis_wolf_sp_wither_hunt

能力 ID：my_addon:form_anubis_wolf_sp_wither_hunt；类型：apoli:multiple。
当24格内存在身上带有凋零效果的生物时，在非灵魂沙区域获得20%移动速度加成。


来源：data/my_addon/powers/form_anubis_wolf_sp_wither_hunt.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sp_wither_on_hit my_addon:form_anubis_wolf_sp_wither_on_hit

能力 ID：my_addon:form_anubis_wolf_sp_wither_on_hit；类型：apoli:self_action_when_hit。

condition：{"type":"ssc_addon:in_own_death_domain","inverted":true}


来源：data/my_addon/powers/form_anubis_wolf_sp_wither_on_hit.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Moisture Extinguish my_addon:form_axolotl_aling_fire_extinguish

能力 ID：my_addon:form_axolotl_aling_fire_extinguish；类型：apoli:action_over_time。
When on fire, consume 10 moisture (air) per second to extinguish. Cannot extinguish if moisture is below 10.
condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:skill_disabled","form":"axolotl","skill":"fire_extinguish"},{"type":"apoli:on_fire"},{"type":"apoli:air","comparison":">=","compare_to":10}]}


来源：data/my_addon/powers/form_axolotl_aling_fire_extinguish.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_aling_health my_addon:form_axolotl_aling_health

能力 ID：my_addon:form_axolotl_aling_health；类型：apoli:attribute。

modifier：{"name":"Form axolotl aling health","attribute":"minecraft:generic.max_health","value":2.0,"operation":"addition"}


来源：data/my_addon/powers/form_axolotl_aling_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Moisture Drain my_addon:form_axolotl_aling_moisture_drain

能力 ID：my_addon:form_axolotl_aling_moisture_drain；类型：apoli:action_over_time。
阿澪：陆地每 27 tick 消耗 3 点湿润值(air)，比荧光幼灵(每 20 tick)慢约 25%。
condition：{"type":"apoli:and","conditions":[{"type":"apoli:on_block"},{"type":"apoli:submerged_in","fluid":"water","inverted":true},{"type":"apoli:in_rain","inverted":true}]}


来源：data/my_addon/powers/form_axolotl_aling_moisture_drain.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Dehydration my_addon:form_axolotl_fluorescent_dehydration

能力 ID：my_addon:form_axolotl_fluorescent_dehydration；类型：apoli:action_over_time。
When moisture (air) is depleted, take 2 damage per second.
condition：{"type":"apoli:air","comparison":"<=","compare_to":0}


来源：data/my_addon/powers/form_axolotl_fluorescent_dehydration.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Slime Membrane Dodge my_addon:form_axolotl_fluorescent_dodge

能力 ID：my_addon:form_axolotl_fluorescent_dodge；类型：apoli:multiple。
20% chance to completely dodge physical attacks (melee / projectiles). Triggers water splash feedback.


来源：data/my_addon/powers/form_axolotl_fluorescent_dodge.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Slime Membrane my_addon:form_axolotl_fluorescent_fall_immunity

能力 ID：my_addon:form_axolotl_fluorescent_fall_immunity；类型：apoli:invulnerability。
Immune to fall damage.


来源：data/my_addon/powers/form_axolotl_fluorescent_fall_immunity.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Moisture Extinguish my_addon:form_axolotl_fluorescent_fire_extinguish

能力 ID：my_addon:form_axolotl_fluorescent_fire_extinguish；类型：apoli:action_over_time。
When on fire, consume 10 moisture (air) per second to extinguish. Cannot extinguish if moisture is below 10.
condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:skill_disabled","form":"axolotl","skill":"fire_extinguish"},{"type":"apoli:on_fire"},{"type":"apoli:air","comparison":">=","compare_to":10}]}


来源：data/my_addon/powers/form_axolotl_fluorescent_fire_extinguish.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_fluorescent_health my_addon:form_axolotl_fluorescent_health

能力 ID：my_addon:form_axolotl_fluorescent_health；类型：apoli:attribute。

modifier：{"name":"Form axolotl fluorescent health","attribute":"minecraft:generic.max_health","value":2.0,"operation":"addition"}


来源：data/my_addon/powers/form_axolotl_fluorescent_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_fluorescent_keep_sneaking_when_head_collide my_addon:form_axolotl_fluorescent_keep_sneaking_when_head_collide

能力 ID：my_addon:form_axolotl_fluorescent_keep_sneaking_when_head_collide；类型：shape-shifter-curse:keep_sneaking。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:must_crawling","width":0.4,"height":1.35},{"type":"apoli:swimming","inverted":true},{"type":"apoli:riding","inverted":true}]}


来源：data/my_addon/powers/form_axolotl_fluorescent_keep_sneaking_when_head_collide.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_fluorescent_laser my_addon:form_axolotl_fluorescent_laser

能力 ID：my_addon:form_axolotl_fluorescent_laser；类型：my_addon:fail_aware_active_self。

cooldown：400


来源：data/my_addon/powers/form_axolotl_fluorescent_laser.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Laser State my_addon:form_axolotl_fluorescent_laser_state

能力 ID：my_addon:form_axolotl_fluorescent_laser_state；类型：apoli:resource。
Internal state for Array Laser skill (0=idle, 1=charging, 2=firing, 3=fading).
min：0
max：3
start_value：0


来源：data/my_addon/powers/form_axolotl_fluorescent_laser_state.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Moisture Drain my_addon:form_axolotl_fluorescent_moisture_drain

能力 ID：my_addon:form_axolotl_fluorescent_moisture_drain；类型：apoli:action_over_time。
On the ground, lose 1% moisture (air) per second. Moisture drops cause dehydration damage.
condition：{"type":"apoli:and","conditions":[{"type":"apoli:on_block"},{"type":"apoli:submerged_in","fluid":"water","inverted":true},{"type":"apoli:in_rain","inverted":true}]}


来源：data/my_addon/powers/form_axolotl_fluorescent_moisture_drain.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_fluorescent_scale my_addon:form_axolotl_fluorescent_scale

能力 ID：my_addon:form_axolotl_fluorescent_scale；类型：shape-shifter-curse:scale。



来源：data/my_addon/powers/form_axolotl_fluorescent_scale.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_fluorescent_shot_cd my_addon:form_axolotl_fluorescent_shot_cd

能力 ID：my_addon:form_axolotl_fluorescent_shot_cd；类型：apoli:resource。

min：0
max：8
start_value：0


来源：data/my_addon/powers/form_axolotl_fluorescent_shot_cd.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_fluorescent_tidal my_addon:form_axolotl_fluorescent_tidal

能力 ID：my_addon:form_axolotl_fluorescent_tidal；类型：my_addon:fail_aware_active_self。

cooldown：160


来源：data/my_addon/powers/form_axolotl_fluorescent_tidal.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Tidal State my_addon:form_axolotl_fluorescent_tidal_state

能力 ID：my_addon:form_axolotl_fluorescent_tidal_state；类型：apoli:resource。
Internal state for Tidal Surge skill (0=idle, 1=charging, 2=orb flying).
min：0
max：2
start_value：0


来源：data/my_addon/powers/form_axolotl_fluorescent_tidal_state.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_sp_crawling my_addon:form_axolotl_sp_crawling

能力 ID：my_addon:form_axolotl_sp_crawling；类型：shape-shifter-curse:crawling。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:swimming","inverted":true}]}


来源：data/my_addon/powers/form_axolotl_sp_crawling.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Moisture Extinguish my_addon:form_axolotl_sp_fire_extinguish

能力 ID：my_addon:form_axolotl_sp_fire_extinguish；类型：apoli:action_over_time。
When on fire, consume 10 moisture (air) per second to extinguish. Cannot extinguish if moisture is below 10.
condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:skill_disabled","form":"axolotl","skill":"fire_extinguish"},{"type":"apoli:on_fire"},{"type":"apoli:air","comparison":">=","compare_to":10}]}


来源：data/my_addon/powers/form_axolotl_sp_fire_extinguish.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_sp_health my_addon:form_axolotl_sp_health

能力 ID：my_addon:form_axolotl_sp_health；类型：apoli:attribute。

modifier：{"name":"Form axolotl sp health","attribute":"minecraft:generic.max_health","value":14.0,"operation":"addition"}


来源：data/my_addon/powers/form_axolotl_sp_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_sp_jump_out_water my_addon:form_axolotl_sp_jump_out_water

能力 ID：my_addon:form_axolotl_sp_jump_out_water；类型：apoli:active_self。

cooldown：2
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":">","compare_to":0},{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<","compare_to":0.2}]}


来源：data/my_addon/powers/form_axolotl_sp_jump_out_water.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Rapid Metabolism my_addon:form_axolotl_sp_natural_regen_boost

能力 ID：my_addon:form_axolotl_sp_natural_regen_boost；类型：apoli:action_over_time。
Natural health regeneration is 20% faster.
condition：{"type":"ssc_addon:skill_disabled","form":"axolotl","skill":"natural_regen_boost"}


来源：data/my_addon/powers/form_axolotl_sp_natural_regen_boost.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 假死 my_addon:form_axolotl_sp_play_dead

能力 ID：my_addon:form_axolotl_sp_play_dead；类型：apoli:multiple。
站立时按次要技能键主动进入假死状态持续6秒，期间获得失明和无法移动效果，消除所有怪物仇恨，持续回复生命（共约15点）并减免60%伤害。31秒冷却。


来源：data/my_addon/powers/form_axolotl_sp_play_dead.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Rain Hydration my_addon:form_axolotl_sp_rain_wetness

能力 ID：my_addon:form_axolotl_sp_rain_wetness；类型：apoli:action_over_time。
Slowly gain wetness (air) when exposed to rain.
condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:skill_disabled","form":"axolotl","skill":"rain_wetness"},{"type":"apoli:in_rain"},{"type":"apoli:exposed_to_sky"}]}


来源：data/my_addon/powers/form_axolotl_sp_rain_wetness.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_sp_scale my_addon:form_axolotl_sp_scale

能力 ID：my_addon:form_axolotl_sp_scale；类型：shape-shifter-curse:scale。



来源：data/my_addon/powers/form_axolotl_sp_scale.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_sp_vortex_charge my_addon:form_axolotl_sp_vortex_charge

能力 ID：my_addon:form_axolotl_sp_vortex_charge；类型：my_addon:fail_aware_active_self。

cooldown：300


来源：data/my_addon/powers/form_axolotl_sp_vortex_charge.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Vortex Impact my_addon:form_axolotl_sp_vortex_impact

能力 ID：my_addon:form_axolotl_sp_vortex_impact；类型：apoli:multiple。
Hold the skill key to start charging: every 0.5s drains 8 wetness and adds damage, up to 4s. Press the skill key again to release early; releases automatically at max charge. Deals AoE physical damage scaling with charge. 50% damage reduction + brief invulnerability while charging. CD 15s after release.


来源：data/my_addon/powers/form_axolotl_sp_vortex_impact.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 强化水流爆破 my_addon:form_axolotl_sp_water_ball

能力 ID：my_addon:form_axolotl_sp_water_ball；类型：my_addon:fail_aware_active_self。
在地面潜行时按次要技能键触发水流爆破：爆炸伤害提升50%，并额外造成4点基础物理伤害。消耗10%湿润度，冷却3秒。
cooldown：60
condition：{"type":"apoli:and","conditions":[{"type":"apoli:on_block"},{"type":"apoli:sneaking"},{"type":"apoli:air","comparison":">","compare_to":0}]}


来源：data/my_addon/powers/form_axolotl_sp_water_ball.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_sp_water_flexibility my_addon:form_axolotl_sp_water_flexibility

能力 ID：my_addon:form_axolotl_sp_water_flexibility；类型：shape-shifter-curse:water_flexibility。

condition：{"type":"apoli:in_block","block_condition":{"type":"apoli:block","block":"minecraft:water"}}


来源：data/my_addon/powers/form_axolotl_sp_water_flexibility.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Water Spear Crafting my_addon:form_axolotl_sp_water_spear

能力 ID：my_addon:form_axolotl_sp_water_spear；类型：apoli:multiple。
Right-click while holding an arrow in offhand to craft a Water Spear. Costs 10% wetness and 1 arrow. Water Spear disappears on hit or landing, deals area damage and applies Slowness II for 5 seconds.


来源：data/my_addon/powers/form_axolotl_sp_water_spear.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 血渴值 my_addon:form_bat_desmodus_blood

能力 ID：my_addon:form_bat_desmodus_blood；类型：apoli:multiple。
战斗中每秒+1；普攻命中+8(0.3s内CD)；凝聚爆破命中+12/6/3；击杀+15。脱战12s后每秒-4。0-25：减伤15%；50-75：吸血30%；75-100：伤害+15%且吸血52.5%。


来源：data/my_addon/powers/form_bat_desmodus_blood.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 猩红汲取 my_addon:form_bat_desmodus_blood_kill

能力 ID：my_addon:form_bat_desmodus_blood_kill；类型：apoli:self_action_on_kill。
击杀生物时汲取精血，血渴值 +15。


来源：data/my_addon/powers/form_bat_desmodus_blood_kill.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 回声定位 my_addon:form_bat_desmodus_echolocation

能力 ID：my_addon:form_bat_desmodus_echolocation；类型：apoli:action_over_time。
一到夜晚就自动启动（不论室内室外），每 3 秒对周围 12 格内的所有生物施加一次发光效果、持续 1 秒，循环不断，便于在夜间追踪敌人。
condition：{"type":"apoli:and","conditions":[{"type":"apoli:time_of_day","comparison":">=","compare_to":13000},{"type":"apoli:time_of_day","comparison":"<=","compare_to":23000}]}


来源：data/my_addon/powers/form_bat_desmodus_echolocation.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 银血脆性 my_addon:form_bat_desmodus_iron_weakness

能力 ID：my_addon:form_bat_desmodus_iron_weakness；类型：apoli:modify_damage_taken。
被持有铁制武器（铁剑/铁斧/铁镐/铁锹/铁锄）的攻击者命中时，受到的伤害额外增加 15%。吸血鬼血脉对精炼之铁尤为敏感。
modifier：{"operation":"multiply_total","value":0.15,"name":"Bat Desmodus Iron Weakness"}


来源：data/my_addon/powers/form_bat_desmodus_iron_weakness.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 幽雾化形 my_addon:form_bat_desmodus_mist_form

能力 ID：my_addon:form_bat_desmodus_mist_form；类型：my_addon:mist_form。
按下后化为一团幽雾，模型消散无形。雾化期间除虚空伤害外免疫一切伤害，持续约4.5秒。化雾1秒后再次按主动技能键可凝聚爆破，对周围4格内敌人造成12点伤害并击退。冷却10秒。
cooldown：200


来源：data/my_addon/powers/form_bat_desmodus_mist_form.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_bat_desmodus_mist_form_invulnerability my_addon:form_bat_desmodus_mist_form_invulnerability

能力 ID：my_addon:form_bat_desmodus_mist_form_invulnerability；类型：apoli:invulnerability。

condition：{"type":"apoli:status_effect","effect":"ssc_addon:mist_form"}


来源：data/my_addon/powers/form_bat_desmodus_mist_form_invulnerability.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 月夜亢奋 my_addon:form_bat_desmodus_moon_buff

能力 ID：my_addon:form_bat_desmodus_moon_buff；类型：apoli:multiple。
在夜晚且头顶天空无遮挡时，月光唤起吸血鬼的猎食本能：攻击力 +15%、移动速度 +5%，且脱战时血渴值衰减减半为每秒 2。


来源：data/my_addon/powers/form_bat_desmodus_moon_buff.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 声破超波 my_addon:form_bat_desmodus_sonic_wave

能力 ID：my_addon:form_bat_desmodus_sonic_wave；类型：my_addon:sonic_wave。
向前方释放3.5格宽、8格长的超声波，对范围内非白名单生物造成6点物理伤害，并施加3秒反胃；玩家目标额外获得3秒失聪（暂时听不到任何声音），非玩家目标获得3秒失明。冷却8秒。
cooldown：160


来源：data/my_addon/powers/form_bat_desmodus_sonic_wave.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 日光虚弱 my_addon:form_bat_desmodus_sun_weak

能力 ID：my_addon:form_bat_desmodus_sun_weak；类型：apoli:multiple。
在日间且头顶天空无遮挡时，吸血鬼的本能使你陷入虚弱：攻击力 -20%、移动速度 -10%，且脱战时血渴值衰减加快 50%。进入阴影、水下或方块掩体后立刻消除。


来源：data/my_addon/powers/form_bat_desmodus_sun_weak.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_bat_parasitic_fruit_health my_addon:form_bat_parasitic_fruit_health

能力 ID：my_addon:form_bat_parasitic_fruit_health；类型：apoli:attribute。

modifier：{"name":"Parasitic fruit bat fixed max health","attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/my_addon/powers/form_bat_parasitic_fruit_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 灵果寄生 my_addon:form_bat_parasitic_fruit_parasitic_seed

能力 ID：my_addon:form_bat_parasitic_fruit_parasitic_seed；类型：my_addon:parasitic_fruit_seed。
抛掷灵果种子（抛物线飞行）。命中生物在其身上生根，周期性结出自适应果实：友方安装蛜露/刺梨/风莓/清香果提供治疗与辅助，敌方安装缠藤/苦涩/腐熟/酸涩果施加减速、虚弱、发光、中毒等削弱。果实只对宿主生效。未命中而落地时种下一圈灵果（绿色治疗环），任意玩家踏入即可拾取并获得相应效果，持续时间随种子留存递减；种子枯萎或被拾取后消失。冷却 1 秒，消耗 1 点种子量。
cooldown：20


来源：data/my_addon/powers/form_bat_parasitic_fruit_parasitic_seed.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 灵果催生 my_addon:form_bat_parasitic_fruit_plant_growth

能力 ID：my_addon:form_bat_parasitic_fruit_plant_growth；类型：my_addon:fruit_bat_plant_growth。
形态被动：每秒对身周 5 格半径内的每株农作物有 5% 概率促使其再生长一次。


来源：data/my_addon/powers/form_bat_parasitic_fruit_plant_growth.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_bat_parasitic_fruit_seed_eat my_addon:form_bat_parasitic_fruit_seed_eat

能力 ID：my_addon:form_bat_parasitic_fruit_seed_eat；类型：shape-shifter-curse:custom_edible。

saturation_modifier：0.0
always_edible：true


来源：data/my_addon/powers/form_bat_parasitic_fruit_seed_eat.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 种子量 my_addon:form_bat_parasitic_fruit_seed_energy

能力 ID：my_addon:form_bat_parasitic_fruit_seed_energy；类型：apoli:resource。
寄生果蝠形态专属能量。上限 10，两个主动技能每次释放消耗 1 点，能量不足时无法释放。潜行下右键主手原版种子可消耗 1 个种子立即回复 1 点能量，同时能量每 5 秒自然回复 1 点。
min：0
max：10
start_value：10


来源：data/my_addon/powers/form_bat_parasitic_fruit_seed_energy.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 种子量恢复 my_addon:form_bat_parasitic_fruit_seed_regen

能力 ID：my_addon:form_bat_parasitic_fruit_seed_regen；类型：apoli:action_over_time。
每 5 秒回复 1 点种子量，吊顶以 10 点为上限。
condition：{"type":"apoli:constant","value":false}


来源：data/my_addon/powers/form_bat_parasitic_fruit_seed_regen.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 感染孢子弹 my_addon:form_bat_parasitic_fruit_spore_bomb

能力 ID：my_addon:form_bat_parasitic_fruit_spore_bomb；类型：my_addon:parasitic_spore_bomb。
投出爆炸种子，命中方块或生物时无伤害爆炸并释放孢子。爆炸 4 格内：受白名单保护的友方在 15 秒内每 3 秒回复 1 点生命，其余生物则被感染——15 秒内每 3 秒受到 1 点真伤并被高亮 1 秒，无法以饱食度恢复血量，造成的所有伤害减少 15%；被感染目标 1.5 格内的非白名单生物会被传染，剩余时间继承自当前感染源。冷却 1 秒，消耗 1 点种子量。
cooldown：20


来源：data/my_addon/powers/form_bat_parasitic_fruit_spore_bomb.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_empower_state my_addon:form_empower_state

能力 ID：my_addon:form_empower_state；类型：apoli:multiple。



来源：data/my_addon/powers/form_empower_state.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 暗影尖啸 my_addon:form_fallen_allay_sp_active_scream

能力 ID：my_addon:form_fallen_allay_sp_active_scream；类型：apoli:multiple。
蓄力1秒后高亮25格半径内所有非白名单生物并击落所有飞行物。高亮持续8秒，全体玩家可见。20秒CD。 对非白名单非玩家生物的发光持续 16 秒；标记期间，其对你造成的伤害减少 25%，你对其造成的伤害额外增加 25%。


来源：data/my_addon/powers/form_fallen_allay_sp_active_scream.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 召唤恼鬼 my_addon:form_fallen_allay_sp_active_vex

能力 ID：my_addon:form_fallen_allay_sp_active_vex；类型：apoli:multiple。
召唤两个恼鬼为你作战。恼鬼会优先攻击被标记的目标和非白名单玩家，存活35秒。全部死亡后进入20秒CD。


来源：data/my_addon/powers/form_fallen_allay_sp_active_vex.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 仇恨标记 my_addon:form_fallen_allay_sp_attack_mark

能力 ID：my_addon:form_fallen_allay_sp_attack_mark；类型：apoli:multiple。
攻击或被攻击时，目标/攻击者会被标记发光10秒，恼鬼会优先攻击被标记的目标。


来源：data/my_addon/powers/form_fallen_allay_sp_attack_mark.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 嗜肉食性 my_addon:form_fallen_allay_sp_cooked_meat_only

能力 ID：my_addon:form_fallen_allay_sp_cooked_meat_only；类型：apoli:prevent_item_use。
你只能食用熟肉，其他食物对你来说难以下咽。


来源：data/my_addon/powers/form_fallen_allay_sp_cooked_meat_only.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 焰灵体质 my_addon:form_fallen_allay_sp_fire_immune

能力 ID：my_addon:form_fallen_allay_sp_fire_immune；类型：apoli:invulnerability。
作为堕落的灵体，你对火焰伤害完全免疫。


来源：data/my_addon/powers/form_fallen_allay_sp_fire_immune.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 暗影利刃 my_addon:form_fallen_allay_sp_melee_damage_up

能力 ID：my_addon:form_fallen_allay_sp_melee_damage_up；类型：apoli:modify_damage_dealt。
手持铁质及以上武器时，近战攻击伤害提高2点。空手或低级武器不生效。
modifier：{"operation":"addition","value":2.0}


来源：data/my_addon/powers/form_fallen_allay_sp_melee_damage_up.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 劫掠阵营 my_addon:form_fallen_allay_sp_raid_faction

能力 ID：my_addon:form_fallen_allay_sp_raid_faction；类型：apoli:multiple。
你归属于劫掠阵营，劫掠者不会攻击你，你也无法攻击劫掠阵营的生物。你的存在会吓跑附近的村民。


来源：data/my_addon/powers/form_fallen_allay_sp_raid_faction.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_barehand_attack_range my_addon:form_familiar_fox_mancianima_barehand_attack_range

能力 ID：my_addon:form_familiar_fox_mancianima_barehand_attack_range；类型：apoli:conditioned_attribute。

tick_rate：5
condition：{"type":"apoli:equipped_item","equipment_slot":"mainhand","item_condition":{"type":"apoli:empty"},"inverted":true}
modifier：{"attribute":"reach-entity-attributes:attack_range","operation":"multiply_total","value":0.2,"name":"Mancianima Bare Hand Attack Range Up"}


来源：data/my_addon/powers/form_familiar_fox_mancianima_barehand_attack_range.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_contract_mark my_addon:form_familiar_fox_mancianima_contract_mark

能力 ID：my_addon:form_familiar_fox_mancianima_contract_mark；类型：my_addon:fail_aware_active_self。

cooldown：100


来源：data/my_addon/powers/form_familiar_fox_mancianima_contract_mark.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_glow_crosshair my_addon:form_familiar_fox_mancianima_glow_crosshair

能力 ID：my_addon:form_familiar_fox_mancianima_glow_crosshair；类型：apoli:entity_glow。



来源：data/my_addon/powers/form_familiar_fox_mancianima_glow_crosshair.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_glow_in_range my_addon:form_familiar_fox_mancianima_glow_in_range

能力 ID：my_addon:form_familiar_fox_mancianima_glow_in_range；类型：apoli:entity_glow。



来源：data/my_addon/powers/form_familiar_fox_mancianima_glow_in_range.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_glow_orange my_addon:form_familiar_fox_mancianima_glow_orange

能力 ID：my_addon:form_familiar_fox_mancianima_glow_orange；类型：apoli:entity_glow。



来源：data/my_addon/powers/form_familiar_fox_mancianima_glow_orange.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_glow_red my_addon:form_familiar_fox_mancianima_glow_red

能力 ID：my_addon:form_familiar_fox_mancianima_glow_red；类型：apoli:entity_glow。



来源：data/my_addon/powers/form_familiar_fox_mancianima_glow_red.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_glow_yellow my_addon:form_familiar_fox_mancianima_glow_yellow

能力 ID：my_addon:form_familiar_fox_mancianima_glow_yellow；类型：apoli:entity_glow。



来源：data/my_addon/powers/form_familiar_fox_mancianima_glow_yellow.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_init_mana my_addon:form_familiar_fox_mancianima_init_mana

能力 ID：my_addon:form_familiar_fox_mancianima_init_mana；类型：apoli:action_on_callback。



来源：data/my_addon/powers/form_familiar_fox_mancianima_init_mana.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_mana my_addon:form_familiar_fox_mancianima_mana

能力 ID：my_addon:form_familiar_fox_mancianima_mana；类型：shape-shifter-curse:mana_type_power。



来源：data/my_addon/powers/form_familiar_fox_mancianima_mana.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_mana_regen my_addon:form_familiar_fox_mancianima_mana_regen

能力 ID：my_addon:form_familiar_fox_mancianima_mana_regen；类型：apoli:multiple。



来源：data/my_addon/powers/form_familiar_fox_mancianima_mana_regen.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_no_buff_effect my_addon:form_familiar_fox_mancianima_no_buff_effect

能力 ID：my_addon:form_familiar_fox_mancianima_no_buff_effect；类型：apoli:effect_immunity。



来源：data/my_addon/powers/form_familiar_fox_mancianima_no_buff_effect.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_soul_teleport my_addon:form_familiar_fox_mancianima_soul_teleport

能力 ID：my_addon:form_familiar_fox_mancianima_soul_teleport；类型：my_addon:fail_aware_active_self。

cooldown：70


来源：data/my_addon/powers/form_familiar_fox_mancianima_soul_teleport.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_villager_drain my_addon:form_familiar_fox_mancianima_villager_drain

能力 ID：my_addon:form_familiar_fox_mancianima_villager_drain；类型：apoli:action_on_entity_use。



来源：data/my_addon/powers/form_familiar_fox_mancianima_villager_drain.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_villager_drain_cd my_addon:form_familiar_fox_mancianima_villager_drain_cd

能力 ID：my_addon:form_familiar_fox_mancianima_villager_drain_cd；类型：apoli:resource。

min：0
max：60
start_value：0


来源：data/my_addon/powers/form_familiar_fox_mancianima_villager_drain_cd.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_villager_drain_cd_tick my_addon:form_familiar_fox_mancianima_villager_drain_cd_tick

能力 ID：my_addon:form_familiar_fox_mancianima_villager_drain_cd_tick；类型：apoli:action_over_time。

condition：{"type":"apoli:resource","resource":"my_addon:form_familiar_fox_mancianima_villager_drain_cd","comparison":">","compare_to":0}


来源：data/my_addon/powers/form_familiar_fox_mancianima_villager_drain_cd_tick.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_weapon_attack_range my_addon:form_familiar_fox_mancianima_weapon_attack_range

能力 ID：my_addon:form_familiar_fox_mancianima_weapon_attack_range；类型：apoli:conditioned_attribute。

tick_rate：5
condition：{"type":"apoli:equipped_item","equipment_slot":"mainhand","item_condition":{"type":"apoli:empty"}}
modifier：{"attribute":"reach-entity-attributes:attack_range","operation":"multiply_total","value":-0.2,"name":"Mancianima Weapon Attack Range Down"}


来源：data/my_addon/powers/form_familiar_fox_mancianima_weapon_attack_range.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_mancianima_weapon_attack_speed my_addon:form_familiar_fox_mancianima_weapon_attack_speed

能力 ID：my_addon:form_familiar_fox_mancianima_weapon_attack_speed；类型：apoli:conditioned_attribute。

tick_rate：5
condition：{"type":"apoli:equipped_item","equipment_slot":"mainhand","item_condition":{"type":"apoli:empty"}}
modifier：{"attribute":"minecraft:generic.attack_speed","operation":"multiply_total","value":-0.2,"name":"Mancianima Weapon Attack Speed Down"}


来源：data/my_addon/powers/form_familiar_fox_mancianima_weapon_attack_speed.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_red_blue_fire_ring my_addon:form_familiar_fox_red_blue_fire_ring

能力 ID：my_addon:form_familiar_fox_red_blue_fire_ring；类型：apoli:multiple。



来源：data/my_addon/powers/form_familiar_fox_red_blue_fire_ring.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_red_blue_fire_ring_amulet my_addon:form_familiar_fox_red_blue_fire_ring_amulet

能力 ID：my_addon:form_familiar_fox_red_blue_fire_ring_amulet；类型：apoli:multiple。



来源：data/my_addon/powers/form_familiar_fox_red_blue_fire_ring_amulet.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_red_debuffs my_addon:form_familiar_fox_red_debuffs

能力 ID：my_addon:form_familiar_fox_red_debuffs；类型：apoli:multiple。



来源：data/my_addon/powers/form_familiar_fox_red_debuffs.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 狐火火球 (Red) my_addon:form_familiar_fox_red_fox_fire_breath

能力 ID：my_addon:form_familiar_fox_red_fox_fire_breath；类型：ssc_addon:familiar_skill。
消耗20点法力，向前喷射狐火火球：前12格穿透敌人造成魔法伤害并触发额外爆炸，12格后碰撞爆炸造成范围物理伤害与腰部火环连锁；发射时近身60°、4格扇形霰击。
cooldown：100
condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":20},{"type":"ssc_addon:not_empowered"}]}


来源：data/my_addon/powers/form_familiar_fox_red_fox_fire_breath.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_red_health my_addon:form_familiar_fox_red_health

能力 ID：my_addon:form_familiar_fox_red_health；类型：apoli:attribute。

modifier：{"name":"Red familiar fox health adjustment","attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/my_addon/powers/form_familiar_fox_red_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_red_mana_regen my_addon:form_familiar_fox_red_mana_regen

能力 ID：my_addon:form_familiar_fox_red_mana_regen；类型：apoli:multiple。



来源：data/my_addon/powers/form_familiar_fox_red_mana_regen.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_red_scale my_addon:form_familiar_fox_red_scale

能力 ID：my_addon:form_familiar_fox_red_scale；类型：shape-shifter-curse:scale。



来源：data/my_addon/powers/form_familiar_fox_red_scale.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 魔力汲取 my_addon:form_familiar_fox_sp_absorb

能力 ID：my_addon:form_familiar_fox_sp_absorb；类型：apoli:action_on_entity_use。
潜行并右击生物可汲取其生命精华：普通生物与人类会回馈大量魔力与饱食度，敌对生物则回馈少量魔力并伴随致盲、缓慢、虚弱等削弱效果。有内置冷却。
condition：{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_use_entity_cooldown","comparison":"<","compare_to":1}]}


来源：data/my_addon/powers/form_familiar_fox_sp_absorb.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 苍蓝火环 my_addon:form_familiar_fox_sp_blue_fire_ring

能力 ID：my_addon:form_familiar_fox_sp_blue_fire_ring；类型：apoli:multiple。
需要满魔力激活。每秒消耗魔力，对周围造成伤害并点燃。激活时增强击杀回复效果。


来源：data/my_addon/powers/form_familiar_fox_sp_blue_fire_ring.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sp_blue_fire_ring_amulet my_addon:form_familiar_fox_sp_blue_fire_ring_amulet

能力 ID：my_addon:form_familiar_fox_sp_blue_fire_ring_amulet；类型：apoli:multiple。



来源：data/my_addon/powers/form_familiar_fox_sp_blue_fire_ring_amulet.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 灵能护幕 my_addon:form_familiar_fox_sp_damage_reduction

能力 ID：my_addon:form_familiar_fox_sp_damage_reduction；类型：apoli:modify_damage_taken。
当魔力保持在50点以上时，持续获得50%伤害减免。
condition：{"type":"shape-shifter-curse:has_mana","mana":50.0}
modifier：{"name":"High Mana Protection","operation":"multiply_total","value":-0.5}


来源：data/my_addon/powers/form_familiar_fox_sp_damage_reduction.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 魔力失衡 my_addon:form_familiar_fox_sp_debuffs

能力 ID：my_addon:form_familiar_fox_sp_debuffs；类型：apoli:multiple。
魔力<10%时攻击、移速、挖掘各-20%，<3%时进一步降至-60%并更易受伤。魔力见底就是折磨。


来源：data/my_addon/powers/form_familiar_fox_sp_debuffs.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sp_effect_nerf my_addon:form_familiar_fox_sp_effect_nerf

能力 ID：my_addon:form_familiar_fox_sp_effect_nerf；类型：apoli:multiple。



来源：data/my_addon/powers/form_familiar_fox_sp_effect_nerf.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sp_food_down my_addon:form_familiar_fox_sp_food_down

能力 ID：my_addon:form_familiar_fox_sp_food_down；类型：apoli:modify_food。

food_modifier：{"name":"Decreased food points","operation":"multiply_base_multiplicative","value":-0.8}
saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-0.8}


来源：data/my_addon/powers/form_familiar_fox_sp_food_down.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 狐火吐息 my_addon:form_familiar_fox_sp_fox_fire_breath

能力 ID：my_addon:form_familiar_fox_sp_fox_fire_breath；类型：ssc_addon:familiar_skill。
消耗15点法力，向前方8格范围喷吐灵魂狐火，造成6点伤害（对非玩家生物×3）并点燃敌人5秒。
cooldown：20
condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":15},{"type":"ssc_addon:not_empowered"}]}


来源：data/my_addon/powers/form_familiar_fox_sp_fox_fire_breath.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 满月初启 my_addon:form_familiar_fox_sp_init_mana

能力 ID：my_addon:form_familiar_fox_sp_init_mana；类型：apoli:action_on_callback。
获得形态时直接充盈至100点魔力，为后续战斗打好底色。


来源：data/my_addon/powers/form_familiar_fox_sp_init_mana.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 灵魂收割 my_addon:form_familiar_fox_sp_kill_mana

能力 ID：my_addon:form_familiar_fox_sp_kill_mana；类型：apoli:self_action_on_kill。
击杀目标后恢复10点魔力。


来源：data/my_addon/powers/form_familiar_fox_sp_kill_mana.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sp_kill_mana_amulet my_addon:form_familiar_fox_sp_kill_mana_amulet

能力 ID：my_addon:form_familiar_fox_sp_kill_mana_amulet；类型：apoli:self_action_on_kill。

condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:has_blue_fire_amulet"},{"type":"apoli:or","conditions":[{"type":"apoli:resource","resource":"my_addon:form_familiar_fox_sp_blue_fire_ring_amulet_toggle_state","comparison":"==","compare_to":1},{"type":"apoli:resource","resource":"my_addon:form_familiar_fox_red_blue_fire_ring_amulet_toggle_state","comparison":"==","compare_to":1}]}]}


来源：data/my_addon/powers/form_familiar_fox_sp_kill_mana_amulet.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 灵源涌动 my_addon:form_familiar_fox_sp_mana_regen

能力 ID：my_addon:form_familiar_fox_sp_mana_regen；类型：apoli:multiple。
每0.5秒被动恢复1点魔力；释放技能消耗魔力后会暂停回复约6秒。月华在你体内缓缓回流。


来源：data/my_addon/powers/form_familiar_fox_sp_mana_regen.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sp_max_mana my_addon:form_familiar_fox_sp_max_mana

能力 ID：my_addon:form_familiar_fox_sp_max_mana；类型：shape-shifter-curse:mana_attribute。



来源：data/my_addon/powers/form_familiar_fox_sp_max_mana.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sp_mob_glow_in_range my_addon:form_familiar_fox_sp_mob_glow_in_range

能力 ID：my_addon:form_familiar_fox_sp_mob_glow_in_range；类型：apoli:entity_glow。



来源：data/my_addon/powers/form_familiar_fox_sp_mob_glow_in_range.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 灵体化身 my_addon:form_familiar_fox_sp_no_buff_effect

能力 ID：my_addon:form_familiar_fox_sp_no_buff_effect；类型：apoli:effect_immunity。
作为强大的灵体生物，你免疫中毒、饥饿、反胃等常规负面状态，且不会因为着火而受到伤害（但仍害怕灵魂火）。


来源：data/my_addon/powers/form_familiar_fox_sp_no_buff_effect.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Phantom Bell Effect my_addon:form_familiar_fox_sp_phantom_bell

能力 ID：my_addon:form_familiar_fox_sp_phantom_bell；类型：apoli:action_when_damage_taken。
Triggers illusion escape when health is below 2 and attacked by entities.
cooldown：1
condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:has_trinket","item":"ssc_addon:phantom_bell"},{"type":"ssc_addon:item_on_cooldown","item":"ssc_addon:phantom_bell","inverted":true},{"type":"apoli:health","comparison":"<","compare_to":4.0}]}


来源：data/my_addon/powers/form_familiar_fox_sp_phantom_bell.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sp_scale my_addon:form_familiar_fox_sp_scale

能力 ID：my_addon:form_familiar_fox_sp_scale；类型：shape-shifter-curse:scale。



来源：data/my_addon/powers/form_familiar_fox_sp_scale.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 隐匿气息 my_addon:form_familiar_fox_sp_visibility

能力 ID：my_addon:form_familiar_fox_sp_visibility；类型：apoli:simple。
你散发着如同幽灵般的气息，怪物发现你的距离减少33%。


来源：data/my_addon/powers/form_familiar_fox_sp_visibility.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 烙印感知 my_addon:form_golden_sandstorm_sp_brand_glow

能力 ID：my_addon:form_golden_sandstorm_sp_brand_glow；类型：apoli:multiple。
被侵蚀烙印标记的生物会发出荧光：黄色(1层)、橙色(2层)、红色(3层)、绿色(冷却)。


来源：data/my_addon/powers/form_golden_sandstorm_sp_brand_glow.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 反噬冲击 my_addon:form_golden_sandstorm_sp_counter_burst

能力 ID：my_addon:form_golden_sandstorm_sp_counter_burst；类型：apoli:action_when_damage_taken。
被攻击时自动触发：对周围4格内的非白名单生物造成小幅击退并附加凋零I效果（5秒）。15秒CD。
cooldown：0


来源：data/my_addon/powers/form_golden_sandstorm_sp_counter_burst.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_golden_sandstorm_sp_counter_burst_cd my_addon:form_golden_sandstorm_sp_counter_burst_cd

能力 ID：my_addon:form_golden_sandstorm_sp_counter_burst_cd；类型：apoli:resource。

min：0
max：300
start_value：0


来源：data/my_addon/powers/form_golden_sandstorm_sp_counter_burst_cd.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_golden_sandstorm_sp_counter_burst_cd_tick my_addon:form_golden_sandstorm_sp_counter_burst_cd_tick

能力 ID：my_addon:form_golden_sandstorm_sp_counter_burst_cd_tick；类型：apoli:action_over_time。

condition：{"type":"apoli:resource","resource":"my_addon:form_golden_sandstorm_sp_counter_burst_cd","comparison":">","compare_to":0}


来源：data/my_addon/powers/form_golden_sandstorm_sp_counter_burst_cd_tick.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 引爆标记 my_addon:form_golden_sandstorm_sp_erosion_brand

能力 ID：my_addon:form_golden_sandstorm_sp_erosion_brand；类型：my_addon:fail_aware_active_self。
引爆所有非绿色状态的侵蚀烙印：造成目标当前生命值20%的伤害（上限20），自身回复已损生命值的20%。3层目标先触发被动爆发再引爆。10秒CD。
cooldown：200
condition：{"type":"apoli:sneaking","inverted":true}


来源：data/my_addon/powers/form_golden_sandstorm_sp_erosion_brand.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 侵蚀烙印 my_addon:form_golden_sandstorm_sp_erosion_brand_hit

能力 ID：my_addon:form_golden_sandstorm_sp_erosion_brand_hit；类型：apoli:action_on_hit。
近战攻击自动叠加侵蚀烙印（每秒最多1层，最多3层，持续10秒）。3层满后继续攻击触发被动爆发：造成目标20%当前生命值伤害（上限20），自愈10%最大生命值，目标进入5秒叠层冷却（绿色标志持续10秒）。近战叠标记不受白名单限制，但扩散叠标记受白名单保护。


来源：data/my_addon/powers/form_golden_sandstorm_sp_erosion_brand_hit.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_golden_sandstorm_sp_immunity my_addon:form_golden_sandstorm_sp_immunity

能力 ID：my_addon:form_golden_sandstorm_sp_immunity；类型：apoli:effect_immunity。



来源：data/my_addon/powers/form_golden_sandstorm_sp_immunity.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 凋零金沙 my_addon:form_golden_sandstorm_sp_wither_sand

能力 ID：my_addon:form_golden_sandstorm_sp_wither_sand；类型：my_addon:fail_aware_active_self。
蓄力1秒后释放15格AoE金沙风暴，对范围内敌对生物施加致盲效果（3秒）并叠加1层侵蚀烙印。蓄力期间减速50%，受到伤害会打断蓄力并进入7秒CD。正常释放CD: 26秒。
cooldown：520
condition：{"type":"apoli:sneaking","inverted":true}


来源：data/my_addon/powers/form_golden_sandstorm_sp_wither_sand.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 击杀赋能 my_addon:form_kill_empower

能力 ID：my_addon:form_kill_empower；类型：apoli:simple。
正常火环或正常次要技能（火球／吐息）击杀非白名单生物后，获得持续10秒的赋能，无独立冷却。每次赋能只能无视冷却与魔力免费释放一次主要或次要技能，二选一；持有期间不叠加、不刷新。赋能火环持续期间仍可使用正常次要技能。赋能技能及其灼烧不会触发新赋能。


来源：data/my_addon/powers/form_kill_empower.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_mancianima_iframes my_addon:form_mancianima_iframes

能力 ID：my_addon:form_mancianima_iframes；类型：apoli:resource。

min：0
max：40
start_value：0


来源：data/my_addon/powers/form_mancianima_iframes.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_mancianima_iframes_tick my_addon:form_mancianima_iframes_tick

能力 ID：my_addon:form_mancianima_iframes_tick；类型：apoli:action_over_time。

condition：{"type":"apoli:resource","resource":"my_addon:form_mancianima_iframes","comparison":">","compare_to":0}


来源：data/my_addon/powers/form_mancianima_iframes_tick.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_mancianima_link_cd my_addon:form_mancianima_link_cd

能力 ID：my_addon:form_mancianima_link_cd；类型：apoli:resource。

min：0
max：2147483647
start_value：0


来源：data/my_addon/powers/form_mancianima_link_cd.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_mancianima_link_cd_tick my_addon:form_mancianima_link_cd_tick

能力 ID：my_addon:form_mancianima_link_cd_tick；类型：apoli:action_over_time。

condition：{"type":"apoli:resource","resource":"my_addon:form_mancianima_link_cd","comparison":">","compare_to":0}


来源：data/my_addon/powers/form_mancianima_link_cd_tick.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_mancianima_resistance my_addon:form_mancianima_resistance

能力 ID：my_addon:form_mancianima_resistance；类型：apoli:resource。

min：0
max：2
start_value：2


来源：data/my_addon/powers/form_mancianima_resistance.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 舍身爆炸 my_addon:form_ocelot_nova_charge

能力 ID：my_addon:form_ocelot_nova_charge；类型：my_addon:fail_aware_active_self。
蓄力 5 秒后自爆并消耗 1 条命：蓄力期间减速 70%、获得抗性提升 III（5 秒）、暂时无法疾跑。半径 5 格内造成 50 点致命伤害，随距离衰减至半径 12 格，不破坏方块、无友伤。冷却 30 秒。 对非白名单非玩家生物的近距离基础伤害为 100 点，保留距离衰减。
cooldown：600


来源：data/my_addon/powers/form_ocelot_nova_charge.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_nova_charging my_addon:form_ocelot_nova_charging

能力 ID：my_addon:form_ocelot_nova_charging；类型：apoli:resource。

min：0
max：1
start_value：0


来源：data/my_addon/powers/form_ocelot_nova_charging.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_nova_health_reduction my_addon:form_ocelot_nova_health_reduction

能力 ID：my_addon:form_ocelot_nova_health_reduction；类型：apoli:attribute。

modifier：{"name":"Nova extra health reduction","attribute":"minecraft:generic.max_health","value":-2.0,"operation":"addition"}


来源：data/my_addon/powers/form_ocelot_nova_health_reduction.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 灵跃闪身 my_addon:form_ocelot_nova_leap

能力 ID：my_addon:form_ocelot_nova_leap；类型：my_addon:fail_aware_active_self。
朝准星方向跳冲（空中可用），可连用两次，两次间隔最多 5 秒；每次使用增加 20% 闪避（持续 3 秒）。冷却 10 秒；只用一次且超过窗口期后进入 6 秒冷却。闪避：有几率完全免疫一次近战或远程伤害，不受伤也不被击退。
cooldown：200


来源：data/my_addon/powers/form_ocelot_nova_leap.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 九命 my_addon:form_ocelot_nova_nine_lives

能力 ID：my_addon:form_ocelot_nova_nine_lives；类型：apoli:resource。
猫有九条命。被致命伤害击倒时自动消耗 1 条命原地复活：恢复 6 点生命、获得 6 颗黄心护盾（30 秒）、1 秒无敌；随后 3 秒内再被击倒将真正死亡。命数上限 8，脱离战斗后每 20 秒回复 1 条命；真正死亡并重生后回满 8 条命。你天生拥有 15% 基础闪避（可完全免疫一次伤害）。
min：0
max：8
start_value：8


来源：data/my_addon/powers/form_ocelot_nova_nine_lives.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_wind_spirit_claw_buff my_addon:form_ocelot_wind_spirit_claw_buff

能力 ID：my_addon:form_ocelot_wind_spirit_claw_buff；类型：my_addon:fail_aware_active_self。

cooldown：40


来源：data/my_addon/powers/form_ocelot_wind_spirit_claw_buff.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_wind_spirit_wind_dash my_addon:form_ocelot_wind_spirit_wind_dash

能力 ID：my_addon:form_ocelot_wind_spirit_wind_dash；类型：my_addon:fail_aware_active_self。

cooldown：240


来源：data/my_addon/powers/form_ocelot_wind_spirit_wind_dash.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 霜寒代谢 my_addon:form_snow_fox_frostspine_cold_metabolism

能力 ID：my_addon:form_snow_fox_frostspine_cold_metabolism；类型：apoli:action_over_time。
身处寒冷生物群系（生物群系温度低于0.2）或站立在细雪上时，每3秒恢复1点生命（技能冷却中暂停恢复）。
condition：{"type":"apoli:and","conditions":[{"type":"apoli:or","conditions":[{"type":"apoli:biome","condition":{"type":"apoli:temperature","comparison":"<","compare_to":0.2}},{"type":"apoli:on_block","block_condition":{"type":"apoli:block","block":"minecraft:powder_snow"}}]},{"type":"my_addon:skill_ready","power":"my_addon:form_snow_fox_frostspine_frost_spike"}]}


来源：data/my_addon/powers/form_snow_fox_frostspine_cold_metabolism.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_frostspine_frost_spike my_addon:form_snow_fox_frostspine_frost_spike

能力 ID：my_addon:form_snow_fox_frostspine_frost_spike；类型：my_addon:fail_aware_active_self。

cooldown：4


来源：data/my_addon/powers/form_snow_fox_frostspine_frost_spike.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 冰雪疾行 my_addon:form_snow_fox_frostspine_snow_stride

能力 ID：my_addon:form_snow_fox_frostspine_snow_stride；类型：apoli:conditioned_attribute。
在冰、雪与细雪上移动速度提升15%。
tick_rate：4
condition：{"type":"apoli:on_block","block_condition":{"type":"apoli:or","conditions":[{"type":"apoli:in_tag","tag":"minecraft:ice"},{"type":"apoli:in_tag","tag":"minecraft:snow"},{"type":"apoli:block","block":"minecraft:powder_snow"}]}}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":0.15}


来源：data/my_addon/powers/form_snow_fox_frostspine_snow_stride.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_cold_strength my_addon:form_snow_fox_sp_cold_strength

能力 ID：my_addon:form_snow_fox_sp_cold_strength；类型：apoli:multiple。



来源：data/my_addon/powers/form_snow_fox_sp_cold_strength.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_form_speed my_addon:form_snow_fox_sp_form_speed

能力 ID：my_addon:form_snow_fox_sp_form_speed；类型：ssc_addon:snow_fox_sp_form_speed。



来源：data/my_addon/powers/form_snow_fox_sp_form_speed.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_frost_pawglove_warning my_addon:form_snow_fox_sp_frost_pawglove_warning

能力 ID：my_addon:form_snow_fox_sp_frost_pawglove_warning；类型：apoli:action_over_time。

condition：{"type":"ssc_addon:has_trinket","item":"shape-shifter-curse:frost_pawglove"}


来源：data/my_addon/powers/form_snow_fox_sp_frost_pawglove_warning.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_frost_regen my_addon:form_snow_fox_sp_frost_regen

能力 ID：my_addon:form_snow_fox_sp_frost_regen；类型：apoli:multiple。



来源：data/my_addon/powers/form_snow_fox_sp_frost_regen.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_frost_shield my_addon:form_snow_fox_sp_frost_shield

能力 ID：my_addon:form_snow_fox_sp_frost_shield；类型：apoli:modify_damage_taken。

condition：{"type":"apoli:resource","resource":"my_addon:form_snow_fox_sp_resource","comparison":">=","compare_to":20}
modifier：{"operation":"multiply_total","value":-0.5}


来源：data/my_addon/powers/form_snow_fox_sp_frost_shield.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_frost_shield_visual my_addon:form_snow_fox_sp_frost_shield_visual

能力 ID：my_addon:form_snow_fox_sp_frost_shield_visual；类型：apoli:action_over_time。

condition：{"type":"apoli:resource","resource":"my_addon:form_snow_fox_sp_resource","comparison":">=","compare_to":20}


来源：data/my_addon/powers/form_snow_fox_sp_frost_shield_visual.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_gain_cooldown my_addon:form_snow_fox_sp_gain_cooldown

能力 ID：my_addon:form_snow_fox_sp_gain_cooldown；类型：apoli:resource。

min：0
max：300
start_value：0


来源：data/my_addon/powers/form_snow_fox_sp_gain_cooldown.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_gain_cooldown_tick my_addon:form_snow_fox_sp_gain_cooldown_tick

能力 ID：my_addon:form_snow_fox_sp_gain_cooldown_tick；类型：apoli:action_over_time。



来源：data/my_addon/powers/form_snow_fox_sp_gain_cooldown_tick.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_health my_addon:form_snow_fox_sp_health

能力 ID：my_addon:form_snow_fox_sp_health；类型：apoli:attribute。

modifier：{"name":"SP Snow Fox Health","attribute":"minecraft:generic.max_health","value":-8.0,"operation":"addition"}


来源：data/my_addon/powers/form_snow_fox_sp_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_hot_weakness my_addon:form_snow_fox_sp_hot_weakness

能力 ID：my_addon:form_snow_fox_sp_hot_weakness；类型：apoli:multiple。



来源：data/my_addon/powers/form_snow_fox_sp_hot_weakness.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_init_mana my_addon:form_snow_fox_sp_init_mana

能力 ID：my_addon:form_snow_fox_sp_init_mana；类型：apoli:action_on_callback。



来源：data/my_addon/powers/form_snow_fox_sp_init_mana.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 雪刺冲刺 my_addon:form_snow_fox_sp_melee_primary

能力 ID：my_addon:form_snow_fox_sp_melee_primary；类型：my_addon:fail_aware_active_self。
向准星方向冲刺8格，碰撞敌人造成6点魔法伤害并施加霜凝效果3秒。消耗15点霜寒值，冷却6秒。
cooldown：120
condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"my_addon:form_snow_fox_sp_switch_state","comparison":"==","compare_to":0},{"type":"apoli:sneaking","inverted":true}]}


来源：data/my_addon/powers/form_snow_fox_sp_melee_primary.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 瞬移攻击 my_addon:form_snow_fox_sp_melee_secondary

能力 ID：my_addon:form_snow_fox_sp_melee_secondary；类型：my_addon:fail_aware_active_self。
瞬移到10格范围内最多3个敌人身后攻击，造成6点伤害（霜凝状态+3点魔法伤害），技能期间受伤减少65%。消耗30点霜寒值，冷却20秒。
cooldown：400
condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"my_addon:form_snow_fox_sp_switch_state","comparison":"==","compare_to":0},{"type":"apoli:sneaking","inverted":true}]}


来源：data/my_addon/powers/form_snow_fox_sp_melee_secondary.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_no_slip my_addon:form_snow_fox_sp_no_slip

能力 ID：my_addon:form_snow_fox_sp_no_slip；类型：shape-shifter-curse:conditioned_modify_slipperiness。

modifier：-0.38


来源：data/my_addon/powers/form_snow_fox_sp_no_slip.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 法术冰球 my_addon:form_snow_fox_sp_ranged_primary

能力 ID：my_addon:form_snow_fox_sp_ranged_primary；类型：my_addon:fail_aware_active_self。
发射无下坠冰球，飞行速度10b/s，最多飞行25格。命中施加霜降效果4秒（移速-30%）。消耗15点霜寒值，冷却5秒。
cooldown：100
condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"my_addon:form_snow_fox_sp_switch_state","comparison":"==","compare_to":1},{"type":"apoli:sneaking","inverted":true}]}


来源：data/my_addon/powers/form_snow_fox_sp_ranged_primary.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 冰风暴 my_addon:form_snow_fox_sp_ranged_secondary

能力 ID：my_addon:form_snow_fox_sp_ranged_secondary；类型：my_addon:fail_aware_active_self。
1.5秒蓄力后在准星位置释放冰风暴，持续10秒，每秒2点魔法伤害，吸附6格内敌人。消耗30点霜寒值，冷却30秒。
cooldown：600
condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"my_addon:form_snow_fox_sp_switch_state","comparison":"==","compare_to":1},{"type":"apoli:sneaking","inverted":true}]}


来源：data/my_addon/powers/form_snow_fox_sp_ranged_secondary.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_resource my_addon:form_snow_fox_sp_resource

能力 ID：my_addon:form_snow_fox_sp_resource；类型：apoli:resource。

min：0
max：100
start_value：0


来源：data/my_addon/powers/form_snow_fox_sp_resource.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_switch_state my_addon:form_snow_fox_sp_switch_state

能力 ID：my_addon:form_snow_fox_sp_switch_state；类型：apoli:resource。

min：0
max：1
start_value：0


来源：data/my_addon/powers/form_snow_fox_sp_switch_state.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_sp_toggle my_addon:form_snow_fox_sp_toggle

能力 ID：my_addon:form_snow_fox_sp_toggle；类型：my_addon:fail_aware_active_self。

cooldown：100
condition：{"type":"apoli:sneaking"}


来源：data/my_addon/powers/form_snow_fox_sp_toggle.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_sp_primary_cd my_addon:form_sp_primary_cd

能力 ID：my_addon:form_sp_primary_cd；类型：apoli:resource。

min：0
max：1200
start_value：0


来源：data/my_addon/powers/form_sp_primary_cd.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_sp_primary_cd_tick my_addon:form_sp_primary_cd_tick

能力 ID：my_addon:form_sp_primary_cd_tick；类型：apoli:action_over_time。

condition：{"type":"apoli:resource","resource":"my_addon:form_sp_primary_cd","comparison":">","compare_to":0}


来源：data/my_addon/powers/form_sp_primary_cd_tick.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_sp_secondary_cd my_addon:form_sp_secondary_cd

能力 ID：my_addon:form_sp_secondary_cd；类型：apoli:resource。

min：0
max：1200
start_value：0


来源：data/my_addon/powers/form_sp_secondary_cd.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_sp_secondary_cd_tick my_addon:form_sp_secondary_cd_tick

能力 ID：my_addon:form_sp_secondary_cd_tick；类型：apoli:action_over_time。

condition：{"type":"apoli:resource","resource":"my_addon:form_sp_secondary_cd","comparison":">","compare_to":0}


来源：data/my_addon/powers/form_sp_secondary_cd_tick.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 茧化猎手 my_addon:form_spider_moon_weaver_cocooned_prey

能力 ID：my_addon:form_spider_moon_weaver_cocooned_prey；类型：apoli:modify_damage_dealt。
近身攻击被你裹茧（完全无法动弹）的目标时，造成的伤害提高 15%。与攻击茧化目标回复蛛丝法力的本能相辅相成。 你对裹茧中的非白名单非玩家生物额外造成 25% 伤害，可与原有增伤叠加。
modifier：{"operation":"multiply_total","value":0.15,"name":"Moon Weaver Cocooned Prey Bonus"}


来源：data/my_addon/powers/form_spider_moon_weaver_cocooned_prey.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 月毒侵蚀 my_addon:form_spider_moon_weaver_moon_poison

能力 ID：my_addon:form_spider_moon_weaver_moon_poison；类型：apoli:simple。
被你的蛛网缠身或裹茧状态所困的敌人，将不断受到月毒侵蚀、持续中毒（中毒 I）。蜘蛛类与悦灵系免疫。


来源：data/my_addon/powers/form_spider_moon_weaver_moon_poison.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_spider_moon_weaver_swing my_addon:form_spider_moon_weaver_swing

能力 ID：my_addon:form_spider_moon_weaver_swing；类型：my_addon:fail_aware_active_self。

cooldown：100


来源：data/my_addon/powers/form_spider_moon_weaver_swing.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_spider_moon_weaver_web my_addon:form_spider_moon_weaver_web

能力 ID：my_addon:form_spider_moon_weaver_web；类型：my_addon:fail_aware_active_self。

cooldown：60


来源：data/my_addon/powers/form_spider_moon_weaver_web.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 蛛网亲和 my_addon:form_spider_moon_weaver_web_affinity

能力 ID：my_addon:form_spider_moon_weaver_web_affinity；类型：apoli:multiple。
当你站上或触碰蜘蛛网与自己所织的蛛网膜时，你的织网主场便会苏醒：移动速度 +20%，每秒回复 1 点蛛丝法力，受到的伤害减少 20%。


来源：data/my_addon/powers/form_spider_moon_weaver_web_affinity.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_spider_salticidae_health my_addon:form_spider_salticidae_health

能力 ID：my_addon:form_spider_salticidae_health；类型：apoli:attribute。

modifier：{"name":"Form spider salticidae health","attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/my_addon/powers/form_spider_salticidae_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_spider_salticidae_jump_high my_addon:form_spider_salticidae_jump_high

能力 ID：my_addon:form_spider_salticidae_jump_high；类型：apoli:modify_jump。

modifier：{"operation":"multiply_total","value":0.65}


来源：data/my_addon/powers/form_spider_salticidae_jump_high.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_spider_salticidae_jump_kill my_addon:form_spider_salticidae_jump_kill

能力 ID：my_addon:form_spider_salticidae_jump_kill；类型：my_addon:fail_aware_active_self。

cooldown：240


来源：data/my_addon/powers/form_spider_salticidae_jump_kill.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 掠食者之力 my_addon:form_spider_salticidae_melee_up

能力 ID：my_addon:form_spider_salticidae_melee_up；类型：apoli:modify_damage_dealt。
左键近战攻击造成的伤害提高 2 点。
modifier：{"operation":"addition","value":2.0}


来源：data/my_addon/powers/form_spider_salticidae_melee_up.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_spider_salticidae_venom my_addon:form_spider_salticidae_venom

能力 ID：my_addon:form_spider_salticidae_venom；类型：my_addon:fail_aware_active_self。

cooldown：200


来源：data/my_addon/powers/form_spider_salticidae_venom.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 毒牙 my_addon:form_spider_salticidae_venom_fang

能力 ID：my_addon:form_spider_salticidae_venom_fang；类型：apoli:action_on_hit。
左键近战攻击命中生物时，注入毒液使其中毒 I 5秒（白名单内个体免疫；装备毒液腺体后变为中毒 II 3秒）。


来源：data/my_addon/powers/form_spider_salticidae_venom_fang.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_spider_salticidae_web_sense_off my_addon:form_spider_salticidae_web_sense_off

能力 ID：my_addon:form_spider_salticidae_web_sense_off；类型：apoli:multiple。



来源：data/my_addon/powers/form_spider_salticidae_web_sense_off.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_sprint_step_sound_1_5_run_0_9 my_addon:form_sprint_step_sound_1_5_run_0_9

能力 ID：my_addon:form_sprint_step_sound_1_5_run_0_9；类型：shape-shifter-curse:modify_footstep_sound_speed。



来源：data/my_addon/powers/form_sprint_step_sound_1_5_run_0_9.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_dash_hud my_addon:form_upgrade_axolotl_dash_hud

能力 ID：my_addon:form_upgrade_axolotl_dash_hud；类型：apoli:multiple。



来源：data/my_addon/powers/form_upgrade_axolotl_dash_hud.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Dehydration my_addon:form_upgrade_axolotl_dehydration

能力 ID：my_addon:form_upgrade_axolotl_dehydration；类型：apoli:action_over_time。
When moisture (air) is depleted, take 2 damage per second.
condition：{"type":"apoli:air","comparison":"<=","compare_to":0}


来源：data/my_addon/powers/form_upgrade_axolotl_dehydration.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_health my_addon:form_upgrade_axolotl_health

能力 ID：my_addon:form_upgrade_axolotl_health；类型：apoli:attribute。

modifier：{"name":"Form upgrade axolotl health","attribute":"minecraft:generic.max_health","value":2.0,"operation":"addition"}


来源：data/my_addon/powers/form_upgrade_axolotl_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_jump_out_water my_addon:form_upgrade_axolotl_jump_out_water

能力 ID：my_addon:form_upgrade_axolotl_jump_out_water；类型：apoli:active_self。

cooldown：2
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":">","compare_to":0},{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<","compare_to":0.2}]}


来源：data/my_addon/powers/form_upgrade_axolotl_jump_out_water.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Moisture Drain my_addon:form_upgrade_axolotl_moisture_drain

能力 ID：my_addon:form_upgrade_axolotl_moisture_drain；类型：apoli:action_over_time。
On the ground, lose 1% moisture (air) per second. Moisture drops cause dehydration damage.
condition：{"type":"apoli:and","conditions":[{"type":"apoli:on_block"},{"type":"apoli:submerged_in","fluid":"water","inverted":true},{"type":"apoli:in_rain","inverted":true}]}


来源：data/my_addon/powers/form_upgrade_axolotl_moisture_drain.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Extra Moisture Drain my_addon:form_upgrade_axolotl_moisture_extra_drain

能力 ID：my_addon:form_upgrade_axolotl_moisture_extra_drain；类型：apoli:action_over_time。
Before unlocking Become Water: extra 0.5x moisture drain on land (total 1.5x).
condition：{"type":"apoli:and","conditions":[{"type":"apoli:on_block"},{"type":"apoli:submerged_in","fluid":"water","inverted":true},{"type":"apoli:in_rain","inverted":true},{"type":"ssc_addon:has_talent","talent_id":"become_water","inverted":true}]}


来源：data/my_addon/powers/form_upgrade_axolotl_moisture_extra_drain.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Rain Hydration my_addon:form_upgrade_axolotl_rain_wetness

能力 ID：my_addon:form_upgrade_axolotl_rain_wetness；类型：apoli:action_over_time。
Slowly regain moisture (air) when exposed to rain.
condition：{"type":"apoli:and","conditions":[{"type":"apoli:in_rain"},{"type":"apoli:exposed_to_sky"}]}


来源：data/my_addon/powers/form_upgrade_axolotl_rain_wetness.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_scale my_addon:form_upgrade_axolotl_scale

能力 ID：my_addon:form_upgrade_axolotl_scale；类型：shape-shifter-curse:scale。



来源：data/my_addon/powers/form_upgrade_axolotl_scale.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_sprint_jump my_addon:form_upgrade_axolotl_sprint_jump

能力 ID：my_addon:form_upgrade_axolotl_sprint_jump；类型：shape-shifter-curse:action_on_jump。



来源：data/my_addon/powers/form_upgrade_axolotl_sprint_jump.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_sprint_speed my_addon:form_upgrade_axolotl_sprint_speed

能力 ID：my_addon:form_upgrade_axolotl_sprint_speed；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:sprinting"},{"type":"apoli:air","comparison":">","compare_to":0},{"type":"ssc_addon:has_talent","talent_id":"water_propel"}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":0.65}


来源：data/my_addon/powers/form_upgrade_axolotl_sprint_speed.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_swim_speed my_addon:form_upgrade_axolotl_swim_speed

能力 ID：my_addon:form_upgrade_axolotl_swim_speed；类型：apoli:conditioned_attribute。

tick_rate：5
condition：{"type":"ssc_addon:has_talent","talent_id":"aquatic_adapt"}
modifier：{"attribute":"additionalentityattributes:generic.water_speed","name":"Additional swim speed","value":1.5,"operation":"multiply_base"}


来源：data/my_addon/powers/form_upgrade_axolotl_swim_speed.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_vortex_guide my_addon:form_upgrade_axolotl_vortex_guide

能力 ID：my_addon:form_upgrade_axolotl_vortex_guide；类型：my_addon:fail_aware_active_self。

cooldown：160


来源：data/my_addon/powers/form_upgrade_axolotl_vortex_guide.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_water_spear my_addon:form_upgrade_axolotl_water_spear

能力 ID：my_addon:form_upgrade_axolotl_water_spear；类型：my_addon:fail_aware_active_self。

cooldown：160


来源：data/my_addon/powers/form_upgrade_axolotl_water_spear.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_axolotl_water_vision my_addon:form_upgrade_axolotl_water_vision

能力 ID：my_addon:form_upgrade_axolotl_water_vision；类型：apoli:night_vision。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:submerged_in","fluid":"minecraft:water"},{"type":"ssc_addon:has_talent","talent_id":"aquatic_adapt"}]}


来源：data/my_addon/powers/form_upgrade_axolotl_water_vision.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_absorb my_addon:form_upgrade_familiar_fox_absorb

能力 ID：my_addon:form_upgrade_familiar_fox_absorb；类型：apoli:action_on_entity_use。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_use_entity_cooldown","comparison":"<","compare_to":1},{"type":"ssc_addon:has_talent","talent_id":"mana_system"}]}


来源：data/my_addon/powers/form_upgrade_familiar_fox_absorb.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_alchemy_effect_immunity my_addon:form_upgrade_familiar_fox_alchemy_effect_immunity

能力 ID：my_addon:form_upgrade_familiar_fox_alchemy_effect_immunity；类型：shape-shifter-curse:optional_effect_immunity。

condition：{"type":"ssc_addon:has_talent","talent_id":"alchemy"}


来源：data/my_addon/powers/form_upgrade_familiar_fox_alchemy_effect_immunity.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_alchemy_mana_boost my_addon:form_upgrade_familiar_fox_alchemy_mana_boost

能力 ID：my_addon:form_upgrade_familiar_fox_alchemy_mana_boost；类型：ssc_addon:conditional_mana_attribute。

condition：{"type":"ssc_addon:has_talent","talent_id":"alchemy"}


来源：data/my_addon/powers/form_upgrade_familiar_fox_alchemy_mana_boost.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_buff_immunity my_addon:form_upgrade_familiar_fox_buff_immunity

能力 ID：my_addon:form_upgrade_familiar_fox_buff_immunity；类型：apoli:multiple。



来源：data/my_addon/powers/form_upgrade_familiar_fox_buff_immunity.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_charm_paper_fireball my_addon:form_upgrade_familiar_fox_charm_paper_fireball

能力 ID：my_addon:form_upgrade_familiar_fox_charm_paper_fireball；类型：apoli:action_on_item_use。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fire_arrow_cooldown","comparison":"<","compare_to":1},{"type":"shape-shifter-curse:has_mana","mana":2.5},{"type":"ssc_addon:has_talent","talent_id":"rocket"}]}


来源：data/my_addon/powers/form_upgrade_familiar_fox_charm_paper_fireball.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_charm_paper_fireball_damage my_addon:form_upgrade_familiar_fox_charm_paper_fireball_damage

能力 ID：my_addon:form_upgrade_familiar_fox_charm_paper_fireball_damage；类型：apoli:modify_projectile_damage。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fireball_kind_boolean_resource","comparison":"==","compare_to":1},{"type":"ssc_addon:has_talent","talent_id":"rocket"}]}
modifier：{"operation":"set_total","value":9.0}


来源：data/my_addon/powers/form_upgrade_familiar_fox_charm_paper_fireball_damage.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_charm_paper_fireball_npc_damage my_addon:form_upgrade_familiar_fox_charm_paper_fireball_npc_damage

能力 ID：my_addon:form_upgrade_familiar_fox_charm_paper_fireball_npc_damage；类型：apoli:modify_projectile_damage。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fireball_kind_boolean_resource","comparison":"==","compare_to":1},{"type":"ssc_addon:has_talent","talent_id":"rocket"}]}
modifier：{"operation":"set_total","value":18.0}


来源：data/my_addon/powers/form_upgrade_familiar_fox_charm_paper_fireball_npc_damage.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_craft_harming_potion my_addon:form_upgrade_familiar_fox_craft_harming_potion

能力 ID：my_addon:form_upgrade_familiar_fox_craft_harming_potion；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5},{"type":"ssc_addon:has_talent","talent_id":"alchemy"}]}


来源：data/my_addon/powers/form_upgrade_familiar_fox_craft_harming_potion.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_craft_healing_potion my_addon:form_upgrade_familiar_fox_craft_healing_potion

能力 ID：my_addon:form_upgrade_familiar_fox_craft_healing_potion；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5},{"type":"ssc_addon:has_talent","talent_id":"alchemy"}]}


来源：data/my_addon/powers/form_upgrade_familiar_fox_craft_healing_potion.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_craft_poison_potion my_addon:form_upgrade_familiar_fox_craft_poison_potion

能力 ID：my_addon:form_upgrade_familiar_fox_craft_poison_potion；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5},{"type":"ssc_addon:has_talent","talent_id":"alchemy"}]}


来源：data/my_addon/powers/form_upgrade_familiar_fox_craft_poison_potion.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 火环 my_addon:form_upgrade_familiar_fox_fire_ring

能力 ID：my_addon:form_upgrade_familiar_fox_fire_ring；类型：my_addon:fail_aware_active_self。
消耗11点法力，以自身为中心释放火环（半径约4格），对非白名单生物造成8点伤害并点燃10秒。需解锁火环节点；解锁后取代火花技能。
cooldown：40
condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:has_talent","talent_id":"fire_ring"},{"type":"shape-shifter-curse:has_mana","mana":11}]}


来源：data/my_addon/powers/form_upgrade_familiar_fox_fire_ring.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_fire_ring_mana_boost my_addon:form_upgrade_familiar_fox_fire_ring_mana_boost

能力 ID：my_addon:form_upgrade_familiar_fox_fire_ring_mana_boost；类型：ssc_addon:conditional_mana_attribute。

condition：{"type":"ssc_addon:has_talent","talent_id":"fire_ring"}


来源：data/my_addon/powers/form_upgrade_familiar_fox_fire_ring_mana_boost.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_fire_ring_particle my_addon:form_upgrade_familiar_fox_fire_ring_particle

能力 ID：my_addon:form_upgrade_familiar_fox_fire_ring_particle；类型：apoli:particle。

condition：{"type":"ssc_addon:has_talent","talent_id":"fire_ring"}


来源：data/my_addon/powers/form_upgrade_familiar_fox_fire_ring_particle.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_mana_regen_pause my_addon:form_upgrade_familiar_fox_mana_regen_pause

能力 ID：my_addon:form_upgrade_familiar_fox_mana_regen_pause；类型：apoli:multiple。



来源：data/my_addon/powers/form_upgrade_familiar_fox_mana_regen_pause.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_mana_shield my_addon:form_upgrade_familiar_fox_mana_shield

能力 ID：my_addon:form_upgrade_familiar_fox_mana_shield；类型：apoli:modify_damage_taken。

condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:has_talent","talent_id":"mana_system"},{"type":"ssc_addon:has_mana_percent_safe","mana_percent":0.5,"comparison":">="}]}
modifier：{"operation":"multiply_total_multiplicative","value":-0.15}


来源：data/my_addon/powers/form_upgrade_familiar_fox_mana_shield.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_mana_system my_addon:form_upgrade_familiar_fox_mana_system

能力 ID：my_addon:form_upgrade_familiar_fox_mana_system；类型：apoli:multiple。



来源：data/my_addon/powers/form_upgrade_familiar_fox_mana_system.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_rocket_mana_boost my_addon:form_upgrade_familiar_fox_rocket_mana_boost

能力 ID：my_addon:form_upgrade_familiar_fox_rocket_mana_boost；类型：ssc_addon:conditional_mana_attribute。

condition：{"type":"ssc_addon:has_talent","talent_id":"rocket"}


来源：data/my_addon/powers/form_upgrade_familiar_fox_rocket_mana_boost.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_scale my_addon:form_upgrade_familiar_fox_scale

能力 ID：my_addon:form_upgrade_familiar_fox_scale；类型：shape-shifter-curse:scale。



来源：data/my_addon/powers/form_upgrade_familiar_fox_scale.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 火花 my_addon:form_upgrade_familiar_fox_spark

能力 ID：my_addon:form_upgrade_familiar_fox_spark；类型：my_addon:fail_aware_active_self。
消耗11点法力，以自身为中心释放削弱版火环（半径约3格），对非白名单生物造成4点伤害并点燃5秒。需解锁火花节点。
cooldown：40
condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:has_talent","talent_id":"spark"},{"type":"ssc_addon:has_talent","talent_id":"fire_ring","inverted":true},{"type":"shape-shifter-curse:has_mana","mana":11}]}


来源：data/my_addon/powers/form_upgrade_familiar_fox_spark.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_spark_mana_boost my_addon:form_upgrade_familiar_fox_spark_mana_boost

能力 ID：my_addon:form_upgrade_familiar_fox_spark_mana_boost；类型：ssc_addon:conditional_mana_attribute。

condition：{"type":"ssc_addon:has_talent","talent_id":"spark"}


来源：data/my_addon/powers/form_upgrade_familiar_fox_spark_mana_boost.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_spirit_vision my_addon:form_upgrade_familiar_fox_spirit_vision

能力 ID：my_addon:form_upgrade_familiar_fox_spirit_vision；类型：apoli:entity_glow。

condition：{"type":"ssc_addon:has_talent","talent_id":"spirit_vision"}


来源：data/my_addon/powers/form_upgrade_familiar_fox_spirit_vision.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_spirit_vision_mana_boost my_addon:form_upgrade_familiar_fox_spirit_vision_mana_boost

能力 ID：my_addon:form_upgrade_familiar_fox_spirit_vision_mana_boost；类型：ssc_addon:conditional_mana_attribute。

condition：{"type":"ssc_addon:has_talent","talent_id":"spirit_vision"}


来源：data/my_addon/powers/form_upgrade_familiar_fox_spirit_vision_mana_boost.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_stick_fireball my_addon:form_upgrade_familiar_fox_stick_fireball

能力 ID：my_addon:form_upgrade_familiar_fox_stick_fireball；类型：apoli:action_on_item_use。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fire_arrow_cooldown","comparison":"<","compare_to":1},{"type":"shape-shifter-curse:has_mana","mana":2.5},{"type":"ssc_addon:has_talent","talent_id":"rocket"}]}


来源：data/my_addon/powers/form_upgrade_familiar_fox_stick_fireball.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_stick_fireball_damage my_addon:form_upgrade_familiar_fox_stick_fireball_damage

能力 ID：my_addon:form_upgrade_familiar_fox_stick_fireball_damage；类型：apoli:modify_projectile_damage。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fireball_kind_boolean_resource","comparison":"==","compare_to":0},{"type":"ssc_addon:has_talent","talent_id":"rocket"}]}
modifier：{"operation":"set_total","value":5.0}


来源：data/my_addon/powers/form_upgrade_familiar_fox_stick_fireball_damage.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## form_upgrade_familiar_fox_stick_fireball_npc_damage my_addon:form_upgrade_familiar_fox_stick_fireball_npc_damage

能力 ID：my_addon:form_upgrade_familiar_fox_stick_fireball_npc_damage；类型：apoli:modify_projectile_damage。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fireball_kind_boolean_resource","comparison":"==","compare_to":0},{"type":"ssc_addon:has_talent","talent_id":"rocket"}]}
modifier：{"operation":"set_total","value":10.0}


来源：data/my_addon/powers/form_upgrade_familiar_fox_stick_fireball_npc_damage.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 诅咒之月共鸣 my_addon:form_wild_cat_nightmare_cursed_moon_resonance

能力 ID：my_addon:form_wild_cat_nightmare_cursed_moon_resonance；类型：apoli:multiple。
你诞生于诅咒之月的魔力，与那轮紫月同频共振：诅咒之月当夜，触发入梦所需的累计伤害从10点降为6点，「恐惧」的冷却从20秒缩短为14秒。


来源：data/my_addon/powers/form_wild_cat_nightmare_cursed_moon_resonance.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 噬梦 my_addon:form_wild_cat_nightmare_dream_devour

能力 ID：my_addon:form_wild_cat_nightmare_dream_devour；类型：apoli:multiple。
你从猎物的梦中汲取滋养：对已入梦目标造成伤害时，将其中的15%转化为自身生命；若目标在梦中死去，你还会吞噬它最后的残梦，额外回复4点生命。


来源：data/my_addon/powers/form_wild_cat_nightmare_dream_devour.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 恐惧 my_addon:form_wild_cat_nightmare_fear

能力 ID：my_addon:form_wild_cat_nightmare_fear；类型：my_addon:fail_aware_active_self。
对已入梦的敌人降下恐惧：其视野被粉色雾气球壳压缩至16格，行动迟缓20%，耳畔萦绕守护者般的心跳——梦魇不在视野内时心跳微弱，靠得越近心跳越响。恐惧期间入梦时间被锁定，首次受到食梦魔或其白名单成员的伤害将翻倍；梦魇在16格外对其完全隐身，进入16格后以每2秒隐匿、0.6秒现形的脉冲闪现，而梦魇的攻击会令其现形1.5秒。对陷入恐惧的生物，它们看不见降下恐惧的梦魔本人，无法对其产生仇恨，被其攻击后仅有2秒的反击之力，随后仇恨彻底消散。恐惧结束后目标强制苏醒，并在20秒内无法再次入梦。冷却20秒。
cooldown：400
condition：{"type":"apoli:sneaking","inverted":true}


来源：data/my_addon/powers/form_wild_cat_nightmare_fear.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 幻翼亲和 my_addon:form_wild_cat_nightmare_phantom_affinity

能力 ID：my_addon:form_wild_cat_nightmare_phantom_affinity；类型：apoli:multiple。
幻翼是失眠者梦魇滋生的造物，它们视你为梦境边缘的同类，绝不会主动将你列为猎物——即使受到你的攻击，也只是远远退开。


来源：data/my_addon/powers/form_wild_cat_nightmare_phantom_affinity.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 惊吓 my_addon:form_wild_cat_nightmare_spook

能力 ID：my_addon:form_wild_cat_nightmare_spook；类型：my_addon:fail_aware_active_self。
在已入梦敌人身后悄悄响起苦力怕的引信声，一只只有它能看见的幽灵苦力怕正在逼近，3秒后原地炸裂（无实伤）。若它忍不住回头攻击那只苦力怕，消散的烟雾中将窜出一只幽灵野猫，哈看低吼着扑向它的脸狠狠一击（12点魔法伤害），随后化作烟花散去，而这一切只有它自己看得见。对已入梦的生物，无形的幽灵野猫会直接撕咬一口（12点魔法伤害），并将其入梦时间重置为20秒。冷却8秒。
cooldown：160
condition：{"type":"apoli:sneaking","inverted":true}


来源：data/my_addon/powers/form_wild_cat_nightmare_spook.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 真实隐身 my_addon:true_invisibility

能力 ID：my_addon:true_invisibility；类型：my_addon:true_invisibility。
按下后进入真实隐身状态，期间所有生物均无法看见你。隐身期间攻击、挖掘或使用物品会破隐（玻璃碎裂声）。再次按键主动解除（猫哈气声）。
cooldown：240


来源：data/my_addon/powers/true_invisibility.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 震慑冲刺 my_addon:true_invisibility_dash

能力 ID：my_addon:true_invisibility_dash；类型：my_addon:true_invisibility_dash。
隐身时按下次要技能键，破隐并向前冲刺5格。落地时震慑周围5格内敌人5秒。
cooldown：240


来源：data/my_addon/powers/true_invisibility_dash.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Feral Diet my_addon:wild_cat_sp_diet

能力 ID：my_addon:wild_cat_sp_diet；类型：apoli:multiple。
Raw meat provides 100% more nourishment.


来源：data/my_addon/powers/wild_cat_sp_diet.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## wild_cat_sp_health my_addon:wild_cat_sp_health

能力 ID：my_addon:wild_cat_sp_health；类型：apoli:attribute。

modifier：{"name":"SP Wild Cat Health","attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/my_addon/powers/wild_cat_sp_health.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Ink Blindness my_addon:wild_cat_sp_ink_blind

能力 ID：my_addon:wild_cat_sp_ink_blind；类型：apoli:active_self。
Right click with Ink Sac (Main Hand) to blind nearby creatures.
cooldown：80
condition：{"type":"apoli:equipped_item","equipment_slot":"mainhand","item_condition":{"type":"apoli:ingredient","ingredient":{"item":"minecraft:ink_sac"}}}


来源：data/my_addon/powers/wild_cat_sp_ink_blind.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## 保命猫尾 my_addon:wild_cat_sp_lifesaving_tail

能力 ID：my_addon:wild_cat_sp_lifesaving_tail；类型：apoli:prevent_death。
其它猫身上的尾巴，或许能阻挡死亡
cooldown：1
condition：{"type":"apoli:and","conditions":[{"type":"ssc_addon:has_trinket","item":"ssc_addon:lifesaving_cat_tail"},{"type":"ssc_addon:item_on_cooldown","item":"ssc_addon:lifesaving_cat_tail","inverted":true},{"type":"apoli:equipped_item","equipment_slot":"mainhand","item_condition":{"type":"apoli:ingredient","ingredient":{"item":"minecraft:totem_of_undying"}},"inverted":true},{"type":"apoli:equipped_item","equipment_slot":"offhand","item_condition":{"type":"apoli:ingredient","ingredient":{"item":"minecraft:totem_of_undying"}},"inverted":true}]}


来源：data/my_addon/powers/wild_cat_sp_lifesaving_tail.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Night Force my_addon:wild_cat_sp_night_strength

能力 ID：my_addon:wild_cat_sp_night_strength；类型：apoli:modify_damage_dealt。
You deal more damage at night. (Note: This bonus applies on hit and may not appear in the inventory).
condition：{"type":"apoli:and","conditions":[{"type":"apoli:time_of_day","compare_to":13000,"comparison":">="},{"type":"apoli:time_of_day","compare_to":23000,"comparison":"<="}]}
modifier：{"operation":"addition","value":1.0,"name":"Wild Cat SP Night Strength"}


来源：data/my_addon/powers/wild_cat_sp_night_strength.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## Permanent Night Vision my_addon:wild_cat_sp_night_vision

能力 ID：my_addon:wild_cat_sp_night_vision；类型：apoli:night_vision。
You have permanent night vision.


来源：data/my_addon/powers/wild_cat_sp_night_vision.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## wild_cat_sp_vf_jump_boost my_addon:wild_cat_sp_vf_jump_boost

能力 ID：my_addon:wild_cat_sp_vf_jump_boost；类型：apoli:multiple。



来源：data/my_addon/powers/wild_cat_sp_vf_jump_boost.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## wild_cat_sp_vf_speed_up my_addon:wild_cat_sp_vf_speed_up

能力 ID：my_addon:wild_cat_sp_vf_speed_up；类型：apoli:multiple。



来源：data/my_addon/powers/wild_cat_sp_vf_speed_up.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。

## beast_form_tag shape-shifter-curse:beast_form_tag

能力 ID：shape-shifter-curse:beast_form_tag；类型：apoli:action_on_callback。



来源：data/shape-shifter-curse/powers/beast_form_tag.json；SSCA 8.0.0-magic-dev.4 源码 c11c69ece681，SSC 1.10.0。数值是出厂默认，服务器配置与数据包优先。
