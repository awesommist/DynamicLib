/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dynamics.tileentity.DynamicTileEntity;

import cpw.mods.fml.relauncher.ReflectionHelper;

// will probably delete
public abstract class DynamicTileBlock extends DynamicBlock implements ITileEntityProvider {

    private Class<? extends TileEntity> tileEntityClass;

    public DynamicTileBlock(Material material) {
        super (material);
    }

    @Override
    public final TileEntity createNewTileEntity(World world, int metadata) {
        if (this.hasBlockTileEntity()) {
            try {
                return this.tileEntityClass.newInstance();
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void setTileEntity(Class<? extends TileEntity> tileEntityClass) {
        this.tileEntityClass = tileEntityClass;
        this.setTileProvider(this.hasBlockTileEntity());
    }

    public <T extends DynamicTileEntity> T getTileEntity(World world, int x, int y, int z) {
        if (!this.hasBlockTileEntity())
            return null;

        final TileEntity te = world.getTileEntity(x, y, z);
        if (this.tileEntityClass.isInstance(te))
            return (T) te;

        return null;
    }

    public void setTileProvider(boolean isTileProvider) {
        ReflectionHelper.setPrivateValue(Block.class, this, isTileProvider, "isTileProvider");
    }

    public boolean hasBlockTileEntity() {
        return this.tileEntityClass != null;
    }

    public Class<? extends TileEntity> getTileEntityClass() {
        return this.tileEntityClass;
    }
}
