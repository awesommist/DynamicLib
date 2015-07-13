/**
 * This class was created by <awesommist>. It's distributed as
 * part of the Extruder Mod. Get the Source Code in github:
 * https://github.com/awesommist/Extruder
 */
package dynamics.utils;

import net.minecraft.item.ItemStack;

public class InventoryUtils {

    public static boolean tryMergeStacks(ItemStack stackToMerge, ItemStack stackInSlot) {
        if (stackInSlot == null || !stackInSlot.isItemEqual(stackToMerge) || !ItemStack.areItemStackTagsEqual(stackToMerge, stackInSlot)) return false;

        int newStackSize = stackInSlot.stackSize + stackToMerge.stackSize;

        final int maxStackSize = stackToMerge.getMaxStackSize();
        if (newStackSize <= maxStackSize) {
            stackToMerge.stackSize = 0;
            stackInSlot.stackSize = newStackSize;
            return true;
        } else if (stackInSlot.stackSize < maxStackSize) {
            stackToMerge.stackSize -= maxStackSize - stackInSlot.stackSize;
            stackInSlot.stackSize = maxStackSize;
            return true;
        }

        return false;
    }
}