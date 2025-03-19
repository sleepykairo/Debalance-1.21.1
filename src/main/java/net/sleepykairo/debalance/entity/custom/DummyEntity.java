package net.sleepykairo.debalance.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sleepykairo.debalance.item.ModItems;
import net.sleepykairo.debalance.util.interfaces.PlayerAttackAccess;
import org.jetbrains.annotations.Nullable;

public class DummyEntity extends AmbientEntity {
    public DummyEntity(EntityType<? extends AmbientEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
//        if (source.getAttacker() instanceof PlayerEntity player) {
//            PlayerAttackAccess playerAttackAccess = (PlayerAttackAccess) player;
//
//            setCustomName(Text.literal(String.valueOf(playerAttackAccess.debalance$getAttackDamage())));
//            setCustomNameVisible(true);
//        }
        setCustomName(Text.literal(String.valueOf(amount)));
        setCustomNameVisible(true);
        return super.damage(source, amount);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!player.getStackInHand(hand).isOf(Items.AIR) || hand == Hand.OFF_HAND) {
            return super.interactMob(player, hand);
        }
        player.setStackInHand(hand, new ItemStack(ModItems.DUMMY));
        this.discard();
        return ActionResult.success(this.getWorld().isClient);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100000)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100000);
    }

    @Override
    public boolean canMoveVoluntarily() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        heal(100000);
    }

}
