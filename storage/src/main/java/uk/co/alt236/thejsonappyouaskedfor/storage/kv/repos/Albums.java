package uk.co.alt236.thejsonappyouaskedfor.storage.kv.repos;

import android.support.annotation.NonNull;

import java.util.List;

import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;
import uk.co.alt236.thejsonappyouaskedfor.storage.kv.KVRepository;
import uk.co.alt236.thejsonappyouaskedfor.storage.kv.KVStore;
import uk.co.alt236.thejsonappyouaskedfor.storage.kv.RootKey;
import uk.co.alt236.thejsonappyouaskedfor.storage.kv.repos.common.KvUtils;

public class Albums implements KVRepository<Album> {
    private static final RootKey KEY = new RootKey("albums", 1);

    private final KVStore kvStore;

    public Albums(KVStore kvStore) {
        this.kvStore = kvStore;
    }

    @NonNull
    @Override
    public List<Album> getAll() {
        final String key = KvUtils.constructKey(getRootKey());
        return KvUtils.getListNullSafe(kvStore, key);
    }

    public void put(final List<Album> data) {
        final String key = KvUtils.constructKey(getRootKey());
        kvStore.put(key, data);
    }

    public boolean has() {
        final String key = KvUtils.constructKey(getRootKey());
        return kvStore.contains(key);
    }

    @NonNull
    @Override
    public RootKey getRootKey() {
        return KEY;
    }
}
