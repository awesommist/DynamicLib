package dynamics;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import dynamics.config.properties.ConfigProcessing;
import dynamics.proxy.IDynamicProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DynamicLib.MODID, name = DynamicLib.NAME, version = "$LIB-VERSION$")
public class DynamicLib {

    public static final String MODID = "dynamiclib";
    public static final String NAME = "DynamicLib";

    @Instance(MODID)
    public static DynamicLib instance;

    @SidedProxy(clientSide = "dynamics.proxy.DynamicClientProxy", serverSide = "dynamics.proxy.DynamicServerProxy")
    public static IDynamicProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(GameRuleManager.instance);

        final File configFile = event.getSuggestedConfigurationFile();
        Configuration config = new Configuration(configFile);
        ConfigProcessing.processAnnotations(MODID, config, LibConfig.class);
        if (config.hasChanged()) config.save();

        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}