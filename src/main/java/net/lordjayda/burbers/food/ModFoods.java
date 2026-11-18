package net.lordjayda.burbers.food;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties BURGER = new FoodProperties.Builder().nutrition(15).saturationModifier(0.4f).build();
    public static final FoodProperties raw_patty =  new FoodProperties.Builder().nutrition(3).saturationModifier(0.2f).build();
    public static final FoodProperties patty =  new FoodProperties.Builder().nutrition(6).saturationModifier(0.5f).build();
    public static final FoodProperties tomato =  new FoodProperties.Builder().nutrition(6).saturationModifier(0.3f).build();
    public static final FoodProperties tomato_slice =  new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).build();
    public static final FoodProperties fries = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).build();
    public static final FoodProperties salad =  new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f).build();
    //kopieren und nutrition und saturation anpassen und in moditems registrieren

}
