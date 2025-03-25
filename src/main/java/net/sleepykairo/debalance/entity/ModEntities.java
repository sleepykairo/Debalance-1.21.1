package net.sleepykairo.debalance.entity;

import com.jcraft.jorbis.Block;
import com.sun.jna.platform.win32.DBT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.entity.custom.*;

public class ModEntities {

    public static final EntityType<BlockProjectileEntity> BLOCK_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Debalance.MOD_ID, "block_projectile"),
            EntityType.Builder.<BlockProjectileEntity>create(BlockProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.98F, 0.98F)
                    .maxTrackingRange(10)
                    .trackingTickInterval(20)
                    .build()
    );
    public static final EntityType<FireJavelinEntity> FIRE_JAVELIN_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Debalance.MOD_ID, "fire_javelin"),
            EntityType.Builder.<FireJavelinEntity>create(FireJavelinEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
                    .build()
    );
    public static final EntityType<RailbowArrowEntity> RAILBOW_ARROW_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Debalance.MOD_ID, "railbow_arrow"),
            EntityType.Builder.<RailbowArrowEntity>create(RailbowArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
                    .build()
    );

    public static final EntityType<VoltageLightningEntity> VOLTAGE_LIGHTNING_BOLT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Debalance.MOD_ID, "voltage_lightning_bolt"),
            EntityType.Builder.<VoltageLightningEntity>create(VoltageLightningEntity::new, SpawnGroup.MISC)
                    .disableSaving()
                    .dimensions(0.0F, 0.0F)
                    .maxTrackingRange(16)
                    .trackingTickInterval(Integer.MAX_VALUE)
                    .build()

    );

    public static final EntityType<DummyEntity> DUMMY = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Debalance.MOD_ID, "dummy"),
            EntityType.Builder.<DummyEntity>create(DummyEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 1.925F)
                    .eyeHeight(1.7775F)
                    .maxTrackingRange(10)
                    .build()
    );

    public static void registerModEntities() {
        Debalance.LOGGER.info("Registering Entities for Debalance!! :D");
    }
}
