package net.lordjayda.erstemod.item;

import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.food.ModFoods;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModItems {

    public static final Item BURGER = registerItem( "burger", properties -> new Item(properties.food(ModFoods.BURGER)));
    public static final Item LETTUCE = registerItem( "lettuce", properties -> new Item(properties.food(ModFoods.salad)));
    public static final Item LETTUCEHEAD = registerItem( "lettuce_head", properties -> new Item(properties.food(ModFoods.salad)));
    public static final Item LETTUCE_SEED = registerItem( "lettuce_seed", properties -> new BlockItem(ModBlocks.lettuce_headcrop, properties.useItemDescriptionPrefix()));
    public static final Item TOMATO = registerItem( "tomato", properties -> new Item(properties.food(ModFoods.tomato)));
    public static final Item TOMATO_SLICE = registerItem( "tomato_slice", properties -> new Item(properties.food(ModFoods.tomato_slice)));
    public static final Item TOMATO_SEED = registerItem( "tomato_seed", properties -> new BlockItem(ModBlocks.tomatocrop, properties.useItemDescriptionPrefix()));
    public static final Item PATTY = registerItem( "patty", properties -> new Item(properties.food(ModFoods.patty)));
    public static final Item RAW_PATTY = registerItem( "raw_patty", properties -> new Item(properties.food(ModFoods.raw_patty)));
    public static final Item BUN = registerItem( "bun", Item::new );
    public static final Item TOP_BUN = registerItem( "top_bun", Item::new );
    public static final Item BOTTOM_BUN = registerItem( "bottom_bun", Item::new );
    public static final Item FRIES = registerItem( "fries", properties -> new Item(properties.food(ModFoods.fries)));


    public static List<CuttableItem> getCuttableItems() {
        return List.of(
                    new CuttableItem(LETTUCEHEAD, new ItemStack(LETTUCE, 4)),
                    new CuttableItem(TOMATO, new ItemStack(TOMATO_SLICE, 4)),
                    new CuttableItem(Items.BEEF, new ItemStack(RAW_PATTY, 1)),
                    new CuttableItem(BUN, List.of(new ItemStack(TOP_BUN, 1), new ItemStack(BOTTOM_BUN, 1))),
                    new CuttableItem(Items.BAKED_POTATO, List.of(new ItemStack(FRIES, 1)))
        );
    }

//einfach kopieren und namen und id ändern


    public static ResourceKey<Item> getRK(Item item ) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }




    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name)))));
    }

    public static void registerModItems () {
        Erstemod.LOGGER.info("Registering Mod Items for" + Erstemod.MOD_ID);

    }
}
