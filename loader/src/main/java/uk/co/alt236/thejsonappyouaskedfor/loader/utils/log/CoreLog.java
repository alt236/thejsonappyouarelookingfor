package uk.co.alt236.thejsonappyouaskedfor.loader.utils.log;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public final class CoreLog {
    private static final String LINE_PREFIX = "SDK:";

    private static LogEngine logEngine = new LogEngine() {
        @Override
        public void d(final String tag, final String message) {
            System.out.println(tag + ": " + message);
        }

        @Override
        public void e(final String tag, final String message) {
            System.err.println(tag + ": " + message);
        }

        @Override
        public void e(final String tag, final String message, final Exception e) {
            System.err.println(tag + ": " + message);
            if (e != null) {
                e.printStackTrace();
            }
        }

        @Override
        public void w(final String tag, final String message) {
            System.err.println(tag + ": " + message);
        }

        @Override
        public void w(final String tag, final String message, final Exception e) {
            System.err.println(tag + ": " + message);
            if (e != null) {
                e.printStackTrace();
            }
        }
    };

    private CoreLog() {
        //NOOP
    }

    public static void setLogEngine(final LogEngine engine) {
        logEngine = engine;
    }

    public static void d(final String message) {
        dInternal(message);
    }

    private static void dInternal(final String message) {
        logEngine.d(calcTag(), calcMessage(message));
    }

    private static String calcTag() {
        return "LOADER";
    }

    private static String calcMessage(final String message) {
        return LINE_PREFIX + message;
    }

    public static void e(final String message) {
        eInternal(message, null);
    }

    private static void eInternal(final String message, final Exception e) {
        logEngine.e(calcTag(), calcMessage(message), e);
    }

    public static void e(final String message, final Exception e) {
        eInternal(message, e);
    }

    private static Set<String> getEscapedClassNames() {
        final Set<String> set = new HashSet<>();

        set.add("java.lang.Thread");
        set.add("dalvik.system.VMStack");
        set.add(CoreLog.class.getName());

        return set;
    }

    public static void w(final String message) {
        wInternal(message, null);
    }

    private static void wInternal(final String message, final Exception e) {
        logEngine.w(calcTag(), calcMessage(message), e);
    }

    public static void w(final String message, final Exception e) {
        wInternal(message, e);
    }
}