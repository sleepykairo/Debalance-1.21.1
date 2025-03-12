package net.sleepykairo.debalance.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.sleepykairo.debalance.util.interfaces.EntityVoltageAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityTickMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void inject(CallbackInfo ci) {
        Entity entity = ((Entity) (Object) this);
        EntityVoltageAccess entityVoltageAccess = (EntityVoltageAccess) entity;

        if (entityVoltageAccess.debalance$getVoltageAmount() > 0) {
            entityVoltageAccess.debalance$decrementVoltageAmount();
        }
    }
}
