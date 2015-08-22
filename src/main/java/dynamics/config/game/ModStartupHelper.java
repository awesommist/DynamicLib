package dynamics.config.game;

import java.io.File;
import java.util.Set;
import java.util.function.Consumer;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import dynamics.config.BlockInstances;
import dynamics.config.ItemInstances;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.event.FMLMissingMappingsEvent;

public class ModStartupHelper {

    private final Set<Class<? extends BlockInstances>> blockHolders = Sets.newHashSet();

    private final Set<Class<? extends ItemInstances>> itemHolders = Sets.newHashSet();

    private final GameConfigProvider gameConfig;

    public ModStartupHelper(String modid) {
        this.gameConfig = new GameConfigProvider(modid);
    }

    public void registerBlocksHolder(Class<? extends BlockInstances> holder) {
        blockHolders.add(holder);
    }

    public void registerItemsHolder(Class<? extends ItemInstances> holder) {
        itemHolders.add(holder);
    }

    public void preInit(File configFile) {
        preInit(new Configuration(configFile));
    }

    public void preInit(Configuration config) {
        final ConfigurableFeatureManager features = new ConfigurableFeatureManager();

        blockHolders.forEach(new Consumer<Class<? extends BlockInstances>>() {
            @Override
            public void accept(Class<? extends BlockInstances> blockHolder) {
                features.collectFromBlocks(blockHolder);
            }
        });

        itemHolders.forEach(new Consumer<Class<? extends ItemInstances>>() {
            @Override
            public void accept(Class<? extends ItemInstances> itemHolder) {
                features.collectFromItems(itemHolder);
            }
        });

        registerCustomFeatures(features);

        populateConfig(config);
        features.loadFromConfiguration(config);

        if (config.hasChanged()) config.save();

        gameConfig.setFeatures(features);

        setupBlockFactory(gameConfig.getBlockFactory());

        setupItemFactory(gameConfig.getItemFactory());

        blockHolders.forEach(new Consumer<Class<? extends BlockInstances>>() {
            @Override
            public void accept(Class<? extends BlockInstances> blockHolder) {
                gameConfig.registerBlocks(blockHolder);
            }
        });

        itemHolders.forEach(new Consumer<Class<? extends ItemInstances>>() {
            @Override
            public void accept(Class<? extends ItemInstances> itemHolder) {
                gameConfig.registerItems(itemHolder);
            }
        });

        setupProvider(gameConfig);
    }

    public void handleRenames(FMLMissingMappingsEvent event) {
        gameConfig.handleRemaps(event.get());
    }

    protected void setupBlockFactory(FactoryRegistry<Block> blockFactory) {}

    protected void setupItemFactory(FactoryRegistry<Item> itemFactory) {}

    protected void populateConfig(Configuration config) {}

    protected void registerCustomFeatures(ConfigurableFeatureManager features) {}

    protected void setupProvider(GameConfigProvider gameConfig) {}
}