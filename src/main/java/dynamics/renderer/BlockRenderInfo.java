package dynamics.renderer;

import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockRenderInfo {

    public final IBlockRenderer renderer;

    private IIcon downIcon = null;
    private IIcon upIcon = null;
    private IIcon northIcon = null;
    private IIcon southIcon = null;
    private IIcon westIcon = null;
    private IIcon eastIcon = null;

    public BlockRenderInfo(IBlockRenderer renderer) {
        this.renderer = renderer;
    }

    public void updateIcons(IIcon down, IIcon up, IIcon north, IIcon south, IIcon west, IIcon east) {
        downIcon = down;
        upIcon = up;
        northIcon = north;
        southIcon = south;
        westIcon = west;
        eastIcon = east;
    }

    public IIcon getTexture(ForgeDirection dir) {
        switch (dir) {
            case DOWN:
                return downIcon;
            case UP:
                return upIcon;
            case NORTH:
                return northIcon;
            case SOUTH:
                return southIcon;
            case WEST:
                return westIcon;
            case EAST:
                return eastIcon;
        }

        return downIcon;
    }

    public boolean isValid() {
        return downIcon != null && upIcon != null && northIcon != null && southIcon != null && westIcon != null && eastIcon != null;
    }
}