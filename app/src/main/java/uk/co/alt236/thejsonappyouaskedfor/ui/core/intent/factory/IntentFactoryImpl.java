package uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.factory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import uk.co.alt236.optional.debug.DeveloperActivity;
import uk.co.alt236.thejsonappyouaskedfor.ui.components.home.HomeActivity;


/**
 *
 */
public class IntentFactoryImpl implements IntentFactory {

    private final Context mContext;

    public IntentFactoryImpl(final Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public Intent getHomeIntent() {
        final Intent intent = HomeActivity.getInstance(mContext);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    public Intent getDevIntent() {
        return DeveloperActivity.getInstance(mContext);
    }

    @Override
    public Intent getOpenIntent(final Uri uri) {
        final Intent intent;

        if (uri == null) {
            intent = null;
        } else {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
        }

        return intent;
    }

}
