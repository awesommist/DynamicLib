package dynamics.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import dynamics.Log;
import dynamics.block.DynamicBlock;
import dynamics.config.BlockInstances;
import dynamics.config.FieldProcessor;
import dynamics.config.FieldProcessor.FieldValueVisitor;

public class BlockRenderingValidator implements FieldValueVisitor<Block> {

    public void verifyBlocks(Class<? extends BlockInstances> container) {
        FieldProcessor.processBlocks(container, this);
    }

    @Override
    public void visit(Block value) {
        if (value instanceof DynamicBlock) {
            DynamicBlock block = (DynamicBlock) value;
            if (block.shouldRenderTesrInInventory()) {
                final Class<? extends TileEntity> teClass = block.getTileClass();
                if (teClass == null)
                    Log.severe("Block %s will render TESR in inventory, but has no TE class set", block);
                else if (TileEntityRendererDispatcher.instance.getSpecialRendererByClass(teClass) == null)
                    Log.severe("Block %s will render TESR in inventory, but TE %s has no custom renderer", block, teClass);
            }
        }
    }
}