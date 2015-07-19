/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicLib
 */
package dynamics.sync;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

public interface ISyncableObject {
    boolean isDirty();

    void markClean();

    void markDirty();

    void readFromStream(DataInputStream stream) throws IOException;

    void writeToStream(DataOutputStream stream) throws IOException;

    void writeToNBT(NBTTagCompound nbt, String name);

    void readFromNBT(NBTTagCompound nbt, String name);
}