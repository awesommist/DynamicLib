/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.client.render;

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
        this.downIcon = down;
        this.upIcon = up;
        this.northIcon = north;
        this.southIcon = south;
        this.westIcon = west;
        this.eastIcon = east;
    }

    public IIcon getTexture(ForgeDirection dir) {
        switch (dir) {
            case DOWN:
                return this.downIcon;
            case UP:
                return this.upIcon;
            case NORTH:
                return this.northIcon;
            case SOUTH:
                return this.southIcon;
            case WEST:
                return this.westIcon;
            case EAST:
                return this.eastIcon;
        }

        return this.downIcon;
    }

    public boolean isValid() {
        return this.downIcon != null && this.upIcon != null && this.northIcon != null && this.southIcon != null && this.westIcon != null && this.eastIcon != null;
    }
}