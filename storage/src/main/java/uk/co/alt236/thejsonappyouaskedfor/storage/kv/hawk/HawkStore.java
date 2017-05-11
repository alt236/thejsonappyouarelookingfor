package uk.co.alt236.thejsonappyouaskedfor.storage.kv.hawk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.LogInterceptor;
import com.orhanobut.hawk.NoEncryption;

import java.util.Set;

import uk.co.alt236.thejsonappyouaskedfor.storage.BuildConfig;
import uk.co.alt236.thejsonappyouaskedfor.storage.kv.AbstractKVStore;

public final class HawkStore extends AbstractKVStore {
    private static final String HAWK_PREFS = BuildConfig.APPLICATION_ID + ".hawk2";

    private final SharedPreferencesStorage storage;
    private final Object lock;

    private HawkStore(final SharedPreferencesStorage storage,
                      final boolean validation) {
        super(validation);
        lock = new Object();
        this.storage = storage;
    }

    @Override
    public String getStoreName() {
        return "HAWK";
    }

    @Override
    protected void putInternal(@NonNull final String key,
                               @Nullable final Object value) {
        synchronized (lock) {
            Hawk.put(key, value);
        }
    }

    @Override
    protected boolean deleteInternal(final String key) {
        synchronized (lock) {
            return Hawk.delete(key);
        }
    }

    @Override
    protected boolean containsInternal(final String key) {
        synchronized (lock) {
            return Hawk.contains(key);
        }
    }

    @Override
    protected void clearInternal() {
        synchronized (lock) {
            Hawk.deleteAll();
        }
    }

    @Override
    @Nullable
    protected <T> T getInternal(final String key) {
        synchronized (lock) {
            return Hawk.get(key);
        }
    }

    @Override
    @NonNull
    protected Set<String> getAllKeysInternal() {
        synchronized (lock) {
            return storage.getKeys();
        }
    }

    @Override
    protected <T> T getInternal(final String key, final T defaultValue) {
        synchronized (lock) {
            return Hawk.get(key, defaultValue);
        }
    }

    public static AbstractKVStore create(final Context context,
                                         final boolean validate,
                                         final LogInterceptor logInterceptor,
                                         final Gson gson) {
        final SharedPreferencesStorage storage = new SharedPreferencesStorage(context, HAWK_PREFS);
        Hawk.init(context.getApplicationContext())
                .setEncryption(new NoEncryption())
                .setLogInterceptor(logInterceptor)
                .setParser(new LoggingGsonParser(gson))
                .setStorage(storage)
                .build();

        return new HawkStore(storage, validate);
    }

}
