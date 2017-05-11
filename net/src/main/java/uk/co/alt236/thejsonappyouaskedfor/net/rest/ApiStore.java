package uk.co.alt236.thejsonappyouaskedfor.net.rest;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import uk.co.alt236.thejsonappyouaskedfor.net.api.PhotoApi;


/**
 *
 */
/*protected*/ class ApiStore {
    private final String mServerEndpoint;
    private final PhotoApi mPhotoApi;

    public ApiStore(final String serverEndpoint, final Gson gson) {
        mServerEndpoint = serverEndpoint;
        final Retrofit adapter = RestAdapterFactory.createDefault(serverEndpoint, gson);
        mPhotoApi = adapter.create(PhotoApi.class);
    }

    public PhotoApi getPhotoApi() {
        return mPhotoApi;
    }

    public String getServerEndpoint() {
        return mServerEndpoint;
    }
}
