package net.sleepykairo.debalance.entity.client.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;

public class ModParticles {
    public static final SimpleParticleType FIRE_JAVELIN_EXPLOSION_PARTICLE =
            registerParticle("fire_javelin_explosion_particle", FabricParticleTypes.simple(true));

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Debalance.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        Debalance.LOGGER.info("Registering Particles for Debalance!! :D");
    }
}
