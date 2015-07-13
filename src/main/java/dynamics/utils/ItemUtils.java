/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

import cpw.mods.fml.common.registry.GameData;

public final class ItemUtils {

    public static Item getByNameOrId(String id) {
        final Item item = GameData.getItemRegistry().getObject(id);
        if (item != null) return item;

        try {
            final int numericId = Integer.parseInt(id);
            return GameData.getItemRegistry().getObjectById(numericId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static NBTTagCompound writeStack(ItemStack stack) {
        NBTTagCompound result = new NBTTagCompound();
        stack.writeToNBT(result);

        final Item item = stack.getItem();
        if (item != null) {
            final String id = GameData.getItemRegistry().getNameForObject(item);
            if (id != null) {
                result.setString("id", id);
            }
        }
        return result;
    }

    public static ItemStack readStack(NBTTagCompound nbt) {
        final Item item;
        if (nbt.hasKey("id", Constants.NBT.TAG_STRING))
            item = getByNameOrId(nbt.getString("id"));
        else
            item = Item.getItemById(nbt.getShort("id"));

        if (item == null) return null;

        final int stackSize = nbt.getByte("Count");
        final int itemDamage = nbt.getShort("Damage");

        final ItemStack result = new ItemStack(item, stackSize, itemDamage);

        if (nbt.hasKey("tag", Constants.NBT.TAG_COMPOUND))
            result.stackTagCompound = nbt.getCompoundTag("tag");
        return result;
    }
}