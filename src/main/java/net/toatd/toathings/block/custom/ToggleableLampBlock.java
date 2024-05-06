package net.toatd.toathings.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.toatd.toathings.Toathings;

import java.util.logging.Logger;

public class ToggleableLampBlock extends Block {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public boolean On = false;

    public ToggleableLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState) this.getDefaultState().with(LIT, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        BlockState blockState = this.toggleLight(state, world, pos);
        float f = blockState.get(LIT) != false ? 0.6f : 0.5f;
        world.playSound(null, pos, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.2f, f);
        world.emitGameEvent((Entity)player, blockState.get(LIT) != false ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
        return ActionResult.CONSUME;
    }

    public BlockState toggleLight(BlockState state, World world, BlockPos pos) {
        state = (BlockState)state.cycle(LIT);
        world.setBlockState(pos, state, Block.NOTIFY_ALL);
        return state;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT).booleanValue()) {
            world.setBlockState(pos, (BlockState)state.cycle(LIT), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
