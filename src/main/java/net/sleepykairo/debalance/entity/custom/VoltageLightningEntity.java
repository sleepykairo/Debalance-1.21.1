package net.sleepykairo.debalance.entity.custom;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class VoltageLightningEntity extends Entity {
    private static final int field_30062 = 2;
    private static final double field_33906 = 3.0;
    private static final double field_33907 = 15.0;
    private int ambientTick;
    public long seed;
    private int remainingActions;
    private boolean cosmetic;
    @Nullable
    private ServerPlayerEntity channeler;
    private final Set<Entity> struckEntities = Sets.<Entity>newHashSet();
    private int blocksSetOnFire;

    public VoltageLightningEntity(EntityType<? extends VoltageLightningEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.ambientTick = 2;
        this.seed = this.random.nextLong();
        this.remainingActions = this.random.nextInt(3) + 1;
    }

    public void setCosmetic(boolean cosmetic) {
        this.cosmetic = cosmetic;
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.PLAYERS;
    }

    @Nullable
    public ServerPlayerEntity getChanneler() {
        return this.channeler;
    }

    public void setChanneler(@Nullable ServerPlayerEntity channeler) {
        this.channeler = channeler;
    }

    @Override
    public void tick() {
        super.tick();
        setCosmetic(true);
        if (this.ambientTick == 2) {
            if (this.getWorld().isClient()) {
//                this.getWorld()
//                        .playSound(
//                                this.getX(),
//                                this.getY(),
//                                this.getZ(),
//                                SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
//                                SoundCategory.WEATHER,
//                                10000.0F,
//                                0.8F + this.random.nextFloat() * 0.2F,
//                                false
//                        );
                this.getWorld()
                        .playSound(
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                                SoundCategory.WEATHER,
                                1.0F,
                                0.75F + this.random.nextFloat() * 0.25F,
                                false
                        );
            }
        }

        this.ambientTick--;
        if (this.ambientTick < 0) {
            if (this.remainingActions == 0) {
                this.discard();
            } else if (this.ambientTick < -this.random.nextInt(10)) {
                this.remainingActions--;
                this.ambientTick = 1;
                this.seed = this.random.nextLong();
            }
        }

        if (this.ambientTick >= 0) {
            if (!(this.getWorld() instanceof ServerWorld)) {
                this.getWorld().setLightningTicksLeft(2);
            }
        }
    }

    private BlockPos getAffectedBlockPos() {
        Vec3d vec3d = this.getPos();
        return BlockPos.ofFloored(vec3d.x, vec3d.y - 1.0E-6, vec3d.z);
    }

    @Override
    public boolean shouldRender(double distance) {
        double d = 16.0 * getRenderDistanceMultiplier();
        return distance < d * d;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }
}
