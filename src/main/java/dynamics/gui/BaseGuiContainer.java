/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.gui;

import net.minecraft.util.StatCollector;
import dynamics.container.ContainerBase;
import dynamics.gui.component.BaseComposite;
import dynamics.gui.component.GuiComponentPanel;

// The GUI code was basically ripped from OpenModsLib.
// This will probably be changing soon, as I analyze the code further.
// Thanks OpenMods! Check them out on github: https://github.com/OpenMods/
public abstract class BaseGuiContainer<T extends ContainerBase<?>> extends ComponentGui {

    protected final String name;

    public BaseGuiContainer(T container, int width, int height, String name) {
        super (container, width, height);
        this.name = name;
    }

    @Override
    protected BaseComposite createRoot() {
        return new GuiComponentPanel(0, 0, xSize, ySize, getContainer());
    }

    @SuppressWarnings("unchecked")
    public T getContainer() {
        return (T) inventorySlots;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        String machineName = StatCollector.translateToLocal(name);
        int x = this.xSize / 2 - (fontRendererObj.getStringWidth(machineName) / 2);
        fontRendererObj.drawString(machineName, x, 6, 4210752);
        String translatedName = StatCollector.translateToLocal("container.inventory");
        fontRendererObj.drawString(translatedName, 8, this.ySize - 96 + 2, 4210752);
    }

    public void sendButtonClick(int buttonId) {
        this.mc.playerController.sendEnchantPacket(getContainer().windowId, buttonId);
    }
}