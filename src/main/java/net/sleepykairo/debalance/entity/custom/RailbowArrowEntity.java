package net.sleepykairo.debalance.entity.custom;

import net.minecraft.client.particle.DustColorTransitionParticle;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.sleepykairo.debalance.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

public class RailbowArrowEntity extends PersistentProjectileEntity {
    public static final ExplosionBehavior EXPLOSION_BEHAVIOR = new AdvancedExplosionBehavior(
            false, true, Optional.empty(),
            Registries.BLOCK.getEntryList(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
    );

    public RailbowArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public RailbowArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ModEntities.RAILBOW_ARROW_ENTITY, owner, world, stack, shotFrom);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        } else if (this.inGround) {
            createExplosion();
            this.discard();
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        createExplosion();
    }

    public void createExplosion() {
        this.getWorld()
                .createExplosion(
                        this,
                        this.getOwner() != null ? this.getOwner().getDamageSources().explosion(this, this.getOwner()) : null,
                        EXPLOSION_BEHAVIOR,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        4.0F,
                        false,
                        World.ExplosionSourceType.TRIGGER,
                        ParticleTypes.EXPLOSION,
                        ParticleTypes.EXPLOSION_EMITTER,
                        SoundEvents.ENTITY_GENERIC_EXPLODE
                );
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return Items.ARROW.getDefaultStack();
    }
}