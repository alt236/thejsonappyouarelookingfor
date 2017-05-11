package uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;
import uk.co.alt236.thejsonappyouaskedfor.net.rest.RestClient;

/**
 *
 */
/*package*/ class NetworkRequestForwarder {

    private final RestClient mRestClient;

    public NetworkRequestForwarder(final RestClient restClient) {
        this.mRestClient = restClient;
    }

    public void forwardGetPhotos(final Integer limit,
                                 final Callback<List<Album>> retrofitCallback) {

        final Call<List<Album>> call = mRestClient.getPhotoApi().getPhotos(String.valueOf(limit));
        call.enqueue(retrofitCallback);
    }

}
