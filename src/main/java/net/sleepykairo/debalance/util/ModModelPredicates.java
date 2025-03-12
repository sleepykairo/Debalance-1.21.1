package net.sleepykairo.debalance.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.component.ModDataComponentTypes;
import net.sleepykairo.debalance.item.ModItems;
import net.sleepykairo.debalance.item.custom.MagmaCrossbowItem;
import net.sleepykairo.debalance.item.custom.MidasSwordItem;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(
                ModItems.MAGMA_CROSSBOW,
                Identifier.ofVanilla("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    } else {
                        return MagmaCrossbowItem.isCharged(stack)
                                ? 0.0F
                                : (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / (float)MagmaCrossbowItem.getPullTime(stack, entity);
                    }
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.MAGMA_CROSSBOW,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !MagmaCrossbowItem.isCharged(stack) ? 1.0F : 0.0F
        );
        ModelPredicateProviderRegistry.register(ModItems.MAGMA_CROSSBOW, Identifier.ofVanilla("charged"), (stack, world, entity, seed) -> MagmaCrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ModItems.MAGMA_CROSSBOW, Identifier.ofVanilla("firework"), (stack, world, entity, seed) -> {
            ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
            return chargedProjectilesComponent != null && chargedProjectilesComponent.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });

        ModelPredicateProviderRegistry.register(ModItems.MIDAS_SWORD, Identifier.of(Debalance.MOD_ID, "midas_sword_value"),
                (stack, world, entity, seed) -> entity == null ? 0 : MidasSwordItem.getValuePercent(stack));
    }
}
