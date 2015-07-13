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
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

public interface IDynamicProxy {

    World getClientWorld();

    World getServerWorld(int dimension);

    EntityPlayer getThePlayer();

    boolean isClientPlayer(Entity player);

    long getTicks(World worldObj);

    File getMinecraftDir();

    String getLogFileName();

    IGuiHandler wrapHandler(IGuiHandler modSpecificHandler);

    void preInit();

    void init();

    void postInit();

    void setNowPlayingTitle(String nowPlaying);

    EntityPlayer getPlayerFromHandler(INetHandler handler);
}