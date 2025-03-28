package net.sleepykairo.debalance.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sleepykairo.debalance.Debalance;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private String armorType;

    private static final Map<RegistryEntry<String>, List<StatusEffectInstance>> ARMOR_TYPE_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryEntry<String>, List<StatusEffectInstance>>())
                    .put(RegistryEntry.of("guardian"),
                            List.of(new StatusEffectInstance(StatusEffects.HASTE, 400, 0, false, false),
                                    new StatusEffectInstance(StatusEffects.WATER_BREATHING, 400, 0, false, false))).build();

    public ModArmorItem(String armorType, RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
        setArmorType(armorType);
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public String getArmorType() {
        return this.armorType;
    }

    public RegistryEntry<String> getArmorTypeRegistryEntry() {
        return RegistryEntry.of(this.armorType);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player) {
                if(hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<RegistryEntry<String>, List<StatusEffectInstance>> entry : ARMOR_TYPE_TO_EFFECT_MAP.entrySet()) {
            RegistryEntry<String> mapArmorType = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if(hasCorrectArmorOn(mapArmorType, player)) {
                addStatusEffectForArmorType(player, mapArmorType, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForArmorType(PlayerEntity player, RegistryEntry<String> mapArmorType, List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if(!hasPlayerEffect) {
            for (StatusEffectInstance instance : mapStatusEffect) {
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles()));
            }
        }
    }

    public boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    public boolean hasCorrectArmorOn(RegistryEntry<String> armorType, PlayerEntity player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ModArmorItem)) {
                return false;
            }
        }

        ModArmorItem boots = ((ModArmorItem)player.getInventory().getArmorStack(0).getItem());
        ModArmorItem leggings = ((ModArmorItem)player.getInventory().getArmorStack(1).getItem());
        ModArmorItem chestplate = ((ModArmorItem)player.getInventory().getArmorStack(2).getItem());
        ModArmorItem helmet = ((ModArmorItem)player.getInventory().getArmorStack(3).getItem());


//        return RegistryEntry.of(helmet.getArmorType()).equals(armorType)
//                && RegistryEntry.of(chestplate.getArmorType()).equals(armorType)
//                && RegistryEntry.of(leggings.getArmorType()).equals(armorType)
//                && RegistryEntry.of(boots.getArmorType()).equals(armorType);
        return boots.getArmorTypeRegistryEntry().equals(armorType) &&
                leggings.getArmorTypeRegistryEntry().equals(armorType) &&
                chestplate.getArmorTypeRegistryEntry().equals(armorType) &&
                helmet.getArmorTypeRegistryEntry().equals(armorType);
    }
}