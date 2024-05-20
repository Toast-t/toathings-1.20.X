package net.toatd.toathings.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.toatd.toathings.block.ModBlocks;
import net.toatd.toathings.block.custom.WallPlantBlock;

import java.util.Iterator;
import java.util.List;

public class PolyporeTreeDecorator extends TreeDecorator {
    public static final Codec<PolyporeTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(PolyporeTreeDecorator::new, (decorator) -> {
        return decorator.probability;
    }).codec();
    private final float probability;

    public PolyporeTreeDecorator(float probability) {
        this.probability = probability;
    }

    protected TreeDecoratorType<?> getType() {
        return TreeDecoratorType.COCOA;
    }

    public void generate(TreeDecorator.Generator generator) {
        Random random = generator.getRandom();
        if (!(random.nextFloat() >= this.probability)) {
            List<BlockPos> list = generator.getLogPositions();
            int i = ((BlockPos)list.get(0)).getY();
            list.stream().filter((pos) -> {
                return pos.getY() - i <= 2;
            }).forEach((pos) -> {
                Iterator var3 = Direction.Type.HORIZONTAL.iterator();

                while(var3.hasNext()) {
                    Direction direction = (Direction)var3.next();
                    if (random.nextFloat() <= 0.25F) {
                        Direction direction2 = direction.getOpposite();
                        BlockPos blockPos = pos.add(direction2.getOffsetX(), 0, direction2.getOffsetZ());
                        if (generator.isAir(blockPos)) {
                            generator.replace(blockPos, (BlockState)((BlockState) ModBlocks.RESINOUS_POLYPORE.getDefaultState()).with(WallPlantBlock.FACING, direction));
                        }
                    }
                }

            });
        }
    }
}
