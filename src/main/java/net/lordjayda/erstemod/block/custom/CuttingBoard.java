package net.lordjayda.erstemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.lordjayda.erstemod.block.entity.custom.CuttingBoardBlockEntity;
import net.lordjayda.erstemod.item.CuttableItem;
import net.lordjayda.erstemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Prediction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.Map;


public class CuttingBoard extends BaseEntityBlock {


    public static final Map<Direction, VoxelShape> SHAPES = Map.of(
            Direction.NORTH, Shapes.or(
                    Block.box(0, 0, 3, 16, 2, 13),

                    Block.box(11, 0.9, 9, 15, 2.9, 11),

                    Block.box(4, 2.5, 10, 11, 2.6, 11),
                    Block.box(5, 2.5, 9, 11, 2.6, 10),
                    Block.box(6, 2.5, 8, 11, 2.6, 9)
            ),

            Direction.SOUTH, Shapes.or(
                    Block.box(0, 0, 3, 16, 2, 13),

                    Block.box(1, 0.9, 5, 5, 2.9, 7),

                    Block.box(5, 2.5, 5, 12, 2.6, 6),
                    Block.box(5, 2.5, 6, 11, 2.6, 7),
                    Block.box(5, 2.5, 7, 10, 2.6, 8)
            ),
            Direction.EAST, Shapes.or(
                    Block.box(3, 0, 0, 13, 2, 16),

                    Block.box(5, 0.9, 11, 7, 2.9, 15),

                    Block.box(5, 2.5, 4, 6, 2.6, 11),
                    Block.box(6, 2.5, 5, 7, 2.6, 11),
                    Block.box(7, 2.5, 6, 8, 2.6, 11)
            ),


            Direction.WEST, Shapes.or(
                    Block.box(3, 0, 0, 13, 2, 16),

                    Block.box(9, 0.9, 1, 11, 2.9, 5),

                    Block.box(10, 2.5, 5, 11, 2.6, 12),
                    Block.box(9, 2.5, 5, 10, 2.6, 11),
                    Block.box(8, 2.5, 5, 9, 2.6, 10)
            )

    );
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;



    public CuttingBoard(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.addParticle(ParticleTypes.ANGRY_VILLAGER, pos.getX() + 0.5, pos.getY() -0.6, pos.getZ() +0.5, 0 ,1 ,0);

        level.playLocalSound(player, SoundEvents.ANVIL_HIT , SoundSource.BLOCKS, 2f,  1f );

        return InteractionResult.SUCCESS;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    /*@Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ModItems.TOMATO)){ itemStack.shrink(1);
            player.addItem(new ItemStack(ModItems.TOMATO_SLICE, 4));
        }
        if (stack.is(ModItems.LETTUCEHEAD)){ itemStack.shrink(1);
            player.addItem(new ItemStack(ModItems.LETTUCE, 4));
        }
        if (stack.is(ModItems.BUN)){ itemStack.shrink(1);
            player.addItem( new ItemStack(ModItems.TOP_BUN, 1));
            player.addItem(new ItemStack(ModItems.BOTTOM_BUN, 1));
        }
        if (stack.is(Items.BEEF)) {
            itemStack.shrink(1);
            player.addItem(new ItemStack(ModItems.RAW_PATTY,1));
        }
        return InteractionResult.SUCCESS;
    }
     */


    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CuttingBoardBlockEntity cuttingBoardBlockEntity) {
            boolean isCuttingBoardempty = cuttingBoardBlockEntity.isEmpty();

            //Insert
            if (isCuttingBoardempty && !itemStack.isEmpty()) {
                cuttingBoardBlockEntity.setTheItem(itemStack);
                itemStack.shrink(1);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            }
            //Extract
            else if (!isCuttingBoardempty) {
                ItemStack stackOnCuttingBoard = cuttingBoardBlockEntity.getTheItem();

                if (player.isShiftKeyDown()) {
                    ItemStack resultStack = ItemStack.EMPTY;

                    boolean success = false;
                    for (CuttableItem item : ModItems.getCuttableItems()) {
                        if ( stackOnCuttingBoard.is(item.input)) {
                            if (item.type == 0) {
                                resultStack = item.output;
                                if (!player.getInventory().add(resultStack)) {
                                    player.drop(resultStack, false, Prediction.PREDICTED);
                                }
                                success = true;
                            } else if (item.type == 1) {
                                for (ItemStack stack : item.outputM) {
                                    if (!player.getInventory().add(stack)) {
                                        player.drop(stack, false, Prediction.PREDICTED);
                                    }
                                }
                                success = true;
                            }
                        }
                    }
                    if (success) {
                        cuttingBoardBlockEntity.clearContent();
                    }


                } else {
                    cuttingBoardBlockEntity.clearContent();

                    if (!player.getInventory().add(stackOnCuttingBoard)) {
                        player.drop(stackOnCuttingBoard, false, Prediction.PREDICTED);
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (level instanceof ServerLevel serverLevel
                && entity instanceof Player player) {

            player.hurtServer(
                    serverLevel,
                    level.damageSources().cactus(),
                    4.0F
            );
        }
        super.stepOn(level, pos, state, entity);




    }

    @Override
    public void playerDestroy(ServerLevel level, ServerPlayer player,
                              BlockPos pos, BlockState state,
                              @Nullable BlockEntity blockEntity,
                              ItemStack destroyedWith) {

        if(level.getBlockEntity(pos) instanceof CuttingBoardBlockEntity cuttingBoardBlockEntity) {
            cuttingBoardBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }

        super.playerDestroy(
                level,
                player,
                pos,
                state,
                blockEntity,
                destroyedWith
        );
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new CuttingBoardBlockEntity(worldPosition, blockState);
    }
}
