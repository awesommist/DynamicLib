/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.gui.listener;

import dynamics.gui.component.BaseComponent;

public interface IMouseDownListener extends IListenerBase {
    void componentMouseDown(BaseComponent component, int x, int y, int button);
}