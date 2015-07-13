/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public interface IBlockRenderer<B extends Block> {
    void renderInventoryBlock(B block, int metadata, int modelID, RenderBlocks renderer);

    boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, B block, int modelId, RenderBlocks renderer);
}