package uk.co.alt236.thejsonappyouaskedfor.ui.core.common.error.quotes;

import android.content.Context;

import java.util.Random;

import uk.co.alt236.thejsonappyouaskedfor.R;

/**
 *
 */
public final class ErrorQuotePicker implements QuotePicker {

    private final CharSequence[] quotes;

    public ErrorQuotePicker(final Context context) {
        quotes = context.getResources().getStringArray(R.array.error_quote_array);
    }


    @Override
    public Quote getQuote() {
        final int idx = new Random().nextInt(quotes.length);
        return new Quote(quotes[idx]);
    }

}
