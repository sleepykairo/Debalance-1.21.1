package net.sleepykairo.debalance.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.entity.custom.DummyEntity;
import net.sleepykairo.debalance.entity.custom.FireJavelinEntity;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class FireJavelinEntityModel extends Model {
	public static final EntityModelLayer FIRE_JAVELIN = new EntityModelLayer(Identifier.of(Debalance.MOD_ID, "fire_javelin"), "main");

	private final ModelPart root;
	private final ModelPart grip;
	private final ModelPart body;
	private final ModelPart tip;
	public FireJavelinEntityModel(ModelPart root) {
		super(RenderLayer::getEntitySolid);
		this.root = root.getChild("root");
		this.grip = this.root.getChild("grip");
		this.body = this.root.getChild("body");
		this.tip = this.root.getChild("tip");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(14, 13).cuboid(-0.75F, -1.0F, -0.75F, 1.5F, 1.0F, 1.5F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData base_r1 = root.addChild("base_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -22.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData grip = root.addChild("grip", ModelPartBuilder.create().uv(10, 13).cuboid(-0.5F, -8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

		ModelPartData cube_r1 = grip.addChild("cube_r1", ModelPartBuilder.create().uv(10, 10).cuboid(-0.5F, -0.5F, -1.5F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(10, 10).cuboid(-0.5F, -5.5F, -1.5F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, -3.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 4).cuboid(-0.5F, 16.0F, -0.5F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 0).cuboid(-0.5F, 5.0F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -27.0F, 0.0F));

		ModelPartData tip = root.addChild("tip", ModelPartBuilder.create().uv(12, 6).cuboid(-0.5F, -17.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 6).cuboid(-0.5F, -24.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 4).cuboid(-1.5F, -22.0F, -0.5F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 10).cuboid(-0.5F, -21.5F, -1.0F, 1.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		this.root.render(matrices, vertices, light, overlay, color);
	}
}