package dynamics.utils;

import cpw.mods.fml.common.FMLCommonHandler;

public final class Platform {

    public static boolean isClient() {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static boolean isServer() {
        return FMLCommonHandler.instance().getEffectiveSide().isServer();
    }
}