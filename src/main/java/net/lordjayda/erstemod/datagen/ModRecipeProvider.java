package net.lordjayda.erstemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {

        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                /*List<ItemLike> Smeltables = List.of(ModItems.RAW_PATTY);

                oreSmelting(Smeltables, RecipeCategory.FOOD, CookingBookCategory.FOOD, ModItems.PATTY, 0.25f,100, "Burger Ingredients");
                shaped(RecipeCategory.FOOD, ModItems.BURGER)
                        .pattern(" A ")
                        .pattern("BCD")
                        .pattern(" E ")
                        .define('A', ModItems.TOP_BUN)
                        .define('B', ModItems.TOMATO_SLICE)
                        .define('C', ModItems.PATTY)
                        .define('D', ModItems.LETTUCE)
                        .define('E', ModItems.BOTTOM_BUN)
                        .unlockedBy(getHasName(ModItems.PATTY), has(ModItems.PATTY))
                        .group("Burgers")
                        .save(output);
                shaped(RecipeCategory.FOOD, ModItems.BUN)
                        .pattern("RR")
                        .pattern("RR")
                        .define('R',Items.WHEAT)
                        .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                        .group("Burgers")
                        .save(output);

                shaped(RecipeCategory.FOOD, ModBlocks.CUTTING_BOARD)
                        .pattern("AB ")
                        .pattern("CDE")
                        .define('A', Items.STICK)
                        .define('B', Items.FLINT)
                        .define('C', Items.OAK_PLANKS)
                        .define('D', Items.OAK_PLANKS)
                        .define('E', Items.OAK_PLANKS)
                        .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                        .group("Burgers")
                        .save(output);

                shaped(RecipeCategory.FOOD, ModBlocks.ASSEMBLER)
                        .pattern(" B ")
                        .pattern("CDE")
                        .define('B', Items.IRON_BARS)
                        .define('C', Items.OAK_PLANKS)
                        .define('D', Items.OAK_PLANKS)
                        .define('E', Items.OAK_PLANKS)
                        .unlockedBy(getHasName(ModItems.BOTTOM_BUN), has(ModItems.BOTTOM_BUN))
                        .unlockedBy(getHasName(ModItems.TOP_BUN), has(ModItems.TOP_BUN))
                        .group("Burgers")
                        .save(output); */

            }
        };
    }

    @Override
    public String getName() {
        return "Erstemod Recipes";
    }
}
