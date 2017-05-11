package uk.co.alt236.thejsonappyouaskedfor.storage.kv.repos.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.List;

import uk.co.alt236.thejsonappyouaskedfor.storage.kv.KVStore;
import uk.co.alt236.thejsonappyouaskedfor.storage.kv.RootKey;

public class KvUtils {
    private static final char ROOT_SEPARATOR = '/';
    private static final char PATH_SEPARATOR = ':';

    @NonNull
    public static <T> List<T> getListNullSafe(final KVStore store,
                                              final String key) {
        final List<T> list = store.get(key);
        return list == null ? Collections.<T>emptyList() : list;
    }

    @NonNull
    public static String constructKey(@NonNull final RootKey rootKey,
                                      @Nullable final String... parts) {
        DataUtils.notNull(rootKey);

        final StringBuilder sb = new StringBuilder();
        sb.append(rootKey.getType());
        sb.append(PATH_SEPARATOR);
        sb.append("v");
        sb.append(rootKey.getVersion());
        sb.append(ROOT_SEPARATOR);

        if (parts != null) {
            for (int i = 0; i < parts.length; i++) {
                DataUtils.notNull("Key part (" + i + ") - " + sb.toString(), parts[i]);
                sb.append(parts[i]);
                if (i != parts.length - 1) {
                    sb.append(PATH_SEPARATOR);
                }
            }
        }
        return sb.toString();
    }
}
