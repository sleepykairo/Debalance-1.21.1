package net.sleepykairo.debalance.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.item.custom.*;

public class ModItems {

    public static final Item DUMMY = registerItem("dummy", new DummyItem(new Item.Settings().maxCount(1)));
    public static final Item ORE_FINDER = registerItem("ore_finder", new OreFinderItem(new Item.Settings().maxCount(1)));
    public static final Item BLAZING_CORE = registerItem("blazing_core", new NetherAccessStatItem(new Item.Settings().maxCount(1)));

    public static final Item ARROW_STAFF = registerItem("arrow_staff", new ArrowStaffItem(30, new Item.Settings()
            .maxCount(1)
            .maxDamage(150))
    );
    public static final Item RUBY_STAFF = registerItem("ruby_staff", new RubyStaffItem(50, new Item.Settings()
            .maxCount(1)
            .maxDamage(500))
    );
    public static final Item DRIPSTONE_CANNON = registerItem("dripstone_cannon", new DripstoneCannonItem(new Item.Settings()
            .maxCount(1)
            .maxDamage(500))
    );
    public static final Item FIRE_JAVELIN = registerItem("fire_javelin", new FireJavelinItem(new Item.Settings()
            .rarity(Rarity.EPIC)
            .maxDamage(250)
            .attributeModifiers(FireJavelinItem.createAttributeModifiers())
            .component(DataComponentTypes.TOOL, FireJavelinItem.createToolComponent()))
    );

    public static final Item MAGMA_CROSSBOW = registerItem(
            "magma_crossbow",
            new MagmaCrossbowItem(new Item.Settings().maxCount(1).maxDamage(465).fireproof()
                    .component(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT))
    );
    public static final Item TOXBOW = registerItem(
            "toxbow",
            new ToxbowItem(new Item.Settings().maxCount(1).maxDamage(465)
                    .component(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT))
    );

    public static final Item MARBLE_SWORD = registerItem(
            "marble_sword", new SwordItem(ModToolMaterials.MARBLE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(
                    ModToolMaterials.MARBLE, 3, -2.1F)))
    );
    public static final Item VORPAL_SWORD = registerItem(
            "vorpal_sword", new SwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(
                    ToolMaterials.DIAMOND, 6, -2.4F)))
    );
    public static final Item EARTH_SWORD = registerItem(
            "earth_sword", new EarthSwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(
                    ToolMaterials.DIAMOND, 4, -2.4F)))
    );
    public static final Item BONE_SWORD = registerItem(
            "bone_sword", new SwordItem(ModToolMaterials.BONE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(
                    ModToolMaterials.BONE, 3, -2.4F)))
    );
    public static final Item MIDAS_SWORD = registerItem(
            "midas_sword", new MidasSwordItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(
                    ToolMaterials.GOLD, 3, -2.4F))
                    .maxDamage(1000))
    );
    public static final Item SOULBOUND_SWORD = registerItem(
            "soulbound_sword", new SoulboundSwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(
                            ToolMaterials.NETHERITE, -2, -2.4F)))
    );


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Debalance.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Debalance.LOGGER.info("Registering Items for Debalance!! :D");
    }
}
