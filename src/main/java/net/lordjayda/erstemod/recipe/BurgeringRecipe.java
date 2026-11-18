package net.lordjayda.erstemod.recipe;

import com.mojang.serialization.MapCodec;
import net.lordjayda.erstemod.crafting.ModRecipeType;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface BurgeringRecipe extends Recipe<BurgeringRecipeInput> {
    //lowk dont know why this needs to be a separate class but its how they did it in vanilla and it works '\_('-' )_/'
    default @NonNull RecipeType<BurgeringRecipe> getType() {
        return ModRecipeType.BURGERING;
    }
}
