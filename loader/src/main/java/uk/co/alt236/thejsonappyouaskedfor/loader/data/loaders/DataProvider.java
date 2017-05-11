package uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders;

import java.util.List;

import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonCallback;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonDeliverable;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonError;
import uk.co.alt236.thejsonappyouaskedfor.loader.utils.log.CoreLog;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;

/**
 *
 */
public class DataProvider {

    private final Loader networkLoader;
    private final LoaderStorage storageLoader;

    public DataProvider(LoaderNetwork network,
                        LoaderStorage storage) {
        networkLoader = network;
        storageLoader = storage;
    }

    public void getPhotos(final CommonCallback<List<Album>> masterCallback) {

        CoreLog.d("Fetching photos");
        networkLoader.getPhotos(new CommonCallback<List<Album>>() {
            @Override
            public void onFailure(final CommonError error) {
                CoreLog.d("Network failed to fetch");
                // Try to get local cache
                storageLoader.getPhotos(new CommonCallback<List<Album>>() {
                    @Override
                    public void onFailure(final CommonError storeError) {
                        // We don't really care..
                        masterCallback.onFailure(error);
                    }

                    @Override
                    public void onSuccess(final CommonDeliverable<List<Album>> deliverable) {
                        masterCallback.onSuccess(deliverable);
                    }
                });
            }

            @Override
            public void onSuccess(final CommonDeliverable<List<Album>> deliverable) {
                // Cache what we got
                storageLoader.store(deliverable.getContent());
                masterCallback.onSuccess(deliverable);
            }
        });
    }
}
