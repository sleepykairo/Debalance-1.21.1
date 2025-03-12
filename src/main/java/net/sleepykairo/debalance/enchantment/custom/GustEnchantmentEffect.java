package net.sleepykairo.debalance.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.entity.ModEntities;
import net.sleepykairo.debalance.entity.custom.VoltageLightningEntity;
import net.sleepykairo.debalance.util.interfaces.EntityVoltageAccess;
import net.sleepykairo.debalance.util.interfaces.PlayerAttackAccess;

public class GustEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<GustEnchantmentEffect> CODEC = MapCodec.unit(GustEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        double vel = user.getVelocity().y > 0 ? user.getVelocity().y + (double) level / 10 : (double) level / 6;
        user.setVelocity(user.getVelocity().x, vel, user.getVelocity().z);

        user.velocityModified = true;
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
