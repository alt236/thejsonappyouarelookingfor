package uk.co.alt236.thejsonappyouaskedfor.data.loader;

import android.app.Activity;
import android.os.Parcelable;

import java.util.List;

import uk.co.alt236.thejsonappyouaskedfor.data.error.UiDataLoadError;

/**
 *
 */
/*package*/ abstract class DataFeedLoaderAbstract<D extends Parcelable> {

    private final Activity mActivity;
    private DataFeedLoaderCallback<D> mCallback;

    /*package*/ DataFeedLoaderAbstract(final Activity activity) {
        mActivity = activity;
    }

    /*package*/ Activity getActivity() {
        return mActivity;
    }

    public abstract void loadData();

    /*package*/ void notifyError(final UiDataLoadError error) {
        if (mCallback != null) {
            if (mActivity == null) {
                mCallback.onError(error);
            } else {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onError(error);
                    }
                });
            }
        }
    }

    /*package*/ void notifySuccess(final List<D> items) {
        if (mCallback != null) {
            if (mActivity == null) {
                mCallback.onSuccess(items);
            } else {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onSuccess(items);
                    }
                });
            }
        }
    }

    public void setCallback(final DataFeedLoaderCallback<D> callback) {
        mCallback = callback;
    }

}