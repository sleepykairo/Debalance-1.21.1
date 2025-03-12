package net.sleepykairo.debalance;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sleepykairo.debalance.block.ModBlocks;
import net.sleepykairo.debalance.component.ModDataComponentTypes;
import net.sleepykairo.debalance.enchantment.ModEnchantmentEffects;
import net.sleepykairo.debalance.entity.ModEntities;
import net.sleepykairo.debalance.entity.custom.DummyEntity;
import net.sleepykairo.debalance.item.ModItems;
import net.sleepykairo.debalance.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Debalance implements ModInitializer {
	public static final String MOD_ID = "debalance";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEnchantmentEffects.registerEnchantmentEffects();
		ModEntities.registerModEntities();
		ModDataComponentTypes.registerDataComponentTypes();

		ModWorldGeneration.generateModWorldGen();

		FabricDefaultAttributeRegistry.register(ModEntities.DUMMY, DummyEntity.createAttributes());

		LOGGER.info("Debalance Initialized!!! :D");
	}
}