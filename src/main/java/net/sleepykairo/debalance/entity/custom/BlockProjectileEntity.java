package net.sleepykairo.debalance.entity.custom;

import com.mojang.logging.LogUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.BrushItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import net.minecraft.world.explosion.Explosion;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.entity.ModEntities;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.function.Predicate;

public class BlockProjectileEntity extends ThrownEntity {
    private BlockState block = Blocks.DIRT.getDefaultState();
    @Nullable
    public NbtCompound blockEntityData;

    public BlockProjectileEntity(EntityType<? extends BlockProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlockProjectileEntity(World world, LivingEntity owner, double x, double y, double z, Vec3d velocity, BlockState block) {
        super(ModEntities.BLOCK_PROJECTILE, world);
        super.setOwner(owner);
        this.block = block;
        this.setPosition(x, y, z);
        this.setVelocity(velocity);
    }

    @Override
    public void tick() {
        super.tick();
        if (getWorld().isClient) {
            this.getWorld().syncWorldEvent(WorldEvents.BLOCK_FINISHED_BRUSHING, this.getBlockPos(), Block.getRawIdFromState(getBlockState()));
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            Entity entity = entityHitResult.getEntity();
            DamageSource source = this.getDamageSources().thrown(this, this.getOwner());

            entity.damage(source, getDamage());
            Debalance.LOGGER.info("Damage was: " + getDamage());
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }

    public float getDamage() {
        double dmg =
                MathHelper.abs((float) getVelocity().x) +
                MathHelper.abs((float) getVelocity().y) +
                MathHelper.abs((float) getVelocity().z);

        return (float) MathHelper.absMax(dmg, 4D);
    }

    public BlockState getBlockState() {
        return this.block;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("BlockState", NbtHelper.fromBlockState(this.block));
        if (this.blockEntityData != null) {
            nbt.put("TileEntityData", this.blockEntityData);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.block = NbtHelper.toBlockState(this.getWorld().createCommandRegistryWrapper(RegistryKeys.BLOCK), nbt.getCompound("BlockState"));
        if (nbt.contains("TileEntityData", NbtElement.COMPOUND_TYPE)) {
            this.blockEntityData = nbt.getCompound("TileEntityData").copy();
        }
    }
}