package net.sleepykairo.debalance.mixin;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.sleepykairo.debalance.util.interfaces.ArrowEntityAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PersistentProjectileEntity.class)
public abstract class ArrowEntityAccessMixin implements ArrowEntityAccess {
    @Shadow protected abstract ItemStack asItemStack();

    @Shadow public abstract ItemStack getItemStack();

    @Unique
    private boolean initialStackPickup = false;
    @Unique
    private ItemStack initialStack = new ItemStack(Items.ARROW);

    @Override
    public void debalance$setInitialStackPickup(boolean bl) {
        this.initialStackPickup = bl;
    }

    @Override
    public boolean debalance$getInitialStackPickup() {
        return this.initialStackPickup;
    }

    @Override
    public void debalance$setInitialStack(ItemStack stack) {
        this.initialStack = stack;
    }

    @Override
    public ItemStack debalance$getInitialStack() {
        return this.initialStack;
    }

    @Redirect(method = "tryPickup", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerInventory;insertStack(Lnet/minecraft/item/ItemStack;)Z"))
    public boolean redir(PlayerInventory instance, ItemStack stack) {
        if (debalance$getInitialStackPickup()) {
            return instance.insertStack(debalance$getInitialStack());
        }
        return instance.insertStack(getItemStack());
    }
}
