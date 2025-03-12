package net.sleepykairo.debalance.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerInventoryMixin {
    private static final int[] ACCESSORY_SLOTS = new int[]{3};
    public final DefaultedList<ItemStack> accessories = DefaultedList.ofSize(3, ItemStack.EMPTY);
}
