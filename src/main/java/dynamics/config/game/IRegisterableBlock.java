package dynamics.config.game;

import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

public interface IRegisterableBlock {
    void setupBlock(String modid, String blockName, Class<? extends TileEntity> tileEntity, Class<? extends ItemBlock> itemClass);
}