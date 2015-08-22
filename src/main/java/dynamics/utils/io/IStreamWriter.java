package dynamics.utils.io;

import java.io.DataOutput;
import java.io.IOException;

public interface IStreamWriter<T> {
    void writeToStream(T o, DataOutput output) throws IOException;
}