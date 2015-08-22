package dynamics.gui.listener;

import dynamics.gui.component.BaseComponent;

public interface IMouseUpListener extends IListenerBase {
    void componentMouseUp(BaseComponent component, int x, int y, int button);
}