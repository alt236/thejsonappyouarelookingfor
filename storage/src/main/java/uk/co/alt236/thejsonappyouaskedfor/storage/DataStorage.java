package uk.co.alt236.thejsonappyouaskedfor.storage;

import uk.co.alt236.thejsonappyouaskedfor.storage.kv.KVStore;
import uk.co.alt236.thejsonappyouaskedfor.storage.kv.repos.Albums;

/**
 * Lazy instantiation for DB models
 */
@SuppressWarnings("ClassWithTooManyFields")
public class DataStorage {

    private final Albums albums;

    public DataStorage(final KVStore kvStore) {
        albums = new Albums(kvStore);
    }

    public Albums getAlbums() {
        return albums;
    }
}
