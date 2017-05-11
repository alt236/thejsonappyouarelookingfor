package uk.co.alt236.thejsonappyouaskedfor.storage.kv.repos.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.List;

public final class DataUtils {

    @NonNull
    public static <T> List<T> getSafeList(@Nullable final List<T> list) {

        final List<T> retVal;
        if (list == null) {
            retVal = Collections.emptyList();
        } else {
            retVal = list;
        }

        return retVal;
    }

    public static void notNull(final String message, final Object object) {
        if (object == null) {
            throw new IllegalStateException(message + " cannot be null!");
        }
    }

    public static void notNull(final Object object) {
        if (object == null) {
            throw new IllegalStateException("Value cannot be null!");
        }
    }

    public static String getObjectInfo(final Object object) {
        final String retVal;

        if (object == null) {
            retVal = "<null>";
        } else {
            if (object instanceof List) {
                final int size = ((List) object).size();
                if (size > 0) {
                    final String type = getObjectInfo(((List) object).get(0));
                    retVal = "List(" + size + ") of " + type;
                } else {
                    retVal = "List(" + size + ")";
                }

            } else {
                retVal = object.getClass().getName();
            }
        }

        return retVal;
    }
}
