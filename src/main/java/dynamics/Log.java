package dynamics;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Log {

    private static final Logger logger = LogManager.getLogger(DynamicLib.MODID);

    private static final Throwable stackInfo = new Throwable();

    private static String getLogLocation(Throwable t) {
        final StackTraceElement[] stack = t.getStackTrace();
        if (stack.length < 2) return "";
        final StackTraceElement caller = stack[1];
        return caller.getClassName() + "." + caller.getMethodName() + "(" + caller.getFileName() + ":" + caller.getLineNumber() + "): ";
    }

    private static void logWithCaller(Throwable callerStack, Level level, String format, Object... data) {
        logger.log(level, getLogLocation(callerStack) + String.format(format, data));
    }

    public static void log(Level level, String format, Object... data) {
        logWithCaller(stackInfo.fillInStackTrace(), level, format, data);
    }

    public static void severe(String format, Object... data) {
        logWithCaller(stackInfo.fillInStackTrace(), Level.ERROR, format, data);
    }

    public static void warn(String format, Object... data) {
        logWithCaller(stackInfo.fillInStackTrace(), Level.WARN, format, data);
    }

    public static void info(String format, Object... data) {
        logWithCaller(stackInfo.fillInStackTrace(), Level.INFO, format, data);
    }

    public static void debug(String format, Object... data) {
        logWithCaller(stackInfo.fillInStackTrace(), Level.DEBUG, format, data);
    }

    public static void trace(String format, Object... data) {
        logWithCaller(stackInfo.fillInStackTrace(), Level.TRACE, format, data);
    }

    public static void log(Level level, Throwable t, String format, Object... data) {
        logger.log(level, String.format(format, data), t);
    }

    public static void severe(Throwable t, String format, Object... data) {
        log(Level.ERROR, t, format, data);
    }

    public static void warn(Throwable t, String format, Object... data) {
        log(Level.WARN, t, format, data);
    }

    public static void info(Throwable t, String format, Object... data) {
        log(Level.INFO, t, format, data);
    }
}