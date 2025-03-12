package net.sleepykairo.debalance.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.sleepykairo.debalance.component.ModDataComponentTypes;

import java.util.List;

public class SoulboundSwordItem extends SwordItem {
    public SoulboundSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postDamageEntity(stack, target, attacker);
        if (target.getHealth() <= 0) {
            stack.set(ModDataComponentTypes.SOULBOUND_SWORD_VALUE, getValueNotNull(stack) + (float) Math.sqrt(target.getMaxHealth()));
        }
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        ItemStack stack = damageSource.getWeaponStack();
        return getBonusDamage(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Power: " + getFormattedFloat(getValueNotNull(stack))).formatted(Formatting.DARK_GRAY));
        tooltip.add(Text.literal("Bonus Attack Damage: " + getFormattedFloat(getBonusDamage(stack))).formatted(Formatting.DARK_GRAY));

        tooltip.add(Text.literal(""));
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.debalance.soulbound_sword.info").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("tooltip.debalance.soulbound_sword.info_1").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.translatable("tooltip.debalance.info").formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    public static float getValueNotNull(ItemStack stack) {
        return stack.get(ModDataComponentTypes.SOULBOUND_SWORD_VALUE) != null ? stack.get(ModDataComponentTypes.SOULBOUND_SWORD_VALUE) : 0;
    }

    public float getBonusDamage(ItemStack stack) {
        return (float) Math.pow(getValueNotNull(stack), 0.25);
    }

    public String getFormattedFloat(float f) {
        return String.format("%.1f", f);
    }
}
