package net.sleepykairo.debalance.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MagmaCrossbowItem extends CrossbowItem {
    public MagmaCrossbowItem(Settings settings) {
        super(settings);
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        ProjectileEntity proj = super.createArrowEntity(world, shooter, weaponStack, projectileStack, critical);
        proj.setOnFireFor(10000);

        if (world instanceof ServerWorld serverWorld) {
            Vec3d pos = shooter.getRotationVector().multiply((double) 2 / 3);
            serverWorld.spawnParticles(
                    ParticleTypes.FLAME,
                    proj.getX() + pos.getX(), proj.getEyeY() + pos.getY(), proj.getZ() + pos.getZ(),
                    10,
                    0.1, 0.1,0.1,
                    0.1
            );
            serverWorld.spawnParticles(
                    ParticleTypes.SMOKE,
                    proj.getX() + pos.getX(), proj.getEyeY() + pos.getY(), proj.getZ() + pos.getZ(),
                    10,
                    0.0, 0.0,0.0,
                    0.1
            );
        }

        return proj;
    }
}
