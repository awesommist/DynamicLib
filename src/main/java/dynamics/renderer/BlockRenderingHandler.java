package dynamics.renderer;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import dynamics.block.DynamicBlock;

import com.google.common.collect.Maps;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class BlockRenderingHandler implements ISimpleBlockRenderingHandler {

    private final int renderId;
    private final boolean itemsIn3d;

    protected static final IBlockRenderer<Block> DEFAULT_RENDERER = new DefaultBlockRenderer();
    protected final Map<Block, IBlockRenderer<Block>> blockRenderers = Maps.newIdentityHashMap();

    public BlockRenderingHandler(int renderId, boolean itemsIn3d) {
        this.renderId = renderId;
        this.itemsIn3d = itemsIn3d;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        DynamicBlock dynamicBlock = null;
        if (block instanceof DynamicBlock) dynamicBlock = (DynamicBlock) block;
        if (dynamicBlock != null) dynamicBlock.getRenderInfo().renderer.renderInventoryBlock(block, metadata, modelId, renderer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        DynamicBlock dynamicBlock = null;
        if (block instanceof DynamicBlock) dynamicBlock = (DynamicBlock) block;
        return dynamicBlock != null && dynamicBlock.getRenderInfo().renderer.renderWorldBlock(world, x, y, z, block, modelId, renderer);
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return itemsIn3d;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }
}