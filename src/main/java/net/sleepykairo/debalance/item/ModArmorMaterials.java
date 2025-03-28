package net.sleepykairo.debalance.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.sleepykairo.debalance.Debalance;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> HONEYCOMB_ARMOR_MATERIAL = registerArmorMaterial("honeycomb",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 4);
            }), 20, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.ofItems(Items.HONEYCOMB),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Debalance.MOD_ID, "honeycomb"))), 0,0));
    public static final RegistryEntry<ArmorMaterial> GUARDIAN_ARMOR_MATERIAL = registerArmorMaterial("guardian",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 5);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.ofItems(Items.PRISMARINE_SHARD),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Debalance.MOD_ID, "guardian"))), 0,0));

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(Debalance.MOD_ID, name), material.get());
    }
}
