/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.gui.misc;

import net.minecraft.client.gui.Gui;
import net.minecraft.inventory.Slot;

public interface ISlotBackgroundRenderer {
    void render(Gui gui, Slot slot);
}