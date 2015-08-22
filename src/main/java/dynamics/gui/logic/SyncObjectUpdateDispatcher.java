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