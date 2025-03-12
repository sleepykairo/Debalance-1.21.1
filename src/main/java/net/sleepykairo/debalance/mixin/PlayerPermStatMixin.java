package net.sleepykairo.debalance.mixin;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.sleepykairo.debalance.util.interfaces.PlayerPermStatAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerPermStatMixin implements PlayerPermStatAccess {

    private boolean netherAccess;
    @Unique
    private static final TrackedData<Boolean> NETHER_ACCESS = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    public void injectInitDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(NETHER_ACCESS, false);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void injectWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("NetherAccess", debalance$getNetherAccess());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void injectReadCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        debalance$setNetherAccess(nbt.getBoolean("NetherAccess"));
    }

    @Override
    public void debalance$setNetherAccess(boolean bl) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        player.getDataTracker().set(NETHER_ACCESS, bl);
    }

    @Override
    public boolean debalance$getNetherAccess() {
        PlayerEntity player = (PlayerEntity) (Object) this;
        netherAccess = player.getDataTracker().get(NETHER_ACCESS);
        return netherAccess;
    }
}
