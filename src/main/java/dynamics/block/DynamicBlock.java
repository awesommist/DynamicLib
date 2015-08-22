package dynamics.block;

import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.IResource;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import dynamics.api.ICustomHarvestDrops;
import dynamics.config.game.IRegisterableBlock;
import dynamics.renderer.BlockRenderInfo;
import dynamics.renderer.DefaultBlockRenderer;
import dynamics.renderer.IBlockRenderer;
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

        isBlockContainer = false;
    }

    protected abstract Object getModInstance();

    @Override
    public void setupBlock(String modid, String blockName, Class<? extends TileEntity> tileEntity, Class<? extends ItemBlock> itemClass) {
        this.blockName = blockName;
        this.modid = modid;

        if (tileEntity != null) {
            teClass = tileEntity;
            isBlockContainer = true;
        }
    }

    public boolean shouldDropFromTeAfterBreak() {
        return true;
    }

    public boolean shouldOverrideHarvestWithTeLogic() {
        return teClass != null && ICustomHarvestDrops.class.isAssignableFrom(teClass);
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
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create TE with class " + teClass, ex);
        }
    }

    public Class<? extends TileEntity> getTileClass() {
        return teClass;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister registry) {
        final BlockRenderInfo info = getRenderInfo();
        final IIcon downIcon;
        final IIcon upIcon;
        final IIcon sideIcon;
        final IIcon northIcon;
        final IIcon southIcon;
        final IIcon westIcon;
        final IIcon eastIcon;

        blockIcon = downIcon = getIconWithSub(registry, getTextureName(), null);
        upIcon = getIconWithSub(registry, getTextureName() + "Top", downIcon);
        sideIcon = getIconWithSub(registry, getTextureName() + "Side", downIcon);
        northIcon = getIconWithSub(registry, getTextureName() + "North", sideIcon); // back
        southIcon = getIconWithSub(registry, getTextureName() + "South", sideIcon); // front
        westIcon = getIconWithSub(registry, getTextureName() + "South", sideIcon);
        eastIcon = getIconWithSub(registry, getTextureName() + "South", sideIcon);

        info.updateIcons(downIcon, upIcon, northIcon, southIcon, westIcon, eastIcon);
    }

    @SideOnly(Side.CLIENT)
    private IIcon getIconWithSub(IIconRegister registry, String name, IIcon substitute) {
        if (substitute != null) {
            try {
                ResourceLocation resLoc = new ResourceLocation(name);
                resLoc = new ResourceLocation(resLoc.getResourceDomain(), String.format("%s/%s%s", "textures/blocks", resLoc.getResourcePath(), ".png"));

                IResource res = Minecraft.getMinecraft().getResourceManager().getResource(resLoc);
                if (res != null) return registry.registerIcon(name);
            } catch (IOException e) {
                return substitute;
            }
        }

        return registry.registerIcon(name);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderInfo getRenderInfo() {
        if (renderInfo != null)
            return renderInfo;

        try {
            final IBlockRenderer render = this.getRendererClass().newInstance();
            renderInfo = new BlockRenderInfo(render);

            return renderInfo;
        } catch (InstantiationException e) {
            throw new IllegalStateException("Failed to create an instance of an illegal class " + getRendererClass(), e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to create an instance of " + getRendererClass() + " because of permissions", e);
        }
    }

    @SideOnly(Side.CLIENT)
    public Class<? extends IBlockRenderer> getRendererClass() {
        return DefaultBlockRenderer.class;
    }

    protected boolean suppressPickBlock() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public final boolean shouldRenderBlock() {
        return renderMode != RenderMode.TESR_ONLY;
    }

    @SideOnly(Side.CLIENT)
    public final boolean shouldRenderTesrInInventory() {
        return renderMode != RenderMode.BLOCK_ONLY;
    }

    protected BlockPlacementMode getPlacementMode() {
        return blockPlacementMode;
    }

    protected void setRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
    }

    protected void setPlacementMode(BlockPlacementMode mode) {
        this.blockPlacementMode = mode;
    }
}