/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicLib
 */
package dynamics.network.targets;

import java.util.Collection;

import net.minecraft.entity.player.EntityPlayerMP;
import dynamics.Log;
import dynamics.network.IPacketTargetSelector;
import dynamics.utils.NetUtils;

import cpw.mods.fml.common.network.handshake.NetworkDispatcher;
import cpw.mods.fml.relauncher.Side;

public class SelectMultiplePlayers implements IPacketTargetSelector {

    public static final IPacketTargetSelector INSTANCE = new SelectMultiplePlayers();

    @Override
    public boolean isAllowedOnSide(Side side) {
        return side == Side.SERVER;
    }

    @Override
    public void listDispatchers(Object arg, Collection<NetworkDispatcher> result) {
        try {
            Collection<EntityPlayerMP> players = (Collection<EntityPlayerMP>) arg;
            for (EntityPlayerMP player : players) {
                NetworkDispatcher dispatcher = NetUtils.getPlayerDispatcher(player);
                if (dispatcher != null) result.add(dispatcher);
                else Log.info("Trying to send message to disconnected player %s", player);
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Argument must be collection of EntityPlayerMP");
        }
    }
}