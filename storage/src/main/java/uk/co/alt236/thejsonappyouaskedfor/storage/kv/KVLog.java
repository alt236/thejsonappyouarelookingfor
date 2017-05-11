package uk.co.alt236.thejsonappyouaskedfor.storage.kv;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Locale;

import uk.co.alt236.thejsonappyouaskedfor.storage.kv.repos.common.DataUtils;

/*package*/ class KVLog {
    private static final String ORIENTATION_INP = "---> ";
    private static final String ORIENTATION_OUT = "<--- ";
    private static final String ORIENTATION_N_A = "     ";

    private static final String TEXT_GENERIC = ORIENTATION_N_A + "%s";
    private static final String TEXT_VALIDATION = ORIENTATION_N_A + "%s key: %s - VALIDATION - %s";
    private static final String TEXT_IN = ORIENTATION_INP + "%s key: %s, in : %s";
    private static final String TEXT_OUT = ORIENTATION_OUT + "%s key: %s, out: %s";

    private final String tag;

    public KVLog(final String tag) {
        this.tag = tag;
    }

    public void logResult(String action, String key, Object payload) {
        final String message = String.format(Locale.US, TEXT_OUT,
                action, getStringWithHash(key), DataUtils.getObjectInfo(payload));
        logInternal(message);
    }

    public void logInput(String action, String key, Object payload) {
        final String message = String.format(Locale.US, TEXT_IN,
                action, getStringWithHash(key), DataUtils.getObjectInfo(payload));
        logInternal(message);
    }

    public void logValidationOk(String action, String key, String text) {
        final String message = String.format(Locale.US, TEXT_VALIDATION,
                action, getStringWithHash(key), text);
        logInternal(message);
    }

    public void logValidationError(String action, String key, String text) {
        final String message = String.format(Locale.US, TEXT_VALIDATION,
                action, getStringWithHash(key), text);
        logInternalErr(message);
    }

    public void log(final String message) {
        final String text = String.format(Locale.US, TEXT_GENERIC, message);
        logInternal(text);
    }

    public void logErr(final String message) {
        final String text = String.format(Locale.US, TEXT_GENERIC, message);
        logInternalErr(text);
    }

    private void logInternal(final String message) {
        Log.d(tag, message);
    }

    private void logInternalErr(final String message) {
        Log.e(tag, message);
    }

    public static String getStringWithHash(@NonNull final String key) {
        return "'" + key + "' (hash: " + key.hashCode() + ")";
    }
}
