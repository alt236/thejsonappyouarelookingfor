package uk.co.alt236.thejsonappyouaskedfor.storage.kv;

import android.support.annotation.NonNull;

import java.util.List;

public interface KVRepository<T> {
    @NonNull
    List<T> getAll();

    @NonNull
    RootKey getRootKey();
}
