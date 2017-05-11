package uk.co.alt236.optional.debug;

import android.os.Bundle;

import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.fragment.BaseFragment;

/**
 *
 */
public class JsonAlbumFragment extends BaseFragment {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        throw new IllegalStateException("You should NOT be here");
    }
}
