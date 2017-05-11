package uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders;

import java.util.List;

import retrofit2.Call;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders.callbacks.AlbumListCallback;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonCallback;
import uk.co.alt236.thejsonappyouaskedfor.loader.utils.log.CoreLog;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;
import uk.co.alt236.thejsonappyouaskedfor.net.rest.RestClient;

public class LoaderNetwork extends Loader {
    private static final int LIMIT = 10;
    private final RestClient restClient;

    public LoaderNetwork(final RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected void getPhotos(final CommonCallback<List<Album>> masterCallback) {
        CoreLog.d("NETWORK: Fetching photos");
        final Call<List<Album>> call = restClient.getPhotoApi().getPhotos(String.valueOf(LIMIT));
        call.enqueue(new AlbumListCallback(masterCallback));
    }

}