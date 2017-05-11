package uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders;

import java.util.List;

import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonCallback;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonDeliverable;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonError;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonErrorKind;
import uk.co.alt236.thejsonappyouaskedfor.loader.utils.log.CoreLog;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;
import uk.co.alt236.thejsonappyouaskedfor.storage.DataStorage;

public class LoaderStorage extends Loader {
    private final DataStorage storage;

    public LoaderStorage(final DataStorage storage) {
        this.storage = storage;
    }

    @Override
    protected void getPhotos(final CommonCallback<List<Album>> masterCallback) {
        CoreLog.d("STORAGE: Fetching photos");
        final boolean hasContent = storage.getAlbums().has();
        if (hasContent) {
            final List<Album> result = storage.getAlbums().getAll();
            final CommonDeliverable<List<Album>> deliverable = CommonDeliverable.from(result);
            CoreLog.d("STORAGE: Photos: " + result.size());
            masterCallback.onSuccess(deliverable);
        } else {
            CoreLog.d("STORAGE: Photos: <none>");
            masterCallback.onFailure(CommonError.from("No content", CommonErrorKind.NO_CONTENT_RETURNED));
        }
    }

    public void store(final List<Album> content) {
        storage.getAlbums().put(content);
    }
}