package dynamics.sync;

import java.util.Collection;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import dynamics.network.ExtendedOutboundHandler;
import dynamics.network.targets.SelectMultiplePlayers;
import dynamics.utils.NetUtils;
import io.netty.buffer.ByteBuf;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;

public class SyncChannelHolder {

    public static final String CHANNEL_NAME = "DynamicLib";

    public static final SyncChannelHolder INSTANCE = new SyncChannelHolder();

    private final Map<Side, FMLEmbeddedChannel> channels;

    private SyncChannelHolder() {
        this.channels = NetworkRegistry.INSTANCE.newChannel(CHANNEL_NAME, new InboundSyncHandler());
        ExtendedOutboundHandler.install(this.channels);
    }

    public static Packet createPacket(ByteBuf payload) {
        return new FMLProxyPacket(payload, CHANNEL_NAME);
    }

    public void sendPayloadToPlayers(ByteBuf payload, Collection<EntityPlayerMP> players) {
        FMLProxyPacket packet = new FMLProxyPacket(payload, CHANNEL_NAME);
        FMLEmbeddedChannel channel = channels.get(Side.SERVER);
        ExtendedOutboundHandler.selectTargets(channel, SelectMultiplePlayers.INSTANCE, players);
        channel.writeAndFlush(packet).addListener(NetUtils.LOGGING_LISTENER);
    }

    public static void ensureLoaded() {}
}