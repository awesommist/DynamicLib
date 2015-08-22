package dynamics.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICustomSlot {
    ItemStack onClick(EntityPlayer player, int button, int modifier);

    boolean canDrag();

    boolean canTransferItemsOut();

    boolean canTransferItemsIn();
}