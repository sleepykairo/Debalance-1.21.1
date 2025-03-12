package net.sleepykairo.debalance.item.custom;

import java.util.function.Consumer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.sleepykairo.debalance.entity.ModEntities;
import net.sleepykairo.debalance.entity.custom.DummyEntity;

public class DummyItem extends Item {
    public DummyItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Direction direction = context.getSide();
        if (direction == Direction.DOWN) {
            return ActionResult.FAIL;
        } else {
            World world = context.getWorld();
            ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
            BlockPos blockPos = itemPlacementContext.getBlockPos();
            ItemStack itemStack = context.getStack();
            Vec3d vec3d = Vec3d.ofBottomCenter(blockPos);
            Box box = ModEntities.DUMMY.getDimensions().getBoxAt(vec3d.getX(), vec3d.getY(), vec3d.getZ());
            if (world.isSpaceEmpty(null, box) && world.getOtherEntities(null, box).isEmpty()) {
                if (world instanceof ServerWorld serverWorld) {
                    Consumer<DummyEntity> consumer = EntityType.copier(serverWorld, itemStack, context.getPlayer());
                    DummyEntity dummyEntity = ModEntities.DUMMY.create(serverWorld, consumer, blockPos, SpawnReason.SPAWN_EGG, true, true);
                    if (dummyEntity == null) {
                        return ActionResult.FAIL;
                    }

                    float f = (float)MathHelper.floor((MathHelper.wrapDegrees(context.getPlayerYaw() - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                    dummyEntity.refreshPositionAndAngles(dummyEntity.getX(), dummyEntity.getY(), dummyEntity.getZ(), f, 0.0F);
                    serverWorld.spawnEntityAndPassengers(dummyEntity);
                    world.playSound(
                            null, dummyEntity.getX(), dummyEntity.getY(), dummyEntity.getZ(), SoundEvents.ENTITY_ARMOR_STAND_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F
                    );
                    dummyEntity.emitGameEvent(GameEvent.ENTITY_PLACE, context.getPlayer());
                }

                itemStack.decrement(1);
                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.FAIL;
            }
        }
    }
}