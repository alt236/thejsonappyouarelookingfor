package uk.co.alt236.thejsonappyouaskedfor.di;

import android.app.Application;

public class ComponentStore {

    private final AndroidAwareComponent androidAwareComponent;

    public ComponentStore(final Application context) {
        final AndroidAwareModule androidAwareModule = new AndroidAwareModule(context);
        androidAwareComponent = DaggerAndroidAwareComponent.builder()
                .androidAwareModule(androidAwareModule)
                .build();
    }

    public AndroidAwareComponent getAndroidAwareComponent() {
        return androidAwareComponent;
    }
}
