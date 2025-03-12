package net.sleepykairo.debalance.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class DripstoneCannonItem extends Item {
    private int numProjectiles = 5;

    public DripstoneCannonItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
            float f = getPullProgress(i);
            if (!((double)f < 0.1)) {
                if (world instanceof ServerWorld serverWorld) {
                    this.shootAll(serverWorld, playerEntity, playerEntity.getActiveHand(), stack, f * 3.0F, 5F, f == 1.0F, null);
                }

                world.playSound(
                        null,
                        playerEntity.getX(),
                        playerEntity.getY(),
                        playerEntity.getZ(),
                        SoundEvents.ENTITY_ARROW_SHOOT,
                        SoundCategory.PLAYERS,
                        1.0F,
                        1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                );
                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
        }
    }

    public void shootAll(ServerWorld world, LivingEntity shooter, Hand hand, ItemStack stack, float speed, float divergence, boolean critical, @Nullable LivingEntity target) {
//        float f = EnchantmentHelper.getProjectileSpread(world, stack, shooter, 0.0F);
        float i = 1.0F;

        for (int j = 0; j < numProjectiles; j++) {
//            float k = 0;
            i = -i;
            ProjectileEntity projectileEntity = new TridentEntity(world, shooter, stack);
            projectileEntity.setVelocity(shooter, shooter.getPitch(), shooter.getYaw(), 0.0F, speed, divergence);
            world.spawnEntity(projectileEntity);
            stack.damage(1, shooter, LivingEntity.getSlotForHand(hand));
        }
    }

    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 40.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    public int getRange() {
        return 15;
    }
}
