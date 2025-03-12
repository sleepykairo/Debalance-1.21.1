package net.sleepykairo.debalance.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Unit;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class ChargeableRangedWeaponItem extends Item {

    public ChargeableRangedWeaponItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    public abstract int getRange();

    protected void shootAll(
            ServerWorld world,
            LivingEntity shooter,
            Hand hand,
            ItemStack stack,
            float speed,
            float divergence,
            boolean critical,
            @Nullable LivingEntity target
    ) {

    }

    protected int getWeaponStackDamage(ItemStack projectile) {
        return 1;
    }

    protected abstract void shoot(
            LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target
    );

    protected ProjectileEntity createProjectile(World world, ProjectileEntity projectileEntity, LivingEntity shooter, ItemStack weaponStack, boolean critical) {
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) projectileEntity;
        if (critical) {
            persistentProjectileEntity.setCritical(true);
        }

        return persistentProjectileEntity;
    }

    protected static List<ItemStack> load(ItemStack stack, ItemStack projectileStack, LivingEntity shooter) {
        if (projectileStack.isEmpty()) {
            return List.of();
        } else {
            int i = shooter.getWorld() instanceof ServerWorld serverWorld ? EnchantmentHelper.getProjectileCount(serverWorld, stack, shooter, 1) : 1;
            List<ItemStack> list = new ArrayList(i);
            ItemStack itemStack = projectileStack.copy();

            for (int j = 0; j < i; j++) {
                ItemStack itemStack2 = getProjectile(stack, j == 0 ? projectileStack : itemStack, shooter, j > 0);
                if (!itemStack2.isEmpty()) {
                    list.add(itemStack2);
                }
            }

            return list;
        }
    }

    protected static ItemStack getProjectile(ItemStack stack, ItemStack projectileStack, LivingEntity shooter, boolean multishot) {
        int i = !multishot && !shooter.isInCreativeMode() && shooter.getWorld() instanceof ServerWorld serverWorld
                ? EnchantmentHelper.getAmmoUse(serverWorld, stack, projectileStack, 1)
                : 0;
        if (i > projectileStack.getCount()) {
            return ItemStack.EMPTY;
        } else if (i == 0) {
            ItemStack itemStack = projectileStack.copyWithCount(1);
            itemStack.set(DataComponentTypes.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
            return itemStack;
        } else {
            ItemStack itemStack = projectileStack.split(i);
            if (projectileStack.isEmpty() && shooter instanceof PlayerEntity playerEntity) {
                playerEntity.getInventory().removeOne(projectileStack);
            }

            return itemStack;
        }
    }
}
