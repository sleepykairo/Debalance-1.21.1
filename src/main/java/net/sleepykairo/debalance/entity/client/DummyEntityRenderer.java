package net.sleepykairo.debalance.entity.client;

import net.minecraft.client.render.entity.*;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.entity.custom.DummyEntity;

public class DummyEntityRenderer extends MobEntityRenderer<DummyEntity, DummyEntityModel<DummyEntity>> {
    public DummyEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DummyEntityModel<>(context.getPart(DummyEntityModel.DUMMY)), 0.75f);
    }

    @Override
    public Identifier getTexture(DummyEntity entity) {
        return Identifier.of(Debalance.MOD_ID, "textures/entity/dummy/dummy.png");
    }

}
