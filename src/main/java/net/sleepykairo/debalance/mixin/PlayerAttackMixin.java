package net.sleepykairo.debalance.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.item.ModItems;
import net.sleepykairo.debalance.util.interfaces.PlayerAttackAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerAttackMixin implements PlayerAttackAccess {
    public float previousAttackCooldown = 1;
    public float attackDamage;
    public float bonusDamage;

    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    @Shadow public abstract ItemCooldownManager getItemCooldownManager();

    @Shadow protected abstract float getDamageAgainst(Entity target, float baseDamage, DamageSource damageSource);

    @Inject(method = "attack", at = @At("HEAD"))
    public void inject(Entity target, CallbackInfo ci) {
        debalance$setPreviousAttackCooldown(this.getAttackCooldownProgress(0.5f));
    }

//    @Inject(method = "attack", at = @At("TAIL"))
//    public void inject2(Entity target, CallbackInfo ci, @Local(ordinal = 0) float f) {
//        debalance$setAttackDamage(f);
//    }

    @Redirect(method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;)V"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getKnockbackAgainst(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;)F")
            )
    )
    private boolean redirectCritDamage(Entity instance, DamageSource source, float amount, @Local(ordinal = 0) boolean bl) {
        float finalAmount = amount;
        boolean bl3 =
                ((PlayerEntity) (Object) this).fallDistance > 0.0F
                && !((PlayerEntity) (Object) this).isOnGround()
                && !((PlayerEntity) (Object) this).isClimbing()
                && !((PlayerEntity) (Object) this).isTouchingWater()
                && !((PlayerEntity) (Object) this).hasStatusEffect(StatusEffects.BLINDNESS)
                && !((PlayerEntity) (Object) this).hasVehicle()
                && instance instanceof LivingEntity
                && !((PlayerEntity) (Object) this).isSprinting()
                && bl;

        finalAmount *= bl3 && source.getWeaponStack().getItem().equals(ModItems.VORPAL_SWORD) ? 1.5F : 1F;

        return instance.damage(source, finalAmount);
    }

    @Inject(method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;hasVehicle()Z"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getKnockbackAgainst(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;)F")
            )
    )
    private void swoop(Entity target, CallbackInfo ci, @Local(ordinal = 0) float i) {
        debalance$setAttackDamage(i);
        Debalance.LOGGER.info("Final attack damage: " + i);
//        Debalance.LOGGER.info("Bonus attack damage: " + debalance$getBonusDamage());
    }

    @Override
    public void debalance$setPreviousAttackCooldown(float prevAttackCooldown) {
        previousAttackCooldown = prevAttackCooldown;
    }

    @Override
    public float debalance$getPreviousAttackCooldown() {
        return previousAttackCooldown;
    }

    @Override
    public void debalance$setAttackDamage(float attackDmg) {
        attackDamage = attackDmg;
    }

    @Override
    public float debalance$getAttackDamage() {
        return attackDamage;
    }

    @Override
    public void debalance$setBonusDamage(float bonusDmg) {
        bonusDamage = bonusDmg;
    }

    @Override
    public float debalance$getBonusDamage() {
        return bonusDamage;
    }
}