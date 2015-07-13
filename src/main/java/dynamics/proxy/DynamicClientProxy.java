/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.proxy;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import dynamics.gui.ClientGuiHandler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.IGuiHandler;

public final class DynamicClientProxy implements IDynamicProxy {

    @Override
    public World getClientWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public World getServerWorld(int dimension) {
        return DimensionManager.getWorld(dimension);
    }

    @Override
    public EntityPlayer getThePlayer() {
        return FMLClientHandler.instance().getClient().thePlayer;
    }

    @Override
    public boolean isClientPlayer(Entity player) {
        return player instanceof EntityPlayerSP;
    }

    @Override
    public long getTicks(World worldObj) {
        if (worldObj != null) { return worldObj.getTotalWorldTime(); }
        World cWorld = getClientWorld();
        if (cWorld != null) return cWorld.getTotalWorldTime();
        return 0;
    }

    @Override
    public File getMinecraftDir() {
        return Minecraft.getMinecraft().mcDataDir;
    }

    @Override
    public String getLogFileName() {
        return "ForgeModLoader-client-0.log";
    }

    @Override
    public IGuiHandler wrapHandler(IGuiHandler modSpecificHandler) {
        return new ClientGuiHandler(modSpecificHandler);
    }

    @Override
    public void preInit() {}

    @Override
    public void init() {}

    @Override
    public void postInit() {}

    @Override
    public void setNowPlayingTitle(String nowPlaying) {
        Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(nowPlaying);
    }

    @Override
    public EntityPlayer getPlayerFromHandler(INetHandler handler) {
        if (handler instanceof NetHandlerPlayServer) return ((NetHandlerPlayServer) handler).playerEntity;

        if (handler instanceof NetHandlerPlayClient) return getThePlayer();

        return null;
    }
}