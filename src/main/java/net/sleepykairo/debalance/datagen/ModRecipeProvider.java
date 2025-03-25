package net.sleepykairo.debalance.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.sleepykairo.debalance.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BLAZING_CORE)
                .pattern("NBN")
                .pattern("BGB")
                .pattern("NBN")
                .input('N', Items.NETHER_BRICK)
                .input('B', Items.BLAZE_POWDER)
                .input('G', Items.GOLD_INGOT)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ARROW_STAFF)
                .pattern(" SA")
                .pattern(" BS")
                .pattern("S  ")
                .input('A', Items.ARROW)
                .input('B', Items.BOW)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.ARROW), conditionsFromItem(Items.ARROW))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.MAGMA_CROSSBOW)
                .pattern("PFP")
                .pattern("MCM")
                .pattern("PFP")
                .input('P', Items.BLAZE_POWDER)
                .input('F', Items.FIRE_CHARGE)
                .input('M', Items.MAGMA_CREAM)
                .input('C', Items.CROSSBOW)
                .criterion(hasItem(Items.CROSSBOW), conditionsFromItem(Items.CROSSBOW))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOXBOW)
                .pattern("PFP")
                .pattern("MCM")
                .pattern("SpS")
                .input('P', Items.POISONOUS_POTATO)
                .input('F', Items.FERMENTED_SPIDER_EYE)
                .input('M', Items.MOSSY_COBBLESTONE)
                .input('C', Items.CROSSBOW)
                .input('S', Items.SLIME_BALL)
                .input('p', Items.PUFFERFISH)
                .criterion(hasItem(Items.CROSSBOW), conditionsFromItem(Items.CROSSBOW))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.BONE_SWORD)
                .pattern("SBS")
                .pattern("SBS")
                .pattern("SbS")
                .input('S', Items.STRING)
                .input('B', Items.BONE_BLOCK)
                .input('b', Items.BONE)
                .criterion(hasItem(Items.BONE), conditionsFromItem(Items.BONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SOULBOUND_SWORD)
                .pattern("SWS")
                .pattern("nNn")
                .pattern("LRL")
                .input('S', Items.SOUL_SAND)
                .input('W', Items.WITHER_SKELETON_SKULL)
                .input('n', Items.NETHERITE_SCRAP)
                .input('N', Items.NETHERITE_SWORD)
                .input('L', Items.SOUL_LANTERN)
                .input('R', Items.RESPAWN_ANCHOR)
                .criterion(hasItem(Items.WITHER_SKELETON_SKULL), conditionsFromItem(Items.WITHER_SKELETON_SKULL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.BOG_BLADE)
                .pattern("VFV")
                .pattern("SDS")
                .pattern("LFL")
                .input('V', Items.VINE)
                .input('F', Items.FERMENTED_SPIDER_EYE)
                .input('S', Items.SLIME_BALL)
                .input('D', Items.DIAMOND_SWORD)
                .input('L', Items.LILY_PAD)
                .criterion(hasItem(Items.SPIDER_EYE), conditionsFromItem(Items.SPIDER_EYE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HONEYCOMB_HELMET, 1)
                .input(Items.IRON_HELMET)
                .input(Items.HONEYCOMB, 8)
                .criterion(hasItem(Items.HONEYCOMB), conditionsFromItem(Items.HONEYCOMB))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HONEYCOMB_CHESTPLATE, 1)
                .input(Items.IRON_CHESTPLATE)
                .input(Items.HONEYCOMB, 8)
                .criterion(hasItem(Items.HONEYCOMB), conditionsFromItem(Items.HONEYCOMB))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HONEYCOMB_LEGGINGS, 1)
                .input(Items.IRON_LEGGINGS)
                .input(Items.HONEYCOMB, 8)
                .criterion(hasItem(Items.HONEYCOMB), conditionsFromItem(Items.HONEYCOMB))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HONEYCOMB_BOOTS, 1)
                .input(Items.IRON_BOOTS)
                .input(Items.HONEYCOMB, 8)
                .criterion(hasItem(Items.HONEYCOMB), conditionsFromItem(Items.HONEYCOMB))
                .offerTo(exporter);
    }
}
