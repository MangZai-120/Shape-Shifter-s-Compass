<!-- compass: mod=shape-shifter-curse version=1.10.0 -->

## air_from_potions shape-shifter-curse:air_from_potions

能力 ID：shape-shifter-curse:air_from_potions；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/air_from_potions.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## air_from_splash_potions shape-shifter-curse:air_from_splash_potions

能力 ID：shape-shifter-curse:air_from_splash_potions；类型：shape-shifter-curse:action_on_splash_potion_take_effect。



来源：data/shape-shifter-curse/powers/air_from_splash_potions.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## always_harvest shape-shifter-curse:always_harvest

能力 ID：shape-shifter-curse:always_harvest；类型：apoli:modify_harvest。



来源：data/shape-shifter-curse/powers/always_harvest.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## aqua_affinity shape-shifter-curse:aqua_affinity

能力 ID：shape-shifter-curse:aqua_affinity；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/aqua_affinity.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## aquatic shape-shifter-curse:aquatic

能力 ID：shape-shifter-curse:aquatic；类型：apoli:entity_group。



来源：data/shape-shifter-curse/powers/aquatic.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## barehand_attack_up shape-shifter-curse:barehand_attack_up

能力 ID：shape-shifter-curse:barehand_attack_up；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:or","conditions":[{"type":"apoli:inventory","process_mode":"items","item_condition":{"type":"shape-shifter-curse:is_weapon"},"slots":["weapon.mainhand"],"inverted":true},{"type":"apoli:inventory","process_mode":"items","comparison":"==","compare_to":0,"slots":["weapon.mainhand"]}]}
modifier：{"attribute":"minecraft:generic.attack_damage","name":"Extra Attack Damage","operation":"addition","value":7.0}


来源：data/shape-shifter-curse/powers/barehand_attack_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## barehand_digging_speed_up shape-shifter-curse:barehand_digging_speed_up

能力 ID：shape-shifter-curse:barehand_digging_speed_up；类型：apoli:modify_break_speed。

condition：{"type":"shape-shifter-curse:can_barehand_digging"}
modifier：{"operation":"multiply_base","value":6.5}


来源：data/shape-shifter-curse/powers/barehand_digging_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## bat_vision shape-shifter-curse:bat_vision

能力 ID：shape-shifter-curse:bat_vision；类型：apoli:night_vision。

condition：{"type":"apoli:submerged_in","fluid":"minecraft:water","inverted":true}


来源：data/shape-shifter-curse/powers/bat_vision.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## breathing_under_water shape-shifter-curse:breathing_under_water

能力 ID：shape-shifter-curse:breathing_under_water；类型：shape-shifter-curse:breathing_under_water。



来源：data/shape-shifter-curse/powers/breathing_under_water.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## bypass_landing_effect shape-shifter-curse:bypass_landing_effect

能力 ID：shape-shifter-curse:bypass_landing_effect；类型：shape-shifter-curse:bypass_landing_effect。



来源：data/shape-shifter-curse/powers/bypass_landing_effect.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## can_eat_amethyst_shard shape-shifter-curse:can_eat_amethyst_shard

能力 ID：shape-shifter-curse:can_eat_amethyst_shard；类型：shape-shifter-curse:custom_edible。

saturation_modifier：0.3
always_edible：true


来源：data/shape-shifter-curse/powers/can_eat_amethyst_shard.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## can_eat_string shape-shifter-curse:can_eat_string

能力 ID：shape-shifter-curse:can_eat_string；类型：shape-shifter-curse:custom_edible。

saturation_modifier：0.3
always_edible：true


来源：data/shape-shifter-curse/powers/can_eat_string.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## can_loot_spider_fluid_cocoon shape-shifter-curse:can_loot_spider_fluid_cocoon

能力 ID：shape-shifter-curse:can_loot_spider_fluid_cocoon；类型：apoli:simple。



来源：data/shape-shifter-curse/powers/can_loot_spider_fluid_cocoon.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## cat_friendly shape-shifter-curse:cat_friendly

能力 ID：shape-shifter-curse:cat_friendly；类型：apoli:simple。



来源：data/shape-shifter-curse/powers/cat_friendly.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## cat_vision shape-shifter-curse:cat_vision

能力 ID：shape-shifter-curse:cat_vision；类型：apoli:night_vision。

condition：{"type":"apoli:submerged_in","fluid":"minecraft:water","inverted":true}


来源：data/shape-shifter-curse/powers/cat_vision.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## damage_up shape-shifter-curse:damage_up

能力 ID：shape-shifter-curse:damage_up；类型：apoli:modify_damage_dealt。

modifier：{"name":"Extra damage when submerged","operation":"addition","value":6.0}


来源：data/shape-shifter-curse/powers/damage_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## disable_player_rotation shape-shifter-curse:disable_player_rotation

能力 ID：shape-shifter-curse:disable_player_rotation；类型：shape-shifter-curse:disable_player_rotation。



来源：data/shape-shifter-curse/powers/disable_player_rotation.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## drop_tool_after_digging shape-shifter-curse:drop_tool_after_digging

能力 ID：shape-shifter-curse:drop_tool_after_digging；类型：apoli:action_on_block_break。



来源：data/shape-shifter-curse/powers/drop_tool_after_digging.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## drop_weapon_after_hit shape-shifter-curse:drop_weapon_after_hit

能力 ID：shape-shifter-curse:drop_weapon_after_hit；类型：apoli:self_action_on_hit。



来源：data/shape-shifter-curse/powers/drop_weapon_after_hit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## elytra_no_render shape-shifter-curse:elytra_no_render

能力 ID：shape-shifter-curse:elytra_no_render；类型：apoli:elytra_flight。



来源：data/shape-shifter-curse/powers/elytra_no_render.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## fall_immunity shape-shifter-curse:fall_immunity

能力 ID：shape-shifter-curse:fall_immunity；类型：apoli:invulnerability。



来源：data/shape-shifter-curse/powers/fall_immunity.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## feral_form_step_sound_speed_up shape-shifter-curse:feral_form_step_sound_speed_up

能力 ID：shape-shifter-curse:feral_form_step_sound_speed_up；类型：shape-shifter-curse:modify_footstep_sound_speed。



来源：data/shape-shifter-curse/powers/feral_form_step_sound_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## fire_and_lava_immunity shape-shifter-curse:fire_and_lava_immunity

能力 ID：shape-shifter-curse:fire_and_lava_immunity；类型：apoli:fire_immunity。



来源：data/shape-shifter-curse/powers/fire_and_lava_immunity.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_all_food_down shape-shifter-curse:form_all_food_down

能力 ID：shape-shifter-curse:form_all_food_down；类型：apoli:modify_food。

food_modifier：{"name":"Decreased food points","operation":"multiply_base_multiplicative","value":-0.2}
saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-0.15}


来源：data/shape-shifter-curse/powers/form_all_food_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_all_food_down2 shape-shifter-curse:form_all_food_down2

能力 ID：shape-shifter-curse:form_all_food_down2；类型：apoli:modify_food。

food_modifier：{"name":"Decreased food points","operation":"multiply_base_multiplicative","value":-0.35}
saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-0.3}


来源：data/shape-shifter-curse/powers/form_all_food_down2.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_all_food_down3 shape-shifter-curse:form_all_food_down3

能力 ID：shape-shifter-curse:form_all_food_down3；类型：apoli:modify_food。

food_modifier：{"name":"Decreased food points","operation":"multiply_base_multiplicative","value":-0.5}
saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-0.5}


来源：data/shape-shifter-curse/powers/form_all_food_down3.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_amethyst_shard_from_block shape-shifter-curse:form_allay_sp_amethyst_shard_from_block

能力 ID：shape-shifter-curse:form_allay_sp_amethyst_shard_from_block；类型：apoli:item_on_item。



来源：data/shape-shifter-curse/powers/form_allay_sp_amethyst_shard_from_block.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## 水晶食性 shape-shifter-curse:form_allay_sp_amethyst_shard_only

能力 ID：shape-shifter-curse:form_allay_sp_amethyst_shard_only；类型：apoli:prevent_item_use。



来源：data/shape-shifter-curse/powers/form_allay_sp_amethyst_shard_only.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_attack_heal shape-shifter-curse:form_allay_sp_attack_heal

能力 ID：shape-shifter-curse:form_allay_sp_attack_heal；类型：apoli:self_action_on_hit。

cooldown：10


来源：data/shape-shifter-curse/powers/form_allay_sp_attack_heal.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_digging_speed_down shape-shifter-curse:form_allay_sp_digging_speed_down

能力 ID：shape-shifter-curse:form_allay_sp_digging_speed_down；类型：apoli:modify_break_speed。

modifier：{"operation":"multiply_base","value":-0.45}


来源：data/shape-shifter-curse/powers/form_allay_sp_digging_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_fly_hunger shape-shifter-curse:form_allay_sp_fly_hunger

能力 ID：shape-shifter-curse:form_allay_sp_fly_hunger；类型：apoli:exhaust。

condition：{"type":"apoli:power_active","power":"shape-shifter-curse:form_allay_sp_levitate"}


来源：data/shape-shifter-curse/powers/form_allay_sp_fly_hunger.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_health shape-shifter-curse:form_allay_sp_health

能力 ID：shape-shifter-curse:form_allay_sp_health；类型：apoli:attribute。

modifier：{"name":"Form ocelot0 health reduction","attribute":"minecraft:generic.max_health","value":-10.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_allay_sp_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_jukebox_heal shape-shifter-curse:form_allay_sp_jukebox_heal

能力 ID：shape-shifter-curse:form_allay_sp_jukebox_heal；类型：apoli:action_over_time。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:and","conditions":[{"type":"apoli:block","block":"minecraft:jukebox"},{"type":"apoli:block_state","property":"has_record","value":true}]},"radius":3,"shape":"cube","comparison":">=","compare_to":1}


来源：data/shape-shifter-curse/powers/form_allay_sp_jukebox_heal.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_levitate shape-shifter-curse:form_allay_sp_levitate

能力 ID：shape-shifter-curse:form_allay_sp_levitate；类型：shape-shifter-curse:levitate。



来源：data/shape-shifter-curse/powers/form_allay_sp_levitate.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_levitate_speed_up shape-shifter-curse:form_allay_sp_levitate_speed_up

能力 ID：shape-shifter-curse:form_allay_sp_levitate_speed_up；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:power_active","power":"shape-shifter-curse:form_allay_sp_levitate"}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":0.16,"name":"Inc speed when flying"}


来源：data/shape-shifter-curse/powers/form_allay_sp_levitate_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_melee_damage_down shape-shifter-curse:form_allay_sp_melee_damage_down

能力 ID：shape-shifter-curse:form_allay_sp_melee_damage_down；类型：apoli:modify_damage_dealt。

modifier：{"operation":"multiply_total","value":-0.33}


来源：data/shape-shifter-curse/powers/form_allay_sp_melee_damage_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_mob_glow_in_range shape-shifter-curse:form_allay_sp_mob_glow_in_range

能力 ID：shape-shifter-curse:form_allay_sp_mob_glow_in_range；类型：apoli:entity_glow。



来源：data/shape-shifter-curse/powers/form_allay_sp_mob_glow_in_range.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_prevent_digging_when_fly shape-shifter-curse:form_allay_sp_prevent_digging_when_fly

能力 ID：shape-shifter-curse:form_allay_sp_prevent_digging_when_fly；类型：apoli:modify_break_speed。

condition：{"type":"apoli:creative_flying"}
modifier：{"operation":"multiply_base","value":-0.8}


来源：data/shape-shifter-curse/powers/form_allay_sp_prevent_digging_when_fly.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_projectile_damage_up shape-shifter-curse:form_allay_sp_projectile_damage_up

能力 ID：shape-shifter-curse:form_allay_sp_projectile_damage_up；类型：apoli:modify_projectile_damage。

modifier：{"operation":"addition","value":6.0}


来源：data/shape-shifter-curse/powers/form_allay_sp_projectile_damage_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_slipperiness shape-shifter-curse:form_allay_sp_slipperiness

能力 ID：shape-shifter-curse:form_allay_sp_slipperiness；类型：apoli:modify_slipperiness。

modifier：{"operation":"multiply_total","value":0.3}


来源：data/shape-shifter-curse/powers/form_allay_sp_slipperiness.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_sound shape-shifter-curse:form_allay_sp_sound

能力 ID：shape-shifter-curse:form_allay_sp_sound；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:enable_random_sound"},{"type":"shape-shifter-curse:chance","chance":0.3}]}


来源：data/shape-shifter-curse/powers/form_allay_sp_sound.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_sound_hurt shape-shifter-curse:form_allay_sp_sound_hurt

能力 ID：shape-shifter-curse:form_allay_sp_sound_hurt；类型：apoli:self_action_when_hit。

condition：{"type":"shape-shifter-curse:chance","chance":0.8}


来源：data/shape-shifter-curse/powers/form_allay_sp_sound_hurt.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_sound_key shape-shifter-curse:form_allay_sp_sound_key

能力 ID：shape-shifter-curse:form_allay_sp_sound_key；类型：apoli:active_self。

cooldown：5


来源：data/shape-shifter-curse/powers/form_allay_sp_sound_key.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_allay_sp_walk_on_powder_snow shape-shifter-curse:form_allay_sp_walk_on_powder_snow

能力 ID：shape-shifter-curse:form_allay_sp_walk_on_powder_snow；类型：shape-shifter-curse:powder_snow_walker。



来源：data/shape-shifter-curse/powers/form_allay_sp_walk_on_powder_snow.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_0_undead_damage_down shape-shifter-curse:form_anubis_wolf_0_undead_damage_down

能力 ID：shape-shifter-curse:form_anubis_wolf_0_undead_damage_down；类型：apoli:modify_damage_dealt。

modifier：{"name":"Undead damage down","operation":"add_base_late","value":-1.0}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_0_undead_damage_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_0_wither_attack shape-shifter-curse:form_anubis_wolf_0_wither_attack

能力 ID：shape-shifter-curse:form_anubis_wolf_0_wither_attack；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_0_wither_attack.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_0_wither_on_hit shape-shifter-curse:form_anubis_wolf_0_wither_on_hit

能力 ID：shape-shifter-curse:form_anubis_wolf_0_wither_on_hit；类型：apoli:self_action_when_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_0_wither_on_hit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_crop_to_sand shape-shifter-curse:form_anubis_wolf_1_crop_to_sand

能力 ID：shape-shifter-curse:form_anubis_wolf_1_crop_to_sand；类型：shape-shifter-curse:modify_block_drop。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_crop_to_sand.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_food_heal_slower shape-shifter-curse:form_anubis_wolf_1_food_heal_slower

能力 ID：shape-shifter-curse:form_anubis_wolf_1_food_heal_slower；类型：shape-shifter-curse:modify_food_heal。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_food_heal_slower.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_meat_to_rotten_flesh shape-shifter-curse:form_anubis_wolf_1_meat_to_rotten_flesh

能力 ID：shape-shifter-curse:form_anubis_wolf_1_meat_to_rotten_flesh；类型：shape-shifter-curse:modify_entity_loot。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_meat_to_rotten_flesh.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_soul_speed shape-shifter-curse:form_anubis_wolf_1_soul_speed

能力 ID：shape-shifter-curse:form_anubis_wolf_1_soul_speed；类型：shape-shifter-curse:soul_speed。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_soul_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_summon_wolf_when_hit shape-shifter-curse:form_anubis_wolf_1_summon_wolf_when_hit

能力 ID：shape-shifter-curse:form_anubis_wolf_1_summon_wolf_when_hit；类型：apoli:action_when_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_summon_wolf_when_hit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_undead_damage_down shape-shifter-curse:form_anubis_wolf_1_undead_damage_down

能力 ID：shape-shifter-curse:form_anubis_wolf_1_undead_damage_down；类型：apoli:modify_damage_dealt。

modifier：{"name":"Undead damage down","operation":"add_base_late","value":-1.0}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_undead_damage_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_vegetarian_saturation_down shape-shifter-curse:form_anubis_wolf_1_vegetarian_saturation_down

能力 ID：shape-shifter-curse:form_anubis_wolf_1_vegetarian_saturation_down；类型：apoli:modify_food。

saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-0.2}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_vegetarian_saturation_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_wither_attack shape-shifter-curse:form_anubis_wolf_1_wither_attack

能力 ID：shape-shifter-curse:form_anubis_wolf_1_wither_attack；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_wither_attack.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_wither_attack_heal shape-shifter-curse:form_anubis_wolf_1_wither_attack_heal

能力 ID：shape-shifter-curse:form_anubis_wolf_1_wither_attack_heal；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_wither_attack_heal.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_wither_health shape-shifter-curse:form_anubis_wolf_1_wither_health

能力 ID：shape-shifter-curse:form_anubis_wolf_1_wither_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：2
condition：{"type":"apoli:status_effect","effect":"minecraft:wither"}
modifier：{"name":"Add Max Health When Wither","attribute":"minecraft:generic.max_health","value":2.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_wither_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_1_wither_on_hit shape-shifter-curse:form_anubis_wolf_1_wither_on_hit

能力 ID：shape-shifter-curse:form_anubis_wolf_1_wither_on_hit；类型：apoli:self_action_when_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_1_wither_on_hit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_crop_to_sand shape-shifter-curse:form_anubis_wolf_2_crop_to_sand

能力 ID：shape-shifter-curse:form_anubis_wolf_2_crop_to_sand；类型：shape-shifter-curse:modify_block_drop。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_crop_to_sand.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_food_heal_slower shape-shifter-curse:form_anubis_wolf_2_food_heal_slower

能力 ID：shape-shifter-curse:form_anubis_wolf_2_food_heal_slower；类型：shape-shifter-curse:modify_food_heal。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_food_heal_slower.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_meat_to_rotten_flesh shape-shifter-curse:form_anubis_wolf_2_meat_to_rotten_flesh

能力 ID：shape-shifter-curse:form_anubis_wolf_2_meat_to_rotten_flesh；类型：shape-shifter-curse:modify_entity_loot。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_meat_to_rotten_flesh.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_soul_speed shape-shifter-curse:form_anubis_wolf_2_soul_speed

能力 ID：shape-shifter-curse:form_anubis_wolf_2_soul_speed；类型：shape-shifter-curse:soul_speed。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_soul_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_summon_wolf_when_hit shape-shifter-curse:form_anubis_wolf_2_summon_wolf_when_hit

能力 ID：shape-shifter-curse:form_anubis_wolf_2_summon_wolf_when_hit；类型：apoli:action_when_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_summon_wolf_when_hit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_undead_damage_down shape-shifter-curse:form_anubis_wolf_2_undead_damage_down

能力 ID：shape-shifter-curse:form_anubis_wolf_2_undead_damage_down；类型：apoli:modify_damage_dealt。

modifier：{"name":"Undead damage down","operation":"add_base_late","value":-2.0}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_undead_damage_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_undead_dealt_damage_down shape-shifter-curse:form_anubis_wolf_2_undead_dealt_damage_down

能力 ID：shape-shifter-curse:form_anubis_wolf_2_undead_dealt_damage_down；类型：apoli:self_action_on_hit。

cooldown：5


来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_undead_dealt_damage_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_vegetarian_saturation_down shape-shifter-curse:form_anubis_wolf_2_vegetarian_saturation_down

能力 ID：shape-shifter-curse:form_anubis_wolf_2_vegetarian_saturation_down；类型：apoli:modify_food。

saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-0.5}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_vegetarian_saturation_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_wither_attack shape-shifter-curse:form_anubis_wolf_2_wither_attack

能力 ID：shape-shifter-curse:form_anubis_wolf_2_wither_attack；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_wither_attack.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_wither_attack_heal shape-shifter-curse:form_anubis_wolf_2_wither_attack_heal

能力 ID：shape-shifter-curse:form_anubis_wolf_2_wither_attack_heal；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_wither_attack_heal.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_wither_health shape-shifter-curse:form_anubis_wolf_2_wither_health

能力 ID：shape-shifter-curse:form_anubis_wolf_2_wither_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：2
condition：{"type":"apoli:status_effect","effect":"minecraft:wither"}
modifier：{"name":"Add Max Health When Wither","attribute":"minecraft:generic.max_health","value":4,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_wither_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_2_wither_on_hit shape-shifter-curse:form_anubis_wolf_2_wither_on_hit

能力 ID：shape-shifter-curse:form_anubis_wolf_2_wither_on_hit；类型：apoli:self_action_when_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_2_wither_on_hit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_crop_to_sand shape-shifter-curse:form_anubis_wolf_3_crop_to_sand

能力 ID：shape-shifter-curse:form_anubis_wolf_3_crop_to_sand；类型：shape-shifter-curse:modify_block_drop。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_crop_to_sand.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_food_heal_slower shape-shifter-curse:form_anubis_wolf_3_food_heal_slower

能力 ID：shape-shifter-curse:form_anubis_wolf_3_food_heal_slower；类型：shape-shifter-curse:modify_food_heal。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_food_heal_slower.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_immune_instant_heal shape-shifter-curse:form_anubis_wolf_3_immune_instant_heal

能力 ID：shape-shifter-curse:form_anubis_wolf_3_immune_instant_heal；类型：shape-shifter-curse:modify_instant_health_scale。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_immune_instant_heal.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_immune_regeneration shape-shifter-curse:form_anubis_wolf_3_immune_regeneration

能力 ID：shape-shifter-curse:form_anubis_wolf_3_immune_regeneration；类型：apoli:effect_immunity。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_immune_regeneration.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_meat_to_rotten_flesh shape-shifter-curse:form_anubis_wolf_3_meat_to_rotten_flesh

能力 ID：shape-shifter-curse:form_anubis_wolf_3_meat_to_rotten_flesh；类型：shape-shifter-curse:modify_entity_loot。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_meat_to_rotten_flesh.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_reverse_instant_damage shape-shifter-curse:form_anubis_wolf_3_reverse_instant_damage

能力 ID：shape-shifter-curse:form_anubis_wolf_3_reverse_instant_damage；类型：shape-shifter-curse:modify_instant_damage_scale。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_reverse_instant_damage.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_soul_speed shape-shifter-curse:form_anubis_wolf_3_soul_speed

能力 ID：shape-shifter-curse:form_anubis_wolf_3_soul_speed；类型：shape-shifter-curse:soul_speed。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_soul_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_summon_wolf_on_hit shape-shifter-curse:form_anubis_wolf_3_summon_wolf_on_hit

能力 ID：shape-shifter-curse:form_anubis_wolf_3_summon_wolf_on_hit；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_summon_wolf_on_hit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_summon_wolf_when_hit shape-shifter-curse:form_anubis_wolf_3_summon_wolf_when_hit

能力 ID：shape-shifter-curse:form_anubis_wolf_3_summon_wolf_when_hit；类型：apoli:action_when_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_summon_wolf_when_hit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_undead_damage_down shape-shifter-curse:form_anubis_wolf_3_undead_damage_down

能力 ID：shape-shifter-curse:form_anubis_wolf_3_undead_damage_down；类型：apoli:modify_damage_dealt。

modifier：{"name":"Undead damage down","operation":"add_base_late","value":-3.0}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_undead_damage_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_undying shape-shifter-curse:form_anubis_wolf_3_undying

能力 ID：shape-shifter-curse:form_anubis_wolf_3_undying；类型：shape-shifter-curse:virtual_totem。

cooldown：9000


来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_undying.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_vegetarian_saturation_down shape-shifter-curse:form_anubis_wolf_3_vegetarian_saturation_down

能力 ID：shape-shifter-curse:form_anubis_wolf_3_vegetarian_saturation_down；类型：apoli:modify_food。

saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-1}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_vegetarian_saturation_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_wither_attack shape-shifter-curse:form_anubis_wolf_3_wither_attack

能力 ID：shape-shifter-curse:form_anubis_wolf_3_wither_attack；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_wither_attack.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_wither_attack_heal shape-shifter-curse:form_anubis_wolf_3_wither_attack_heal

能力 ID：shape-shifter-curse:form_anubis_wolf_3_wither_attack_heal；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_wither_attack_heal.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_wither_health shape-shifter-curse:form_anubis_wolf_3_wither_health

能力 ID：shape-shifter-curse:form_anubis_wolf_3_wither_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：2
condition：{"type":"apoli:status_effect","effect":"minecraft:wither"}
modifier：{"name":"Add Max Health When Wither","attribute":"minecraft:generic.max_health","value":6.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_wither_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_wither_instant_kill shape-shifter-curse:form_anubis_wolf_3_wither_instant_kill

能力 ID：shape-shifter-curse:form_anubis_wolf_3_wither_instant_kill；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_wither_instant_kill.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_3_wither_on_hit shape-shifter-curse:form_anubis_wolf_3_wither_on_hit

能力 ID：shape-shifter-curse:form_anubis_wolf_3_wither_on_hit；类型：apoli:self_action_when_hit。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_3_wither_on_hit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_can_eat_bone shape-shifter-curse:form_anubis_wolf_can_eat_bone

能力 ID：shape-shifter-curse:form_anubis_wolf_can_eat_bone；类型：shape-shifter-curse:custom_edible。

saturation_modifier：0.4
always_edible：true


来源：data/shape-shifter-curse/powers/form_anubis_wolf_can_eat_bone.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_disable_hurt_camera_when_withered shape-shifter-curse:form_anubis_wolf_disable_hurt_camera_when_withered

能力 ID：shape-shifter-curse:form_anubis_wolf_disable_hurt_camera_when_withered；类型：shape-shifter-curse:disable_hurt_camera。

condition：{"type":"apoli:status_effect","effect":"minecraft:wither"}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_disable_hurt_camera_when_withered.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_disable_wither_hearts shape-shifter-curse:form_anubis_wolf_disable_wither_hearts

能力 ID：shape-shifter-curse:form_anubis_wolf_disable_wither_hearts；类型：shape-shifter-curse:disable_wither_hearts。

condition：{"type":"apoli:status_effect","effect":"minecraft:wither"}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_disable_wither_hearts.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_instinct_biome shape-shifter-curse:form_anubis_wolf_instinct_biome

能力 ID：shape-shifter-curse:form_anubis_wolf_instinct_biome；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:biome","biome":"minecraft:desert"}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_instinct_biome.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_instinct_eat_rotten_flesh shape-shifter-curse:form_anubis_wolf_instinct_eat_rotten_flesh

能力 ID：shape-shifter-curse:form_anubis_wolf_instinct_eat_rotten_flesh；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_instinct_eat_rotten_flesh.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_instinct_wither_effect shape-shifter-curse:form_anubis_wolf_instinct_wither_effect

能力 ID：shape-shifter-curse:form_anubis_wolf_instinct_wither_effect；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:status_effect","effect":"minecraft:wither"}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_instinct_wither_effect.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_rotten_flesh_up shape-shifter-curse:form_anubis_wolf_rotten_flesh_up

能力 ID：shape-shifter-curse:form_anubis_wolf_rotten_flesh_up；类型：apoli:modify_food。

food_modifiers：[{"name":"increase food points","operation":"addition","value":4}]
saturation_modifiers：[{"name":"increase food points","operation":"addition","value":0.6}]


来源：data/shape-shifter-curse/powers/form_anubis_wolf_rotten_flesh_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sound shape-shifter-curse:form_anubis_wolf_sound

能力 ID：shape-shifter-curse:form_anubis_wolf_sound；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:enable_random_sound"},{"type":"shape-shifter-curse:chance","chance":0.3}]}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_sound.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sound_hurt shape-shifter-curse:form_anubis_wolf_sound_hurt

能力 ID：shape-shifter-curse:form_anubis_wolf_sound_hurt；类型：apoli:self_action_when_hit。

condition：{"type":"shape-shifter-curse:chance","chance":0.8}


来源：data/shape-shifter-curse/powers/form_anubis_wolf_sound_hurt.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_sound_key shape-shifter-curse:form_anubis_wolf_sound_key

能力 ID：shape-shifter-curse:form_anubis_wolf_sound_key；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_sound_key.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_anubis_wolf_twolf_friendly shape-shifter-curse:form_anubis_wolf_twolf_friendly

能力 ID：shape-shifter-curse:form_anubis_wolf_twolf_friendly；类型：shape-shifter-curse:t_wolf_friendly。



来源：data/shape-shifter-curse/powers/form_anubis_wolf_twolf_friendly.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_0_new_puff_speed_down shape-shifter-curse:form_axolotl_0_new_puff_speed_down

能力 ID：shape-shifter-curse:form_axolotl_0_new_puff_speed_down；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:or","conditions":[{"type":"apoli:on_block","block_condition":{"type":"apoli:in_tag","tag":"shape-shifter-curse:puff_block"}},{"type":"apoli:on_block","block_condition":{"type":"apoli:block_state","property":"snowy","value":true}}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":-0.2,"name":"Decreased speed on sand"}


来源：data/shape-shifter-curse/powers/form_axolotl_0_new_puff_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_0_swim_speed shape-shifter-curse:form_axolotl_0_swim_speed

能力 ID：shape-shifter-curse:form_axolotl_0_swim_speed；类型：apoli:attribute。

modifier：{"attribute":"additionalentityattributes:generic.water_speed","name":"Additional swim speed","value":1.2,"operation":"multiply_base"}


来源：data/shape-shifter-curse/powers/form_axolotl_0_swim_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_1_new_water_flexibility shape-shifter-curse:form_axolotl_1_new_water_flexibility

能力 ID：shape-shifter-curse:form_axolotl_1_new_water_flexibility；类型：shape-shifter-curse:water_flexibility。

condition：{"type":"apoli:in_block","block_condition":{"type":"apoli:block","block":"minecraft:water"}}


来源：data/shape-shifter-curse/powers/form_axolotl_1_new_water_flexibility.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_1_swim_speed shape-shifter-curse:form_axolotl_1_swim_speed

能力 ID：shape-shifter-curse:form_axolotl_1_swim_speed；类型：apoli:attribute。

modifier：{"attribute":"additionalentityattributes:generic.water_speed","name":"Additional swim speed","value":1.5,"operation":"multiply_base"}


来源：data/shape-shifter-curse/powers/form_axolotl_1_swim_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_crawling shape-shifter-curse:form_axolotl_2_crawling

能力 ID：shape-shifter-curse:form_axolotl_2_crawling；类型：shape-shifter-curse:crawling。

condition：{"type":"apoli:sneaking"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_crawling.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_dry_speed_down shape-shifter-curse:form_axolotl_2_dry_speed_down

能力 ID：shape-shifter-curse:form_axolotl_2_dry_speed_down；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:air","comparison":"<=","compare_to":0}
modifier：{"name":"Decrease speed when not having air(moist)","attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":-0.5}


来源：data/shape-shifter-curse/powers/form_axolotl_2_dry_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_falling_protection shape-shifter-curse:form_axolotl_2_falling_protection

能力 ID：shape-shifter-curse:form_axolotl_2_falling_protection；类型：shape-shifter-curse:falling_protection。



来源：data/shape-shifter-curse/powers/form_axolotl_2_falling_protection.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_0 shape-shifter-curse:form_axolotl_2_new_oxygen_health_0

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_0；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:air","comparison":">=","compare_to":270}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":6.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_0.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_1 shape-shifter-curse:form_axolotl_2_new_oxygen_health_1

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_1；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:air","comparison":"<","compare_to":270},{"type":"apoli:air","comparison":">=","compare_to":240}]}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":4.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_1.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_2 shape-shifter-curse:form_axolotl_2_new_oxygen_health_2

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_2；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:air","comparison":"<","compare_to":240},{"type":"apoli:air","comparison":">=","compare_to":210}]}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":2.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_2.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_3 shape-shifter-curse:form_axolotl_2_new_oxygen_health_3

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_3；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:air","comparison":"<","compare_to":210},{"type":"apoli:air","comparison":">=","compare_to":180}]}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":0.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_3.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_4 shape-shifter-curse:form_axolotl_2_new_oxygen_health_4

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_4；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:air","comparison":"<","compare_to":180},{"type":"apoli:air","comparison":">=","compare_to":150}]}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":0.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_4.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_5 shape-shifter-curse:form_axolotl_2_new_oxygen_health_5

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_5；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:air","comparison":"<","compare_to":150},{"type":"apoli:air","comparison":">=","compare_to":120}]}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":0.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_5.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_6 shape-shifter-curse:form_axolotl_2_new_oxygen_health_6

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_6；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:air","comparison":"<","compare_to":120},{"type":"apoli:air","comparison":">=","compare_to":90}]}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_6.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_7 shape-shifter-curse:form_axolotl_2_new_oxygen_health_7

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_7；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:air","comparison":"<","compare_to":90},{"type":"apoli:air","comparison":">=","compare_to":60}]}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":-6.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_7.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_8 shape-shifter-curse:form_axolotl_2_new_oxygen_health_8

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_8；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:air","comparison":"<","compare_to":60},{"type":"apoli:air","comparison":">=","compare_to":30}]}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":-8.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_8.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_health_9 shape-shifter-curse:form_axolotl_2_new_oxygen_health_9

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_health_9；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:air","comparison":"<","compare_to":30}
modifier：{"name":"Oxygen health","attribute":"minecraft:generic.max_health","value":-10.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_health_9.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_oxygen_regeneration shape-shifter-curse:form_axolotl_2_new_oxygen_regeneration

能力 ID：shape-shifter-curse:form_axolotl_2_new_oxygen_regeneration；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:food_level","comparison":">=","compare_to":6},{"type":"apoli:health","comparison":"<","compare_to":32},{"type":"apoli:air","comparison":">","compare_to":0}]}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_oxygen_regeneration.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_new_water_flexibility shape-shifter-curse:form_axolotl_2_new_water_flexibility

能力 ID：shape-shifter-curse:form_axolotl_2_new_water_flexibility；类型：shape-shifter-curse:water_flexibility。

condition：{"type":"apoli:in_block","block_condition":{"type":"apoli:block","block":"minecraft:water"}}


来源：data/shape-shifter-curse/powers/form_axolotl_2_new_water_flexibility.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_regeneration shape-shifter-curse:form_axolotl_2_regeneration

能力 ID：shape-shifter-curse:form_axolotl_2_regeneration；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:food_level","comparison":">=","compare_to":6},{"type":"apoli:health","comparison":"<","compare_to":30}]}


来源：data/shape-shifter-curse/powers/form_axolotl_2_regeneration.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_water_breathing shape-shifter-curse:form_axolotl_2_water_breathing

能力 ID：shape-shifter-curse:form_axolotl_2_water_breathing；类型：shape-shifter-curse:custom_water_breathing。



来源：data/shape-shifter-curse/powers/form_axolotl_2_water_breathing.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_water_spurt shape-shifter-curse:form_axolotl_2_water_spurt

能力 ID：shape-shifter-curse:form_axolotl_2_water_spurt；类型：apoli:active_self。

cooldown：100
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":">=","compare_to":1}]}


来源：data/shape-shifter-curse/powers/form_axolotl_2_water_spurt.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_2_wet_health shape-shifter-curse:form_axolotl_2_wet_health

能力 ID：shape-shifter-curse:form_axolotl_2_wet_health；类型：apoli:conditioned_attribute。

tick_rate：40
condition：{"type":"apoli:air","comparison":">","compare_to":0}
modifier：{"name":"Gain health when having air(moist)","attribute":"minecraft:generic.max_health","value":6.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_axolotl_2_wet_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_always_swimming shape-shifter-curse:form_axolotl_3_always_swimming

能力 ID：shape-shifter-curse:form_axolotl_3_always_swimming；类型：shape-shifter-curse:always_sprint_swimming。



来源：data/shape-shifter-curse/powers/form_axolotl_3_always_swimming.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_crawling shape-shifter-curse:form_axolotl_3_crawling

能力 ID：shape-shifter-curse:form_axolotl_3_crawling；类型：shape-shifter-curse:crawling。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:swimming","inverted":true}]}


来源：data/shape-shifter-curse/powers/form_axolotl_3_crawling.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_ground_speed_down shape-shifter-curse:form_axolotl_3_ground_speed_down

能力 ID：shape-shifter-curse:form_axolotl_3_ground_speed_down；类型：apoli:conditioned_attribute。

tick_rate：5
condition：{"type":"apoli:and","conditions":[{"type":"apoli:on_block"},{"type":"apoli:in_block","block_condition":{"type":"apoli:block","block":"minecraft:water"},"inverted":true},{"type":"apoli:sneaking","inverted":true},{"type":"apoli:sprinting","inverted":true}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":-0.3}


来源：data/shape-shifter-curse/powers/form_axolotl_3_ground_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_keep_sneaking_no_air shape-shifter-curse:form_axolotl_3_keep_sneaking_no_air

能力 ID：shape-shifter-curse:form_axolotl_3_keep_sneaking_no_air；类型：shape-shifter-curse:keep_sneaking。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:air","comparison":"<=","compare_to":0}]}


来源：data/shape-shifter-curse/powers/form_axolotl_3_keep_sneaking_no_air.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_no_render_arm shape-shifter-curse:form_axolotl_3_no_render_arm

能力 ID：shape-shifter-curse:form_axolotl_3_no_render_arm；类型：shape-shifter-curse:no_render_arm。

condition：{"type":"apoli:or","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:swimming"}]}


来源：data/shape-shifter-curse/powers/form_axolotl_3_no_render_arm.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_particle shape-shifter-curse:form_axolotl_3_particle

能力 ID：shape-shifter-curse:form_axolotl_3_particle；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:sprinting"},{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:on_block"},{"type":"apoli:air","comparison":">","compare_to":0}]}


来源：data/shape-shifter-curse/powers/form_axolotl_3_particle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_slipperiness shape-shifter-curse:form_axolotl_3_slipperiness

能力 ID：shape-shifter-curse:form_axolotl_3_slipperiness；类型：shape-shifter-curse:conditioned_modify_slipperiness。

modifier：0.35


来源：data/shape-shifter-curse/powers/form_axolotl_3_slipperiness.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_sneaking_speed shape-shifter-curse:form_axolotl_3_sneaking_speed

能力 ID：shape-shifter-curse:form_axolotl_3_sneaking_speed；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:sneaking"},{"type":"apoli:air","comparison":">","compare_to":0}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"addition","value":0.2}


来源：data/shape-shifter-curse/powers/form_axolotl_3_sneaking_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_sprint_step_height shape-shifter-curse:form_axolotl_3_sprint_step_height

能力 ID：shape-shifter-curse:form_axolotl_3_sprint_step_height；类型：shape-shifter-curse:modify_step_height。

condition：{"type":"apoli:sprinting"}


来源：data/shape-shifter-curse/powers/form_axolotl_3_sprint_step_height.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_sprinting_attack shape-shifter-curse:form_axolotl_3_sprinting_attack

能力 ID：shape-shifter-curse:form_axolotl_3_sprinting_attack；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_axolotl_3_sprinting_attack.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_sprinting_cost_oxygen shape-shifter-curse:form_axolotl_3_sprinting_cost_oxygen

能力 ID：shape-shifter-curse:form_axolotl_3_sprinting_cost_oxygen；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:sprinting"},{"type":"apoli:air","comparison":">","compare_to":0}]}


来源：data/shape-shifter-curse/powers/form_axolotl_3_sprinting_cost_oxygen.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_sprinting_jump shape-shifter-curse:form_axolotl_3_sprinting_jump

能力 ID：shape-shifter-curse:form_axolotl_3_sprinting_jump；类型：shape-shifter-curse:action_on_jump。



来源：data/shape-shifter-curse/powers/form_axolotl_3_sprinting_jump.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_sprinting_jump_high shape-shifter-curse:form_axolotl_3_sprinting_jump_high

能力 ID：shape-shifter-curse:form_axolotl_3_sprinting_jump_high；类型：apoli:modify_jump。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:sprinting"},{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:on_block"},{"type":"apoli:air","comparison":">","compare_to":0}]}
modifier：{"name":"Bonus jump force while sneaking","operation":"multiply_base","value":0.1}


来源：data/shape-shifter-curse/powers/form_axolotl_3_sprinting_jump_high.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_sprinting_sneaking_water_explode shape-shifter-curse:form_axolotl_3_sprinting_sneaking_water_explode

能力 ID：shape-shifter-curse:form_axolotl_3_sprinting_sneaking_water_explode；类型：apoli:active_self。

cooldown：60
condition：{"type":"apoli:and","conditions":[{"type":"apoli:on_block"},{"type":"apoli:sprinting"},{"type":"apoli:air","comparison":">","compare_to":0}]}


来源：data/shape-shifter-curse/powers/form_axolotl_3_sprinting_sneaking_water_explode.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_sprinting_speed shape-shifter-curse:form_axolotl_3_sprinting_speed

能力 ID：shape-shifter-curse:form_axolotl_3_sprinting_speed；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:sprinting"},{"type":"apoli:air","comparison":">","compare_to":0}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":0.65}


来源：data/shape-shifter-curse/powers/form_axolotl_3_sprinting_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_3_water_breathing shape-shifter-curse:form_axolotl_3_water_breathing

能力 ID：shape-shifter-curse:form_axolotl_3_water_breathing；类型：shape-shifter-curse:custom_water_breathing。



来源：data/shape-shifter-curse/powers/form_axolotl_3_water_breathing.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_instinct_attack_fish shape-shifter-curse:form_axolotl_instinct_attack_fish

能力 ID：shape-shifter-curse:form_axolotl_instinct_attack_fish；类型：apoli:self_action_on_hit。



来源：data/shape-shifter-curse/powers/form_axolotl_instinct_attack_fish.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_instinct_biome shape-shifter-curse:form_axolotl_instinct_biome

能力 ID：shape-shifter-curse:form_axolotl_instinct_biome；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:biome","biome":"minecraft:lush_caves"}


来源：data/shape-shifter-curse/powers/form_axolotl_instinct_biome.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_instinct_eat_fish shape-shifter-curse:form_axolotl_instinct_eat_fish

能力 ID：shape-shifter-curse:form_axolotl_instinct_eat_fish；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/form_axolotl_instinct_eat_fish.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_instinct_in_water shape-shifter-curse:form_axolotl_instinct_in_water

能力 ID：shape-shifter-curse:form_axolotl_instinct_in_water；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":">","compare_to":0}


来源：data/shape-shifter-curse/powers/form_axolotl_instinct_in_water.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_instinct_near_dripleaf shape-shifter-curse:form_axolotl_instinct_near_dripleaf

能力 ID：shape-shifter-curse:form_axolotl_instinct_near_dripleaf；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:or","conditions":[{"type":"apoli:block","block":"minecraft:big_dripleaf"},{"type":"apoli:block","block":"minecraft:big_dripleaf_stem"},{"type":"apoli:block","block":"minecraft:small_dripleaf"}]},"radius":2,"shape":"cube","comparison":">=","compare_to":4}


来源：data/shape-shifter-curse/powers/form_axolotl_instinct_near_dripleaf.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_keep_sneaking_when_head_collide shape-shifter-curse:form_axolotl_keep_sneaking_when_head_collide

能力 ID：shape-shifter-curse:form_axolotl_keep_sneaking_when_head_collide；类型：shape-shifter-curse:keep_sneaking。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:must_crawling","width":0.54,"height":1.35},{"type":"apoli:swimming","inverted":true},{"type":"apoli:riding","inverted":true}]}


来源：data/shape-shifter-curse/powers/form_axolotl_keep_sneaking_when_head_collide.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_sound shape-shifter-curse:form_axolotl_sound

能力 ID：shape-shifter-curse:form_axolotl_sound；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:enable_random_sound"},{"type":"shape-shifter-curse:chance","chance":0.3}]}


来源：data/shape-shifter-curse/powers/form_axolotl_sound.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_sound_hurt shape-shifter-curse:form_axolotl_sound_hurt

能力 ID：shape-shifter-curse:form_axolotl_sound_hurt；类型：apoli:self_action_when_hit。

condition：{"type":"shape-shifter-curse:chance","chance":0.8}


来源：data/shape-shifter-curse/powers/form_axolotl_sound_hurt.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_axolotl_sound_key shape-shifter-curse:form_axolotl_sound_key

能力 ID：shape-shifter-curse:form_axolotl_sound_key；类型：apoli:active_self。

cooldown：5


来源：data/shape-shifter-curse/powers/form_axolotl_sound_key.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_0_sun_health shape-shifter-curse:form_bat_0_sun_health

能力 ID：shape-shifter-curse:form_bat_0_sun_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：1
condition：{"type":"apoli:exposed_to_sun"}
modifier：{"name":"Reduced health in the sun","attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_bat_0_sun_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_1_falling_protection shape-shifter-curse:form_bat_1_falling_protection

能力 ID：shape-shifter-curse:form_bat_1_falling_protection；类型：shape-shifter-curse:falling_protection。



来源：data/shape-shifter-curse/powers/form_bat_1_falling_protection.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_1_sun_health shape-shifter-curse:form_bat_1_sun_health

能力 ID：shape-shifter-curse:form_bat_1_sun_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：1
condition：{"type":"apoli:exposed_to_sun"}
modifier：{"name":"Reduced health in the sun","attribute":"minecraft:generic.max_health","value":-6.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_bat_1_sun_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_1_sun_speed_down shape-shifter-curse:form_bat_1_sun_speed_down

能力 ID：shape-shifter-curse:form_bat_1_sun_speed_down；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:exposed_to_sun"}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":-0.02,"name":"Decrease speed when under the sun"}


来源：data/shape-shifter-curse/powers/form_bat_1_sun_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_2_sun_health shape-shifter-curse:form_bat_2_sun_health

能力 ID：shape-shifter-curse:form_bat_2_sun_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：1
condition：{"type":"apoli:exposed_to_sun"}
modifier：{"name":"Reduced health in the sun","attribute":"minecraft:generic.max_health","value":-10.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_bat_2_sun_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_2_sun_speed_down shape-shifter-curse:form_bat_2_sun_speed_down

能力 ID：shape-shifter-curse:form_bat_2_sun_speed_down；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:exposed_to_sun"}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":-0.05,"name":"Decrease speed when under the sun"}


来源：data/shape-shifter-curse/powers/form_bat_2_sun_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_3_block_attach shape-shifter-curse:form_bat_3_block_attach

能力 ID：shape-shifter-curse:form_bat_3_block_attach；类型：shape-shifter-curse:bat_block_attach。



来源：data/shape-shifter-curse/powers/form_bat_3_block_attach.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_3_damage_up_when_no_sun shape-shifter-curse:form_bat_3_damage_up_when_no_sun

能力 ID：shape-shifter-curse:form_bat_3_damage_up_when_no_sun；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_bat_3_damage_up_when_no_sun.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_3_ground_speed_down shape-shifter-curse:form_bat_3_ground_speed_down

能力 ID：shape-shifter-curse:form_bat_3_ground_speed_down；类型：apoli:conditioned_attribute。

tick_rate：5
condition：{"type":"apoli:on_block"}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":-0.2}


来源：data/shape-shifter-curse/powers/form_bat_3_ground_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_3_jump_boost shape-shifter-curse:form_bat_3_jump_boost

能力 ID：shape-shifter-curse:form_bat_3_jump_boost；类型：shape-shifter-curse:action_on_jump。



来源：data/shape-shifter-curse/powers/form_bat_3_jump_boost.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_3_jump_high shape-shifter-curse:form_bat_3_jump_high

能力 ID：shape-shifter-curse:form_bat_3_jump_high；类型：apoli:modify_jump。

modifier：{"operation":"addition","value":0.2}


来源：data/shape-shifter-curse/powers/form_bat_3_jump_high.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_3_sky_speed shape-shifter-curse:form_bat_3_sky_speed

能力 ID：shape-shifter-curse:form_bat_3_sky_speed；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:on_block","inverted":true},{"type":"apoli:sprinting"},{"type":"apoli:fall_flying","inverted":true}]}


来源：data/shape-shifter-curse/powers/form_bat_3_sky_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_diamond_mining_claw_tool shape-shifter-curse:form_bat_diamond_mining_claw_tool

能力 ID：shape-shifter-curse:form_bat_diamond_mining_claw_tool；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_bat_diamond_mining_claw_tool.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_hit_wall_damage_reduce shape-shifter-curse:form_bat_hit_wall_damage_reduce

能力 ID：shape-shifter-curse:form_bat_hit_wall_damage_reduce；类型：apoli:modify_damage_taken。

modifier：{"name":"no_hit_wall_damage","operation":"set_total","value":0.0}


来源：data/shape-shifter-curse/powers/form_bat_hit_wall_damage_reduce.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_instinct_biome shape-shifter-curse:form_bat_instinct_biome

能力 ID：shape-shifter-curse:form_bat_instinct_biome；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:biome","biome":"minecraft:dripstone_caves"}


来源：data/shape-shifter-curse/powers/form_bat_instinct_biome.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_instinct_eat_fruit shape-shifter-curse:form_bat_instinct_eat_fruit

能力 ID：shape-shifter-curse:form_bat_instinct_eat_fruit；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/form_bat_instinct_eat_fruit.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_instinct_in_air shape-shifter-curse:form_bat_instinct_in_air

能力 ID：shape-shifter-curse:form_bat_instinct_in_air；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:on_block","inverted":true}]}


来源：data/shape-shifter-curse/powers/form_bat_instinct_in_air.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_instinct_in_dark shape-shifter-curse:form_bat_instinct_in_dark

能力 ID：shape-shifter-curse:form_bat_instinct_in_dark；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:brightness","comparison":"<=","compare_to":0.25}


来源：data/shape-shifter-curse/powers/form_bat_instinct_in_dark.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_instinct_near_dripstone shape-shifter-curse:form_bat_instinct_near_dripstone

能力 ID：shape-shifter-curse:form_bat_instinct_near_dripstone；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:or","conditions":[{"type":"apoli:block","block":"minecraft:dripstone_block"},{"type":"apoli:block","block":"minecraft:pointed_dripstone"}]},"radius":2,"shape":"cube","comparison":">=","compare_to":4}


来源：data/shape-shifter-curse/powers/form_bat_instinct_near_dripstone.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_sound_hurt shape-shifter-curse:form_bat_sound_hurt

能力 ID：shape-shifter-curse:form_bat_sound_hurt；类型：apoli:self_action_when_hit。

condition：{"type":"shape-shifter-curse:chance","chance":0.8}


来源：data/shape-shifter-curse/powers/form_bat_sound_hurt.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_bat_sound_key shape-shifter-curse:form_bat_sound_key

能力 ID：shape-shifter-curse:form_bat_sound_key；类型：apoli:active_self。

cooldown：5


来源：data/shape-shifter-curse/powers/form_bat_sound_key.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_camera_bobbing_bat shape-shifter-curse:form_camera_bobbing_bat

能力 ID：shape-shifter-curse:form_camera_bobbing_bat；类型：shape-shifter-curse:form_camera_bobbing。



来源：data/shape-shifter-curse/powers/form_camera_bobbing_bat.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_camera_bobbing_feral shape-shifter-curse:form_camera_bobbing_feral

能力 ID：shape-shifter-curse:form_camera_bobbing_feral；类型：shape-shifter-curse:form_camera_bobbing。



来源：data/shape-shifter-curse/powers/form_camera_bobbing_feral.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_camera_bobbing_float shape-shifter-curse:form_camera_bobbing_float

能力 ID：shape-shifter-curse:form_camera_bobbing_float；类型：shape-shifter-curse:form_camera_bobbing。



来源：data/shape-shifter-curse/powers/form_camera_bobbing_float.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## 肉食者 shape-shifter-curse:form_carnivore

能力 ID：shape-shifter-curse:form_carnivore；类型：apoli:modify_food。

food_modifiers：[{"operation":"set_total","value":0.0}]
saturation_modifiers：[{"operation":"set_total","value":0.0}]


来源：data/shape-shifter-curse/powers/form_carnivore.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_dark_speed_up shape-shifter-curse:form_dark_speed_up

能力 ID：shape-shifter-curse:form_dark_speed_up；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:brightness","comparison":"<=","compare_to":0.25}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":0.35,"name":"Increased speed when dark"}


来源：data/shape-shifter-curse/powers/form_dark_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_disable_chest_armor shape-shifter-curse:form_disable_chest_armor

能力 ID：shape-shifter-curse:form_disable_chest_armor；类型：apoli:restrict_armor。



来源：data/shape-shifter-curse/powers/form_disable_chest_armor.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_disable_feet_armor shape-shifter-curse:form_disable_feet_armor

能力 ID：shape-shifter-curse:form_disable_feet_armor；类型：apoli:restrict_armor。



来源：data/shape-shifter-curse/powers/form_disable_feet_armor.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_disable_head_armor shape-shifter-curse:form_disable_head_armor

能力 ID：shape-shifter-curse:form_disable_head_armor；类型：apoli:restrict_armor。



来源：data/shape-shifter-curse/powers/form_disable_head_armor.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_disable_leg_armor shape-shifter-curse:form_disable_leg_armor

能力 ID：shape-shifter-curse:form_disable_leg_armor；类型：apoli:restrict_armor。



来源：data/shape-shifter-curse/powers/form_disable_leg_armor.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_0_dizzy_when_attack_witch shape-shifter-curse:form_familiar_fox_0_dizzy_when_attack_witch

能力 ID：shape-shifter-curse:form_familiar_fox_0_dizzy_when_attack_witch；类型：apoli:self_action_on_hit。

cooldown：10


来源：data/shape-shifter-curse/powers/form_familiar_fox_0_dizzy_when_attack_witch.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_0_looting shape-shifter-curse:form_familiar_fox_0_looting

能力 ID：shape-shifter-curse:form_familiar_fox_0_looting；类型：shape-shifter-curse:simple_looting。



来源：data/shape-shifter-curse/powers/form_familiar_fox_0_looting.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_0_sprint_speed_up shape-shifter-curse:form_familiar_fox_0_sprint_speed_up

能力 ID：shape-shifter-curse:form_familiar_fox_0_sprint_speed_up；类型：apoli:conditioned_attribute。

tick_rate：4
condition：{"type":"apoli:sprinting"}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":0.15,"name":"Increased speed when sprinting"}


来源：data/shape-shifter-curse/powers/form_familiar_fox_0_sprint_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_0_water_speed_down shape-shifter-curse:form_familiar_fox_0_water_speed_down

能力 ID：shape-shifter-curse:form_familiar_fox_0_water_speed_down；类型：shape-shifter-curse:in_water_speed_modifier。

modifier：0.9


来源：data/shape-shifter-curse/powers/form_familiar_fox_0_water_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_1_dizzy_when_attack_illager shape-shifter-curse:form_familiar_fox_1_dizzy_when_attack_illager

能力 ID：shape-shifter-curse:form_familiar_fox_1_dizzy_when_attack_illager；类型：apoli:self_action_on_hit。

cooldown：10


来源：data/shape-shifter-curse/powers/form_familiar_fox_1_dizzy_when_attack_illager.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_1_health shape-shifter-curse:form_familiar_fox_1_health

能力 ID：shape-shifter-curse:form_familiar_fox_1_health；类型：apoli:attribute。

modifier：{"name":"Form ocelot0 health reduction","attribute":"minecraft:generic.max_health","value":-2.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_familiar_fox_1_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_1_looting shape-shifter-curse:form_familiar_fox_1_looting

能力 ID：shape-shifter-curse:form_familiar_fox_1_looting；类型：shape-shifter-curse:simple_looting。



来源：data/shape-shifter-curse/powers/form_familiar_fox_1_looting.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_1_use_normal_creature shape-shifter-curse:form_familiar_fox_1_use_normal_creature

能力 ID：shape-shifter-curse:form_familiar_fox_1_use_normal_creature；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_1_use_normal_creature.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_attract_to_witch shape-shifter-curse:form_familiar_fox_2_attract_to_witch

能力 ID：shape-shifter-curse:form_familiar_fox_2_attract_to_witch；类型：apoli:action_over_time。

condition：{"type":"apoli:raycast","distance":20,"block":false,"entity":true,"shape_type":"visual","fluid_handling":"any","hit_bientity_condition":{"type":"apoli:and","conditions":[{"type":"apoli:target_condition","condition":{"type":"apoli:or","conditions":[{"type":"apoli:entity_type","entity_type":"minecraft:witch"},{"type":"apoli:entity_type","entity_type":"minecraft:evoker"}]}},{"type":"apoli:distance","comparison":">=","compare_to":1.5}]}}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_attract_to_witch.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_charm_paper_fireball shape-shifter-curse:form_familiar_fox_2_charm_paper_fireball

能力 ID：shape-shifter-curse:form_familiar_fox_2_charm_paper_fireball；类型：apoli:action_on_item_use。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fire_arrow_cooldown","comparison":"<","compare_to":1},{"type":"shape-shifter-curse:has_mana","mana":2.5}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_charm_paper_fireball.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_charm_paper_fireball_damage shape-shifter-curse:form_familiar_fox_2_charm_paper_fireball_damage

能力 ID：shape-shifter-curse:form_familiar_fox_2_charm_paper_fireball_damage；类型：apoli:modify_projectile_damage。

condition：{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fireball_kind_boolean_resource","comparison":"==","compare_to":1}
modifier：{"operation":"set_total","value":9.0}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_charm_paper_fireball_damage.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_craft_harming_potion shape-shifter-curse:form_familiar_fox_2_craft_harming_potion

能力 ID：shape-shifter-curse:form_familiar_fox_2_craft_harming_potion；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_craft_harming_potion.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_craft_healing_potion shape-shifter-curse:form_familiar_fox_2_craft_healing_potion

能力 ID：shape-shifter-curse:form_familiar_fox_2_craft_healing_potion；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_craft_healing_potion.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_craft_poison_potion shape-shifter-curse:form_familiar_fox_2_craft_poison_potion

能力 ID：shape-shifter-curse:form_familiar_fox_2_craft_poison_potion；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_craft_poison_potion.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_health shape-shifter-curse:form_familiar_fox_2_health

能力 ID：shape-shifter-curse:form_familiar_fox_2_health；类型：apoli:attribute。

modifier：{"name":"Form ocelot0 health reduction","attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_low_mana_debuff shape-shifter-curse:form_familiar_fox_2_low_mana_debuff

能力 ID：shape-shifter-curse:form_familiar_fox_2_low_mana_debuff；类型：shape-shifter-curse:apply_effect。

condition：{"type":"shape-shifter-curse:has_mana_percent","mana_percent":0.06,"inverted":true}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_low_mana_debuff.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_mana shape-shifter-curse:form_familiar_fox_2_mana

能力 ID：shape-shifter-curse:form_familiar_fox_2_mana；类型：shape-shifter-curse:mana_type_power。



来源：data/shape-shifter-curse/powers/form_familiar_fox_2_mana.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_stick_fireball shape-shifter-curse:form_familiar_fox_2_stick_fireball

能力 ID：shape-shifter-curse:form_familiar_fox_2_stick_fireball；类型：apoli:action_on_item_use。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fire_arrow_cooldown","comparison":"<","compare_to":1},{"type":"shape-shifter-curse:has_mana","mana":2.5}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_stick_fireball.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_stick_fireball_damage shape-shifter-curse:form_familiar_fox_2_stick_fireball_damage

能力 ID：shape-shifter-curse:form_familiar_fox_2_stick_fireball_damage；类型：apoli:modify_projectile_damage。

condition：{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fireball_kind_boolean_resource","comparison":"==","compare_to":0}
modifier：{"operation":"set_total","value":5.0}


来源：data/shape-shifter-curse/powers/form_familiar_fox_2_stick_fireball_damage.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_tan_temperature_modify shape-shifter-curse:form_familiar_fox_2_tan_temperature_modify

能力 ID：shape-shifter-curse:form_familiar_fox_2_tan_temperature_modify；类型：shape-shifter-curse:tan_form_temperature_modifier。



来源：data/shape-shifter-curse/powers/form_familiar_fox_2_tan_temperature_modify.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_use_illager_creature shape-shifter-curse:form_familiar_fox_2_use_illager_creature

能力 ID：shape-shifter-curse:form_familiar_fox_2_use_illager_creature；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_2_use_illager_creature.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_2_use_illager_creature_bottle shape-shifter-curse:form_familiar_fox_2_use_illager_creature_bottle

能力 ID：shape-shifter-curse:form_familiar_fox_2_use_illager_creature_bottle；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_2_use_illager_creature_bottle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_attract_to_witch2 shape-shifter-curse:form_familiar_fox_3_attract_to_witch2

能力 ID：shape-shifter-curse:form_familiar_fox_3_attract_to_witch2；类型：shape-shifter-curse:attract_by_entity。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_attract_to_witch2.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_craft_harming_potion shape-shifter-curse:form_familiar_fox_3_craft_harming_potion

能力 ID：shape-shifter-curse:form_familiar_fox_3_craft_harming_potion；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_craft_harming_potion.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_craft_healing_potion shape-shifter-curse:form_familiar_fox_3_craft_healing_potion

能力 ID：shape-shifter-curse:form_familiar_fox_3_craft_healing_potion；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_craft_healing_potion.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_craft_poison_potion shape-shifter-curse:form_familiar_fox_3_craft_poison_potion

能力 ID：shape-shifter-curse:form_familiar_fox_3_craft_poison_potion；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_craft_poison_potion.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_digging_speed_down shape-shifter-curse:form_familiar_fox_3_digging_speed_down

能力 ID：shape-shifter-curse:form_familiar_fox_3_digging_speed_down；类型：apoli:modify_break_speed。

modifier：{"operation":"multiply_base","value":-0.45}


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_digging_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_fire_ring shape-shifter-curse:form_familiar_fox_3_fire_ring

能力 ID：shape-shifter-curse:form_familiar_fox_3_fire_ring；类型：apoli:active_self。

cooldown：40


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_fire_ring.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_health shape-shifter-curse:form_familiar_fox_3_health

能力 ID：shape-shifter-curse:form_familiar_fox_3_health；类型：apoli:attribute。

modifier：{"name":"Form ocelot0 health reduction","attribute":"minecraft:generic.max_health","value":-6.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_hurt_when_attack_witch shape-shifter-curse:form_familiar_fox_3_hurt_when_attack_witch

能力 ID：shape-shifter-curse:form_familiar_fox_3_hurt_when_attack_witch；类型：apoli:self_action_on_hit。

cooldown：10


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_hurt_when_attack_witch.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_in_fire_cost_mana shape-shifter-curse:form_familiar_fox_3_in_fire_cost_mana

能力 ID：shape-shifter-curse:form_familiar_fox_3_in_fire_cost_mana；类型：apoli:action_over_time。

condition：{"type":"apoli:in_block","block_condition":{"type":"apoli:or","conditions":[{"type":"apoli:block","block":"minecraft:fire"},{"type":"apoli:block","block":"minecraft:lava"},{"type":"apoli:block","block":"minecraft:soul_fire"}]}}


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_in_fire_cost_mana.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_jump_high shape-shifter-curse:form_familiar_fox_3_jump_high

能力 ID：shape-shifter-curse:form_familiar_fox_3_jump_high；类型：apoli:modify_jump。

modifier：{"name":"Bonus jump force","operation":"multiply_base","value":0.2}


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_jump_high.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_looting shape-shifter-curse:form_familiar_fox_3_looting

能力 ID：shape-shifter-curse:form_familiar_fox_3_looting；类型：shape-shifter-curse:simple_looting。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_looting.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_low_mana_damage shape-shifter-curse:form_familiar_fox_3_low_mana_damage

能力 ID：shape-shifter-curse:form_familiar_fox_3_low_mana_damage；类型：apoli:action_over_time。

condition：{"type":"shape-shifter-curse:has_mana","mana":6,"inverted":true}


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_low_mana_damage.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_mana shape-shifter-curse:form_familiar_fox_3_mana

能力 ID：shape-shifter-curse:form_familiar_fox_3_mana；类型：shape-shifter-curse:mana_type_power。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_mana.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_mob_glow_in_range shape-shifter-curse:form_familiar_fox_3_mob_glow_in_range

能力 ID：shape-shifter-curse:form_familiar_fox_3_mob_glow_in_range；类型：apoli:entity_glow。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_mob_glow_in_range.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_no_attack_witch shape-shifter-curse:form_familiar_fox_3_no_attack_witch

能力 ID：shape-shifter-curse:form_familiar_fox_3_no_attack_witch；类型：apoli:action_on_hit。

cooldown：10


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_no_attack_witch.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_no_buff_effect shape-shifter-curse:form_familiar_fox_3_no_buff_effect

能力 ID：shape-shifter-curse:form_familiar_fox_3_no_buff_effect；类型：apoli:effect_immunity。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_no_buff_effect.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_no_buff_effect_other shape-shifter-curse:form_familiar_fox_3_no_buff_effect_other

能力 ID：shape-shifter-curse:form_familiar_fox_3_no_buff_effect_other；类型：shape-shifter-curse:optional_effect_immunity。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_no_buff_effect_other.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_particle shape-shifter-curse:form_familiar_fox_3_particle

能力 ID：shape-shifter-curse:form_familiar_fox_3_particle；类型：apoli:particle。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_particle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_tan_temperature_modify shape-shifter-curse:form_familiar_fox_3_tan_temperature_modify

能力 ID：shape-shifter-curse:form_familiar_fox_3_tan_temperature_modify；类型：shape-shifter-curse:tan_form_temperature_modifier。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_tan_temperature_modify.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_use_enemy_creature shape-shifter-curse:form_familiar_fox_3_use_enemy_creature

能力 ID：shape-shifter-curse:form_familiar_fox_3_use_enemy_creature；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_use_enemy_creature.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_use_human_creature shape-shifter-curse:form_familiar_fox_3_use_human_creature

能力 ID：shape-shifter-curse:form_familiar_fox_3_use_human_creature；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_use_human_creature.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_use_human_creature_bottle shape-shifter-curse:form_familiar_fox_3_use_human_creature_bottle

能力 ID：shape-shifter-curse:form_familiar_fox_3_use_human_creature_bottle；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_use_human_creature_bottle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_use_illager_creature shape-shifter-curse:form_familiar_fox_3_use_illager_creature

能力 ID：shape-shifter-curse:form_familiar_fox_3_use_illager_creature；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_use_illager_creature.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_use_illager_creature_bottle shape-shifter-curse:form_familiar_fox_3_use_illager_creature_bottle

能力 ID：shape-shifter-curse:form_familiar_fox_3_use_illager_creature_bottle；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_use_illager_creature_bottle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_use_normal_creature shape-shifter-curse:form_familiar_fox_3_use_normal_creature

能力 ID：shape-shifter-curse:form_familiar_fox_3_use_normal_creature；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_use_normal_creature.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_use_sorcery_creature shape-shifter-curse:form_familiar_fox_3_use_sorcery_creature

能力 ID：shape-shifter-curse:form_familiar_fox_3_use_sorcery_creature；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_use_sorcery_creature.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_use_sorcery_creature_bottle shape-shifter-curse:form_familiar_fox_3_use_sorcery_creature_bottle

能力 ID：shape-shifter-curse:form_familiar_fox_3_use_sorcery_creature_bottle；类型：apoli:action_on_entity_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_3_use_sorcery_creature_bottle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_3_water_speed_down shape-shifter-curse:form_familiar_fox_3_water_speed_down

能力 ID：shape-shifter-curse:form_familiar_fox_3_water_speed_down；类型：shape-shifter-curse:in_water_speed_modifier。

modifier：0.75


来源：data/shape-shifter-curse/powers/form_familiar_fox_3_water_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_explosive_charm_paper shape-shifter-curse:form_familiar_fox_explosive_charm_paper

能力 ID：shape-shifter-curse:form_familiar_fox_explosive_charm_paper；类型：apoli:action_on_item_use。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_familiar_fox_fire_arrow_cooldown","comparison":"<","compare_to":1},{"type":"shape-shifter-curse:has_mana","mana":2.5}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_explosive_charm_paper.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_explosive_charm_paper_damage shape-shifter-curse:form_familiar_fox_explosive_charm_paper_damage

能力 ID：shape-shifter-curse:form_familiar_fox_explosive_charm_paper_damage；类型：apoli:modify_projectile_damage。

modifier：{"operation":"set_total","value":16.0}


来源：data/shape-shifter-curse/powers/form_familiar_fox_explosive_charm_paper_damage.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_fire_arrow_cooldown shape-shifter-curse:form_familiar_fox_fire_arrow_cooldown

能力 ID：shape-shifter-curse:form_familiar_fox_fire_arrow_cooldown；类型：apoli:cooldown。

cooldown：20


来源：data/shape-shifter-curse/powers/form_familiar_fox_fire_arrow_cooldown.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_fire_explode_cooldown shape-shifter-curse:form_familiar_fox_fire_explode_cooldown

能力 ID：shape-shifter-curse:form_familiar_fox_fire_explode_cooldown；类型：apoli:cooldown。

cooldown：60


来源：data/shape-shifter-curse/powers/form_familiar_fox_fire_explode_cooldown.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_fireball_kind_boolean_resource shape-shifter-curse:form_familiar_fox_fireball_kind_boolean_resource

能力 ID：shape-shifter-curse:form_familiar_fox_fireball_kind_boolean_resource；类型：apoli:resource。

min：0
max：1


来源：data/shape-shifter-curse/powers/form_familiar_fox_fireball_kind_boolean_resource.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_instinct_biome shape-shifter-curse:form_familiar_fox_instinct_biome

能力 ID：shape-shifter-curse:form_familiar_fox_instinct_biome；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:biome","condition":{"type":"apoli:in_tag","tag":"minecraft:is_taiga"}}


来源：data/shape-shifter-curse/powers/form_familiar_fox_instinct_biome.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_instinct_eat_berry shape-shifter-curse:form_familiar_fox_instinct_eat_berry

能力 ID：shape-shifter-curse:form_familiar_fox_instinct_eat_berry；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/form_familiar_fox_instinct_eat_berry.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_instinct_in_berry_bush shape-shifter-curse:form_familiar_fox_instinct_in_berry_bush

能力 ID：shape-shifter-curse:form_familiar_fox_instinct_in_berry_bush；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:or","conditions":[{"type":"apoli:block","block":"minecraft:sweet_berry_bush"}]},"radius":1,"shape":"cube","comparison":">=","compare_to":1}


来源：data/shape-shifter-curse/powers/form_familiar_fox_instinct_in_berry_bush.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_instinct_looking_witch shape-shifter-curse:form_familiar_fox_instinct_looking_witch

能力 ID：shape-shifter-curse:form_familiar_fox_instinct_looking_witch；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:raycast","distance":20,"block":false,"entity":true,"shape_type":"visual","fluid_handling":"any","hit_bientity_condition":{"type":"apoli:target_condition","condition":{"type":"apoli:or","conditions":[{"type":"apoli:entity_type","entity_type":"minecraft:witch"},{"type":"apoli:entity_type","entity_type":"minecraft:evoker"}]}}}


来源：data/shape-shifter-curse/powers/form_familiar_fox_instinct_looking_witch.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_instinct_moving_on_snow shape-shifter-curse:form_familiar_fox_instinct_moving_on_snow

能力 ID：shape-shifter-curse:form_familiar_fox_instinct_moving_on_snow；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:or","conditions":[{"type":"apoli:on_block","block_condition":{"type":"apoli:block","block":"minecraft:snow_block"}},{"type":"apoli:on_block","block_condition":{"type":"apoli:block_state","property":"snowy","value":true}}]},{"type":"apoli:moving"}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_instinct_moving_on_snow.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_low_mana_exhaustion shape-shifter-curse:form_familiar_fox_low_mana_exhaustion

能力 ID：shape-shifter-curse:form_familiar_fox_low_mana_exhaustion；类型：apoli:modify_exhaustion。

condition：{"type":"shape-shifter-curse:has_mana_percent","mana_percent":0.333,"inverted":true}
modifier：{"name":"Increased exhaustion","operation":"multiply_base","value":3.0}


来源：data/shape-shifter-curse/powers/form_familiar_fox_low_mana_exhaustion.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sound shape-shifter-curse:form_familiar_fox_sound

能力 ID：shape-shifter-curse:form_familiar_fox_sound；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:enable_random_sound"},{"type":"shape-shifter-curse:chance","chance":0.3}]}


来源：data/shape-shifter-curse/powers/form_familiar_fox_sound.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sound_hurt shape-shifter-curse:form_familiar_fox_sound_hurt

能力 ID：shape-shifter-curse:form_familiar_fox_sound_hurt；类型：apoli:self_action_when_hit。

condition：{"type":"shape-shifter-curse:chance","chance":0.8}


来源：data/shape-shifter-curse/powers/form_familiar_fox_sound_hurt.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_sound_key shape-shifter-curse:form_familiar_fox_sound_key

能力 ID：shape-shifter-curse:form_familiar_fox_sound_key；类型：apoli:active_self。

cooldown：5


来源：data/shape-shifter-curse/powers/form_familiar_fox_sound_key.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_familiar_fox_use_entity_cooldown shape-shifter-curse:form_familiar_fox_use_entity_cooldown

能力 ID：shape-shifter-curse:form_familiar_fox_use_entity_cooldown；类型：apoli:cooldown。

cooldown：60


来源：data/shape-shifter-curse/powers/form_familiar_fox_use_entity_cooldown.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_feral_cat_vf_health shape-shifter-curse:form_feral_cat_vf_health

能力 ID：shape-shifter-curse:form_feral_cat_vf_health；类型：apoli:attribute。

modifier：{"name":"2 health reduction","attribute":"minecraft:generic.max_health","value":-2.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_feral_cat_vf_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_feral_cat_vf_jump_boost shape-shifter-curse:form_feral_cat_vf_jump_boost

能力 ID：shape-shifter-curse:form_feral_cat_vf_jump_boost；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_feral_cat_vf_jump_boost.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_feral_cat_vf_speed_up shape-shifter-curse:form_feral_cat_vf_speed_up

能力 ID：shape-shifter-curse:form_feral_cat_vf_speed_up；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_feral_cat_vf_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_force_disable_feet_armor shape-shifter-curse:form_force_disable_feet_armor

能力 ID：shape-shifter-curse:form_force_disable_feet_armor；类型：apoli:restrict_armor。



来源：data/shape-shifter-curse/powers/form_force_disable_feet_armor.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_force_disable_leg_armor shape-shifter-curse:form_force_disable_leg_armor

能力 ID：shape-shifter-curse:form_force_disable_leg_armor；类型：apoli:restrict_armor。



来源：data/shape-shifter-curse/powers/form_force_disable_leg_armor.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_instinct_use_catalyst shape-shifter-curse:form_instinct_use_catalyst

能力 ID：shape-shifter-curse:form_instinct_use_catalyst；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/form_instinct_use_catalyst.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_instinct_use_golden_apple shape-shifter-curse:form_instinct_use_golden_apple

能力 ID：shape-shifter-curse:form_instinct_use_golden_apple；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/form_instinct_use_golden_apple.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_meat_food_down shape-shifter-curse:form_meat_food_down

能力 ID：shape-shifter-curse:form_meat_food_down；类型：apoli:modify_food。

food_modifier：{"name":"Decreased food points","operation":"multiply_base_multiplicative","value":-0.5}
saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-0.5}


来源：data/shape-shifter-curse/powers/form_meat_food_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_0_health shape-shifter-curse:form_ocelot_0_health

能力 ID：shape-shifter-curse:form_ocelot_0_health；类型：apoli:attribute。

modifier：{"name":"Form ocelot0 health reduction","attribute":"minecraft:generic.max_health","value":-2.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_ocelot_0_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_1_health shape-shifter-curse:form_ocelot_1_health

能力 ID：shape-shifter-curse:form_ocelot_1_health；类型：apoli:attribute。

modifier：{"name":"Form ocelot0 health reduction","attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_ocelot_1_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_1_sneaking_jump_far shape-shifter-curse:form_ocelot_1_sneaking_jump_far

能力 ID：shape-shifter-curse:form_ocelot_1_sneaking_jump_far；类型：apoli:active_self。

cooldown：10
condition：{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:food_level","comparison":">=","compare_to":6}]}


来源：data/shape-shifter-curse/powers/form_ocelot_1_sneaking_jump_far.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_arrow_dodge shape-shifter-curse:form_ocelot_2_arrow_dodge

能力 ID：shape-shifter-curse:form_ocelot_2_arrow_dodge；类型：shape-shifter-curse:projectile_dodge。

cooldown：20


来源：data/shape-shifter-curse/powers/form_ocelot_2_arrow_dodge.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_barepaw_sweeping shape-shifter-curse:form_ocelot_2_barepaw_sweeping

能力 ID：shape-shifter-curse:form_ocelot_2_barepaw_sweeping；类型：shape-shifter-curse:always_sweeping。

condition：{"type":"apoli:or","conditions":[{"type":"apoli:inventory","process_mode":"items","item_condition":{"type":"apoli:harvest_level","comparison":"<=","compare_to":1},"slots":["weapon.mainhand"]},{"type":"apoli:inventory","process_mode":"items","comparison":"==","compare_to":0,"slots":["weapon.mainhand"]}]}


来源：data/shape-shifter-curse/powers/form_ocelot_2_barepaw_sweeping.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_climb_wood shape-shifter-curse:form_ocelot_2_climb_wood

能力 ID：shape-shifter-curse:form_ocelot_2_climb_wood；类型：shape-shifter-curse:climbing_ex。



来源：data/shape-shifter-curse/powers/form_ocelot_2_climb_wood.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_health shape-shifter-curse:form_ocelot_2_health

能力 ID：shape-shifter-curse:form_ocelot_2_health；类型：apoli:attribute。

modifier：{"name":"Form ocelot0 health reduction","attribute":"minecraft:generic.max_health","value":-6.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_ocelot_2_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_light_armor shape-shifter-curse:form_ocelot_2_light_armor

能力 ID：shape-shifter-curse:form_ocelot_2_light_armor；类型：apoli:restrict_armor。



来源：data/shape-shifter-curse/powers/form_ocelot_2_light_armor.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## 生肉食性 shape-shifter-curse:form_ocelot_2_raw_meat_only

能力 ID：shape-shifter-curse:form_ocelot_2_raw_meat_only；类型：apoli:modify_food。

food_modifiers：[{"operation":"set_total","value":0}]
saturation_modifiers：[{"operation":"set_total","value":0}]


来源：data/shape-shifter-curse/powers/form_ocelot_2_raw_meat_only.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_sneaking_hunger shape-shifter-curse:form_ocelot_2_sneaking_hunger

能力 ID：shape-shifter-curse:form_ocelot_2_sneaking_hunger；类型：apoli:exhaust。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:moving"},{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0}]}


来源：data/shape-shifter-curse/powers/form_ocelot_2_sneaking_hunger.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_sneaking_jump_far shape-shifter-curse:form_ocelot_2_sneaking_jump_far

能力 ID：shape-shifter-curse:form_ocelot_2_sneaking_jump_far；类型：shape-shifter-curse:action_on_jump。



来源：data/shape-shifter-curse/powers/form_ocelot_2_sneaking_jump_far.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_sneaking_jump_high shape-shifter-curse:form_ocelot_2_sneaking_jump_high

能力 ID：shape-shifter-curse:form_ocelot_2_sneaking_jump_high；类型：apoli:modify_jump。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:food_level","comparison":">=","compare_to":6}]}
modifier：{"name":"Bonus jump force while sneaking","operation":"multiply_base","value":0.15}


来源：data/shape-shifter-curse/powers/form_ocelot_2_sneaking_jump_high.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_sneaking_scale shape-shifter-curse:form_ocelot_2_sneaking_scale

能力 ID：shape-shifter-curse:form_ocelot_2_sneaking_scale；类型：shape-shifter-curse:condition_scale。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"}]}


来源：data/shape-shifter-curse/powers/form_ocelot_2_sneaking_scale.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_sneaking_speed_up shape-shifter-curse:form_ocelot_2_sneaking_speed_up

能力 ID：shape-shifter-curse:form_ocelot_2_sneaking_speed_up；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:sneaking"},{"type":"apoli:food_level","comparison":">=","compare_to":6}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"addition","value":0.65,"name":"Sneaking rush"}


来源：data/shape-shifter-curse/powers/form_ocelot_2_sneaking_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_2_water_speed_down shape-shifter-curse:form_ocelot_2_water_speed_down

能力 ID：shape-shifter-curse:form_ocelot_2_water_speed_down；类型：shape-shifter-curse:in_water_speed_modifier。

modifier：0.8


来源：data/shape-shifter-curse/powers/form_ocelot_2_water_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_3_climb_all shape-shifter-curse:form_ocelot_3_climb_all

能力 ID：shape-shifter-curse:form_ocelot_3_climb_all；类型：shape-shifter-curse:climbing_ex。



来源：data/shape-shifter-curse/powers/form_ocelot_3_climb_all.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_3_hunger shape-shifter-curse:form_ocelot_3_hunger

能力 ID：shape-shifter-curse:form_ocelot_3_hunger；类型：apoli:exhaust。



来源：data/shape-shifter-curse/powers/form_ocelot_3_hunger.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_3_jump_far shape-shifter-curse:form_ocelot_3_jump_far

能力 ID：shape-shifter-curse:form_ocelot_3_jump_far；类型：shape-shifter-curse:action_on_jump。



来源：data/shape-shifter-curse/powers/form_ocelot_3_jump_far.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_3_jump_high shape-shifter-curse:form_ocelot_3_jump_high

能力 ID：shape-shifter-curse:form_ocelot_3_jump_high；类型：apoli:modify_jump。

modifier：{"name":"Bonus jump force","operation":"multiply_base","value":0.25}


来源：data/shape-shifter-curse/powers/form_ocelot_3_jump_high.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_3_sneaking_jump_clash shape-shifter-curse:form_ocelot_3_sneaking_jump_clash

能力 ID：shape-shifter-curse:form_ocelot_3_sneaking_jump_clash；类型：shape-shifter-curse:sneaking_jump_clash。



来源：data/shape-shifter-curse/powers/form_ocelot_3_sneaking_jump_clash.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_3_sneaking_step_height shape-shifter-curse:form_ocelot_3_sneaking_step_height

能力 ID：shape-shifter-curse:form_ocelot_3_sneaking_step_height；类型：shape-shifter-curse:modify_step_height。

condition：{"type":"apoli:sneaking"}


来源：data/shape-shifter-curse/powers/form_ocelot_3_sneaking_step_height.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_hiss_phantom shape-shifter-curse:form_ocelot_hiss_phantom

能力 ID：shape-shifter-curse:form_ocelot_hiss_phantom；类型：shape-shifter-curse:hiss_phantom_power。



来源：data/shape-shifter-curse/powers/form_ocelot_hiss_phantom.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_instinct_attack_barepaw shape-shifter-curse:form_ocelot_instinct_attack_barepaw

能力 ID：shape-shifter-curse:form_ocelot_instinct_attack_barepaw；类型：apoli:action_on_hit。



来源：data/shape-shifter-curse/powers/form_ocelot_instinct_attack_barepaw.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_instinct_biome shape-shifter-curse:form_ocelot_instinct_biome

能力 ID：shape-shifter-curse:form_ocelot_instinct_biome；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:biome","condition":{"type":"apoli:in_tag","tag":"minecraft:is_jungle"}}


来源：data/shape-shifter-curse/powers/form_ocelot_instinct_biome.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_instinct_eat_raw_meat shape-shifter-curse:form_ocelot_instinct_eat_raw_meat

能力 ID：shape-shifter-curse:form_ocelot_instinct_eat_raw_meat；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/form_ocelot_instinct_eat_raw_meat.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_instinct_on_leaf shape-shifter-curse:form_ocelot_instinct_on_leaf

能力 ID：shape-shifter-curse:form_ocelot_instinct_on_leaf；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:on_block","block_condition":{"type":"apoli:in_tag","tag":"shape-shifter-curse:leaves"}}


来源：data/shape-shifter-curse/powers/form_ocelot_instinct_on_leaf.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_sound shape-shifter-curse:form_ocelot_sound

能力 ID：shape-shifter-curse:form_ocelot_sound；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:enable_random_sound"},{"type":"shape-shifter-curse:chance","chance":0.3}]}


来源：data/shape-shifter-curse/powers/form_ocelot_sound.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_sound_hurt shape-shifter-curse:form_ocelot_sound_hurt

能力 ID：shape-shifter-curse:form_ocelot_sound_hurt；类型：apoli:self_action_when_hit。

condition：{"type":"shape-shifter-curse:chance","chance":0.8}


来源：data/shape-shifter-curse/powers/form_ocelot_sound_hurt.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_ocelot_sound_key shape-shifter-curse:form_ocelot_sound_key

能力 ID：shape-shifter-curse:form_ocelot_sound_key；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_ocelot_sound_key.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_raw_fish_food_up shape-shifter-curse:form_raw_fish_food_up

能力 ID：shape-shifter-curse:form_raw_fish_food_up；类型：apoli:modify_food。

food_modifiers：[{"name":"increase food points","operation":"addition","value":3.0},{"name":"increase food points","operation":"min_base","value":3.0},{"name":"increase food points","operation":"multiply_base","value":0.25}]
saturation_modifiers：[{"name":"increase food points","operation":"min_total","value":0.6}]


来源：data/shape-shifter-curse/powers/form_raw_fish_food_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_raw_meat_food_up shape-shifter-curse:form_raw_meat_food_up

能力 ID：shape-shifter-curse:form_raw_meat_food_up；类型：apoli:modify_food。

food_modifiers：[{"name":"increase food points","operation":"addition","value":4.0},{"name":"increase food points","operation":"min_base","value":4.0},{"name":"increase food points","operation":"multiply_total_additive","value":0.25}]
saturation_modifiers：[{"name":"increase food points","operation":"min_total","value":0.7}]


来源：data/shape-shifter-curse/powers/form_raw_meat_food_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_0_desert_health shape-shifter-curse:form_snow_fox_0_desert_health

能力 ID：shape-shifter-curse:form_snow_fox_0_desert_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：20
condition：{"type":"apoli:or","conditions":[{"type":"apoli:and","conditions":[{"type":"apoli:biome","condition":{"type":"apoli:temperature","comparison":">=","compare_to":1}},{"type":"apoli:and","conditions":[{"type":"apoli:time_of_day","comparison":">=","compare_to":1000},{"type":"apoli:time_of_day","comparison":"<=","compare_to":12000}]}]},{"type":"apoli:biome","condition":{"type":"apoli:in_tag","tag":"minecraft:is_nether"}}]}
modifier：{"attribute":"minecraft:generic.max_health","value":-2.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_snow_fox_0_desert_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_0_triple_jump shape-shifter-curse:form_snow_fox_0_triple_jump

能力 ID：shape-shifter-curse:form_snow_fox_0_triple_jump；类型：shape-shifter-curse:triple_jump。



来源：data/shape-shifter-curse/powers/form_snow_fox_0_triple_jump.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_1_critical_attack_up shape-shifter-curse:form_snow_fox_1_critical_attack_up

能力 ID：shape-shifter-curse:form_snow_fox_1_critical_attack_up；类型：shape-shifter-curse:critical_damage_modifier。



来源：data/shape-shifter-curse/powers/form_snow_fox_1_critical_attack_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_1_desert_health shape-shifter-curse:form_snow_fox_1_desert_health

能力 ID：shape-shifter-curse:form_snow_fox_1_desert_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：20
condition：{"type":"apoli:or","conditions":[{"type":"apoli:and","conditions":[{"type":"apoli:biome","condition":{"type":"apoli:temperature","comparison":">=","compare_to":1}},{"type":"apoli:and","conditions":[{"type":"apoli:time_of_day","comparison":">=","compare_to":1000},{"type":"apoli:time_of_day","comparison":"<=","compare_to":12000}]}]},{"type":"apoli:biome","condition":{"type":"apoli:in_tag","tag":"minecraft:is_nether"}}]}
modifier：{"attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_snow_fox_1_desert_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_1_triple_jump shape-shifter-curse:form_snow_fox_1_triple_jump

能力 ID：shape-shifter-curse:form_snow_fox_1_triple_jump；类型：shape-shifter-curse:triple_jump。



来源：data/shape-shifter-curse/powers/form_snow_fox_1_triple_jump.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_2_desert_health shape-shifter-curse:form_snow_fox_2_desert_health

能力 ID：shape-shifter-curse:form_snow_fox_2_desert_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：20
condition：{"type":"apoli:or","conditions":[{"type":"apoli:and","conditions":[{"type":"apoli:biome","condition":{"type":"apoli:temperature","comparison":">=","compare_to":1}},{"type":"apoli:and","conditions":[{"type":"apoli:time_of_day","comparison":">=","compare_to":1000},{"type":"apoli:time_of_day","comparison":"<=","compare_to":12000}]}]},{"type":"apoli:biome","condition":{"type":"apoli:in_tag","tag":"minecraft:is_nether"}}]}
modifier：{"attribute":"minecraft:generic.max_health","value":-6.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_snow_fox_2_desert_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_2_falling_protection shape-shifter-curse:form_snow_fox_2_falling_protection

能力 ID：shape-shifter-curse:form_snow_fox_2_falling_protection；类型：shape-shifter-curse:falling_protection。



来源：data/shape-shifter-curse/powers/form_snow_fox_2_falling_protection.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_2_plain_health shape-shifter-curse:form_snow_fox_2_plain_health

能力 ID：shape-shifter-curse:form_snow_fox_2_plain_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:and","conditions":[{"type":"apoli:biome","condition":{"type":"apoli:temperature","comparison":"<","compare_to":1}},{"type":"apoli:biome","condition":{"type":"apoli:temperature","comparison":">","compare_to":0.7}}]},{"type":"apoli:and","conditions":[{"type":"apoli:time_of_day","comparison":">=","compare_to":1000},{"type":"apoli:time_of_day","comparison":"<=","compare_to":12000}]}]}
modifier：{"attribute":"minecraft:generic.max_health","value":-2.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_snow_fox_2_plain_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_2_snowball_up shape-shifter-curse:form_snow_fox_2_snowball_up

能力 ID：shape-shifter-curse:form_snow_fox_2_snowball_up；类型：apoli:modify_projectile_damage。

modifier：{"operation":"addition","value":5}


来源：data/shape-shifter-curse/powers/form_snow_fox_2_snowball_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_2_tan_temperature_remap shape-shifter-curse:form_snow_fox_2_tan_temperature_remap

能力 ID：shape-shifter-curse:form_snow_fox_2_tan_temperature_remap；类型：shape-shifter-curse:tan_form_temperature_modifier。



来源：data/shape-shifter-curse/powers/form_snow_fox_2_tan_temperature_remap.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_2_triple_jump shape-shifter-curse:form_snow_fox_2_triple_jump

能力 ID：shape-shifter-curse:form_snow_fox_2_triple_jump；类型：shape-shifter-curse:triple_jump。



来源：data/shape-shifter-curse/powers/form_snow_fox_2_triple_jump.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_air_speed shape-shifter-curse:form_snow_fox_3_air_speed

能力 ID：shape-shifter-curse:form_snow_fox_3_air_speed；类型：apoli:modify_air_speed。

modifier：{"operation":"multiply_total","value":1.25}


来源：data/shape-shifter-curse/powers/form_snow_fox_3_air_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_burn_damage_up shape-shifter-curse:form_snow_fox_3_burn_damage_up

能力 ID：shape-shifter-curse:form_snow_fox_3_burn_damage_up；类型：shape-shifter-curse:burn_damage_modifier。

modifier：1.0


来源：data/shape-shifter-curse/powers/form_snow_fox_3_burn_damage_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_desert_health shape-shifter-curse:form_snow_fox_3_desert_health

能力 ID：shape-shifter-curse:form_snow_fox_3_desert_health；类型：shape-shifter-curse:delay_attribute。

tick_rate：20
condition：{"type":"apoli:or","conditions":[{"type":"apoli:and","conditions":[{"type":"apoli:biome","condition":{"type":"apoli:temperature","comparison":">=","compare_to":1}},{"type":"apoli:and","conditions":[{"type":"apoli:time_of_day","comparison":">=","compare_to":1000},{"type":"apoli:time_of_day","comparison":"<=","compare_to":12000}]}]},{"type":"apoli:biome","condition":{"type":"apoli:in_tag","tag":"minecraft:is_nether"}}]}
modifier：{"attribute":"minecraft:generic.max_health","value":-8.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_snow_fox_3_desert_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_enhanced_falling_attack shape-shifter-curse:form_snow_fox_3_enhanced_falling_attack

能力 ID：shape-shifter-curse:form_snow_fox_3_enhanced_falling_attack；类型：shape-shifter-curse:enhanced_falling_attack。



来源：data/shape-shifter-curse/powers/form_snow_fox_3_enhanced_falling_attack.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_fall_speed shape-shifter-curse:form_snow_fox_3_fall_speed

能力 ID：shape-shifter-curse:form_snow_fox_3_fall_speed；类型：apoli:modify_falling。



来源：data/shape-shifter-curse/powers/form_snow_fox_3_fall_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_falling_bouncing shape-shifter-curse:form_snow_fox_3_falling_bouncing

能力 ID：shape-shifter-curse:form_snow_fox_3_falling_bouncing；类型：apoli:action_on_block_use。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_snow_fox_bounce_cooldown","comparison":"<","compare_to":1},{"type":"apoli:fall_distance","comparison":">=","compare_to":0.5},{"type":"apoli:fall_flying","inverted":true}]}


来源：data/shape-shifter-curse/powers/form_snow_fox_3_falling_bouncing.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_falling_protection shape-shifter-curse:form_snow_fox_3_falling_protection

能力 ID：shape-shifter-curse:form_snow_fox_3_falling_protection；类型：shape-shifter-curse:falling_protection。



来源：data/shape-shifter-curse/powers/form_snow_fox_3_falling_protection.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_fox_friendly shape-shifter-curse:form_snow_fox_3_fox_friendly

能力 ID：shape-shifter-curse:form_snow_fox_3_fox_friendly；类型：shape-shifter-curse:fox_friendly。



来源：data/shape-shifter-curse/powers/form_snow_fox_3_fox_friendly.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_keep_burn shape-shifter-curse:form_snow_fox_3_keep_burn

能力 ID：shape-shifter-curse:form_snow_fox_3_keep_burn；类型：apoli:action_over_time。

condition：{"type":"apoli:on_fire"}


来源：data/shape-shifter-curse/powers/form_snow_fox_3_keep_burn.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_near_fire_damage shape-shifter-curse:form_snow_fox_3_near_fire_damage

能力 ID：shape-shifter-curse:form_snow_fox_3_near_fire_damage；类型：apoli:damage_over_time。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:or","conditions":[{"type":"apoli:block","block":"minecraft:fire"},{"type":"apoli:and","conditions":[{"type":"apoli:block","block":"minecraft:campfire"},{"type":"apoli:block_state","property":"lit","value":"true"}]}]},"radius":1,"shape":"cube","comparison":">=","compare_to":1}


来源：data/shape-shifter-curse/powers/form_snow_fox_3_near_fire_damage.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_near_lava_damage shape-shifter-curse:form_snow_fox_3_near_lava_damage

能力 ID：shape-shifter-curse:form_snow_fox_3_near_lava_damage；类型：apoli:damage_over_time。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:or","conditions":[{"type":"apoli:block","block":"minecraft:lava"}]},"radius":1,"shape":"cube","comparison":">=","compare_to":1}


来源：data/shape-shifter-curse/powers/form_snow_fox_3_near_lava_damage.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_particle shape-shifter-curse:form_snow_fox_3_particle

能力 ID：shape-shifter-curse:form_snow_fox_3_particle；类型：apoli:particle。



来源：data/shape-shifter-curse/powers/form_snow_fox_3_particle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_plain_health shape-shifter-curse:form_snow_fox_3_plain_health

能力 ID：shape-shifter-curse:form_snow_fox_3_plain_health；类型：apoli:conditioned_attribute。

tick_rate：20
condition：{"type":"apoli:and","conditions":[{"type":"apoli:and","conditions":[{"type":"apoli:biome","condition":{"type":"apoli:temperature","comparison":"<","compare_to":1}},{"type":"apoli:biome","condition":{"type":"apoli:temperature","comparison":">","compare_to":0.7}}]},{"type":"apoli:and","conditions":[{"type":"apoli:time_of_day","comparison":">=","compare_to":1000},{"type":"apoli:time_of_day","comparison":"<=","compare_to":12000}]}]}
modifier：{"attribute":"minecraft:generic.max_health","value":-4.0,"operation":"addition"}


来源：data/shape-shifter-curse/powers/form_snow_fox_3_plain_health.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_snowball_up shape-shifter-curse:form_snow_fox_3_snowball_up

能力 ID：shape-shifter-curse:form_snow_fox_3_snowball_up；类型：apoli:modify_projectile_damage。

modifier：{"operation":"addition","value":7.5}


来源：data/shape-shifter-curse/powers/form_snow_fox_3_snowball_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_sweet_berry_up shape-shifter-curse:form_snow_fox_3_sweet_berry_up

能力 ID：shape-shifter-curse:form_snow_fox_3_sweet_berry_up；类型：apoli:modify_food。

food_modifier：{"name":"increase food points","operation":"addition","value":6.0}
saturation_modifier：{"name":"increase food points","operation":"addition","value":0.5}


来源：data/shape-shifter-curse/powers/form_snow_fox_3_sweet_berry_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_tan_temperature_remap shape-shifter-curse:form_snow_fox_3_tan_temperature_remap

能力 ID：shape-shifter-curse:form_snow_fox_3_tan_temperature_remap；类型：shape-shifter-curse:tan_form_temperature_modifier。



来源：data/shape-shifter-curse/powers/form_snow_fox_3_tan_temperature_remap.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_triple_jump shape-shifter-curse:form_snow_fox_3_triple_jump

能力 ID：shape-shifter-curse:form_snow_fox_3_triple_jump；类型：shape-shifter-curse:triple_jump。



来源：data/shape-shifter-curse/powers/form_snow_fox_3_triple_jump.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_3_water_bottle_put_out_fire shape-shifter-curse:form_snow_fox_3_water_bottle_put_out_fire

能力 ID：shape-shifter-curse:form_snow_fox_3_water_bottle_put_out_fire；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/form_snow_fox_3_water_bottle_put_out_fire.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_bottled_snowfall_cooldown shape-shifter-curse:form_snow_fox_bottled_snowfall_cooldown

能力 ID：shape-shifter-curse:form_snow_fox_bottled_snowfall_cooldown；类型：apoli:cooldown。

cooldown：8


来源：data/shape-shifter-curse/powers/form_snow_fox_bottled_snowfall_cooldown.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_bottled_snowfall_plus_tool shape-shifter-curse:form_snow_fox_bottled_snowfall_plus_tool

能力 ID：shape-shifter-curse:form_snow_fox_bottled_snowfall_plus_tool；类型：apoli:action_on_item_use。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_snow_fox_bottled_snowfall_cooldown","comparison":"<","compare_to":1}]}


来源：data/shape-shifter-curse/powers/form_snow_fox_bottled_snowfall_plus_tool.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_bottled_snowfall_tool shape-shifter-curse:form_snow_fox_bottled_snowfall_tool

能力 ID：shape-shifter-curse:form_snow_fox_bottled_snowfall_tool；类型：apoli:action_on_item_use。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:resource","resource":"shape-shifter-curse:form_snow_fox_bottled_snowfall_cooldown","comparison":"<","compare_to":1},{"type":"apoli:food_level","comparison":">","compare_to":0}]}


来源：data/shape-shifter-curse/powers/form_snow_fox_bottled_snowfall_tool.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_bounce_cooldown shape-shifter-curse:form_snow_fox_bounce_cooldown

能力 ID：shape-shifter-curse:form_snow_fox_bounce_cooldown；类型：apoli:cooldown。

cooldown：10


来源：data/shape-shifter-curse/powers/form_snow_fox_bounce_cooldown.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_instinct_sprint_jump shape-shifter-curse:form_snow_fox_instinct_sprint_jump

能力 ID：shape-shifter-curse:form_snow_fox_instinct_sprint_jump；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:on_block","inverted":true},{"type":"apoli:sprinting"}]}


来源：data/shape-shifter-curse/powers/form_snow_fox_instinct_sprint_jump.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_snowball_transform shape-shifter-curse:form_snow_fox_snowball_transform

能力 ID：shape-shifter-curse:form_snow_fox_snowball_transform；类型：shape-shifter-curse:snowball_block_transform。



来源：data/shape-shifter-curse/powers/form_snow_fox_snowball_transform.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_snow_fox_walk_on_powder_snow shape-shifter-curse:form_snow_fox_walk_on_powder_snow

能力 ID：shape-shifter-curse:form_snow_fox_walk_on_powder_snow；类型：shape-shifter-curse:powder_snow_walker。



来源：data/shape-shifter-curse/powers/form_snow_fox_walk_on_powder_snow.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_0_dew_covered_cobweb_instinct shape-shifter-curse:form_spider_0_dew_covered_cobweb_instinct

能力 ID：shape-shifter-curse:form_spider_0_dew_covered_cobweb_instinct；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:block","block":"shape-shifter-curse:dew_covered_cobweb"},"radius":1,"shape":"cube","comparison":">=","compare_to":6}


来源：data/shape-shifter-curse/powers/form_spider_0_dew_covered_cobweb_instinct.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_0_dew_covered_cobweb_particle shape-shifter-curse:form_spider_0_dew_covered_cobweb_particle

能力 ID：shape-shifter-curse:form_spider_0_dew_covered_cobweb_particle；类型：apoli:action_over_time。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:block","block":"shape-shifter-curse:dew_covered_cobweb"},"radius":1,"shape":"cube","comparison":">=","compare_to":6}


来源：data/shape-shifter-curse/powers/form_spider_0_dew_covered_cobweb_particle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_0_instinct_max_particle shape-shifter-curse:form_spider_0_instinct_max_particle

能力 ID：shape-shifter-curse:form_spider_0_instinct_max_particle；类型：apoli:action_over_time。

condition：{"type":"shape-shifter-curse:instinct_value","comparison":">=","compare_to":95.0}


来源：data/shape-shifter-curse/powers/form_spider_0_instinct_max_particle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_0_mana shape-shifter-curse:form_spider_0_mana

能力 ID：shape-shifter-curse:form_spider_0_mana；类型：shape-shifter-curse:mana_type_power。



来源：data/shape-shifter-curse/powers/form_spider_0_mana.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_0_vegetarian_saturation_down shape-shifter-curse:form_spider_0_vegetarian_saturation_down

能力 ID：shape-shifter-curse:form_spider_0_vegetarian_saturation_down；类型：apoli:modify_food。

saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-0.2}


来源：data/shape-shifter-curse/powers/form_spider_0_vegetarian_saturation_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_0_web_projectile shape-shifter-curse:form_spider_0_web_projectile

能力 ID：shape-shifter-curse:form_spider_0_web_projectile；类型：shape-shifter-curse:charge_action。



来源：data/shape-shifter-curse/powers/form_spider_0_web_projectile.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_1_base_instinct shape-shifter-curse:form_spider_1_base_instinct

能力 ID：shape-shifter-curse:form_spider_1_base_instinct；类型：shape-shifter-curse:add_sustained_instinct。



来源：data/shape-shifter-curse/powers/form_spider_1_base_instinct.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_1_cocoon_instinct shape-shifter-curse:form_spider_1_cocoon_instinct

能力 ID：shape-shifter-curse:form_spider_1_cocoon_instinct；类型：shape-shifter-curse:add_sustained_instinct。

condition：{"type":"apoli:block_in_radius","block_condition":{"type":"apoli:or","conditions":[{"type":"apoli:block","block":"shape-shifter-curse:dew_covered_cobweb"}]},"radius":1,"shape":"cube","comparison":">=","compare_to":6}


来源：data/shape-shifter-curse/powers/form_spider_1_cocoon_instinct.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_1_darkness shape-shifter-curse:form_spider_1_darkness

能力 ID：shape-shifter-curse:form_spider_1_darkness；类型：apoli:action_over_time。



来源：data/shape-shifter-curse/powers/form_spider_1_darkness.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_1_in_cocoon_features shape-shifter-curse:form_spider_1_in_cocoon_features

能力 ID：shape-shifter-curse:form_spider_1_in_cocoon_features；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_spider_1_in_cocoon_features.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_1_instinct_max_particle shape-shifter-curse:form_spider_1_instinct_max_particle

能力 ID：shape-shifter-curse:form_spider_1_instinct_max_particle；类型：apoli:action_over_time。

condition：{"type":"shape-shifter-curse:instinct_value","comparison":">=","compare_to":95.0}


来源：data/shape-shifter-curse/powers/form_spider_1_instinct_max_particle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_1_jump_height shape-shifter-curse:form_spider_1_jump_height

能力 ID：shape-shifter-curse:form_spider_1_jump_height；类型：apoli:modify_jump。

modifier：{"operation":"multiply_base_multiplicative","value":-0.75}


来源：data/shape-shifter-curse/powers/form_spider_1_jump_height.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_1_particle shape-shifter-curse:form_spider_1_particle

能力 ID：shape-shifter-curse:form_spider_1_particle；类型：apoli:action_over_time。



来源：data/shape-shifter-curse/powers/form_spider_1_particle.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_1_speed_down shape-shifter-curse:form_spider_1_speed_down

能力 ID：shape-shifter-curse:form_spider_1_speed_down；类型：apoli:attribute。

modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":-0.85,"name":"Movement speed down"}


来源：data/shape-shifter-curse/powers/form_spider_1_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_2_mana shape-shifter-curse:form_spider_2_mana

能力 ID：shape-shifter-curse:form_spider_2_mana；类型：shape-shifter-curse:mana_type_power。



来源：data/shape-shifter-curse/powers/form_spider_2_mana.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_2_meat_and_fluid_cocoon_only shape-shifter-curse:form_spider_2_meat_and_fluid_cocoon_only

能力 ID：shape-shifter-curse:form_spider_2_meat_and_fluid_cocoon_only；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_spider_2_meat_and_fluid_cocoon_only.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_2_speed_down shape-shifter-curse:form_spider_2_speed_down

能力 ID：shape-shifter-curse:form_spider_2_speed_down；类型：apoli:conditioned_attribute。

tick_rate：2
condition：{"type":"apoli:and","conditions":[{"type":"apoli:on_block"},{"type":"apoli:in_block","block_condition":{"type":"apoli:block","block":"minecraft:water"},"inverted":true}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_base","value":-0.1}


来源：data/shape-shifter-curse/powers/form_spider_2_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_2_web_projectile shape-shifter-curse:form_spider_2_web_projectile

能力 ID：shape-shifter-curse:form_spider_2_web_projectile；类型：shape-shifter-curse:charge_action。



来源：data/shape-shifter-curse/powers/form_spider_2_web_projectile.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_auto_feed shape-shifter-curse:form_spider_3_auto_feed

能力 ID：shape-shifter-curse:form_spider_3_auto_feed；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:food_level","comparison":"<=","compare_to":13},{"type":"shape-shifter-curse:check_accessory","group":"hand","slot":"extra_hand","slot_index":0,"condition":{"type":"apoli:ingredient","ingredient":{"item":"shape-shifter-curse:spider_fluid_cocoon"}}}]}


来源：data/shape-shifter-curse/powers/form_spider_3_auto_feed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_burn_damage_up shape-shifter-curse:form_spider_3_burn_damage_up

能力 ID：shape-shifter-curse:form_spider_3_burn_damage_up；类型：shape-shifter-curse:burn_damage_modifier。

modifier：1.0


来源：data/shape-shifter-curse/powers/form_spider_3_burn_damage_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_climb shape-shifter-curse:form_spider_3_climb

能力 ID：shape-shifter-curse:form_spider_3_climb；类型：shape-shifter-curse:climbing_ex。



来源：data/shape-shifter-curse/powers/form_spider_3_climb.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_extra_hand_auxiliary_tools shape-shifter-curse:form_spider_3_extra_hand_auxiliary_tools

能力 ID：shape-shifter-curse:form_spider_3_extra_hand_auxiliary_tools；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_spider_3_extra_hand_auxiliary_tools.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_extra_slot_render shape-shifter-curse:form_spider_3_extra_slot_render

能力 ID：shape-shifter-curse:form_spider_3_extra_slot_render；类型：shape-shifter-curse:render_accessory_slot。



来源：data/shape-shifter-curse/powers/form_spider_3_extra_slot_render.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_falling_protection shape-shifter-curse:form_spider_3_falling_protection

能力 ID：shape-shifter-curse:form_spider_3_falling_protection；类型：shape-shifter-curse:falling_protection。



来源：data/shape-shifter-curse/powers/form_spider_3_falling_protection.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_jump_lower_and_far shape-shifter-curse:form_spider_3_jump_lower_and_far

能力 ID：shape-shifter-curse:form_spider_3_jump_lower_and_far；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_spider_3_jump_lower_and_far.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_make_cobweb shape-shifter-curse:form_spider_3_make_cobweb

能力 ID：shape-shifter-curse:form_spider_3_make_cobweb；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":5}]}


来源：data/shape-shifter-curse/powers/form_spider_3_make_cobweb.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_make_web_composter shape-shifter-curse:form_spider_3_make_web_composter

能力 ID：shape-shifter-curse:form_spider_3_make_web_composter；类型：apoli:item_on_item。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:has_mana","mana":20}]}


来源：data/shape-shifter-curse/powers/form_spider_3_make_web_composter.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_mana shape-shifter-curse:form_spider_3_mana

能力 ID：shape-shifter-curse:form_spider_3_mana；类型：shape-shifter-curse:mana_type_power。



来源：data/shape-shifter-curse/powers/form_spider_3_mana.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_mana_attack_cocooned_enemy shape-shifter-curse:form_spider_3_mana_attack_cocooned_enemy

能力 ID：shape-shifter-curse:form_spider_3_mana_attack_cocooned_enemy；类型：apoli:self_action_on_hit。

cooldown：20


来源：data/shape-shifter-curse/powers/form_spider_3_mana_attack_cocooned_enemy.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_mana_eat_string shape-shifter-curse:form_spider_3_mana_eat_string

能力 ID：shape-shifter-curse:form_spider_3_mana_eat_string；类型：apoli:action_on_item_use。



来源：data/shape-shifter-curse/powers/form_spider_3_mana_eat_string.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_mana_recover shape-shifter-curse:form_spider_3_mana_recover

能力 ID：shape-shifter-curse:form_spider_3_mana_recover；类型：apoli:action_over_time。



来源：data/shape-shifter-curse/powers/form_spider_3_mana_recover.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_night_vision shape-shifter-curse:form_spider_3_night_vision

能力 ID：shape-shifter-curse:form_spider_3_night_vision；类型：apoli:night_vision。

condition：{"type":"apoli:submerged_in","fluid":"minecraft:water","inverted":true}


来源：data/shape-shifter-curse/powers/form_spider_3_night_vision.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_poison_immunity shape-shifter-curse:form_spider_3_poison_immunity

能力 ID：shape-shifter-curse:form_spider_3_poison_immunity；类型：apoli:effect_immunity。



来源：data/shape-shifter-curse/powers/form_spider_3_poison_immunity.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_speed_down shape-shifter-curse:form_spider_3_speed_down

能力 ID：shape-shifter-curse:form_spider_3_speed_down；类型：apoli:conditioned_attribute。

tick_rate：2
condition：{"type":"apoli:and","conditions":[{"type":"apoli:on_block"},{"type":"apoli:in_block","block_condition":{"type":"apoli:block","block":"minecraft:water"},"inverted":true}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_base","value":-0.2}


来源：data/shape-shifter-curse/powers/form_spider_3_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_spider_fluid_cocoon_only shape-shifter-curse:form_spider_3_spider_fluid_cocoon_only

能力 ID：shape-shifter-curse:form_spider_3_spider_fluid_cocoon_only；类型：apoli:modify_food。

food_modifiers：[{"operation":"set_total","value":0.0}]
saturation_modifiers：[{"operation":"set_total","value":0.0}]


来源：data/shape-shifter-curse/powers/form_spider_3_spider_fluid_cocoon_only.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_step_height shape-shifter-curse:form_spider_3_step_height

能力 ID：shape-shifter-curse:form_spider_3_step_height；类型：shape-shifter-curse:modify_step_height。



来源：data/shape-shifter-curse/powers/form_spider_3_step_height.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_step_sound_speed_up shape-shifter-curse:form_spider_3_step_sound_speed_up

能力 ID：shape-shifter-curse:form_spider_3_step_sound_speed_up；类型：shape-shifter-curse:modify_footstep_sound_speed。



来源：data/shape-shifter-curse/powers/form_spider_3_step_sound_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_totem_of_undying_extra_slot shape-shifter-curse:form_spider_3_totem_of_undying_extra_slot

能力 ID：shape-shifter-curse:form_spider_3_totem_of_undying_extra_slot；类型：shape-shifter-curse:virtual_totem。

cooldown：1
condition：{"type":"shape-shifter-curse:check_accessory","group":"hand","slot":"extra_hand","slot_index":0,"condition":{"type":"apoli:ingredient","ingredient":{"item":"minecraft:totem_of_undying"}}}


来源：data/shape-shifter-curse/powers/form_spider_3_totem_of_undying_extra_slot.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_web_bridge shape-shifter-curse:form_spider_3_web_bridge

能力 ID：shape-shifter-curse:form_spider_3_web_bridge；类型：shape-shifter-curse:charge_action。



来源：data/shape-shifter-curse/powers/form_spider_3_web_bridge.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_3_web_projectile shape-shifter-curse:form_spider_3_web_projectile

能力 ID：shape-shifter-curse:form_spider_3_web_projectile；类型：shape-shifter-curse:charge_action。



来源：data/shape-shifter-curse/powers/form_spider_3_web_projectile.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_climb_web shape-shifter-curse:form_spider_climb_web

能力 ID：shape-shifter-curse:form_spider_climb_web；类型：apoli:multiple。



来源：data/shape-shifter-curse/powers/form_spider_climb_web.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_entity_group shape-shifter-curse:form_spider_entity_group

能力 ID：shape-shifter-curse:form_spider_entity_group；类型：apoli:entity_group。



来源：data/shape-shifter-curse/powers/form_spider_entity_group.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_sound shape-shifter-curse:form_spider_sound

能力 ID：shape-shifter-curse:form_spider_sound；类型：apoli:action_over_time。

condition：{"type":"apoli:and","conditions":[{"type":"shape-shifter-curse:enable_random_sound"},{"type":"shape-shifter-curse:chance","chance":0.3}]}


来源：data/shape-shifter-curse/powers/form_spider_sound.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_sound_hurt shape-shifter-curse:form_spider_sound_hurt

能力 ID：shape-shifter-curse:form_spider_sound_hurt；类型：apoli:self_action_when_hit。

condition：{"type":"shape-shifter-curse:chance","chance":0.8}


来源：data/shape-shifter-curse/powers/form_spider_sound_hurt.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_sound_key shape-shifter-curse:form_spider_sound_key

能力 ID：shape-shifter-curse:form_spider_sound_key；类型：apoli:active_self。

cooldown：5


来源：data/shape-shifter-curse/powers/form_spider_sound_key.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_web_break_speed shape-shifter-curse:form_spider_web_break_speed

能力 ID：shape-shifter-curse:form_spider_web_break_speed；类型：apoli:modify_break_speed。

modifier：{"operation":"multiply_total","value":15.0}


来源：data/shape-shifter-curse/powers/form_spider_web_break_speed.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_spider_web_no_slow shape-shifter-curse:form_spider_web_no_slow

能力 ID：shape-shifter-curse:form_spider_web_no_slow；类型：shape-shifter-curse:slowdown_percent。



来源：data/shape-shifter-curse/powers/form_spider_web_no_slow.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_sprint_speed_up shape-shifter-curse:form_sprint_speed_up

能力 ID：shape-shifter-curse:form_sprint_speed_up；类型：apoli:conditioned_attribute。

tick_rate：4
condition：{"type":"apoli:sprinting"}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":0.35,"name":"Increased speed when sprinting"}


来源：data/shape-shifter-curse/powers/form_sprint_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_tan_axolotl_thirst_in_water shape-shifter-curse:form_tan_axolotl_thirst_in_water

能力 ID：shape-shifter-curse:form_tan_axolotl_thirst_in_water；类型：apoli:action_over_time。

condition：{"type":"apoli:submerged_in","fluid":"water"}


来源：data/shape-shifter-curse/powers/form_tan_axolotl_thirst_in_water.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_tan_cold_resistance_mild shape-shifter-curse:form_tan_cold_resistance_mild

能力 ID：shape-shifter-curse:form_tan_cold_resistance_mild；类型：shape-shifter-curse:tan_form_temperature_modifier。



来源：data/shape-shifter-curse/powers/form_tan_cold_resistance_mild.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_tan_hot_resistance_mild shape-shifter-curse:form_tan_hot_resistance_mild

能力 ID：shape-shifter-curse:form_tan_hot_resistance_mild；类型：shape-shifter-curse:tan_form_temperature_modifier。



来源：data/shape-shifter-curse/powers/form_tan_hot_resistance_mild.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_tan_prevent_dirty_water_thirst shape-shifter-curse:form_tan_prevent_dirty_water_thirst

能力 ID：shape-shifter-curse:form_tan_prevent_dirty_water_thirst；类型：shape-shifter-curse:tan_prevent_dirty_water_thirst_effect。



来源：data/shape-shifter-curse/powers/form_tan_prevent_dirty_water_thirst.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## 素食者 shape-shifter-curse:form_vegetarian

能力 ID：shape-shifter-curse:form_vegetarian；类型：apoli:modify_food。

food_modifiers：[{"operation":"set_total","value":0.0}]
saturation_modifiers：[{"operation":"set_total","value":0.0}]


来源：data/shape-shifter-curse/powers/form_vegetarian.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_vegetarian_food_down shape-shifter-curse:form_vegetarian_food_down

能力 ID：shape-shifter-curse:form_vegetarian_food_down；类型：apoli:modify_food。

food_modifier：{"name":"Decreased food points","operation":"multiply_base_multiplicative","value":-0.5}
saturation_modifier：{"name":"Decreased saturation points","operation":"multiply_base_multiplicative","value":-0.5}


来源：data/shape-shifter-curse/powers/form_vegetarian_food_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## form_vegetarian_food_up shape-shifter-curse:form_vegetarian_food_up

能力 ID：shape-shifter-curse:form_vegetarian_food_up；类型：apoli:modify_food。

food_modifiers：[{"name":"increase food points","operation":"addition","value":2.0},{"name":"increase food points","operation":"min_base","value":4.0},{"name":"increase food points","operation":"multiply_base","value":0.2}]
saturation_modifiers：[{"name":"increase food points","operation":"min_total","value":0.6}]


来源：data/shape-shifter-curse/powers/form_vegetarian_food_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## high_jump_2 shape-shifter-curse:high_jump_2

能力 ID：shape-shifter-curse:high_jump_2；类型：apoli:modify_jump。

modifier：{"operation":"addition","value":0.2}


来源：data/shape-shifter-curse/powers/high_jump_2.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## hold_breath shape-shifter-curse:hold_breath

能力 ID：shape-shifter-curse:hold_breath；类型：shape-shifter-curse:hold_breath。



来源：data/shape-shifter-curse/powers/hold_breath.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## hostile_iron_golem shape-shifter-curse:hostile_iron_golem

能力 ID：shape-shifter-curse:hostile_iron_golem；类型：apoli:simple。



来源：data/shape-shifter-curse/powers/hostile_iron_golem.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## hotblooded shape-shifter-curse:hotblooded

能力 ID：shape-shifter-curse:hotblooded；类型：apoli:effect_immunity。



来源：data/shape-shifter-curse/powers/hotblooded.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## hunger_immunity shape-shifter-curse:hunger_immunity

能力 ID：shape-shifter-curse:hunger_immunity；类型：apoli:effect_immunity。



来源：data/shape-shifter-curse/powers/hunger_immunity.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## jump_out_water shape-shifter-curse:jump_out_water

能力 ID：shape-shifter-curse:jump_out_water；类型：apoli:active_self。

cooldown：10
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":">","compare_to":0},{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<","compare_to":0.2}]}


来源：data/shape-shifter-curse/powers/jump_out_water.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## like_water shape-shifter-curse:like_water

能力 ID：shape-shifter-curse:like_water；类型：apoli:simple。



来源：data/shape-shifter-curse/powers/like_water.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## modify_potion_stack_8 shape-shifter-curse:modify_potion_stack_8

能力 ID：shape-shifter-curse:modify_potion_stack_8；类型：shape-shifter-curse:modify_potion_stack。

condition：{"type":"apoli:constant","value":true}


来源：data/shape-shifter-curse/powers/modify_potion_stack_8.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## more_exhaustion shape-shifter-curse:more_exhaustion

能力 ID：shape-shifter-curse:more_exhaustion；类型：apoli:modify_exhaustion。

modifier：{"name":"Extra exhaustion from large appetite","value":0.6,"operation":"multiply_base"}


来源：data/shape-shifter-curse/powers/more_exhaustion.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## no_exhaustion shape-shifter-curse:no_exhaustion

能力 ID：shape-shifter-curse:no_exhaustion；类型：apoli:modify_exhaustion。

modifier：{"name":"No exhaustion","value":-0.9,"operation":"multiply_base"}


来源：data/shape-shifter-curse/powers/no_exhaustion.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## no_hurt_pet shape-shifter-curse:no_hurt_pet

能力 ID：shape-shifter-curse:no_hurt_pet；类型：apoli:modify_damage_dealt。

modifier：{"operation":"multiply_total","value":-1}


来源：data/shape-shifter-curse/powers/no_hurt_pet.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## no_render_arm shape-shifter-curse:no_render_arm

能力 ID：shape-shifter-curse:no_render_arm；类型：shape-shifter-curse:no_render_arm。



来源：data/shape-shifter-curse/powers/no_render_arm.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## no_render_arm_when_sneaking shape-shifter-curse:no_render_arm_when_sneaking

能力 ID：shape-shifter-curse:no_render_arm_when_sneaking；类型：shape-shifter-curse:no_render_arm。

condition：{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"}]}


来源：data/shape-shifter-curse/powers/no_render_arm_when_sneaking.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## no_render_tp_item shape-shifter-curse:no_render_tp_item

能力 ID：shape-shifter-curse:no_render_tp_item；类型：shape-shifter-curse:hide_tp_held_item。



来源：data/shape-shifter-curse/powers/no_render_tp_item.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## 持盾不能 shape-shifter-curse:no_shield

能力 ID：shape-shifter-curse:no_shield；类型：apoli:prevent_item_use。
你双手的造型让你无法正常地手持盾牌。


来源：data/shape-shifter-curse/powers/no_shield.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## no_step_sound shape-shifter-curse:no_step_sound

能力 ID：shape-shifter-curse:no_step_sound；类型：shape-shifter-curse:no_step_sound。

condition：{"type":"apoli:constant","value":true}


来源：data/shape-shifter-curse/powers/no_step_sound.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## pillager_friendly shape-shifter-curse:pillager_friendly

能力 ID：shape-shifter-curse:pillager_friendly；类型：shape-shifter-curse:pillager_friendly。

condition：{"type":"apoli:constant","value":true}


来源：data/shape-shifter-curse/powers/pillager_friendly.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## poison_immunity shape-shifter-curse:poison_immunity

能力 ID：shape-shifter-curse:poison_immunity；类型：apoli:effect_immunity。



来源：data/shape-shifter-curse/powers/poison_immunity.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## prevent_berry_effect shape-shifter-curse:prevent_berry_effect

能力 ID：shape-shifter-curse:prevent_berry_effect；类型：shape-shifter-curse:prevent_berry_effect。

condition：{"type":"apoli:constant","value":true}


来源：data/shape-shifter-curse/powers/prevent_berry_effect.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## 禁用弓弩 shape-shifter-curse:prevent_ranged_weapon_use

能力 ID：shape-shifter-curse:prevent_ranged_weapon_use；类型：apoli:prevent_item_use。



来源：data/shape-shifter-curse/powers/prevent_ranged_weapon_use.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## prevent_sprinting shape-shifter-curse:prevent_sprinting

能力 ID：shape-shifter-curse:prevent_sprinting；类型：apoli:prevent_sprinting。

condition：{"type":"apoli:constant","value":true}


来源：data/shape-shifter-curse/powers/prevent_sprinting.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## scare_creepers shape-shifter-curse:scare_creepers

能力 ID：shape-shifter-curse:scare_creepers；类型：apoli:simple。



来源：data/shape-shifter-curse/powers/scare_creepers.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## scare_skeleton shape-shifter-curse:scare_skeleton

能力 ID：shape-shifter-curse:scare_skeleton；类型：apoli:simple。



来源：data/shape-shifter-curse/powers/scare_skeleton.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## scare_villager shape-shifter-curse:scare_villager

能力 ID：shape-shifter-curse:scare_villager；类型：shape-shifter-curse:scare_villager。

condition：{"type":"apoli:constant","value":true}


来源：data/shape-shifter-curse/powers/scare_villager.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## slow_falling shape-shifter-curse:slow_falling

能力 ID：shape-shifter-curse:slow_falling；类型：apoli:modify_falling。

condition：{"type":"apoli:or","conditions":[{"type":"apoli:and","conditions":[{"type":"apoli:sneaking"},{"type":"apoli:fall_flying"}]},{"type":"apoli:and","conditions":[{"type":"apoli:sneaking","inverted":true},{"type":"apoli:fall_flying","inverted":true},{"type":"apoli:on_block","inverted":true},{"type":"shape-shifter-curse:is_sleep","inverted":true}]}]}


来源：data/shape-shifter-curse/powers/slow_falling.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## sneaking_speed_up shape-shifter-curse:sneaking_speed_up

能力 ID：shape-shifter-curse:sneaking_speed_up；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:sneaking"}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"addition","value":0.35,"name":"Increased sprinting speed"}


来源：data/shape-shifter-curse/powers/sneaking_speed_up.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## sneaking_speed_up_low shape-shifter-curse:sneaking_speed_up_low

能力 ID：shape-shifter-curse:sneaking_speed_up_low；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:and","conditions":[{"type":"apoli:fluid_height","fluid":"minecraft:water","comparison":"<=","compare_to":0},{"type":"apoli:sneaking"}]}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"addition","value":0.2,"name":"Increased sprinting speed"}


来源：data/shape-shifter-curse/powers/sneaking_speed_up_low.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## speed_down shape-shifter-curse:speed_down

能力 ID：shape-shifter-curse:speed_down；类型：apoli:attribute。

modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":-0.15,"name":"Movement speed down"}


来源：data/shape-shifter-curse/powers/speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## spider_friendly shape-shifter-curse:spider_friendly

能力 ID：shape-shifter-curse:spider_friendly；类型：apoli:simple。



来源：data/shape-shifter-curse/powers/spider_friendly.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## sprint_speed_down shape-shifter-curse:sprint_speed_down

能力 ID：shape-shifter-curse:sprint_speed_down；类型：apoli:conditioned_attribute。

tick_rate：1
condition：{"type":"apoli:sprinting"}
modifier：{"attribute":"minecraft:generic.movement_speed","operation":"multiply_total","value":-0.07,"name":"Decrease sprinting speed"}


来源：data/shape-shifter-curse/powers/sprint_speed_down.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## toggle_clip_at_ledge shape-shifter-curse:toggle_clip_at_ledge

能力 ID：shape-shifter-curse:toggle_clip_at_ledge；类型：origins:toggle。



来源：data/shape-shifter-curse/powers/toggle_clip_at_ledge.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## undead_group shape-shifter-curse:undead_group

能力 ID：shape-shifter-curse:undead_group；类型：apoli:entity_group。



来源：data/shape-shifter-curse/powers/undead_group.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## velvet_paws shape-shifter-curse:velvet_paws

能力 ID：shape-shifter-curse:velvet_paws；类型：apoli:prevent_game_event。



来源：data/shape-shifter-curse/powers/velvet_paws.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## water_vision shape-shifter-curse:water_vision

能力 ID：shape-shifter-curse:water_vision；类型：apoli:night_vision。

condition：{"type":"apoli:submerged_in","fluid":"minecraft:water"}


来源：data/shape-shifter-curse/powers/water_vision.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。

## witch_friendly shape-shifter-curse:witch_friendly

能力 ID：shape-shifter-curse:witch_friendly；类型：shape-shifter-curse:witch_friendly。

condition：{"type":"apoli:constant","value":true}


来源：data/shape-shifter-curse/powers/witch_friendly.json；SSC 1.10.0 发布 JAR。数值是出厂默认，服务器配置与数据包优先。
