package net.sleepykairo.debalance.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.entity.custom.FireJavelinEntity;

@Environment(EnvType.CLIENT)
public class FireJavelinEntityRenderer extends EntityRenderer<FireJavelinEntity> {
    public static final Identifier TEXTURE = Identifier.of(Debalance.MOD_ID,"textures/entity/fire_javelin.png");
    private final FireJavelinEntityModel model;

    public FireJavelinEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new FireJavelinEntityModel(context.getPart(FireJavelinEntityModel.FIRE_JAVELIN));
    }

    public void render(FireJavelinEntity fireJavelinEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, fireJavelinEntity.prevYaw, fireJavelinEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, fireJavelinEntity.prevPitch, fireJavelinEntity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumerProvider, this.model.getLayer(this.getTexture(fireJavelinEntity)), false, fireJavelinEntity.isEnchanted()
        );
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();
        super.render(fireJavelinEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(FireJavelinEntity entity) {
        return TEXTURE;
    }
}
