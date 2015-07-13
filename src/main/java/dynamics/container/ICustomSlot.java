/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICustomSlot {
    ItemStack onClick(EntityPlayer player, int button, int modifier);

    boolean canDrag();

    boolean canTransferItemsOut();

    boolean canTransferItemsIn();
}