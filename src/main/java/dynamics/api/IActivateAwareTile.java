package dynamics.api;

import net.minecraft.entity.player.EntityPlayer;

public interface IActivateAwareTile {
    boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ);
}