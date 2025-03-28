package net.sleepykairo.debalance.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sleepykairo.debalance.util.interfaces.ArrowEntityAccess;

public class ToxbowItem extends CrossbowItem {
    public ToxbowItem(Settings settings) {
        super(settings);
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        ProjectileEntity proj = super.createArrowEntity(world, shooter, weaponStack, projectileStack, critical);
        if (proj instanceof ArrowEntity arrowProj) {
            ArrowEntityAccess arrowEntityAccess = (ArrowEntityAccess) arrowProj;
            arrowEntityAccess.debalance$setInitialStack(projectileStack);
            arrowEntityAccess.debalance$setInitialStackPickup(true);

            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.POISON, 50, 0);
            arrowProj.addEffect(statusEffectInstance);
        }

        if (world instanceof ServerWorld serverWorld) {
            Vec3d pos = shooter.getRotationVector().normalize().multiply((double) 2 / 3);
            serverWorld.spawnParticles(
                    ParticleTypes.ITEM_SLIME,
                    proj.getX() + pos.getX(), proj.getEyeY() + pos.getY(), proj.getZ() + pos.getZ(),
                    10,
                    0.1, 0.1,0.1,
                    0.2
            );
        }

        return proj;
    }
}
