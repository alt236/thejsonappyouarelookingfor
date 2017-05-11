package uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.dispatch;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

/**
 */
@SuppressWarnings("unused")
public interface IntentDispatcher {

    void open(View source, Uri uri);

    void dispatch(View source, Intent intent);

    void dispatchForResult(View source, Intent intent, int requestCode);

    void openHomeActivity();

    void openDevScreen(View source);
}
