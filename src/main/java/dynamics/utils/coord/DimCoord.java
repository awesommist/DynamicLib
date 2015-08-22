package dynamics.utils.coord;

import net.minecraft.tileentity.TileEntity;
import dynamics.api.ICopyable;

/**
 * 4-tuple of integers used to represent coordinates in a three-dimensional space and a given dimension.
 * More stuff will be added as needed. Mostly used as block coords.
 */
public class DimCoord implements ICopyable<DimCoord> {

    /**
     * Offset in the 'x' axis
     */
    public final int x;

    /**
     * Offset in the 'y' axis
     */
    public final int y;

    /**
     * Offset in the 'z' axis
     */
    public final int z;

    /**
     * The dimension id
     */
    public final int d;

    /**
     * Creates a DimCoord on the origin (0, 0, 0, 0)
     */
    public DimCoord() {
        this (0, 0, 0, 0);
    }

    /**
     * Creates a DimCoord given the specified offsets for each axis (x, y, z).
     * Dimension id defaults to 0
     *
     * @param x
     *      Offset in the 'x' axis
     * @param y
     *      Offset in the 'y' axis
     * @param z
     *      Offset in the 'z' axis
     */
    public DimCoord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.d = 0;
    }

    /**
     * Creates a DimCoord given the specified offsets for each axis (x, y, z, d)
     *
     * @param x
     *      Offset in the 'x' axis
     * @param y
     *      Offset in the 'y' axis
     * @param z
     *      Offset in the 'z' axis
     * @param d
     *      The dimension id
     */
    public DimCoord(int x, int y, int z, int d) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.d = d;
    }

    /**
     * Creates a Coord3D from a given {@link TileEntity} using its coordinates (x, y, z, d)
     *
     * @param te
     *      Tile Entity to create DimCoord from
     */
    public DimCoord(TileEntity te) {
        this.x = te.xCoord;
        this.y = te.yCoord;
        this.z = te.zCoord;
        this.d = te.getWorldObj().provider.dimensionId;
    }

    @Override
    public DimCoord copy() { // TODO: javadoc this
        return new DimCoord(x, y, z, d);
    }

    /**
     * Returns true if the given object and this coordinate are equal
     * @param obj
     *      The object to compare this coordinate to
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof DimCoord &&
                ((DimCoord) obj).x == x &&
                ((DimCoord) obj).y == y &&
                ((DimCoord) obj).z == z &&
                ((DimCoord) obj).d == d;
    }

    /**
     * Returns a {@link String} in the format (x, y, z, d)
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + d + ")";
    }
}