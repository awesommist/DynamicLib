package dynamics.item;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class MetaItem extends Item {

    protected Map<Integer, IMetaItem> metaitems = Maps.newHashMap();

    public MetaItem() {
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    public void registerItem(int id, IMetaItem item) {
        IMetaItem prev = metaitems.put(id, item);
        Preconditions.checkState(prev == null, "Config error: replacing meta item %s with %s", prev, item);
    }

    public void initRecipes() {
        metaitems.forEach(new BiConsumer<Integer, IMetaItem>() {
            @Override
            public void accept(Integer id, IMetaItem item) {
                item.addRecipe();
            }
        });
    }

    @Override
    public void registerIcons(final IIconRegister iconRegister) {
        metaitems.forEach(new BiConsumer<Integer, IMetaItem>() {
            @Override
            public void accept(Integer id, IMetaItem item) {
                item.registerIcons(iconRegister);
            }
        });
    }

    @Override
    public IIcon getIconFromDamage(int i) {
        IMetaItem meta = getMeta(i);
        if (meta != null) return meta.getIcon();
        return null;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        IMetaItem meta = getMeta(stack);
        if (meta != null) return "item." + meta.getUnlocalizedName(stack);
        return "";
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        IMetaItem meta = getMeta(stack);
        return meta == null || meta.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        IMetaItem meta = getMeta(stack);
        if (meta != null) return meta.onItemRightClick(stack, world, player);
        return stack;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
        IMetaItem meta = getMeta(stack);
        return meta == null || meta.hitEntity(stack, target, player);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        IMetaItem meta = getMeta(stack);
        return meta != null && meta.hasEffect(pass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item item, CreativeTabs tab, final List subItems) {
        metaitems.forEach(new BiConsumer<Integer, IMetaItem>() {
            @Override
            public void accept(Integer id, IMetaItem meta) {
                meta.addToCreativeList(item, id, subItems);
            }
        });
    }

    public IMetaItem getMeta(int id) {
        return metaitems.get(id);
    }

    public IMetaItem getMeta(ItemStack itemStack) {
        return getMeta(itemStack.getItemDamage());
    }

    public ItemStack newItemStack(int id, int number) {
        return new ItemStack(this, number, id);
    }

    public ItemStack newItemStack(int id) {
        return newItemStack(id, 1);
    }

    public ItemStack newItemStack(final IMetaItem item, final int size) {
        metaitems.forEach(new BiConsumer<Integer, IMetaItem>() {
            @Override
            public void accept(Integer id, IMetaItem meta) {
                if (meta.equals(item)) newItemStack(id, size);
            }
        });
        return null;
    }
}