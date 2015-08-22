package dynamics.gui;

import dynamics.container.ContainerBase;
import dynamics.gui.logic.IValueUpdateAction;
import dynamics.gui.logic.SyncObjectUpdateDispatcher;
import dynamics.sync.ISyncMapProvider;

public class SyncedGuiContainer<T extends ContainerBase<? extends ISyncMapProvider>> extends BaseGuiContainer<T> {

    private SyncObjectUpdateDispatcher dispatcher;

    public SyncedGuiContainer(T container, int width, int height, String name) {
        super (container, width, height, name);

        if (dispatcher != null) dispatcher.triggerAll();
    }

    protected SyncObjectUpdateDispatcher dispatcher() {
        if (dispatcher == null) {
            dispatcher = new SyncObjectUpdateDispatcher();
            getContainer().getOwner().getSyncMap().addUpdateListener(dispatcher);
        }

        return dispatcher;
    }

    public void addSyncUpdateListener(IValueUpdateAction action) {
        dispatcher().addAction(action);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        if (dispatcher != null) getContainer().getOwner().getSyncMap().removeUpdateListener(dispatcher);
    }
}