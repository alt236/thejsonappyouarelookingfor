package uk.co.alt236.thejsonappyouaskedfor.storage.kv;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Set;

import uk.co.alt236.thejsonappyouaskedfor.storage.kv.repos.common.DataUtils;

public abstract class AbstractKVStore implements KVStore {
    private static final String VERB_GET_DEF = "[GET_DEF]";
    private static final String VERB_GET = "[GET]";
    private static final String VERB_PUT = "[PUT]";
    private static final String VERB_DEL = "[DEL]";
    private static final String VERB_CLEAR = "[CLEAR]";
    private static final String VERB_KEYS = "[KEYS]";
    private static final String VERB_CONTAINS = "[CONTAINS]";

    private final KVLog logger;
    private final boolean validation;

    protected AbstractKVStore(final boolean validation) {
        final String tag = "KV-" + getStoreName() + "@" + System.identityHashCode(this);
        this.validation = validation;
        this.logger = new KVLog(tag);

        logger.log("New " + getStoreName() + " store. Validation enabled? " + validation);
    }

    public final void put(@NonNull final String key, final Object object) {
        DataUtils.notNull("key", key);
        logger.logInput(VERB_PUT, key, object);
        putInternal(key, object);
        if (validation) {
            final String in = KVLog.getStringWithHash(DataUtils.getObjectInfo(object));
            final String out = KVLog.getStringWithHash(DataUtils.getObjectInfo(getInternal(key)));
            if (in.equals(out)) {
                logger.logValidationOk(VERB_PUT, key, "OK");
            } else {
                final String message = "ERROR: expected " + in + " but got " + out;
                logger.logValidationError(VERB_PUT, key, message);
                throw new IllegalStateException(key + " " + message);
            }
        }
    }

    public final boolean delete(@NonNull final String key) {
        DataUtils.notNull("key", key);
        final boolean retVal = deleteInternal(key);
        logger.logResult(VERB_DEL, key, retVal);
        return retVal;
    }

    public final boolean contains(@NonNull final String key) {
        DataUtils.notNull("key", key);
        final boolean retVal = containsInternal(key);
        logger.logResult(VERB_CONTAINS, key, retVal);
        return retVal;
    }

    public final void clear() {
        logger.log(VERB_CLEAR);
        clearInternal();
        logger.log(VERB_CLEAR + " - finished");
    }

    @Nullable
    public final <T> T get(@NonNull String key) {
        DataUtils.notNull("key", key);
        final T retVal = getInternal(key);
        logger.logResult(VERB_GET, key, retVal);
        if (validation) {
            if (retVal == null) {
                final boolean keyPresent = containsInternal(key);
                if (keyPresent) {
                    logger.logValidationError(VERB_GET, key, "Result was null, but key is present?");
                } else {
                    logger.logValidationOk(VERB_GET, key, "Result was null, but key is not present -- expected");
                }
            }
        }
        return retVal;
    }

    public final <T> T get(@NonNull String key, T defaultValue) {
        DataUtils.notNull("key", key);
        final T retVal = getInternal(key, defaultValue);
        logger.logResult(VERB_GET_DEF, key, retVal);
        return retVal;
    }

    @NonNull
    public final Set<String> getAllKeys() {
        final Set<String> keys = getAllKeysInternal();
        logger.log(VERB_KEYS + " result: " + keys);
        return keys;
    }

    public abstract String getStoreName();

    protected abstract boolean containsInternal(final String key);

    protected abstract void putInternal(final String key, final Object object);

    protected abstract boolean deleteInternal(final String key);

    protected abstract void clearInternal();

    protected abstract <T> T getInternal(final String key);

    protected abstract <T> T getInternal(final String key, final T defaultValue);

    protected abstract Set<String> getAllKeysInternal();
}
