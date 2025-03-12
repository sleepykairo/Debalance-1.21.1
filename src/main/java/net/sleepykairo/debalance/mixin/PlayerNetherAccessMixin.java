package net.sleepykairo.debalance.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.sleepykairo.debalance.util.interfaces.PlayerPermStatAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerNetherAccessMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    public void injectTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player instanceof PlayerPermStatAccess playerPermStatAccess) {
            if (!playerPermStatAccess.debalance$getNetherAccess() && player.getWorld().getDimension().ultrawarm()) {
                player.setOnFireFromLava();
            }
        }
    }
}
