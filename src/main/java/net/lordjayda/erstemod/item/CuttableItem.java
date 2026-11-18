package net.lordjayda.erstemod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class CuttableItem  {
    public Item input;
    public ItemStack output;
    public List<ItemStack> outputM;
    public int type;

    /**
     * A container for an item and its output when used on the Cutting Board
     * @param input what the player puts on the cutting board
     * @param output what the cutting board gives the player
     */
    public CuttableItem(Item input, ItemStack output) {
        this.input = input;
        this.output = output;
        this.type = 0;
    }
    /**
     * A container for an item and its outputs when used on the Cutting Board
     * @param input what the player puts on the cutting board
     * @param output what the cutting board gives the player
     */
    public CuttableItem(Item input, List<ItemStack> output) {
        this.input = input;
        this.outputM = output;
        this.type = 1;
    }
}
