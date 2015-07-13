/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.container;

import net.minecraft.inventory.IInventory;
import dynamics.inventory.IInventoryProvider;

public abstract class ContainerInventoryProvider<T extends IInventoryProvider> extends ContainerBase<T> {

    public ContainerInventoryProvider(IInventory playerInventory, T owner) {
        super (playerInventory, owner.getInventory(), owner);
    }
}