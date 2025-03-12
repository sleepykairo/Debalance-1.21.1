package net.sleepykairo.debalance.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<Integer> MIDAS_SWORD_VALUE = register(
            "midas_sword_value", builder -> builder.codec(Codec.INT));
    public static final ComponentType<Float> SOULBOUND_SWORD_VALUE = register(
            "soulbound_sword_value", builder -> builder.codec(Codec.FLOAT));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Debalance.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        Debalance.LOGGER.info("Registering Data Component Types for Debalance!! :D");
    }
}
