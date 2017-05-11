package uk.co.alt236.optional.debug;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.activity.BaseActivity;

/**
 *
 */
public class DeveloperActivity extends BaseActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        throw new IllegalStateException("You should NOT be here");
    }

    public static Intent getInstance(final Context context) {
        return new Intent(context, DeveloperActivity.class);
    }
}
