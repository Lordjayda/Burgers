package net.lordjayda.erstemod.menu.custom;

import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.crafting.ModRecipeType;
import net.lordjayda.erstemod.item.ModItems;
import net.lordjayda.erstemod.menu.ModMenuTypes;
import net.lordjayda.erstemod.recipe.BurgeringRecipe;
import net.lordjayda.erstemod.recipe.BurgeringRecipeInput;
import net.lordjayda.erstemod.tags.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssemblerMenu extends AbstractContainerMenu {
    private final Container inventory;
    @Override
    public void slotsChanged(Container container) {
        boolean canCraft = !inventory.getItem(0).isEmpty() && !inventory.getItem(8).isEmpty();

        if (container == this.inventory && inventory instanceof BlockEntity blockEntity) {
            if (canCraft) {
                Level lvl = blockEntity.getLevel();
                if (lvl instanceof ServerLevel lvls) {
                    BurgeringRecipeInput input = new BurgeringRecipeInput(
                            List.of(inventory.getItem(0), inventory.getItem(8)),
                            List.of(inventory.getItem(6), inventory.getItem(7)),
                            List.of(inventory.getItem(1), inventory.getItem(2), inventory.getItem(3), inventory.getItem(4), inventory.getItem(5))
                    );
                    RecipeManager ra = lvls.recipeAccess();
                    Optional<RecipeHolder<BurgeringRecipe>> match =
                            ra.getRecipeFor(ModRecipeType.BURGERING, input, lvls);
                    Erstemod.LOGGER.debug(String.valueOf(match.isPresent()));
                    if (match.isPresent()) {
                        inventory.setItem(9, match.get().value().assemble(input));
                    } else {
                        ItemStack newBurger = new ItemStack(ModItems.BURGER);

                        List<ItemStack> ingredientList = List.of(inventory.getItem(1), inventory.getItem(2), inventory.getItem(3), inventory.getItem(4), inventory.getItem(5));
                        List<ItemStack> newIngredientList = new ArrayList<>();
                        ingredientList.forEach(itemStack -> {
                            if (!itemStack.isEmpty()) newIngredientList.add(itemStack);
                        });

                        List<ItemStack> sauceList = List.of(inventory.getItem(6), inventory.getItem(7));
                        List<ItemStack> newSauceList = new ArrayList<>();
                        sauceList.forEach(itemStack -> {
                            if (!itemStack.isEmpty()) newSauceList.add(itemStack);
                        });

                        List<Component> tooltipList = new ArrayList<>();

                        tooltipList.add(Component.translatable("item.erstemod.burger.ingredients").withColor(TextColor.WHITE).withStyle(ChatFormatting.UNDERLINE));
                        if (!newIngredientList.isEmpty()) {
                            newIngredientList.forEach(itemStack -> {
                                tooltipList.add(itemStack.getDisplayName().plainCopy().withColor(TextColor.GRAY));
                            });
                        } else {
                            tooltipList.add(Component.translatable("item.erstemod.burger.none").withColor(TextColor.GRAY));
                        }
                        tooltipList.add(Component.translatable("item.erstemod.burger.sauces").withColor(TextColor.WHITE).withStyle(ChatFormatting.UNDERLINE));
                        if (!newSauceList.isEmpty()) {
                            newSauceList.forEach(itemStack -> {
                                tooltipList.add(itemStack.getDisplayName().plainCopy().withColor(TextColor.GRAY));
                            });
                        } else {
                            tooltipList.add(Component.translatable("item.erstemod.burger.none").withColor(TextColor.GRAY));
                        }


                        int h = 0;
                        int noi = 0;

                        float s = 0;
                        int nos = 0;
                        //aggregate of ingredient nutrition / saturation -> h
                        for (ItemStack item : newIngredientList) {
                            noi ++;
                            try {
                                h += item.get(DataComponents.FOOD).nutrition();
                                s += item.get(DataComponents.FOOD).saturation();
                            } catch (NullPointerException _) {
                            }
                        }
                        //aggregate of sauce nutrition / saturation -> s
                        for (ItemStack item : newSauceList) {
                            nos ++;
                            try {
                                h += item.get(DataComponents.FOOD).nutrition();
                                s += item.get(DataComponents.FOOD).saturation();
                            } catch (NullPointerException _) {
                            }
                        }

                        int finH = (int) ((double) h /noi * ((1.2f*(noi*0.3f))+1));
                        float finS = Math.min(s, 1f);
                        newBurger.set(DataComponents.FOOD, new FoodProperties(finH,finS,false));
                        newBurger.set(DataComponents.CONSUMABLE, new Consumable((1f+(float) h /10), ItemUseAnimation.EAT, SoundEvents.GENERIC_EAT,true,List.of()));

                        tooltipList.add(Component.empty());
                        tooltipList.add(Component.translatable("item.erstemod.burger.hunger").withColor(TextColor.WHITE).append(String.valueOf(finH)));
                        tooltipList.add(Component.translatable("item.erstemod.burger.saturation").withColor(TextColor.WHITE).append(String.valueOf(finS)));

                        newBurger.set(DataComponents.LORE, new ItemLore(
                                tooltipList
                        ));
                        inventory.setItem(9,newBurger);

                    }
                }
            } else {
                inventory.setItem(9, ItemStack.EMPTY);
            }
        }
    }
    public AssemblerMenu(int containerId, Inventory inv, BlockPos blockPos) {
        this(containerId, inv, inv.player.level().getBlockEntity(blockPos));
    }


    public AssemblerMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenuTypes.ASSEMBLER_MENU, containerId);
        this.inventory = ((Container) blockEntity);
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        //bottom bun
        addSlot(new Slot(inventory, 0, 73, 65) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.BOTTOM_BUN);
            }
            @Override
            public Identifier getNoItemIcon() {
                return Identifier.fromNamespaceAndPath(Erstemod.MOD_ID,"container/slot/bun_bottom");
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });

        //top bun
        addSlot(new Slot(inventory, 8, 73, 13) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.TOP_BUN);
            }
            public Identifier getNoItemIcon() {
                return Identifier.fromNamespaceAndPath(Erstemod.MOD_ID,"container/slot/bun_top");
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });

        //Ingredient Slot 1
        addSlot(new Slot(inventory, 1, 16, 39) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
            return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }
            public Identifier getNoItemIcon() {
                return Identifier.fromNamespaceAndPath(Erstemod.MOD_ID,"container/slot/ingredient");
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });

        //Ingredient Slot 2
        addSlot(new Slot(inventory, 2, 35, 39) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }
            public Identifier getNoItemIcon() {
                return Identifier.fromNamespaceAndPath(Erstemod.MOD_ID,"container/slot/ingredient");
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });

        //Ingredient Slot 3
        addSlot(new Slot(inventory, 3, 54, 39) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }
            public Identifier getNoItemIcon() {
                return Identifier.fromNamespaceAndPath(Erstemod.MOD_ID,"container/slot/ingredient");
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });

        //Ingredient Slot 4
        addSlot(new Slot(inventory, 4, 73, 39) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }
            public Identifier getNoItemIcon() {
                return Identifier.fromNamespaceAndPath(Erstemod.MOD_ID,"container/slot/ingredient");
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });

        //Ingredient Slot 5
        addSlot(new Slot(inventory, 5, 92, 39) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }
            public Identifier getNoItemIcon() {
                return Identifier.fromNamespaceAndPath(Erstemod.MOD_ID,"container/slot/ingredient");
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });

        //sauce 1
        addSlot(new Slot(inventory, 6, 92, 13) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.SAUCES);
            }
            public Identifier getNoItemIcon() {
                return Identifier.fromNamespaceAndPath(Erstemod.MOD_ID,"container/slot/sauce");
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });

        //sauce 2
        addSlot(new Slot(inventory, 7, 92, 65) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.SAUCES);
            }
            public Identifier getNoItemIcon() {
                return Identifier.fromNamespaceAndPath(Erstemod.MOD_ID,"container/slot/sauce");
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });
        // Result Slot
        addSlot(new Slot(inventory, 9, 141, 36) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
            @Override
            public void onTake(Player player, ItemStack stack) {
                for (int i = 0; i < 9; i++) {
                    inventory.setItem(i, ItemStack.EMPTY);
                }
                super.onTake(player, stack);
            }
            @Override
            public void setChanged() {
                slotsChanged(this.container);
                this.container.setChanged();
            }
        });
    }


    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 10;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveSingleItemToAssembler(sourceStack)) {
                return ItemStack.EMPTY;
            }
         return ItemStack.EMPTY;  // EMPTY_ITEM
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            Erstemod.LOGGER.debug("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.inventory.stillValid(pPlayer);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    private boolean moveSingleItemToAssembler(ItemStack stack) {
        Erstemod.LOGGER.debug("Move: " + stack);

        for(int i = TE_INVENTORY_FIRST_SLOT_INDEX;
            i < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT;
            i++) {

            Erstemod.LOGGER.debug("Check Slot: " + i);

            Slot slot = slots.get(i);

            if(slot.mayPlace(stack) && !slot.hasItem()) {
                Erstemod.LOGGER.debug("Found slot");

                ItemStack copy = stack.copy();
                copy.setCount(1);

                slot.set(copy);
                stack.shrink(1);

                return true;
            }
        }

        return false;
    }

}