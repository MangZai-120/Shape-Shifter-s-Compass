<!-- compass: mod=shape-shifter-curse version=1.9.2 -->

## SSC 1.9.2 变形与自定义形态指令
/shape_shifter_curse set_form <目标> <形态ID>：直接设置；transform_to_form <目标> <形态ID>：带变身流程。两者需OP 2，普通内置/SSCA形态用这两个入口。
1.9.2 自定义形态入口是 set_custom_form <目标> <形态ID> 和 transform_to_custom_form <目标> <形态ID>。没有新版的 set_dynamic_form / transform_to_dynamic_form / set_sub_form / transform_to_sub_form。
来源：SSC 1.9.2 发布 JAR 的 command.ShapeShifterCurseCommand 注册字节码与 player_form.ability.PlayerFormComponent。

## SSC 1.9.2 世界、颜色与调试指令
管理员：/shape_shifter_curse jump_to_next_cursed_moon、world_time set <时间>、world_time add <时间>、adjust_feral_item_loc <旋转中心> <位置偏移> <旋转X>。
玩家：keep_original_skin <true|false>；set_form_color [启用和完整颜色参数]；form_color menu|save|load|delete|list|config|to_chat|set_color_from_string；patron_info。
调试树包含 clear_player_form_data、clear_player_instinct_data、clear_player_skin_data、clear_player_minion_data、clear_player_mana_data；清除数据会影响进度，不作为常规状态查询。
来源：SSC 1.9.2 command.ShapeShifterCurseCommand 注册字节码；实际参数以 Tab 补全和服务器权限为准。
