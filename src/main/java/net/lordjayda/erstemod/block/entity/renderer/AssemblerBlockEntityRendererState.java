package net.lordjayda.erstemod.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class AssemblerBlockEntityRendererState extends BlockEntityRenderState {
    public Level level;
    public Direction facing = Direction.NORTH;

    public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
}