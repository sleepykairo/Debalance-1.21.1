package net.sleepykairo.debalance.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.sleepykairo.debalance.Debalance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerMixin {

    @Redirect(method = "combineItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    public boolean combineItemsRedirect(ItemStack instance, Item item) { //returns negative fsr
        Debalance.LOGGER.info(instance.getItem().getName().toString());
        Debalance.LOGGER.info(item.getName().toString());
        return instance.isOf(item)
                || instance.isOf(Items.BOOK);
//                || item == Items.BOOK;
    }

/**
 * @author sleepykairo
 * @reason allows items to transfer enchants to books in grindstone
 */
    @Overwrite
    public void transferEnchantments(ItemStack target, ItemStack source) {
        EnchantmentHelper.apply(target, components -> {
            ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.getEnchantments(source);

            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
                RegistryEntry<Enchantment> registryEntry = (RegistryEntry<Enchantment>)entry.getKey();
                if (target.isOf(Items.BOOK) || !registryEntry.isIn(EnchantmentTags.CURSE) || components.getLevel(registryEntry) == 0) {
                    components.add(registryEntry, entry.getIntValue());
                }
            }
        });
    }
}
