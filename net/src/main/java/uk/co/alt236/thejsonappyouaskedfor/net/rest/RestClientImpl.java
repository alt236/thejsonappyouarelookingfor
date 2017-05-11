package uk.co.alt236.thejsonappyouaskedfor.net.rest;

import com.google.gson.Gson;

import uk.co.alt236.thejsonappyouaskedfor.net.api.PhotoApi;

/**
 * Rest Client class
 */
public final class RestClientImpl implements RestClient {

    private final Gson gson;
    private final String apiBase;
    private ApiStore mApiStore;

    public RestClientImpl(final Gson gson,
                          final String apiBase) {
        this.gson = gson;
        this.apiBase = apiBase;
    }

    @Override
    public synchronized PhotoApi getPhotoApi() {
        validateApiStore();
        return mApiStore.getPhotoApi();
    }

    private void validateApiStore() {
        final boolean recreate;

        if (mApiStore == null) {
            recreate = true;
        } else {
            final String currentEndpoint = apiBase;
            final String apiEndpoint = mApiStore.getServerEndpoint();
            recreate = !currentEndpoint.equals(apiEndpoint);
        }

        if (recreate) {
            final String currentEndpoint = apiBase;
            mApiStore = new ApiStore(currentEndpoint, gson);
        }
    }

}