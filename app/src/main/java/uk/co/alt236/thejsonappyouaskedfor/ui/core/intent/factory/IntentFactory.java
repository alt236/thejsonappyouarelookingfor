package uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.factory;

import android.content.Intent;
import android.net.Uri;

/**
 *
 */
public interface IntentFactory {

    Intent getHomeIntent();

    Intent getDevIntent();

    Intent getOpenIntent(Uri uri);
}
