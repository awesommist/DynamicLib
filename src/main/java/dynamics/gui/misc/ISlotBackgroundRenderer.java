package dynamics.gui.misc;

import net.minecraft.client.gui.Gui;
import net.minecraft.inventory.Slot;

public interface ISlotBackgroundRenderer {
    void render(Gui gui, Slot slot);
}