package dynamics.gui.listener;

import dynamics.gui.component.BaseComponent;

public interface IKeyTypedListener extends IListenerBase {
    void componentKeyTyped(BaseComponent component, char character, int keyCode);
}