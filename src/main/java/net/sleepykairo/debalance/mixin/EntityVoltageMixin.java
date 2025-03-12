package net.sleepykairo.debalance.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.sleepykairo.debalance.util.interfaces.EntityVoltageAccess;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
public abstract class EntityVoltageMixin implements EntityVoltageAccess {
    float voltageAmount = 0;

    @Override
    public void debalance$setVoltageAmount(float amount) {
        voltageAmount = amount;
    }

    @Override
    public float debalance$getVoltageAmount() {
        return voltageAmount;
    }

    @Override
    public void debalance$incrementVoltageAmount(float amount) {
        voltageAmount += amount;
    }

    @Override
    public void debalance$decrementVoltageAmount() {
        voltageAmount -= 1;
    }
}
