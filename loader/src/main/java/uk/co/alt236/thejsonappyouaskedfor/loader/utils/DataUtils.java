package uk.co.alt236.thejsonappyouaskedfor.loader.utils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 */
public final class DataUtils {
    private static final String PATTERN = "^http(s?)://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/&\\?=\\-\\.~%]*";

    private DataUtils() {
        // NOOP
    }

    public static String getLastPathSegment(final String url) {

        String retVal;
        try {
            final URI uri = new URI(url);
            final String path = uri.getPath();
            retVal = path.substring(path.lastIndexOf('/') + 1);
        } catch (URISyntaxException e) {
            retVal = "";
        }

        return retVal;
    }

    public static boolean isURL(final String url) {
        if (url == null) {
            return false;
        }
        // Assigning the url format regular expression
        return url.matches(PATTERN);
    }
}
