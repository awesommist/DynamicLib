/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicLib
 */
package dynamics.gui.logic;

import java.util.Set;

import dynamics.sync.ISyncListener;
import dynamics.sync.ISyncableObject;

public class SyncObjectUpdateDispatcher extends ValueUpdateDispatcher implements ISyncListener {

    @Override
    public void onSync(Set<ISyncableObject> changes) {
        trigger(changes);
    }
}