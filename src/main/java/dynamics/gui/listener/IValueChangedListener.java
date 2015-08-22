package dynamics.gui.listener;

public interface IValueChangedListener<T> extends IListenerBase {
    void valueChanged(T value);
}