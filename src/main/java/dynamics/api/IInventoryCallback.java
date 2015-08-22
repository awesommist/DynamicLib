package dynamics.api;

import net.minecraft.inventory.IInventory;

public interface IInventoryCallback {
    void onInventoryChanged(IInventory inventory, int slot);
}