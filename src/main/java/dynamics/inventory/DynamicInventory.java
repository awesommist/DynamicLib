/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import dynamics.api.IInventoryCallback;
import dynamics.utils.ItemUtils;

public class DynamicInventory implements IInventory {

    public static final String TAG_SLOT = "Slot";
    public static final String TAG_ITEMS = "Items";
    public static final String TAG_SIZE = "size";

    protected List<IInventoryCallback> callbacks;
    protected String inventoryTitle;
    protected int size;
    protected ItemStack[] inventoryContents;
    protected boolean isInvNameLocalized;

    public DynamicInventory(String name, boolean isInvNameLocalized, int size) {
        this.callbacks = new ArrayList<IInventoryCallback>();
        this.inventoryTitle = name;
        this.size = size;
        this.inventoryContents = new ItemStack[size];
        this.isInvNameLocalized = isInvNameLocalized;
    }

    public DynamicInventory addCallback(IInventoryCallback callback) {
        callbacks.add(callback);
        return this;
    }

    public void onInventoryChanged(int slot) {
        for (IInventoryCallback callback : callbacks)
            callback.onInventoryChanged(this, slot);
    }

    @Override
    public int getSizeInventory() {
        return size;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventoryContents[slot];
    }

    public ItemStack getStackInSlot(Enum<?> i) {
        return getStackInSlot(i.ordinal());
    }

    @Override
    public ItemStack decrStackSize(int slot, int count) {
        if (inventoryContents[slot] != null) {
            ItemStack itemstack;

            if (inventoryContents[slot].stackSize <= count) {
                itemstack = inventoryContents[slot];
                inventoryContents[slot] = null;
                onInventoryChanged(slot);
                return itemstack;
            }
            itemstack = inventoryContents[slot].splitStack(count);
            if (inventoryContents[slot].stackSize == 0)
                inventoryContents[slot] = null;

            onInventoryChanged(slot);
            return itemstack;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (slot >= inventoryContents.length)
            return null;
        if (inventoryContents[slot] != null) {
            ItemStack itemstack = inventoryContents[slot];
            inventoryContents[slot] = null;
            return itemstack;
        }
        return null;
    }

    public boolean isItem(int slot, Item item) {
        return inventoryContents[slot] != null && inventoryContents[slot].getItem() == item;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventoryContents[slot] = stack;

        if (stack != null && stack.stackSize > getInventoryStackLimit())
            stack.stackSize = getInventoryStackLimit();

        onInventoryChanged(slot);
    }

    @Override
    public String getInventoryName() {
        return inventoryTitle;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return isInvNameLocalized;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }


    @Override
    public void markDirty() {
        onInventoryChanged(0);
    }

    public void writeToNBT(NBTTagCompound tag) {
        tag.setInteger(TAG_SIZE, getSizeInventory());
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < inventoryContents.length; i++) {
            if (inventoryContents[i] != null) {
                NBTTagCompound stacktag = ItemUtils.writeStack(inventoryContents[i]);
                stacktag.setByte(TAG_SLOT, (byte)i);
                nbttaglist.appendTag(stacktag);
            }
        }
        tag.setTag(TAG_ITEMS, nbttaglist);
    }

    public void readFromNBT(NBTTagCompound tag) {
        if (tag.hasKey(TAG_SIZE))
            size = tag.getInteger(TAG_SIZE);

        NBTTagList nbttaglist = tag.getTagList(TAG_ITEMS, 10);
        inventoryContents = new ItemStack[size];

        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound stacktag = nbttaglist.getCompoundTagAt(i);
            int j = stacktag.getByte(TAG_SLOT);
            if (j >= 0 && j < inventoryContents.length) {
                inventoryContents[j] = ItemUtils.readStack(stacktag);
            }
        }
    }

    public void clearAndSetSlotCount(int amount) {
        size = amount;
        inventoryContents = new ItemStack[amount];
        onInventoryChanged(0);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }

    public List<ItemStack> contents() {
        return Arrays.asList(inventoryContents);
    }
}