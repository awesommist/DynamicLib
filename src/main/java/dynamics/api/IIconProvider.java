package dynamics.api;

import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public interface IIconProvider {
    IIcon getIcon(ForgeDirection rotatedDir);
}