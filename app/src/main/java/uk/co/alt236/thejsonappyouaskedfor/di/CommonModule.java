package uk.co.alt236.thejsonappyouaskedfor.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.lang.reflect.Modifier;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.alt236.thejsonappyouaskedfor.BuildConfig;
import uk.co.alt236.thejsonappyouaskedfor.data.gson.TypeAdapterFactoryCreator;
import uk.co.alt236.thejsonappyouaskedfor.net.rest.RestClient;
import uk.co.alt236.thejsonappyouaskedfor.net.rest.RestClientImpl;

@Module
public class CommonModule {

    @Provides
    @Singleton
    RestClient providesRestClient(final Gson gson) {
        return new RestClientImpl(gson, BuildConfig.API_ENDPOINT_STRING);
    }

    @Provides
    @Singleton
    Gson providesGson() {
        final GsonBuilder retVal = new GsonBuilder();
        retVal.serializeNulls();
        retVal.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE);

        // register the type adapter factories
        final TypeAdapterFactoryCreator creator = new TypeAdapterFactoryCreator();
        for (final TypeAdapterFactory factory : creator.getAdapters()) {
            retVal.registerTypeAdapterFactory(factory);
        }

        return retVal.create();
    }
}
