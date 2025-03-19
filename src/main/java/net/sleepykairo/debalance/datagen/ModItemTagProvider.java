package net.sleepykairo.debalance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.sleepykairo.debalance.item.ModItems;
import net.sleepykairo.debalance.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.MARBLE_SWORD)
                .add(ModItems.VORPAL_SWORD)
                .add(ModItems.EARTH_SWORD)
                .add(ModItems.BONE_SWORD)
                .add(ModItems.MIDAS_SWORD)
                .add(ModItems.SOULBOUND_SWORD);

        getOrCreateTagBuilder(ItemTags.TRIDENT_ENCHANTABLE)
                .add(ModItems.FIRE_JAVELIN);

        getOrCreateTagBuilder(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(ModItems.MAGMA_CROSSBOW)
                .add(ModItems.TOXBOW);
    }
}
