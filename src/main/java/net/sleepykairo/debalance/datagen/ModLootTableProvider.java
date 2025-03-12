package net.sleepykairo.debalance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import net.sleepykairo.debalance.block.ModBlocks;
import net.sleepykairo.debalance.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.MARBLE);

        addDrop(ModBlocks.RUBY_ORE, oreDrops(ModBlocks.RUBY_ORE, ModItems.DUMMY));
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, oreDrops(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.DUMMY));
        addDrop(ModBlocks.SAPPHIRE_ORE, oreDrops(ModBlocks.SAPPHIRE_ORE, ModItems.DUMMY));
        addDrop(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, oreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.DUMMY));
    }
}
