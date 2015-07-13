/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.api;

import net.minecraft.entity.player.EntityPlayer;

public interface IHasGui {
    Object getServerGui(EntityPlayer player);

    Object getClientGui(EntityPlayer player);

    boolean canOpenGui(EntityPlayer player);
}