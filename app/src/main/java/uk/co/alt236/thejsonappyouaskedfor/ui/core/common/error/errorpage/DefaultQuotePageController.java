package uk.co.alt236.thejsonappyouaskedfor.ui.core.common.error.errorpage;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.alt236.thejsonappyouaskedfor.R;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.error.quotes.Quote;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.error.quotes.QuotePicker;

/**
 *
 */
public class DefaultQuotePageController implements QuotePageController {

    private final QuotePicker mQuotePicker;
    @Bind(R.id.error_button)
    protected Button mActionButton;
    @Bind(R.id.error_description)
    protected TextView mErrorDescription;
    @Bind(R.id.error_quote)
    protected TextView mQuote;

    public DefaultQuotePageController(final View view, final QuotePicker quotePicker) {
        ButterKnife.bind(this, view);
        mQuotePicker = quotePicker;
    }

    @Override
    public void display(final CharSequence message) {
        display(message, null);
    }

    @Override
    public void display(final CharSequence message, final QuoteOnClickListenerWrapper listenerWrapper) {
        final Quote quote = mQuotePicker.getQuote();
        mQuote.setText(quote.getQuote());

        mErrorDescription.setText(message);

        if (listenerWrapper == null || listenerWrapper.getListener() == null) {
            mActionButton.setVisibility(View.GONE);
        } else {
            mActionButton.setVisibility(View.VISIBLE);
            // set up the action button label
            if (listenerWrapper.getResId() == 0) {
                mActionButton.setText(R.string.button_label_error_try_again);
            } else {
                mActionButton.setText(listenerWrapper.getResId());
            }
            mActionButton.setOnClickListener(listenerWrapper.getListener());
        }

    }

}
