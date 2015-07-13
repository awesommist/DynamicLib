/**
 * This class was created by <awesommist>. It's distributed as
 * part of the DynamicsLib Mod. Get the Source Code in github:
 * https://github.com/awesommist/DynamicsLib
 */
package dynamics.utils.io;

import java.io.DataInput;
import java.io.IOException;

public interface IStreamReader<T> {
    T readFromStream(DataInput input) throws IOException;
}