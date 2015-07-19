/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicLib
 */
package dynamics.utils.render;

import java.nio.FloatBuffer;
import java.util.Set;

import dynamics.Log;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Util;
import org.lwjgl.util.vector.Matrix4f;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

public class OpenGLUtils {

    public static final Function<Integer, String> CONVERT_GL_ERROR = new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return Util.translateGLErrorString(input);
        }
    };

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public static synchronized void loadMatrix(Matrix4f transform) {
        transform.store(matrixBuffer);
        matrixBuffer.flip();
        GL11.glMultMatrix(matrixBuffer);
    }

    public static Set<Integer> getGlErrors() {
        int glError = GL11.glGetError();

        if (glError == GL11.GL_NO_ERROR) return ImmutableSet.of();

        ImmutableSet.Builder<Integer> result = ImmutableSet.builder();
        do {
            result.add(glError);
        } while ((glError = GL11.glGetError()) != GL11.GL_NO_ERROR);

        return result.build();
    }

    public static void flushGlErrors(String location) {
        Set<Integer> errors = getGlErrors();
        if (!errors.isEmpty()) Log.warn("OpenGl errors detected in '%s': [%s]", location, errorsToString(errors));
    }

    public static String errorsToString(Iterable<Integer> errors) {
        return Joiner.on(',').join(Iterables.transform(errors, CONVERT_GL_ERROR));
    }
}