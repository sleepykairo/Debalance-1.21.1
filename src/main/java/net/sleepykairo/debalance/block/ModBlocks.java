package net.sleepykairo.debalance.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.sleepykairo.debalance.Debalance;

public class ModBlocks {

    public static final Block MARBLE = registerBlock("marble",
            new Block(AbstractBlock.Settings.create().strength(3f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));


    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(4, 9),
                    AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool().strength(3.0F, 3.0F)));

    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(4, 9),
                    AbstractBlock.Settings.copyShallow(Blocks.DIAMOND_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(4.5F, 3.0F)
                            .sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(4, 9),
                    AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool().strength(3.0F, 3.0F)));

    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(4, 9),
                    AbstractBlock.Settings.copyShallow(Blocks.DIAMOND_ORE)
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .strength(4.5F, 3.0F)
                            .sounds(BlockSoundGroup.DEEPSLATE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Debalance.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Debalance.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Debalance.LOGGER.info("Registering Blocks for Debalance!! :D");
    }
}
