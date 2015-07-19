/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicLib
 */
package dynamics.gui.logic;

import dynamics.api.IValueProvider;
import dynamics.api.IValueReceiver;

import com.google.common.collect.ImmutableList;

public class ValueCopyAction<T> implements IValueUpdateAction {

    private final Object trigger;
    private final IValueProvider<T> provider;
    private final IValueReceiver<T> receiver;

    public ValueCopyAction(Object trigger, IValueProvider<T> provider, IValueReceiver<T> receiver) {
        this.trigger = trigger;
        this.provider = provider;
        this.receiver = receiver;
    }

    @Override
    public Iterable<?> getTriggers() {
        return ImmutableList.of(trigger);
    }

    @Override
    public void execute() {
        T value = provider.getValue();
        receiver.setValue(value);
    }

    public static <T> ValueCopyAction<T> create(IValueProvider<T> provider, IValueReceiver<T> receiver) {
        return new ValueCopyAction<T>(provider, provider, receiver);
    }

    public static <T> ValueCopyAction<T> create(Object trigger, IValueProvider<T> provider, IValueReceiver<T> receiver) {
        return new ValueCopyAction<T>(trigger, provider, receiver);
    }
}