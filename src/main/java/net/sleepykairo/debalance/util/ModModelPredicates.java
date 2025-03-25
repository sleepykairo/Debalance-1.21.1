package net.sleepykairo.debalance.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.item.ModItems;
import net.sleepykairo.debalance.item.custom.MidasSwordItem;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        registerCrossbow(ModItems.MAGMA_CROSSBOW);
        registerCrossbow(ModItems.TOXBOW);
        registerCrossbow(ModItems.RAILBOW);

        ModelPredicateProviderRegistry.register(ModItems.MIDAS_SWORD, Identifier.of(Debalance.MOD_ID, "midas_sword_value"),
                (stack, world, entity, seed) -> entity == null ? 0 : MidasSwordItem.getValuePercent(stack));
    }

    public static void registerCrossbow(Item item) {
        ModelPredicateProviderRegistry.register(
                item,
                Identifier.ofVanilla("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(stack)
                                ? 0.0F
                                : (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack, entity);
                    }
                }
        );
        ModelPredicateProviderRegistry.register(
                item,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
        );
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("charged"), (stack, world, entity, seed) -> CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("firework"), (stack, world, entity, seed) -> {
            ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
            return chargedProjectilesComponent != null && chargedProjectilesComponent.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
    }
}
