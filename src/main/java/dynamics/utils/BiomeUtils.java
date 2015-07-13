/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils;

import net.minecraft.world.biome.BiomeGenBase;

public final class BiomeUtils {

    public static float getBiomeTemperature(BiomeGenBase biome) {
        // Special cases
        if (biome == BiomeGenBase.sky)
            return 173.15F; // -100 °C
        else if (biome == BiomeGenBase.hell)
            return 373.15F; // 100 °C

        switch (biome.getTempCategory()) {
            case COLD:
                return 263.15F; // -10 °C
            case OCEAN:
                return 298.15F; // 25 °C
            case MEDIUM:
                return 298.15F; // 25 °C
            case WARM:
                return 313.15F; // 40 °C
            default:
                throw new RuntimeException("wtf");
        }
    }
}