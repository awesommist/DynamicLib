package dynamics.gui.listener;

import dynamics.gui.component.BaseComponent;

public interface IMouseDragListener extends IListenerBase {
    void componentMouseDrag(BaseComponent component, int x, int y, int button, long time);
}