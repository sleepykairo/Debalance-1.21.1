package net.sleepykairo.debalance.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.entity.custom.RailbowArrowEntity;

@Environment(EnvType.CLIENT)
public class RailbowArrowEntityRenderer extends ProjectileEntityRenderer<RailbowArrowEntity> {
    public static final Identifier TEXTURE = Identifier.of(Debalance.MOD_ID,"textures/entity/projectiles/railbow_arrow.png");

    public RailbowArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(RailbowArrowEntity railbowArrowEntity) {
        return TEXTURE;
    }
}
