/**
 * This class was created by <awesommist>. It's distributed as
 * part of the Extruder Mod. Get the Source Code in github:
 * https://github.com/awesommist/Extruder
 */
package dynamics.sync;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import com.google.common.base.Objects;

public class SyncableString extends SyncableObjectBase implements ISyncableValueProvider<String>{

    private String value;

    public SyncableString() {
        this("");
    }

    public SyncableString(String val) {
        this.value = val;
    }

    public void setValue(String val) {
        if (!Objects.equal(val, value)) {
            value = val;
            markDirty();
        }
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void readFromStream(DataInputStream stream) throws IOException {
        value = stream.readUTF();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        stream.writeUTF(value);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt, String name) {
        nbt.setString(name, value);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt, String name) {
        value = nbt.getString(name);
    }

    public void clear() {
        setValue("");
    }
}