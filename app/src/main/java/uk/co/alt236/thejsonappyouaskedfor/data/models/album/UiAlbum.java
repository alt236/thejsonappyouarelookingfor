package uk.co.alt236.thejsonappyouaskedfor.data.models.album;

import android.os.Parcel;
import android.os.Parcelable;

import uk.co.alt236.thejsonappyouaskedfor.data.models.base.BaseUiModel;

/**
 *
 */
public interface UiAlbum extends BaseUiModel {
    Parcelable.Creator<UiAlbum> CREATOR = new Parcelable.Creator<UiAlbum>() {
        @Override
        public UiAlbum createFromParcel(final Parcel source) {
            return new UiAlbumImpl(source);
        }

        @Override
        public UiAlbum[] newArray(final int size) {
            return new UiAlbum[size];
        }
    };

    String getImageUrl();

    String getSubtitle();

    String getTitle();

    String getLink();
}
