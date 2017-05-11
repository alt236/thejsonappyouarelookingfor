package uk.co.alt236.thejsonappyouaskedfor.di;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.orhanobut.hawk.LogInterceptor;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import uk.co.alt236.thejsonappyouaskedfor.BuildConfig;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders.DataProvider;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders.LoaderNetwork;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders.LoaderStorage;
import uk.co.alt236.thejsonappyouaskedfor.net.rest.RestClient;
import uk.co.alt236.thejsonappyouaskedfor.storage.DataStorage;
import uk.co.alt236.thejsonappyouaskedfor.storage.kv.KVStore;
import uk.co.alt236.thejsonappyouaskedfor.storage.kv.hawk.HawkStore;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.imagefetcher.ImageFetcher;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.imagefetcher.PicassoImageFetcher;
import uk.co.alt236.thejsonappyouaskedfor.utils.AppLog;

@Module(includes = CommonModule.class)
public class AndroidAwareModule {
    private static final String PICASSO_CACHE_DIR = "picasso";
    private static final int PICASSO_CACHE_SIZE = 1000000;

    private final Context context;

    public AndroidAwareModule(Application application) {
        this.context = application;
    }

    @Provides
    @Singleton
    DataProvider providesDataProvider(final LoaderNetwork loaderNetwork,
                                      final LoaderStorage loaderStorage) {
        return new DataProvider(loaderNetwork, loaderStorage);
    }

    @Provides
    @Singleton
    LoaderNetwork providesLoaderNetwork(final RestClient restClient) {
        return new LoaderNetwork(restClient);
    }

    @Provides
    @Singleton
    LoaderStorage providesLoaderStorage(final Gson gson) {
        final KVStore kvStore = createKvStore(gson);

        final DataStorage storage = new DataStorage(kvStore);
        return new LoaderStorage(storage);
    }

    private KVStore createKvStore(final Gson gson) {
        final LogInterceptor logInterceptor = new LogInterceptor() {
            @Override
            public void onLog(final String message) {
                AppLog.d(message);
            }
        };

        return HawkStore.create(context, BuildConfig.DEBUG, logInterceptor, gson);
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    ImageFetcher providesImageFetcher(final Context context) {
        final Cache cache = new Cache(
                new File(context.getCacheDir(), PICASSO_CACHE_DIR),
                PICASSO_CACHE_SIZE);

        final OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();


        final Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .indicatorsEnabled(BuildConfig.DEBUG)
                .build();

        return new PicassoImageFetcher(picasso);
    }
}
