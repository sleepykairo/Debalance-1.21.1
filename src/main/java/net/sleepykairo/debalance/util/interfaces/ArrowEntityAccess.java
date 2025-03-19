package net.sleepykairo.debalance.util.interfaces;

import net.minecraft.item.ItemStack;

public interface ArrowEntityAccess {
    void debalance$setInitialStackPickup(boolean bl);

    boolean debalance$getInitialStackPickup();

    void debalance$setInitialStack(ItemStack stack);

    ItemStack debalance$getInitialStack();
}
