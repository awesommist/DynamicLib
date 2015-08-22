package dynamics.utils.render;

import java.util.EnumSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.common.util.ForgeDirection;
import dynamics.utils.colour.RGBA;
import org.lwjgl.opengl.GL11;

/**
 * Class with a bunch of helper methods to assist in rendering
 */
public final class RenderUtils {

    /**
     * Renders a cube in a given area using a given tessellator instance
     *
     * @param tessellator The tessellator instance that will render the cube
     * @param x1          Origin of the 'x' axis
     * @param y1          Origin of the 'y' axis
     * @param z1          Origin of the 'z' axis
     * @param x2          Offset in the 'x' axis
     * @param y2          Offset in the 'y' axis
     * @param z2          Offset in the 'z' axis
     */
    public static void renderCube(Tessellator tessellator, double x1, double y1, double z1, double x2, double y2, double z2) {
        tessellator.addVertex(x1, y1, z1);
        tessellator.addVertex(x1, y2, z1);
        tessellator.addVertex(x2, y2, z1);
        tessellator.addVertex(x2, y1, z1);

        tessellator.addVertex(x1, y1, z2);
        tessellator.addVertex(x2, y1, z2);
        tessellator.addVertex(x2, y2, z2);
        tessellator.addVertex(x1, y2, z2);

        tessellator.addVertex(x1, y1, z1);
        tessellator.addVertex(x1, y1, z2);
        tessellator.addVertex(x1, y2, z2);
        tessellator.addVertex(x1, y2, z1);

        tessellator.addVertex(x2, y1, z1);
        tessellator.addVertex(x2, y2, z1);
        tessellator.addVertex(x2, y2, z2);
        tessellator.addVertex(x2, y1, z2);

        tessellator.addVertex(x1, y1, z1);
        tessellator.addVertex(x2, y1, z1);
        tessellator.addVertex(x2, y1, z2);
        tessellator.addVertex(x1, y1, z2);

        tessellator.addVertex(x1, y2, z1);
        tessellator.addVertex(x1, y2, z2);
        tessellator.addVertex(x2, y2, z2);
        tessellator.addVertex(x2, y2, z1);
    }

    public static void disableLightmap() {
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public static void enableLightmap() {
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public static void resetFacesOnRenderer(RenderBlocks renderer) { // TODO: javadoc this!
        renderer.uvRotateBottom = 0;
        renderer.uvRotateTop = 0;
        renderer.uvRotateNorth = 0;
        renderer.uvRotateSouth = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateEast = 0;
        renderer.flipTexture = false;
    }

    public static void renderInventoryBlock(RenderBlocks renderer, Block block) {
        renderInventoryBlock(renderer, block, null);
    }

    public static void renderInventoryBlock(RenderBlocks renderer, Block block, RGBA rgba) {
        renderInventoryBlock(renderer, block, rgba, EnumSet.allOf(ForgeDirection.class));
    }

    public static void renderInventoryBlock(RenderBlocks renderer, Block block, RGBA rgba, Set<ForgeDirection> enabledSides) {
        boolean isTransparent = rgba != null && block.getRenderBlockPass() == 1;
        Tessellator tessellator = Tessellator.instance;
        block.setBlockBoundsForItemRender();
        renderer.setRenderBoundsFromBlock(block);

        GL11.glPushMatrix();
        if (isTransparent) {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4b(rgba.r, rgba.g, rgba.b, rgba.a);
        }
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        if (enabledSides.contains(ForgeDirection.DOWN)) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 0));
            tessellator.draw();
        }
        if (enabledSides.contains(ForgeDirection.UP)) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 1));
            tessellator.draw();
        }
        if (enabledSides.contains(ForgeDirection.SOUTH)) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 2));
            tessellator.draw();
        }
        if (enabledSides.contains(ForgeDirection.NORTH)) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 3));
            tessellator.draw();
        }
        if (enabledSides.contains(ForgeDirection.WEST)) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 4));
            tessellator.draw();
        }
        if (enabledSides.contains(ForgeDirection.EAST)) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 5));
            tessellator.draw();
        }
        if (isTransparent) GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}