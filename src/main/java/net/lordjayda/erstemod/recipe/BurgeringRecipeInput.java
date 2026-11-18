package net.lordjayda.erstemod.recipe;

import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class BurgeringRecipeInput implements RecipeInput {
    public List<ItemStack> buns;
    public List<ItemStack> sauces;
    public List<ItemStack> ingredients;
    public BurgeringRecipeInput(List<ItemStack> buns, List<ItemStack> sauces, List<ItemStack> ingredients){
        this.buns = buns;
        this.sauces = sauces;
        this.ingredients = ingredients;
    }
    @Override
    public @NonNull ItemStack getItem(int slot) {
        if (slot < 2) {
            return buns.get(slot);
        }
        if (slot < 4) {
            return sauces.get(slot - 2);
        }
        return ingredients.get(slot - 4);
    }
    @Override
    public int size() {
        return 9;
    }
}
