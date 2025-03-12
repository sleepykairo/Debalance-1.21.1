package net.sleepykairo.debalance.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sleepykairo.debalance.entity.custom.BlockProjectileEntity;

public class EarthSwordItem extends SwordItem {
    public EarthSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            BlockProjectileEntity blockProjectileEntity = new BlockProjectileEntity(
                    world, user, user.getX(), user.getEyeY(), user.getZ(),
                    Vec3d.ZERO, Blocks.DIRT.getDefaultState());
            blockProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 1.0F);
            world.spawnEntity(blockProjectileEntity);

            user.getItemCooldownManager().set(this, 10);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
