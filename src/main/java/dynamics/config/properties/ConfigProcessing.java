package dynamics.config.properties;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

public class ConfigProcessing {

    public static class ModConfig {
        private final Configuration config;
        public final Class<?> configClass;
        public final String modid;

        private Table<String, String, ConfigPropertyMeta> properties = HashBasedTable.create();

        private ModConfig(String modid, Configuration config, Class<?> configClass) {
            this.modid = modid;
            this.config = config;
            this.configClass = configClass;
        }

        void tryProcessConfig(Field field) {
            ConfigPropertyMeta meta = ConfigPropertyMeta.createMetaForField(config, field);
            if (meta != null) {
                meta.updateValueFromConfig(false);
                properties.put(meta.category.toLowerCase(), meta.name.toLowerCase(), meta);
            }
        }

        public File getConfigFile() {
            return config.getConfigFile();
        }

        public void save() {
            if (config.hasChanged()) config.save();
        }

        public Collection<String> getCategories() {
            return Collections.unmodifiableCollection(properties.rowKeySet());
        }

        public Collection<String> getValues(String category) {
            return Collections.unmodifiableCollection(properties.row(category.toLowerCase()).keySet());
        }

        public ConfigPropertyMeta getValue(String category, String name) {
            return properties.get(category.toLowerCase(), name.toLowerCase());
        }
    }

    private static final Map<String, ModConfig> configs = Maps.newHashMap();

    public static Collection<String> getConfigsIds() {
        return Collections.unmodifiableCollection(configs.keySet());
    }

    public static ModConfig getModConfig(String modid) {
        return configs.get(modid.toLowerCase());
    }

    public static void processAnnotations(String modid, Configuration config, Class<?> klazz) {
        Preconditions.checkState(!configs.containsKey(modid), "Trying to configure mod '%s' twice", modid);
        ModConfig configMeta = new ModConfig(modid, config, klazz);
        configs.put(modid.toLowerCase(), configMeta);

        for (Field f : klazz.getFields())
            configMeta.tryProcessConfig(f);
    }
}