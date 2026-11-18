package net.lordjayda.erstemod.crafting;

import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.recipe.BurgeringRecipe;
import net.lordjayda.erstemod.recipe.SimpleBurgeringRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.*;

public class ModRecipeType {
    /*
    Burgering - verb
      1. The act of doing any activity related to burgers
            "The player is burgering right now."
            "The player burgered and is now eating the burger that they burgered."

    Source: the johnseagull English dictionary
    */
    public static final RecipeType<BurgeringRecipe> BURGERING = register("burgering");
    public static final RecipeSerializer<SimpleBurgeringRecipe> BURGERING_CEREALIZER =
            Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
                    Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, "burgering"),
                    new RecipeSerializer<>(SimpleBurgeringRecipe.CODEC, SimpleBurgeringRecipe.STREAM_CODEC));
    static <T extends Recipe<?>> RecipeType register(final String name) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.withDefaultNamespace(name), new RecipeType<T>() {
            public String toString() {
                return name;
            }
        });
    }
    public static void registerRecipes(){
        Erstemod.LOGGER.info("Registering recipes");
        // :D
    }
}
