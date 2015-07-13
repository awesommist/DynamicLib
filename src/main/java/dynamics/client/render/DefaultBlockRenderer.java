/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.client.render;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import dynamics.block.DynamicBlock;
import dynamics.tileentity.DynamicTileEntity;
import dynamics.utils.render.RenderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DefaultBlockRenderer implements IBlockRenderer<Block> {

    private final Map<Block, TileEntity> inventoryTileEntities = Maps.newIdentityHashMap();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        DynamicBlock dynamicBlock = (block instanceof DynamicBlock) ? (DynamicBlock) block : null;
        final TileEntity te;
        if (dynamicBlock != null && dynamicBlock.shouldRenderTesrInInventory())
            te = getTileEntityForBlock(dynamicBlock);
        else
            te = null;

        if (te instanceof DynamicTileEntity) ((DynamicTileEntity) te).prepareForInventoryRender(block, metadata);

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        if (te != null) {
            GL11.glPushAttrib(GL11.GL_TEXTURE_BIT);
            GL11.glPushMatrix();
            GL11.glTranslated(-0.5, -0.5, -0.5);
            TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);
            GL11.glPopMatrix();
            GL11.glPopAttrib();
        }

        RenderUtils.renderInventoryBlock(renderer, block);
        RenderUtils.resetFacesOnRenderer(renderer);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        DynamicBlock dynamicBlock = (block instanceof DynamicBlock) ? (DynamicBlock) block : null;

        if (dynamicBlock == null || dynamicBlock.shouldRenderBlock()) {
            if (dynamicBlock != null) {
                int metadata = world.getBlockMetadata(x, y, z);
            }
            renderer.renderStandardBlock(block, x, y, z);
            RenderUtils.resetFacesOnRenderer(renderer);
        }

        return true;
    }

    public TileEntity getTileEntityForBlock(DynamicBlock block) {
        TileEntity te = inventoryTileEntities.get(block);
        if (te == null) {
            te = block.createTileEntityForRender();
            inventoryTileEntities.put(block, te);
        }
        return te;
    }
}