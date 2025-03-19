package net.sleepykairo.debalance.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ArrowStaffItem extends StaffItem {
    public ArrowStaffItem(int cooldown, Settings settings) {
        super(cooldown, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        playUseSound(world, user, user.getPos());
        if (!world.isClient) {
            for (int i = 0; i < 3; i++) {
                ArrowEntity arrowEntity = new ArrowEntity(world, user, Items.ARROW.getDefaultStack(), user.getStackInHand(hand));
                arrowEntity.setPos(user.getX(), user.getEyeY(), user.getZ());
                arrowEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, i * 1.5F);
                world.spawnEntity(arrowEntity);
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
