<!-- compass: mod=shape-shifter-curse version=1.9.2,1.10.0 -->

## 变形、形态 ID 与 Origin ID
SSC 和 SSCA 的内置形态都使用 /shape_shifter_curse set_form <目标> <形态ID> 或 transform_to_form <目标> <形态ID>。前者立即设置，后者走变形流程，均需管理员权限。SSC 形态命名空间是 shape-shifter-curse，SSCA 形态命名空间是 my_addon。
形态 ID 如 my_addon:familiar_fox_sp；Origin 数据 ID 如 my_addon:form_familiar_fox_sp。form_ 前缀不能混入形态命令。SP悦灵 my_addon:allay_sp 与原版悦灵 shape-shifter-curse:allay_sp 是不同形态。
1.9.2 使用 set_custom_form / transform_to_custom_form 管理自定义形态；1.10.0 使用 set_dynamic_form / transform_to_dynamic_form，另有子形态命令。不要把新版命令用于旧版。
来源：SSC 两版命令类、PlayerFormBase/IForm、SSCA FormIdentifiers 与 SscAddonForms。

## 变回人类：开书前和开书后
shape-shifter-curse:original_before_enable 是开启模组内容前的人类；shape-shifter-curse:original_shifter 是幻形者初始形态。二者进度含义不同，应先确认玩家要哪一种。
形态全表请检索“shape-shifter-curse:形态路径”或“my_addon:形态路径”，结果根据当前安装版本筛选。

## 生命上限、饱食度与形态限制
形态可能通过属性修饰符改变最大生命值。/attribute 的 base set 修改基础值，最终值还取决于加法和乘法修饰符；设置上限不会自动恢复当前血量。get_player_status 的最终属性才是验收依据，不能对所有形态套同一个 -6 修正。
免疫 minecraft:saturation 是免疫药水效果，并不自动证明所有食物无效。get_food_info 读取实际食物及当前 modify_food 修正；进化形态还受已解锁能力影响。压缩能量药水的药水 ID 是 shape-shifter-curse:feed_potion，饮用/喷溅/滞留版分别用对应的原版药水物品携带 Potion NBT。任何作弊操作都要先满足 Compass 作弊开关与真实 OP 权限，并读取操作结果。
