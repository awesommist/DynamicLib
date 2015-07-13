/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.container;

import net.minecraft.inventory.IInventory;

public abstract class ContainerInventory<T extends IInventory> extends ContainerBase<T> {

    public ContainerInventory(IInventory playerInventory, T owner) {
        super (playerInventory, owner, owner);
    }
}