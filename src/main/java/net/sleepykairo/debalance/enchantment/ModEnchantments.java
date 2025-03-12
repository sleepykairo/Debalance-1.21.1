package net.sleepykairo.debalance.enchantment;

import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.enchantment.custom.GustEnchantmentEffect;
import net.sleepykairo.debalance.enchantment.custom.VoltageEnchantmentEffect;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> VOLTAGE =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Debalance.MOD_ID, "voltage"));
    public static final RegistryKey<Enchantment> GUST =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Debalance.MOD_ID, "gust"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, VOLTAGE, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                2,
                2,
                Enchantment.leveledCost(15, 9),
                Enchantment.leveledCost(65, 9),
                4,
                AttributeModifierSlot.MAINHAND))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new VoltageEnchantmentEffect()));

        register(registerable, GUST, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        2,
                        2,
                        Enchantment.leveledCost(15, 9),
                        Enchantment.leveledCost(65, 9),
                        4,
                        AttributeModifierSlot.MAINHAND))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new GustEnchantmentEffect()));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
