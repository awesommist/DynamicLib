/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicLib
 */
package dynamics.utils.bitmap;

public interface IWriteableBitMap<T> {
    void mark(T value);

    void clear(T value);

    void set(T key, boolean value);

    void toggle(T value);

    void clearAll();
}