/**
 * This class was created by <awesommist>. It's distributed as
 * part of the Extruder Mod. Get the Source Code in github:
 * https://github.com/awesommist/Extruder
 */
package dynamics.utils;

import java.util.Map;

import com.google.common.collect.Maps;

public abstract class CachedFactory<K, V> {

    private final Map<K, V> cache = Maps.newHashMap();

    public V getOrCreate(K key) {
        V value = cache.get(key);

        if (value == null) {
            value = create(key);
            cache.put(key, value);
        }

        return value;
    }

    public V remove(K key) {
        return cache.remove(key);
    }

    protected abstract V create(K key);
}