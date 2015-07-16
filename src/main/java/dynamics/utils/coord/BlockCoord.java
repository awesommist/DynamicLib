/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils.coord;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraftforge.common.util.ForgeDirection;
import dynamics.api.Copyable;

/**
 * 3-tuple of integers used to represent coordinates in a three-dimensional space.
 * More stuff will be added as needed.
 */
public class BlockCoord implements Copyable<BlockCoord> {

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
     * Creates a BlockCoord given the specified offsets for each axis
     *
     * @param x
     *      Offset in the 'x' axis
     * @param y
     *      Offset in the 'y' axis
     * @param z
     *      Offset in the 'z' axis
     */
    public BlockCoord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a BlockCoord on the origin (0, 0, 0)
     */
    public BlockCoord() {
        this (0, 0, 0);
    }

    /**
     * Creates a BlockCoord given the specified offsets for each axis
     *
     * @param x
     *      Offset in the 'x' axis
     * @param y
     *      Offset in the 'y' axis
     * @param z
     *      Offset in the 'z' axis
     */
    public BlockCoord(double x, double y, double z) {
        this ((int) Math.round(x), (int) Math.round(y), (int) Math.round(z));
    }

    public BlockCoord(ForgeDirection direction) { // TODO: javadoc this
        this (direction.offsetX, direction.offsetY, direction.offsetZ);
    }

    /**
     * Creates a BlockCoord given the specified offsets for each axis in an integer array
     *
     * @param coords
     *      The array with the coordinates
     */
    public BlockCoord(int[] coords) {
        this (coords[0], coords[1], coords[2]);
    }

    /**
     * Creates a BlockCoord given a {@link ChunkPosition}
     *
     * @param pos
     *      The chunk position
     */
    public BlockCoord(ChunkPosition pos) {
        this (pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ);
    }

    /**
     * Creates a BlockCoord given {@link ChunkCoordinates}
     *
     * @param pos
     *      The chunk coordinates
     */
    public BlockCoord(ChunkCoordinates pos) {
        this (pos.posX, pos.posY, pos.posZ);
    }

    /**
     * Creates a BlockCoord given a {@link Vec3}
     *
     * @param vec
     *      The vector
     */
    public BlockCoord(Vec3 vec) {
        this (vec.xCoord, vec.yCoord, vec.zCoord);
    }

    @Override
    public BlockCoord copy() { // TODO: javadoc this
        return new BlockCoord(x, y, z);
    }

    /**
     * Returns these coordinates as an integer array
     */
    public int[] asIntArray() {
        return new int[] { x, y, z };
    }

    /**
     * Returns these coordinates as a {@link ChunkPosition}
     */
    public ChunkPosition asChunkPosition() {
        return new ChunkPosition(x, y, z);
    }

    /**
     * Returns these coordinates as {@link ChunkCoordinates}
     */
    public ChunkCoordinates asChunkCoordinates() {
        return new ChunkCoordinates(x, y, z);
    }

    /**
     * Returns these coordinates as a {@link Vec3}
     */
    public Vec3 asVec3() {
        return Vec3.createVectorHelper(x, y, z);
    }

    /**
     * Returns true if the given object and this coordinate are equal
     *
     * @param obj The object to compare this coordinate to
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BlockCoord &&
                ((BlockCoord) obj).x == x &&
                ((BlockCoord) obj).y == y &&
                ((BlockCoord) obj).z == z;
    }

    /**
     * Returns a {@link String} in the format (x, y, z)
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public boolean isAbove(BlockCoord pos) {
        return pos != null && y > pos.y;
    }

    public boolean isBelow(BlockCoord pos) {
        return pos != null && y < pos.y;
    }

    public boolean isNorthOf(BlockCoord pos) {
        return pos != null && z < pos.z;
    }

    public boolean isSouthOf(BlockCoord pos) {
        return pos != null && z > pos.z;
    }

    public boolean isWestOf(BlockCoord pos) {
        return pos != null && x < pos.x;
    }

    public boolean isEastOf(BlockCoord pos) {
        return pos != null && x > pos.x;
    }

    public boolean isXAligned(BlockCoord pos) {
        return pos != null && x == pos.x;
    }

    public boolean isYAligned(BlockCoord pos) {
        return pos != null && y == pos.y;
    }

    public boolean isZAligned(BlockCoord pos) {
        return pos != null && z == pos.z;
    }
}