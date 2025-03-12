package net.sleepykairo.debalance.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.entity.ModEntities;
import net.sleepykairo.debalance.entity.custom.VoltageLightningEntity;
import net.sleepykairo.debalance.util.interfaces.EntityVoltageAccess;
import net.sleepykairo.debalance.util.interfaces.PlayerAttackAccess;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class VoltageEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<VoltageEnchantmentEffect> CODEC = MapCodec.unit(VoltageEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        float dmg = 0;
        float hitCooldown = 1;
        if (context.owner() instanceof PlayerEntity player) {
            PlayerAttackAccess playerAttackAccess = (PlayerAttackAccess) player;
            dmg = playerAttackAccess.debalance$getAttackDamage();
            hitCooldown = playerAttackAccess.debalance$getPreviousAttackCooldown();
        } else {
            dmg = (float) context.owner().getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        }

        EntityVoltageAccess entityVoltageAccess = (EntityVoltageAccess) user;

        if (level == 1) {
            entityVoltageAccess.debalance$incrementVoltageAmount(hitCooldown * 40);
            if (entityVoltageAccess.debalance$getVoltageAmount() > 100) {
                VoltageLightningEntity lightningEntity = ModEntities.VOLTAGE_LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
                user.damage(context.owner().getDamageSources().lightningBolt(), dmg + (dmg / 2f));

                entityVoltageAccess.debalance$setVoltageAmount(0);
            }
        } else {
            entityVoltageAccess.debalance$incrementVoltageAmount(hitCooldown * 50);
            if (entityVoltageAccess.debalance$getVoltageAmount() > 100) {
                VoltageLightningEntity lightningEntity = ModEntities.VOLTAGE_LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
                if (user instanceof LivingEntity livingUser) {
                    Debalance.LOGGER.info("Health before strike: " + String.valueOf(livingUser.getHealth()));
                    user.damage(context.owner().getDamageSources().lightningBolt(), dmg + (dmg / 1.5f));
                    Debalance.LOGGER.info("Health after strike: " + String.valueOf(livingUser.getHealth()));
                }

                entityVoltageAccess.debalance$setVoltageAmount(0);
            }
        }
        Debalance.LOGGER.info(String.valueOf("Voltage amount: " + entityVoltageAccess.debalance$getVoltageAmount()));
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
