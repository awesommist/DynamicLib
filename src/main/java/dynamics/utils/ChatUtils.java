/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public final class ChatUtils {

    public static void clearChat() {
        if (Platform.isClient())
            Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }

    public static void writeString(String message) {
        if (Platform.isClient())
            if (Minecraft.getMinecraft().thePlayer != null)
                sendMessageToPlayer(Minecraft.getMinecraft().thePlayer, message);
    }

    public static void sendMessageToPlayer(EntityPlayer player, String message) {
        String[] parts = message.split("\\n");
        for (String part : parts) {
            ChatComponentTranslation chat = new ChatComponentTranslation(part);
            player.addChatMessage(chat);
        }
    }
}