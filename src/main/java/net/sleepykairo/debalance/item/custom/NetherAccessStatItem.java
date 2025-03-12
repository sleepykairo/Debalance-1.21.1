package net.sleepykairo.debalance.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sleepykairo.debalance.util.interfaces.PlayerPermStatAccess;

public class NetherAccessStatItem extends Item {
    public NetherAccessStatItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof PlayerPermStatAccess playerPermStatAccess) {
            playerPermStatAccess.debalance$setNetherAccess(!playerPermStatAccess.debalance$getNetherAccess());
        }

        user.getStackInHand(hand).decrement(1);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
