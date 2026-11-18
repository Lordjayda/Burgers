package net.lordjayda.erstemod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.lordjayda.erstemod.block.custom.CuttingBoard;
import net.lordjayda.erstemod.block.entity.custom.CuttingBoardBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class AssemblerBlockEntityRenderer implements BlockEntityRenderer<CuttingBoardBlockEntity, CuttingBoardBlockEntityRendererState> {
    private final ItemModelResolver itemModelResolver;

    public AssemblerBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public CuttingBoardBlockEntityRendererState createRenderState() {
        return new CuttingBoardBlockEntityRendererState();
    }

    @Override
    public void extractRenderState(
            CuttingBoardBlockEntity blockEntity,
            CuttingBoardBlockEntityRendererState state,
            float partialTicks,
            Vec3 cameraPosition,
            ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
    ) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        Level level = blockEntity.getLevel();

        if (level == null) {
            return;
        }

        state.level = level;

        if (blockEntity.getBlockState().hasProperty(CuttingBoard.FACING)) {
            state.facing = blockEntity.getBlockState().getValue(CuttingBoard.FACING);
        } else {
            state.facing = Direction.NORTH;
        }

        itemModelResolver.updateForTopItem(
                state.itemStackRenderState,
                blockEntity.getTheItem(),
                ItemDisplayContext.FIXED,
                level,
                null,
                0
        );
    }

    @Override
    public void submit(
            CuttingBoardBlockEntityRendererState state,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            CameraRenderState camera
    ) {
        poseStack.pushPose();

        poseStack.translate(0.5f, 0.0f, 0.5f);
        poseStack.rotate(Axis.YP.rotationDegrees(getRotationDegrees(state.facing)));
        poseStack.translate(-0.3f, 0.25f, -0.3f);
        poseStack.scale(0.55f, 0.55f, 0.55f);

        state.itemStackRenderState.submit(
                poseStack,
                submitNodeCollector,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                0
        );

        poseStack.popPose();
    }

    private static float getRotationDegrees(Direction facing) {
        return switch (facing) {
            case EAST -> -90.0f;
            case SOUTH -> 180.0f;
            case WEST -> 90.0f;
            default -> 0.0f;
        };
    }
}