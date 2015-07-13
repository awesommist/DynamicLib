/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dynamics.client.render.BlockRenderInfo;
import dynamics.client.render.DefaultBlockRenderer;
import dynamics.client.render.IBlockRenderer;
import dynamics.config.game.IRegisterableBlock;
import dynamics.tileentity.DynamicTileEntity;

import com.google.common.base.Preconditions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class DynamicBlock extends Block implements IRegisterableBlock {

    public static final int DYNAMICS_TE_GUI = -1;

    public enum BlockPlacementMode {
        ENTITY_ANGLE,
        SURFACE
    }

    public enum RenderMode {
        TESR_ONLY,
        BLOCK_ONLY,
        BOTH
    }

    private String blockName;
    private String modid;

    private Class<? extends TileEntity> teClass = null;

    protected BlockPlacementMode blockPlacementMode = BlockPlacementMode.ENTITY_ANGLE;
    protected RenderMode renderMode = RenderMode.BLOCK_ONLY;

    @SideOnly(Side.CLIENT)
    private BlockRenderInfo renderInfo;

    protected DynamicBlock(Material material) {
        super (material);

        this.isBlockContainer = false;
    }

    protected abstract Object getModInstance();

    @Override
    public void setupBlock(String modid, String blockName, Class<? extends TileEntity> tileEntity, Class<? extends ItemBlock> itemClass) {
        this.blockName = blockName;
        this.modid = modid;

        if (tileEntity != null) {
            this.teClass = tileEntity;
            this.isBlockContainer = true;
        }
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        final TileEntity te = createTileEntity();

        if (te != null) {
            te.blockType = this;
            if (te instanceof DynamicTileEntity) {
                ((DynamicTileEntity) te).setup();
            }
        }

        return te;
    }

    public TileEntity createTileEntityForRender() {
        final TileEntity te = createTileEntity();
        Preconditions.checkNotNull(te, "Trying to get rendering TE for '%s', but it's not configured", this);
        te.blockType = this;
        te.blockMetadata = 0;
        return te;
    }

    protected TileEntity createTileEntity() {
        if (teClass == null) return null;
        try {
            return teClass.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to create TE with class " + teClass, e);
        }
    }

    public Class<? extends TileEntity> getTileClass() {
        return teClass;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderInfo getRenderInfo() {
        if (this.renderInfo != null)
            return this.renderInfo;

        try {
            final IBlockRenderer render = this.getRendererClass().newInstance();
            this.renderInfo = new BlockRenderInfo(render);

            return this.renderInfo;
        } catch (InstantiationException e) {
            throw new IllegalStateException("Failed to create an instance of an illegal class " + this.getRendererClass(), e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to create an instance of " + this.getRendererClass() + " because of permissions", e);
        }
    }

    @SideOnly(Side.CLIENT)
    public Class<? extends IBlockRenderer> getRendererClass() {
        return DefaultBlockRenderer.class;
    }

    @SideOnly(Side.CLIENT)
    public void setRenderStateByMeta(int metadata) {}

    @SideOnly(Side.CLIENT)
    public final boolean shouldRenderBlock() {
        return renderMode != RenderMode.TESR_ONLY;
    }

    @SideOnly(Side.CLIENT)
    public final boolean shouldRenderTesrInInventory() {
        return renderMode != RenderMode.BLOCK_ONLY;
    }
}