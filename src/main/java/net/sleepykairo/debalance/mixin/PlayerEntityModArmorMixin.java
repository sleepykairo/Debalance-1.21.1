package net.sleepykairo.debalance.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.util.interfaces.PlayerEntityArmorAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityModArmorMixin {

    @Unique private int cooldown = 0;

    @Inject(method = "tick", at = @At("TAIL"))
    void tickTailInject(CallbackInfo ci) {
        if (cooldown > 0) {
            cooldown--;
        }
    }

    @Inject(method = "eatFood", at = @At("HEAD"))
    void eatFoodHoneycombArmorInject(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> cir) {
        PlayerEntity player = (PlayerEntity)(Object) this;
        PlayerEntityArmorAccess playerEntityArmorAccess = (PlayerEntityArmorAccess) player;

        if (playerEntityArmorAccess.debalance$getArmorType().equals("honeycomb") && !world.isClient) {
            int nutrition = Math.max(1, foodComponent.nutrition() / 2);
            float saturation = stack.isOf(Items.HONEY_BOTTLE) ? 1.1F : foodComponent.saturation() / 2;

            player.getHungerManager().add(nutrition, saturation);
            player.heal(nutrition);
        }
    }

    @Inject(method = "damage", at = @At("TAIL"))
    void damageGuardianArmorInject(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity)(Object) this;
        PlayerEntityArmorAccess playerEntityArmorAccess = (PlayerEntityArmorAccess) player;

        if (playerEntityArmorAccess.debalance$getArmorType().equals("guardian")) {
            Entity attacker = source.getAttacker();
            if (attacker != null) {
                if (attacker instanceof LivingEntity livingEntity
                        && attacker.distanceTo(player) < 10
                        && cooldown <= 0
                ) {
                    shootBeam(livingEntity);
                }
            }
        }
    }

    @Unique
    void shootBeam(LivingEntity target) {
        Debalance.LOGGER.info("Beam time");
        cooldown = 100;
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getWorld() instanceof ServerWorld serverWorld) {
            double d = 1;
            double e = target.getX() - player.getX();
            double f = target.getBodyY(1) - player.getEyeY();
            double g = target.getZ() - player.getZ();
            double h = Math.sqrt(e * e + f * f + g * g);
            e /= h;
            f /= h;
            g /= h;
            double j = player.getRandom().nextDouble();

            while (j < h) {
                j += 1.8 - d + player.getRandom().nextDouble() * (1.7 - d);
                serverWorld.spawnParticles(
                        ParticleTypes.ENCHANTED_HIT,
                        player.getX() + e * j, player.getEyeY() + f * j, player.getZ() + g * j,
                        25,
                        0.0, 0.0,0.0, 0
                );
            }

            serverWorld.playSound(player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_GUARDIAN_ATTACK, SoundCategory.PLAYERS,
                    1.0F, 1.0F, false);
        }
        target.damage(player.getDamageSources().indirectMagic(player, player), 5.0F);
    }
}
