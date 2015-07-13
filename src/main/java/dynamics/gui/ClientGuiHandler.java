/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.gui;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dynamics.api.IHasGui;
import dynamics.block.DynamicBlock;

import cpw.mods.fml.common.network.IGuiHandler;

public class ClientGuiHandler extends CommonGuiHandler {

    public ClientGuiHandler() {}

    public ClientGuiHandler(IGuiHandler wrappedHandler) {
        super (wrappedHandler);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world instanceof WorldClient) {
            if (ID != DynamicBlock.DYNAMICS_TE_GUI) {
                return wrappedHandler != null ? wrappedHandler.getClientGuiElement(ID, player, world, x, y, z) : null;
            } else {
                TileEntity tile = world.getTileEntity(x, y, z);
                if (tile instanceof IHasGui)
                    return ((IHasGui) tile).getClientGui(player);
            }
        }
        return null;
    }
}