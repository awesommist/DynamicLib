/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.config.game;

public abstract class AbstractFeatureManager {

    public static final String CATEGORY_ITEMS = "items";

    public static final String CATEGORY_BLOCKS = "blocks";

    public abstract boolean isEnabled(String category, String name);

    public boolean isBlockEnabled(String name) {
        return isEnabled(CATEGORY_BLOCKS, name);
    }

    public boolean isItemEnabled(String name) {
        return isEnabled(CATEGORY_ITEMS, name);
    }
}