/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils.coord;

import net.minecraft.util.Vec3;
import dynamics.api.Copyable;

/**
 * 3-tuple of doubles used to represent coordinates in a three-dimensional space.
 * More stuff will be added as needed.
 */
public class Coord3D implements Copyable<Coord3D> {

    /**
     * Offset in the 'x' axis
     */
    public double x;

    /**
     * Offset in the 'y' axis
     */
    public double y;

    /**
     * Offset in the 'z' axis
     */
    public double z;

    /**
     * Creates a Coord3D on the origin (0, 0, 0)
     */
    public Coord3D() {
        this (0, 0, 0);
    }

    /**
     * Creates a Coord3D given the specified offsets for each axis (x, y, z)
     *
     * @param x
     *      Offset in the 'x' axis
     * @param y
     *      Offset in the 'x' axis
     * @param z
     *      Offset in the 'x' axis
     */
    public Coord3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a Coord3D from a given {@link Vec3} using its coordinates (x, y, z)
     *
     * @param vec
     *      Vector to create Coord3D from
     */
    public Coord3D(Vec3 vec) {
        this.x = vec.xCoord;
        this.y = vec.yCoord;
        this.z = vec.zCoord;
    }

    @Override
    public Coord3D copy() { // TODO: javadoc this
        return new Coord3D(x, y, z);
    }

    /**
     * Returns true if the given object and this coordinate are equal
     * @param obj
     *      The object to compare this coordinate to
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coord3D &&
                ((Coord3D) obj).x == x &&
                ((Coord3D) obj).y == y &&
                ((Coord3D) obj).z == z;
    }

    /**
     * Returns a {@link String} in the format (x, y, z)
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}