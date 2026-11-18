package net.lordjayda.burbers.tags;

import net.lordjayda.burbers.Burbers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.VillagerTrade;

public class ModTags {

    public static class Items {
        public static final TagKey<Item> ASSEMBLER_INGREDIENTS = createTag("assembler/ingredients");
        public static final TagKey<Item> TOP_BUN = createTag("assembler/top_bun");
        public static final TagKey<Item> BOTTOM_BUN = createTag("assembler/bottom_bun");
        public static final TagKey<Item> SAUCES = createTag("assembler/sauces");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Burbers.MOD_ID, name));
        }
    }

    public static class Trades {
        public static final TagKey<VillagerTrade> FAST_FOOD_WORKER_1 =
                createTag("fast_food_worker/level_1");

        private static TagKey<VillagerTrade> createTag(String id) {
            return TagKey.create(
                    Registries.VILLAGER_TRADE,
                    Identifier.fromNamespaceAndPath(Burbers.MOD_ID, id)
            );
        }
    }
}