package uk.co.alt236.thejsonappyouaskedfor.di;

import javax.inject.Singleton;

import dagger.Component;
import uk.co.alt236.optional.debug.BaseJsonViewFragment;
import uk.co.alt236.optional.debug.JsonAlbumFragment;
import uk.co.alt236.thejsonappyouaskedfor.ui.components.home.HomeFragment;

@Singleton
@Component(modules = {AndroidAwareModule.class, CommonModule.class})
public interface AndroidAwareComponent {
    void inject(HomeFragment target);

    void inject(JsonAlbumFragment target);

    void inject(BaseJsonViewFragment target);
}
