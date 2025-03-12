package net.sleepykairo.debalance.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WitherEntity.class)
public abstract class WitherEntityMixin {

	@ModifyReturnValue(method = "createWitherAttributes", at = @At("RETURN"))
	private static DefaultAttributeContainer.Builder injected(DefaultAttributeContainer.Builder original) {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 600.0)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6f)
				.add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6f)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
				.add(EntityAttributes.GENERIC_ARMOR, 4.0);
	}
}