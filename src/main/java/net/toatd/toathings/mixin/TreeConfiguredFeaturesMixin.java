package net.toatd.toathings.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(TreeConfiguredFeatures.class)
public class TreeConfiguredFeaturesMixin {
	@ModifyReturnValue(method = {"birch", "superBirch"}, at = @At("TAIL"))
	private static TreeFeatureConfig.Builder changeBirch(TreeFeatureConfig.Builder original) {
		return original.decorators(List.of(new BeehiveTreeDecorator(1.0f)));
	}
}

