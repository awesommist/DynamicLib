/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

public class ByteUtils {

    public static int on(int val, int bit) {
        return val | (1 << bit);
    }

    public static int off(int val, int bit) {
        return val & ~(1 << bit);
    }

    public static void writeVLI(DataOutput output, int value) {
        Preconditions.checkArgument(value >= 0, "Value cannot be negative");

        try {
            while (true) {
                int b = value & 0x7F;
                int next = value >> 7;
                if (next > 0) {
                    b |= 0x80;
                    output.writeByte(b);
                    value = next;
                } else {
                    output.writeByte(b);
                    break;
                }
            }
        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }

    public static int readVLI(DataInput input) {
        int result = 0;
        int shift = 0;
        int b;
        try {
            do {
                b = input.readByte();
                result = result | ((b & 0x7F) << shift);
                shift += 7;
            } while (b < 0);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return result;
    }
}