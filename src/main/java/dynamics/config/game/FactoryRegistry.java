/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.config.game;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

public class FactoryRegistry<T> {

    public interface Factory<T> {
        T construct();
    }

    private final Map<String, Factory<T>> customFactories = Maps.newHashMap();

    public void registerFactory(String feature, Factory<T> factory) {
        customFactories.put(feature, factory);
    }

    public <C extends T> C construct(String feature, Class<? extends C> cls) {
        Factory<T> customFactory = customFactories.get(feature);
        if (customFactory != null) {
            C result = (C) customFactory.construct();
            Preconditions.checkArgument(cls.isInstance(result),
                    "Invalid class for feature entry '%s', got '%s', expected '%s'",
                    feature, result != null ? result.getClass().toString() : "null", cls);
            return result;
        }

        try {
            return cls.newInstance();
        } catch (ReflectiveOperationException e) {
            throw Throwables.propagate(e);
        }
    }
}