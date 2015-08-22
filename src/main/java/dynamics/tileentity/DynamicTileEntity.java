package dynamics.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import dynamics.Log;
import dynamics.utils.coord.BlockCoord;
import dynamics.utils.coord.DimCoord;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class DynamicTileEntity extends TileEntity {

    private boolean initialized = false;
    private boolean isActive = false;

    private boolean isUsedForClientInventoryRendering = false;

    public void setup() {}

    protected void initialize() {}

    @Override
    public void updateEntity() {
        isActive = true;
        if (!initialized) {
            initialize();
            initialized = true;
        }
    }

    @SideOnly(Side.CLIENT)
    public void prepareForInventoryRender(Block block, int metadata) {
        if (this.worldObj != null)
            Log.severe("SEVERE PROGRAMMER ERROR! Inventory Render on World TileEntity. Expect hell!");
        isUsedForClientInventoryRendering = true;
        this.blockType = block;
        this.blockMetadata = metadata;
    }

    public boolean isLoaded() {
        return initialized;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isRenderedInInventory() {
        return isUsedForClientInventoryRendering;
    }

    public BlockCoord getBlockCoord() {
        return new BlockCoord(xCoord, yCoord, zCoord);
    }

    public DimCoord getDimCoord() {
        return new DimCoord(xCoord, yCoord, zCoord, worldObj.provider.dimensionId);
    }

    public boolean isAddedToWorld() {
        return worldObj != null;
    }

    public void markUpdated() {
        worldObj.markTileEntityChunkModified(xCoord, yCoord, zCoord, this);
    }

    public boolean isValid(EntityPlayer player) {
        return (worldObj.getTileEntity(xCoord, yCoord, zCoord) == this) && (player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64.0D);
    }
}