package net.sleepykairo.debalance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.sleepykairo.debalance.block.ModBlocks;
import net.sleepykairo.debalance.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MARBLE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.DUMMY, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ORE_FINDER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ARROW_STAFF, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_STAFF, Models.HANDHELD);

        itemModelGenerator.register(ModItems.MARBLE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.VORPAL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EARTH_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BONE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SOULBOUND_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BOG_BLADE, Models.HANDHELD);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.HONEYCOMB_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HONEYCOMB_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HONEYCOMB_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HONEYCOMB_BOOTS);
    }
}
