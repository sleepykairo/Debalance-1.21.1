package net.sleepykairo.debalance.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Arm;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.item.custom.ModArmorItem;
import net.sleepykairo.debalance.util.interfaces.PlayerEntityArmorAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityArmorMixin implements PlayerEntityArmorAccess {
    @Override
    public String debalance$getArmorType() {
        PlayerEntity player = (PlayerEntity)(Object) this;

        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return null;
            }
        }

        Item boots = (player.getInventory().getArmorStack(0).getItem());
        Item leggings = (player.getInventory().getArmorStack(1).getItem());
        Item chestplate = (player.getInventory().getArmorStack(2).getItem());
        Item helmet = (player.getInventory().getArmorStack(3).getItem());

        boolean modArmor = true;
        for (ItemStack armorStack: player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ModArmorItem)) {
                modArmor = false;
                break;
            }
        }

        if (!modArmor) {
            if (((ArmorItem) boots).getMaterial().equals(((ArmorItem) leggings).getMaterial()) &&
                    ((ArmorItem) boots).getMaterial().equals(((ArmorItem) chestplate).getMaterial()) &&
                    ((ArmorItem) boots).getMaterial().equals(((ArmorItem) helmet).getMaterial())) {
                return ((ArmorItem) boots).getMaterial().getIdAsString();
            }

            return null;
        }

        if (((ModArmorItem) boots).getArmorTypeRegistryEntry().equals(((ModArmorItem) leggings).getArmorTypeRegistryEntry()) &&
                ((ModArmorItem) boots).getArmorTypeRegistryEntry().equals(((ModArmorItem) chestplate).getArmorTypeRegistryEntry()) &&
                ((ModArmorItem) boots).getArmorTypeRegistryEntry().equals(((ModArmorItem) helmet).getArmorTypeRegistryEntry())) {
            return ((ModArmorItem) boots).getArmorType();
        } else {
            return null;
        }
    }

//    @Inject(method = "tick", at = @At("HEAD"))
//    private void lol(CallbackInfo ci) {
//        Debalance.LOGGER.info(debalance$getArmorType());
//    }
}
