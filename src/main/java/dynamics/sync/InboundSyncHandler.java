package dynamics.sync;

import java.io.DataInputStream;

import net.minecraft.world.World;
import dynamics.DynamicLib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;

@ChannelHandler.Sharable
public class InboundSyncHandler extends SimpleChannelInboundHandler<FMLProxyPacket> {

    public static class SyncException extends RuntimeException {
        private static final long serialVersionUID = 497353325313039442L;

        public SyncException(Throwable cause, ISyncMapProvider provider) {
            super (String.format("Failed to sync %s (%s)", provider, provider.getClass()), cause);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception {
        World world = DynamicLib.proxy.getClientWorld();

        ByteBuf payload = msg.payload();
        DataInputStream input = new DataInputStream(new ByteBufInputStream(payload));

        ISyncMapProvider provider = SyncMap.findSyncMap(world, input);
        try {
            if (provider != null) provider.getSyncMap().readFromStream(input);
        } catch (Throwable e) {
            throw new SyncException(e, provider);
        }
    }
}