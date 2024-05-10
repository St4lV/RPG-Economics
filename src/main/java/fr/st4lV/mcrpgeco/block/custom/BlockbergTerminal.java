package fr.st4lV.mcrpgeco.block.custom;

import fr.st4lV.mcrpgeco.block.entity.BlockbergTerminalBlockEntity;
import fr.st4lV.mcrpgeco.block.entity.ModBlockEntities;
import fr.st4lV.mcrpgeco.util.TickableBlockEntity;
import fr.st4lV.mcrpgeco.client.ClientHooks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS;

public class BlockbergTerminal extends HorizontalDirectionalBlock implements EntityBlock {
    public BlockbergTerminal(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return ModBlockEntities.BLOCKBERG_TERMINAL_BE.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return TickableBlockEntity.getTickerHelper(level);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        if(hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        if(!pLevel.isClientSide()) return InteractionResult.SUCCESS;

        BlockEntity be = pLevel.getBlockEntity(pPos);
        if(be instanceof BlockbergTerminalBlockEntity) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openBlockbergTerminalScreen(pPos));

            pLevel.playSound(pPlayer, pPos, SoundEvents.NOTE_BLOCK_BIT.get(), SoundSource.BLOCKS, 1f, 5f);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(FACING, direction);
    }
}