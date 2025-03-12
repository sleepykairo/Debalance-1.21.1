package net.sleepykairo.debalance.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.sleepykairo.debalance.Debalance;
import net.sleepykairo.debalance.entity.custom.DummyEntity;

// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class DummyEntityModel<T extends DummyEntity> extends SinglePartEntityModel<T> {
	public static final EntityModelLayer DUMMY = new EntityModelLayer(Identifier.of(Debalance.MOD_ID, "dummy"), "main");

	private final ModelPart root;
	private final ModelPart dummy;
	private final ModelPart body;
	private final ModelPart base;
	private final ModelPart leg;
	private final ModelPart neck;
	private final ModelPart arms;

	public DummyEntityModel(ModelPart root) {
		this.root = root.getChild("root");
		this.dummy = this.root.getChild("dummy");
		this.body = this.dummy.getChild("body");
		this.base = this.dummy.getChild("base");
		this.leg = this.dummy.getChild("leg");
		this.neck = this.dummy.getChild("neck");
		this.arms = this.dummy.getChild("arms");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData dummy = root.addChild("dummy", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body = dummy.addChild("body", ModelPartBuilder.create().uv(28, 0).cuboid(-4.5F, -21.0F, -4.5F, 9.0F, 12.0F, 9.0F, new Dilation(0.0F))
		.uv(0, 16).cuboid(-4.0F, -31.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData base = dummy.addChild("base", ModelPartBuilder.create().uv(0, 32).cuboid(-6.0F, 11.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

		ModelPartData leg = dummy.addChild("leg", ModelPartBuilder.create().uv(8, 0).cuboid(-1.15F, 3.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.15F, -12.0F, 0.0F));

		ModelPartData neck = dummy.addChild("neck", ModelPartBuilder.create().uv(8, 0).cuboid(-1.15F, -11.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.15F, -12.0F, 0.0F));

		ModelPartData arms = dummy.addChild("arms", ModelPartBuilder.create().uv(3, 0).cuboid(-1.0F, -19.0F, -11.0F, 2.0F, 2.0F, 7.0F, new Dilation(0.0F))
				.uv(3, 0).cuboid(-1.0F, -19.0F, 4.0F, 2.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(DummyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		root.render(matrices, vertexConsumer, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return dummy;
	}
}