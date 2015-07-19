/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils;

import net.minecraft.entity.Entity;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class MathUtils {

    public static Matrix4f createEntityRotateMatrix(Entity entity) {
        double yaw = Math.toRadians(entity.rotationYaw - 180);
        double pitch = Math.toRadians(entity.rotationPitch);

        Matrix4f initial = new Matrix4f();
        initial.rotate((float)pitch, new Vector3f(1, 0, 0));
        initial.rotate((float)yaw, new Vector3f(0, 1, 0));
        return initial;
    }
}