package dynamics.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public interface IBlockRenderer<B extends Block> {
    void renderInventoryBlock(B block, int metadata, int modelID, RenderBlocks renderer);

    boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, B block, int modelId, RenderBlocks renderer);
}