/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils.io;

import java.io.DataOutput;
import java.io.IOException;

public interface IStreamWriter<T> {
    void writeToStream(T o, DataOutput output) throws IOException;
}