<!-- compass: mod=shape-shifter-curse version=1.10.0 -->

## SSC 1.10.0 变形、动态形态与子形态指令
/shape_shifter_curse set_form <目标> <形态ID> 立即设置普通内置/SSCA形态；transform_to_form <目标> <形态ID> 走变身流程。均需 OP 2。
数据包动态形态：set_dynamic_form <目标> <形态ID>、transform_to_dynamic_form <目标> <形态ID>；子形态：set_sub_form <目标> <形态ID>、transform_to_sub_form <目标> <形态ID>。不可套用 1.9.2 的 set_custom_form 名称。
来源：SSC 1.10.0 command/ShapeShifterCurseCommand.java，详细 Wiki docs/server/commands.md（1.10.0基线）。

## SSC 1.10.0 世界、颜色与调试指令
管理员：/shape_shifter_curse jump_to_next_cursed_moon、world_time set <时间>、world_time add <时间>、adjust_feral_item_loc <旋转中心> <位置偏移> <旋转X>。
玩家：keep_original_skin <true|false>；set_form_color [完整颜色参数]；form_color menu；form_color save|load|delete <form|global|form_default> <槽位名> [形态]；form_color list <form|global|form_default> [形态]；form_color to_chat 和 set_color_from_string；patron_info。
debug 下的清除玩家形态、皮肤、随从与法力数据是管理修复操作，不能为了查询状态而调用。
来源：SSC 1.10.0命令注册树；具体完整颜色参数和可用选项以当前服务器 Tab 补全为准。
