package uk.co.alt236.optional.debug;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import uk.co.alt236.thejsonappyouaskedfor.R;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.activity.BaseActivity;


public class DeveloperActivity extends BaseActivity {

    private static final String FRAGMENT_TAG = DeveloperActivity.class.getSimpleName() + "_fragment_tag";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_fragment_container);
        setTitle("Here there be dragons");
        addContentFragmentIfMissing(JsonAlbumFragment.newInstance(), FRAGMENT_TAG);
    }

    public static Intent getInstance(final Context context) {
        return new Intent(context, DeveloperActivity.class);
    }
}
