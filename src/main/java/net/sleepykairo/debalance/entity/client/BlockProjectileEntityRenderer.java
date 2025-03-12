package net.sleepykairo.debalance.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.sleepykairo.debalance.entity.custom.BlockProjectileEntity;

@Environment(EnvType.CLIENT)
public class BlockProjectileEntityRenderer extends EntityRenderer<BlockProjectileEntity> {
    private final BlockRenderManager blockRenderManager;

    public BlockProjectileEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderManager = context.getBlockRenderManager();
    }

    @Override
    public Identifier getTexture(BlockProjectileEntity entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }

    public void render(BlockProjectileEntity blockProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        BlockState blockState = blockProjectileEntity.getBlockState();
        if (blockState.getRenderType() == BlockRenderType.MODEL) {
            World world = blockProjectileEntity.getWorld();
            if (blockState != world.getBlockState(blockProjectileEntity.getBlockPos()) && blockState.getRenderType() != BlockRenderType.INVISIBLE) {
                matrixStack.push();
                BlockPos blockPos = BlockPos.ofFloored(blockProjectileEntity.getX(), blockProjectileEntity.getBoundingBox().maxY, blockProjectileEntity.getZ());
                matrixStack.translate(-0.5, 0.0, -0.5);
                this.blockRenderManager
                        .getModelRenderer()
                        .render(
                                world,
                                this.blockRenderManager.getModel(blockState),
                                blockState,
                                blockPos,
                                matrixStack,
                                vertexConsumerProvider.getBuffer(RenderLayers.getMovingBlockLayer(blockState)),
                                false,
                                Random.create(),
                                Random.create().nextLong(),
                                OverlayTexture.DEFAULT_UV
                        );
                matrixStack.pop();
                super.render(blockProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
            }
        }
    }
}
