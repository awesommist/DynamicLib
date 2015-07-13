/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics;

import java.util.Map;
import java.util.function.BiConsumer;

import net.minecraft.world.GameRules;
import net.minecraftforge.event.world.WorldEvent;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class GameRuleManager {

    public static final GameRuleManager instance = new GameRuleManager();

    private static Map<String, String> modRules = Maps.newHashMap();

    public static void addRule(String name, String defaultValue) {
        modRules.put(name, defaultValue);
    }

    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        modRules.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String name, String defaultValue) {
                final GameRules rules = event.world.getGameRules();
                if (!rules.hasRule(name))
                    rules.addGameRule(name, defaultValue);
            }
        });
    }
}