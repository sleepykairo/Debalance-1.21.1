package net.sleepykairo.debalance.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.util.interfaces.PlayerEntityArmorAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityEatFoodMixin {
    @Inject(method = "eatFood", at = @At("HEAD"))
    void inject(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> cir) {
        PlayerEntity player = (PlayerEntity)(Object) this;
        PlayerEntityArmorAccess playerEntityArmorAccess = (PlayerEntityArmorAccess) player;

        if (playerEntityArmorAccess.debalance$getArmorType().equals("honeycomb") && !world.isClient) {
            float saturation = stack.isOf(Items.HONEY_BOTTLE) ? 1.1F : foodComponent.saturation() / 2;
            int nutrition = Math.max(1, foodComponent.nutrition() / 2);

            player.getHungerManager().add(nutrition, saturation);
            player.heal(nutrition);
        }
    }
}
