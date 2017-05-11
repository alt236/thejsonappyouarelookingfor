package uk.co.alt236.thejsonappyouaskedfor.data.loader;

import android.app.Activity;

import java.util.List;
import java.util.Locale;

import uk.co.alt236.thejsonappyouaskedfor.data.error.UiDataLoadErrorFactory;
import uk.co.alt236.thejsonappyouaskedfor.data.models.album.UiAlbum;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders.DataProvider;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonCallback;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonDeliverable;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonError;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;
import uk.co.alt236.thejsonappyouaskedfor.utils.AppLog;

/**
 *
 */
public class UiAlbumLoader extends DataFeedLoaderAbstract<UiAlbum> {
    final DataProvider mDataProvider;
    final AlbumAdapter mFactory;

    public UiAlbumLoader(final Activity activity,
                         final DataProvider dataProvider) {
        super(activity);
        this.mDataProvider = dataProvider;
        mFactory = new AlbumAdapter();
    }

    @Override
    public void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // move the current Thread into the background
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                loadAlbums();
            }
        }).start();
    }

    private void loadAlbums() {

        mDataProvider.getPhotos(new CommonCallback<List<Album>>() {
            @Override
            public void onFailure(final CommonError error) {
                // convert the error to a ui one
                AppLog.w(String.format("Error loading albums: %s", error));
                notifyError(UiDataLoadErrorFactory.createError(getActivity(), error));
            }

            @Override
            public void onSuccess(final CommonDeliverable<List<Album>> deliverable) {
                AppLog.d(String.format(Locale.UK, "%d albums loaded", deliverable.getContent().size()));
                final List<UiAlbum> uiAlbums = mFactory.createUiAlbumList(deliverable.getContent());
                AppLog.d(String.format(Locale.UK, "%d ui albums converted", uiAlbums.size()));
                notifySuccess(uiAlbums);
            }
        });
    }
}
