package net.sleepykairo.debalance.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = {"net.minecraft.screen.GrindstoneScreenHandler$2", "net.minecraft.screen.GrindstoneScreenHandler$3"})
public class GrindstoneScreenHandlerInputsMixin {
    @Redirect(method = "canInsert", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isDamageable()Z"))
    public boolean canInsert1(ItemStack instance) {
        return instance.isDamageable() || instance.isOf(Items.BOOK);
    }
}
