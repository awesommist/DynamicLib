package dynamics.container;

import net.minecraft.inventory.IInventory;
import dynamics.inventory.IInventoryProvider;

public abstract class ContainerInventoryProvider<T extends IInventoryProvider> extends ContainerBase<T> {

    public ContainerInventoryProvider(IInventory playerInventory, T owner) {
        super (playerInventory, owner.getInventory(), owner);
    }
}