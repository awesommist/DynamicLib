/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils.io;

import net.minecraft.nbt.NBTTagCompound;

public interface INbtReader<T> {
    T readFromNBT(NBTTagCompound tag, String name);
}