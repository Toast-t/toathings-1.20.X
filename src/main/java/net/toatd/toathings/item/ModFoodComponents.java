package net.toatd.toathings.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent APPLE_JUICE = new FoodComponent.Builder()
            .hunger(5).saturationModifier(5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST,600),1f).build();

    public static final FoodComponent CARROT_JUICE = new FoodComponent.Builder()
            .hunger(5).saturationModifier(5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE,600),1f).build();

    public static final FoodComponent PUMPKIN_JUICE = new FoodComponent.Builder()
            .hunger(5).saturationModifier(5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,600),1f).build();

    public static final FoodComponent MELON_JUICE = new FoodComponent.Builder()
            .hunger(5).saturationModifier(5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,600),1f).build();

    public static final FoodComponent KELP_JUICE = new FoodComponent.Builder()
            .hunger(5).saturationModifier(5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE,600),1f).build();

    public static final FoodComponent GOLDEN_JUICE = new FoodComponent.Builder()
            .hunger(5).saturationModifier(5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST,1200,1),1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE,1200,1),1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,1200,1),1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,1200,1),1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE,1200,1),1f)
            .build();

}
