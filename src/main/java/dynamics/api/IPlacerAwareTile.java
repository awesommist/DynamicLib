package dynamics.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IPlacerAwareTile {
    void onBlockPlacedBy(EntityLivingBase placer, ItemStack stack);
}