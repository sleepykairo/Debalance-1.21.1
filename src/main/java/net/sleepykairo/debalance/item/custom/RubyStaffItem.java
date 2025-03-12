package net.sleepykairo.debalance.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RubyStaffItem extends StaffItem{
    public RubyStaffItem(int cooldown, Settings settings) {
        super(cooldown, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        playUseSound(world, user, user.getPos());
        if (!world.isClient) {
//            FireballEntity fireballEntity = new FireballEntity(world, user, Vec3d.ZERO, 1);
//            fireballEntity.setPos(user.getX(), user.getEyeY(), user.getZ());
//            fireballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0.0F);
//            world.spawnEntity(fireballEntity);

            for (int i = 0; i < 5; i++) {
                SmallFireballEntity smallFireballEntity = new SmallFireballEntity(world, user, Vec3d.ZERO);
                smallFireballEntity.setPos(user.getX(), user.getEyeY(), user.getZ());
                smallFireballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1 + i * 1.5F);
                world.spawnEntity(smallFireballEntity);
            }
        }
        return super.use(world, user, hand);
    }

    public void playUseSound(World world, PlayerEntity user, Vec3d pos) {
        world.playSound(
                user,
                pos.getX(),
                pos.getX(),
                pos.getZ(),
                SoundEvents.ENTITY_BLAZE_SHOOT,
                SoundCategory.PLAYERS,
                1.0F,
                0.75F
        );
    }
}
