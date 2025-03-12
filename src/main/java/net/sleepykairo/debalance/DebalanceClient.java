package net.sleepykairo.debalance;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.sleepykairo.debalance.entity.ModEntities;
import net.sleepykairo.debalance.entity.client.*;
import net.sleepykairo.debalance.entity.client.particle.FireJavelinExplosionParticle;
import net.sleepykairo.debalance.entity.client.particle.ModParticles;
import net.sleepykairo.debalance.entity.custom.FireJavelinEntity;
import net.sleepykairo.debalance.util.ModModelPredicates;

public class DebalanceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BLOCK_PROJECTILE, BlockProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.VOLTAGE_LIGHTNING_BOLT, VoltageLightningEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(DummyEntityModel.DUMMY, DummyEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DUMMY, DummyEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(FireJavelinEntityModel.FIRE_JAVELIN, FireJavelinEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.FIRE_JAVELIN_ENTITY, FireJavelinEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.FIRE_JAVELIN_EXPLOSION_PARTICLE, FireJavelinExplosionParticle.Factory::new);

        ModModelPredicates.registerModelPredicates();
    }
}
