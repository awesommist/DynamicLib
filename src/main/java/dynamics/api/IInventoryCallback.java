/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.api;

import net.minecraft.inventory.IInventory;

public interface IInventoryCallback {
    void onInventoryChanged(IInventory inventory, int slot);
}