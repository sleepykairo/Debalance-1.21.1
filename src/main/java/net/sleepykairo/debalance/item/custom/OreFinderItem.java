package net.sleepykairo.debalance.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.sleepykairo.debalance.block.ModBlocks;
import org.joml.Vector3d;

import java.util.Objects;

public class OreFinderItem extends Item {
    public OreFinderItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            BlockPos clickPos = context.getBlockPos();

            for (int y = clickPos.getY(); y > 0; y--) {
                for (int z = -1; z <= 1; z++) {
                    for (int x = -1; x <= 1; x++) {
                        Vec3i vec = new Vec3i(x, y, z);
                        BlockPos pos = clickPos.add(vec);
                        BlockState state = context.getWorld().getBlockState(pos);
                        context.getWorld().setBlockState(pos, Blocks.GOLD_BLOCK.getDefaultState());
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }
}
