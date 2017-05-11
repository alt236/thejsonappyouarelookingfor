package uk.co.alt236.thejsonappyouaskedfor.loader.utils.log;

public interface LogEngine {
    void d(final String tag, final String message);

    void e(final String tag, final String message);

    void e(final String tag, final String message, final Exception e);

    void w(final String tag, final String message);

    void w(final String tag, final String message, final Exception e);
}
