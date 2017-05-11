package uk.co.alt236.thejsonappyouaskedfor.data.loader;

import android.os.Parcelable;

import java.util.List;

import uk.co.alt236.thejsonappyouaskedfor.data.error.UiDataLoadError;

/**
 *
 */
public interface DataFeedLoaderCallback<T extends Parcelable> {

    void onError(final UiDataLoadError error);

    void onSuccess(final List<T> result);
}
