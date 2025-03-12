package net.sleepykairo.debalance.mixin;

import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerShieldCooldownMixin {
    @Inject(method = "disableShield", at = @At(value = "TAIL"))
    public void inject(CallbackInfo ci) {
        ((PlayerEntity) (Object) this).getItemCooldownManager().set(Items.SHIELD, 20);
    }
}
