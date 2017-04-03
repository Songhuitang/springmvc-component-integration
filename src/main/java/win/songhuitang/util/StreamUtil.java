package win.songhuitang.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by simon.song on 2017/4/2.
 * Function: Stream Util Class.
 */
public class StreamUtil {
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
