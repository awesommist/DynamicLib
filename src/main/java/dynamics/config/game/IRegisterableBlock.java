/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.config.game;

import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

public interface IRegisterableBlock {
    void setupBlock(String modid, String blockName, Class<? extends TileEntity> tileEntity, Class<? extends ItemBlock> itemClass);
}