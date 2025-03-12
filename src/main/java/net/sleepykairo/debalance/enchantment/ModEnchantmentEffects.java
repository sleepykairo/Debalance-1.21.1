package net.sleepykairo.debalance.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.enchantment.custom.GustEnchantmentEffect;
import net.sleepykairo.debalance.enchantment.custom.VoltageEnchantmentEffect;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> VOLTAGE =
            registerEntityEffect("voltage", VoltageEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> GUST =
            registerEntityEffect("gust", GustEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(Debalance.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        Debalance.LOGGER.info("Registering Enchantment Effects for Debalance!! :D");
    }
}
