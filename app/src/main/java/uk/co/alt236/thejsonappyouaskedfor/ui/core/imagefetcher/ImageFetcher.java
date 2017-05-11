package uk.co.alt236.thejsonappyouaskedfor.ui.core.imagefetcher;

import android.widget.ImageView;

/**
 *
 */
public interface ImageFetcher {
    void loadIntoImageView(final String imagePath, final int placeholder, final ImageView imageView);
}
