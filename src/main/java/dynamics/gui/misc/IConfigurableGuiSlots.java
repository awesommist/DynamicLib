package dynamics.gui.misc;

import java.util.Set;

import net.minecraftforge.common.util.ForgeDirection;
import dynamics.api.IValueProvider;
import dynamics.api.IValueReceiver;
import dynamics.utils.bitmap.IWriteableBitMap;

public interface IConfigurableGuiSlots<T extends Enum<T>> {
    IValueProvider<Set<ForgeDirection>> createAllowedDirectionsProvider(T slot);

    IWriteableBitMap<ForgeDirection> createAllowedDirectionsReceiver(T slot);

    IValueProvider<Boolean> createAutoFlagProvider(T slot);

    IValueReceiver<Boolean> createAutoSlotReceiver(T slot);
}