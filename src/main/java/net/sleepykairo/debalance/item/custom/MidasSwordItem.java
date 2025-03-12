package net.sleepykairo.debalance.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.component.ModDataComponentTypes;

import java.awt.*;
import java.util.List;

public class MidasSwordItem extends SwordItem {
    private static final int MAX_VALUE = 3456;

    public MidasSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT && otherStack.getItem() == Items.GOLD_INGOT) {
            int amountToConsume = Math.min(MAX_VALUE - getValueNotNull(stack), otherStack.getCount());

            otherStack.decrement(amountToConsume);
            player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.2F + player.getWorld().getRandom().nextFloat() * 0.4F);
            stack.set(ModDataComponentTypes.MIDAS_SWORD_VALUE, getValueNotNull(stack) + amountToConsume);
            return true;
        }
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (getValuePercent(stack) >= 1) {
            tooltip.add(Text.literal("Power: " + getValueNotNull(stack) + "/" + MAX_VALUE).formatted(Formatting.GOLD));
        } else if (getValuePercent(stack) >= 0.5) {
            tooltip.add(Text.literal("Power: " + getValueNotNull(stack) + "/" + MAX_VALUE).formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.literal("Power: " + getValueNotNull(stack) + "/" + MAX_VALUE).formatted(Formatting.DARK_GRAY));
        }
        tooltip.add(Text.literal("Bonus Attack Damage: " + getBonusDamage(stack)).formatted(Formatting.DARK_GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public Text getName(ItemStack stack) {
        if (getValuePercent(stack) >= 1) {
            return Text.translatable("item.debalance.midas_sword.max");
        } else if (getValuePercent(stack) >= 0.5) {
            return Text.translatable("item.debalance.midas_sword.half");
        } else {
            return Text.translatable("item.debalance.midas_sword");
        }
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        ItemStack stack = damageSource.getWeaponStack();
        return getBonusDamage(stack);
    }

    public static int getValueNotNull(ItemStack stack) {
        return stack.get(ModDataComponentTypes.MIDAS_SWORD_VALUE) != null ? stack.get(ModDataComponentTypes.MIDAS_SWORD_VALUE) : 0;
    }

    public static int getMaxValue() {
        return MAX_VALUE;
    }

    public static float getValuePercent(ItemStack stack) {
        return (float) MidasSwordItem.getValueNotNull(stack) / MidasSwordItem.getMaxValue();
    }

    public float getBonusDamage(ItemStack stack) {
        float valuePercent = getValuePercent(stack);
        int midasLevelBonus = 0;
        if (valuePercent >= 1) {
            midasLevelBonus = 6;
        } else if (valuePercent >= 0.5) {
            midasLevelBonus = 3;
        }
        int percentBonus = (int) (valuePercent * 20);
        return percentBonus + midasLevelBonus;
    }
}
