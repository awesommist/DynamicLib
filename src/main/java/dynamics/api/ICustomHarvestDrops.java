package dynamics.api;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICustomHarvestDrops {
    boolean suppressNormalHarvestDrops();

    void addHarvestDrops(@Nullable EntityPlayer player, List<ItemStack> drops);
}