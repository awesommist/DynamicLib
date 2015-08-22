package dynamics.network;

import java.util.Collection;

import cpw.mods.fml.common.network.handshake.NetworkDispatcher;
import cpw.mods.fml.relauncher.Side;

public interface IPacketTargetSelector {
    boolean isAllowedOnSide(Side side);

    void listDispatchers(Object arg, Collection<NetworkDispatcher> result);
}