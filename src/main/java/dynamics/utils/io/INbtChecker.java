/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils.io;

import net.minecraft.nbt.NBTBase;

public interface INbtChecker {
    boolean checkTagType(NBTBase tag);
}