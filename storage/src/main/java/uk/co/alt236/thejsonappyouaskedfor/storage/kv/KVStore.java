package uk.co.alt236.thejsonappyouaskedfor.storage.kv;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Set;

public interface KVStore {

    String getStoreName();

    void put(@NonNull final String key, final Object object);

    boolean delete(final String key);

    boolean contains(final String key);

    void clear();

    @Nullable
    <T> T get(String key);

    <T> T get(String key, T defaultValue);

    @NonNull
    Set<String> getAllKeys();
}
