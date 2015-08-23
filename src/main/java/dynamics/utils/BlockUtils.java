package dynamics.utils;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import dynamics.inventory.IInventoryProvider;
import dynamics.utils.coord.BlockCoord;

public class BlockUtils {

    public static EntityItem dropItemStackInWorld(World worldObj, double x, double y, double z, ItemStack stack) {
        float f = 0.7F;
        float f0 = worldObj.rand.nextFloat() * f + (1.0F - f) * 0.5F;
        float f1 = worldObj.rand.nextFloat() * f + (1.0F - f) * 0.5F;
        float f2 = worldObj.rand.nextFloat() * f + (1.0F - f) * 0.5F;
        EntityItem entityitem = new EntityItem(worldObj, x + f0, y + f1, z + f2, stack);
        entityitem.delayBeforeCanPickup = 10;
        if (stack.hasTagCompound())
            entityitem.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
        worldObj.spawnEntityInWorld(entityitem);
        return entityitem;
    }

    public static boolean getTileInventoryDrops(TileEntity tileEntity, List<ItemStack> drops) {
        if (tileEntity == null) return false;

        if (tileEntity instanceof IInventory) {
            drops.addAll(InventoryUtils.getInventoryContents((IInventory) tileEntity));
            return true;
        } else if (tileEntity instanceof IInventoryProvider) {
            drops.addAll(InventoryUtils.getInventoryContents(((IInventoryProvider) tileEntity).getInventory()));
            return true;
        }

        return false;
    }

    public static TileEntity getTileInDirection(TileEntity tile, ForgeDirection direction) {
        int targetX = tile.xCoord + direction.offsetX;
        int targetY = tile.yCoord + direction.offsetY;
        int targetZ = tile.zCoord + direction.offsetZ;
        return tile.getWorldObj().getTileEntity(targetX, targetY, targetZ);
    }

    public static TileEntity getTileInDirection(World world, BlockCoord coord, ForgeDirection direction) {
        int targetX = coord.x + direction.offsetX;
        int targetY = coord.y + direction.offsetY;
        int targetZ = coord.z + direction.offsetZ;
        return world.getTileEntity(targetX, targetY, targetZ);
    }
}