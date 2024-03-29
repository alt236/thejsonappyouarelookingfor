package uk.co.alt236.thejsonappyouaskedfor.ui.core.common.viewmanagement;

import android.view.View;

import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.error.errorpage.QuoteOnClickListenerWrapper;

/**
 *
 */
public interface UiStateKeeper {

    View getContentView();

    View getEmptyView();

    View getErrorView();

    View getProgressView();

    void showContent();

    void showEmpty();

    void showEmpty(CharSequence message);

    void showError(final CharSequence message);

    void showError(final CharSequence message, final QuoteOnClickListenerWrapper listenerWrapper);

    void showProgress();
}
