package uk.co.alt236.thejsonappyouaskedfor.ui.core.common.notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import uk.co.alt236.thejsonappyouaskedfor.BuildConfig;

public final class AppToast {

    private static final Length DEFAULT_LENGTH = Length.SHORT;
    private final Toast mToast;

    private AppToast(final Toast toast) {
        this.mToast = toast;
    }

    public void show() {
        mToast.show();
    }

    public void cancel() {
        mToast.cancel();
    }

    public static AppToast create(final Context context, final int resId, final Length length) {
        return create(context, context.getString(resId), length);
    }

    @SuppressLint("ShowToast")
    public static AppToast create(final Context context, final CharSequence charSequence, final Length length) {
        final int duration = getInternalToastDuration(length);
        //noinspection ResourceType
        return new AppToast(Toast.makeText(context, charSequence, duration));
    }

    private static int getInternalToastDuration(final Length length) {
        if (length == null) {
            return Toast.LENGTH_SHORT;
        } else if (length == Length.SHORT) {
            return Toast.LENGTH_SHORT;
        } else {
            return Toast.LENGTH_LONG;
        }
    }

    public static void show(final Context context, final CharSequence charSequence) {
        show(context, charSequence, DEFAULT_LENGTH);
    }

    public static void show(final Context context, final CharSequence charSequence, final Length length) {
        create(context, charSequence, length).show();
    }

    public static void show(final Context context, final int resId) {
        show(context, resId, DEFAULT_LENGTH);
    }

    public static void show(final Context context, final int resId, final Length length) {
        show(context, context.getString(resId), length);
    }

    public static void showDebug(final Context context, final CharSequence charSequence) {
        showDebug(context, charSequence, DEFAULT_LENGTH);
    }

    public static void showDebug(final Context context, final CharSequence charSequence, final Length length) {
        if (BuildConfig.DEV_MODE) {
            show(context, charSequence, length);
        }
    }

    public enum Length {
        SHORT,
        LONG
    }

}
