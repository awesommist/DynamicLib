/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.client.texture;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MissingIcon implements IIcon {

    private final boolean isBlock;

    public MissingIcon(Object obj) {
        this.isBlock = obj instanceof Block;
    }

    public IIcon getMissing() {
        return ((TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(this.isBlock ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture )).getAtlasSprite("missingno");
    }

    @Override
    public int getIconWidth() {
        return this.getMissing().getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return this.getMissing().getIconHeight();
    }

    @Override
    public float getMinU() {
        return this.getMissing().getMinU();
    }

    @Override
    public float getMaxU() {
        return this.getMissing().getMaxU();
    }

    @Override
    public float getInterpolatedU(double value) {
        return this.getMissing().getInterpolatedU(value);
    }

    @Override
    public float getMinV() {
        return this.getMissing().getMinV();
    }

    @Override
    public float getMaxV() {
        return this.getMissing().getMaxV();
    }

    @Override
    public float getInterpolatedV(double value) {
        return this.getMissing().getInterpolatedV(value);
    }

    @Override
    public String getIconName() {
        return this.getMissing().getIconName();
    }
}