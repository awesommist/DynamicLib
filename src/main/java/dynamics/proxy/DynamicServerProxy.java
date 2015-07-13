/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.proxy;

import java.io.File;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import dynamics.gui.CommonGuiHandler;

import cpw.mods.fml.common.network.IGuiHandler;

public final class DynamicServerProxy implements IDynamicProxy {

    @Override
    public World getClientWorld() {
        return null;
    }

    @Override
    public World getServerWorld(int dimension) {
        return DimensionManager.getWorld(dimension);
    }

    @Override
    public EntityPlayer getThePlayer() {
        return null;
    }

    @Override
    public boolean isClientPlayer(Entity player) {
        return false;
    }

    @Override
    public long getTicks(World worldObj) {
        return worldObj.getTotalWorldTime();
    }

    @Override
    public File getMinecraftDir() {
        return MinecraftServer.getServer().getFile("");
    }

    @Override
    public String getLogFileName() {
        return "ForgeModLoader-server-0.log";
    }

    @Override
    public IGuiHandler wrapHandler(IGuiHandler modSpecificHandler) {
        return new CommonGuiHandler(modSpecificHandler);
    }

    @Override
    public void preInit() {}

    @Override
    public void init() {}

    @Override
    public void postInit() {}

    @Override
    public void setNowPlayingTitle(String nowPlaying) {}

    @Override
    public EntityPlayer getPlayerFromHandler(INetHandler handler) {
        if (handler instanceof NetHandlerPlayServer) return ((NetHandlerPlayServer) handler).playerEntity;

        return null;
    }
}