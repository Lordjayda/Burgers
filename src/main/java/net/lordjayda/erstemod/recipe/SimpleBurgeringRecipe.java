package net.lordjayda.erstemod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.lordjayda.erstemod.crafting.ModRecipeType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class SimpleBurgeringRecipe implements BurgeringRecipe {
    private final List<Ingredient> buns;
    private final List<Ingredient> sauces;
    private final List<Ingredient> ingredients;
    final ItemStackTemplate result;
    final CommonInfo commonInfo;
    @Override
    public PlacementInfo placementInfo() {
        List<Ingredient> allIngredients = new ArrayList<>();
        allIngredients.addAll(buns);
        allIngredients.addAll(sauces);
        allIngredients.addAll(ingredients);
        return PlacementInfo.create(allIngredients);
    }
    //smth smth json smth
    public static final MapCodec<SimpleBurgeringRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.listOf().fieldOf("buns").forGetter(SimpleBurgeringRecipe::getBuns),
                    Ingredient.CODEC.listOf().fieldOf("sauces").forGetter(SimpleBurgeringRecipe::getSauces),
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(SimpleBurgeringRecipe::getIngredients),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(r -> r.result),
                    CommonInfo.MAP_CODEC.forGetter(r -> r.commonInfo)
            ).apply(instance, SimpleBurgeringRecipe::new)
    );
    //smth smth networking smth
    public static final StreamCodec<RegistryFriendlyByteBuf, SimpleBurgeringRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), SimpleBurgeringRecipe::getBuns,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), SimpleBurgeringRecipe::getSauces,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), SimpleBurgeringRecipe::getIngredients,
            ItemStackTemplate.STREAM_CODEC, r -> r.result,
            CommonInfo.STREAM_CODEC, r -> r.commonInfo,
            SimpleBurgeringRecipe::new
    );

    public SimpleBurgeringRecipe(List<Ingredient> buns, List<Ingredient> sauces, List<Ingredient> ingredients,
                              ItemStackTemplate result, CommonInfo common) {
        this.buns = buns;
        this.sauces = sauces;
        this.ingredients = ingredients;
        this.result = result;
        this.commonInfo = common;
    }
    @Override
    public boolean matches(BurgeringRecipeInput input, Level level) {

        boolean e =  matchPositional(input.buns, buns)
                && matchPositional(input.sauces, sauces)
                && matchPositional(input.ingredients, ingredients);
        //IO.println(e);
        return e;
    }
    private static boolean matchPositional(List<ItemStack> stacks, List<Ingredient> requirements) {
        List<ItemStack> temp = new ArrayList<>();
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                temp.add(stack);
            }
        }
        if (temp.size() != requirements.size()) {
            return false;
        }
        for (int i = 0; i < requirements.size(); i++) {
            if (!requirements.get(i).test(temp.get(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    //Items, Assemble!
    public ItemStack assemble(BurgeringRecipeInput input) {
        return result.create();
    }
    @Override
    public boolean showNotification() {
        return commonInfo.showNotification();
    }
    @Override
    public String group() {
        return "";
    }
    @Override
    public RecipeSerializer<? extends Recipe<BurgeringRecipeInput>> getSerializer() {
        return ModRecipeType.BURGERING_CEREALIZER;
    }
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
    public List<Ingredient> getBuns() { return buns; }
    public List<Ingredient> getSauces() { return sauces; }
    public List<Ingredient> getIngredients() { return ingredients; }
}