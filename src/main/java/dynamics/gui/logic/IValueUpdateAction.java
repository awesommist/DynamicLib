package dynamics.gui.logic;

public interface IValueUpdateAction {
    Iterable<?> getTriggers();

    void execute();
}