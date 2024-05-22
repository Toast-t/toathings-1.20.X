package net.toatd.toathings.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.toatd.toathings.Toathings;
import net.toatd.toathings.block.ModBlocks;
import net.toatd.toathings.block.custom.WallPlantBlock;
import net.toatd.toathings.world.gen.ModTreeGeneration;

public class PolyporeTreeDecorator extends TreeDecorator {
    public static final PolyporeTreeDecorator INSTANCE = new PolyporeTreeDecorator();
    public static final Codec<PolyporeTreeDecorator> CODEC = Codec.unit(() -> INSTANCE);

    public PolyporeTreeDecorator() {

    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return Toathings.POLYPORE_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();

        ObjectArrayList<BlockPos> list = generator.getLogPositions();
        int i = ((BlockPos)list.get(0)).getY();

        // Pick a value from 0 (inclusive) to 4 (exclusive) and if it's 0, continue
        // This is the chance for spawning the gold block
        list.stream().filter(pos -> pos.getY() - i <= 5).forEach(pos -> {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                Direction direction2;
                BlockPos blockPos;
                if (!(random.nextFloat() <= 0.25f) || !generator.isAir(blockPos = pos.add((direction2 = direction).getOffsetX(), 0, direction2.getOffsetZ()))) continue;
                generator.replace(blockPos, ModBlocks.RESINOUS_POLYPORE.getDefaultState().with(WallPlantBlock.FACING, direction));
            }
        });
    }
}
