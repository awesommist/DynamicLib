package dynamics.utils.io;

import net.minecraft.nbt.NBTTagCompound;

public interface INbtWriter<T> {
    void writeToNBT(T o, NBTTagCompound tag, String name);
}