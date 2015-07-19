/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicLib
 */
package dynamics.gui.logic;

public interface IValueUpdateAction {
    Iterable<?> getTriggers();

    void execute();
}