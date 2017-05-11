package uk.co.alt236.thejsonappyouaskedfor.net.rest;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
/*package*/ final class RestAdapterFactory {

    private RestAdapterFactory() {
    }

    public static Retrofit createDefault(final String endpoint,
                                         final Gson gson) {

        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(endpoint);
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.client(getClient());

        return builder.build();
    }


    private static OkHttpClient getClient() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        return builder.build();
    }

}
