package uk.co.alt236.thejsonappyouaskedfor.ui.core.imagefetcher;

import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class PicassoImageFetcher implements ImageFetcher {
    private final Picasso picasso;

    public PicassoImageFetcher(final Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void loadIntoImageView(final String imagePath, final int placeholder, final ImageView imageView) {
        picasso
                .load(imagePath)
                .placeholder(placeholder)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        //NOOP
                    }

                    @Override
                    public void onError() {
                        picasso
                                .load(imagePath)
                                .placeholder(placeholder)
                                .networkPolicy(NetworkPolicy.OFFLINE)
                                .into(imageView);
                    }
                });
    }
}
