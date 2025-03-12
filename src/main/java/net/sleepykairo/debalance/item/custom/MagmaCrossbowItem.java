package net.sleepykairo.debalance.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class MagmaCrossbowItem extends CrossbowItem {
    public MagmaCrossbowItem(Settings settings) {
        super(settings);
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        ProjectileEntity proj = super.createArrowEntity(world, shooter, weaponStack, projectileStack, critical);
        proj.setOnFireFor(10000);
        return proj;
    }
}
