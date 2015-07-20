/**
 * This class was created by <awesommist>. It's distributed as
 * part of the Extruder Mod. Get the Source Code in github:
 * https://github.com/awesommist/Extruder
 */
package dynamics.sync;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import net.minecraft.nbt.NBTTagCompound;
import dynamics.Log;

public class DummySyncableObject extends SyncableObjectBase {

    public static final DummySyncableObject INSTANCE = new DummySyncableObject();

    @Override
    public void readFromStream(DataInputStream stream) {
        Log.warn("Trying to read dummy syncable object");
    }

    @Override
    public void writeToStream(DataOutputStream stream) {
        Log.warn("Trying to write dummy syncable object");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt, String name) {
        Log.warn("Trying to write dummy syncable object");
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt, String name) {
        Log.warn("Trying to read dummy syncable object");
    }
}