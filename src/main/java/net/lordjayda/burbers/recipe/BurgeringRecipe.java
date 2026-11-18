package net.lordjayda.burbers.recipe;

import net.lordjayda.burbers.crafting.ModRecipeType;
import net.minecraft.world.item.crafting.*;
import org.jspecify.annotations.NonNull;

public interface BurgeringRecipe extends Recipe<BurgeringRecipeInput> {
    //lowk dont know why this needs to be a separate class but its how they did it in vanilla and it works '\_('-' )_/'
    default @NonNull RecipeType<BurgeringRecipe> getType() {
        return ModRecipeType.BURGERING;
    }
}
