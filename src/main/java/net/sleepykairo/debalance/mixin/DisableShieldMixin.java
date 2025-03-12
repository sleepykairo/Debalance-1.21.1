package net.sleepykairo.debalance.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(LivingEntity.class)
public class DisableShieldMixin {
    @ModifyReturnValue(method = "disablesShield", at = @At("RETURN"))
    public boolean disablesShield(boolean original) {
        return true;
    }
}
