package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

/** get_player_status：读玩家所有数值状态（血量/饥饿/护甲/经验/氧气/坐标/状态效果/移动速度等属性）。 */
public class PlayerStatusTool implements CompassTool {
    @Override
    public String name() {
        return "get_player_status";
    }

    @Override
    public String description() {
        return "获取玩家当前的所有数值状态：血量、饥饿值、饱和度、护甲、经验等级、氧气、坐标、朝向、维度、"
                + "游戏模式、状态效果（buff/debuff 含等级与剩余时间）、以及移动速度/攻击伤害/攻击速度等属性。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject schema = new JsonObject();
        schema.addProperty("type", "object");
        schema.add("properties", new JsonObject());
        return schema;
    }

    @Override
    public String execute(JsonObject args) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayerEntity p = mc.player;
        if (p == null) {
            return "玩家当前不在游戏世界中。";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("血量: %.1f / %.1f%n", p.getHealth(), p.getMaxHealth()));
        sb.append(String.format("饥饿值: %d / 20，饱和度: %.1f%n",
                p.getHungerManager().getFoodLevel(), p.getHungerManager().getSaturationLevel()));
        sb.append("护甲值: ").append(p.getArmor()).append('\n');
        sb.append(String.format("经验: 等级 %d（当前级进度 %.0f%%）%n", p.experienceLevel, p.experienceProgress * 100));
        sb.append("氧气: ").append(p.getAir()).append(" / ").append(p.getMaxAir()).append('\n');
        sb.append(String.format("坐标: [%.1f, %.1f, %.1f]  朝向: yaw %.0f, pitch %.0f%n",
                p.getX(), p.getY(), p.getZ(), MathHelper.wrapDegrees(p.getYaw()), p.getPitch()));
        sb.append("维度: ").append(p.getWorld().getRegistryKey().getValue()).append('\n');
        if (mc.interactionManager != null) {
            sb.append("游戏模式: ").append(mc.interactionManager.getCurrentGameMode().getName()).append('\n');
        }
        sb.append("状态标志: ")
                .append(p.isOnFire() ? "着火 " : "")
                .append(p.isSneaking() ? "潜行 " : "")
                .append(p.isSprinting() ? "疾跑 " : "")
                .append(p.getAbilities().flying ? "飞行 " : "")
                .append(p.isSwimming() ? "游泳 " : "")
                .append(p.isTouchingWater() ? "水中 " : "")
                .append(p.isOnGround() ? "" : "腾空 ")
                .append('\n');

        var effects = p.getStatusEffects();
        if (effects.isEmpty()) {
            sb.append("状态效果: 无\n");
        } else {
            sb.append("状态效果:\n");
            for (StatusEffectInstance e : effects) {
                Identifier idObj = Registries.STATUS_EFFECT.getId(e.getEffectType());
                String id = idObj == null ? "unknown" : idObj.toString();
                int dur = e.getDuration();
                String durStr = (dur < 0 || dur > 1_000_000) ? "很长/无限" : (dur / 20 + " 秒");
                sb.append("  ").append(id).append(" 等级 ").append(e.getAmplifier() + 1)
                        .append("，剩余 ").append(durStr).append('\n');
            }
        }

        sb.append("属性:\n");
        appendAttr(sb, p, "移动速度", EntityAttributes.GENERIC_MOVEMENT_SPEED);
        appendAttr(sb, p, "攻击伤害", EntityAttributes.GENERIC_ATTACK_DAMAGE);
        appendAttr(sb, p, "攻击速度", EntityAttributes.GENERIC_ATTACK_SPEED);
        appendAttr(sb, p, "护甲", EntityAttributes.GENERIC_ARMOR);
        appendAttr(sb, p, "护甲韧性", EntityAttributes.GENERIC_ARMOR_TOUGHNESS);
        appendAttr(sb, p, "击退抗性", EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
        appendAttr(sb, p, "幸运", EntityAttributes.GENERIC_LUCK);
        return sb.toString();
    }

    private static void appendAttr(StringBuilder sb, ClientPlayerEntity p, String label, EntityAttribute attr) {
        if (p.getAttributes().hasAttribute(attr)) {
            sb.append("  ").append(label).append(": ")
                    .append(String.format("%.3f", p.getAttributeValue(attr))).append('\n');
        }
    }
}
