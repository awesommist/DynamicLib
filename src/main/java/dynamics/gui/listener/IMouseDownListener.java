package dynamics.gui.listener;

import dynamics.gui.component.BaseComponent;

public interface IMouseDownListener extends IListenerBase {
    void componentMouseDown(BaseComponent component, int x, int y, int button);
}