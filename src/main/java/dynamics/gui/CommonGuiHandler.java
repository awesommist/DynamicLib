package dynamics.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dynamics.api.IHasGui;
import dynamics.block.DynamicBlock;

import cpw.mods.fml.common.network.IGuiHandler;

public class CommonGuiHandler implements IGuiHandler {

    protected final IGuiHandler wrappedHandler;

    public CommonGuiHandler() {
        this.wrappedHandler = null;
    }

    public CommonGuiHandler(IGuiHandler wrappedHandler) {
        this.wrappedHandler = wrappedHandler;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID != DynamicBlock.DYNAMICS_TE_GUI) {
            return wrappedHandler != null ? wrappedHandler.getServerGuiElement(ID, player, world, x, y, z) : null;
        } else {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof IHasGui)
                return ((IHasGui) tile).getServerGui(player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}