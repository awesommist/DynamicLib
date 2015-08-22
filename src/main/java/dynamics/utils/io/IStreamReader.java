package dynamics.utils.io;

import java.io.DataInput;
import java.io.IOException;

public interface IStreamReader<T> {
    T readFromStream(DataInput input) throws IOException;
}