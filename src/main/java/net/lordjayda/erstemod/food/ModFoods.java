package net.lordjayda.erstemod.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {
    public static final FoodProperties BURGER = new FoodProperties.Builder().nutrition(15).saturationModifier(0.4f).build();
    public static final FoodProperties raw_patty =  new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build();
    public static final FoodProperties patty =  new FoodProperties.Builder().nutrition(10).saturationModifier(0.3f).build();
    public static final FoodProperties tomato =  new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).build();
    public static final FoodProperties tomato_slice =  new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build();
    public static final FoodProperties fries = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).build();
    public static final FoodProperties salad =  new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f).build();
    //kopieren und nutrition und saturation anpassen und in moditems registrieren

}
