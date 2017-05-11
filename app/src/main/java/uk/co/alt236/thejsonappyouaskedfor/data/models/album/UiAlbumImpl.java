package uk.co.alt236.thejsonappyouaskedfor.data.models.album;

import android.os.Parcel;

/**
 *
 */
public class UiAlbumImpl implements UiAlbum {


    private final String mTitle;
    private final String mSubtitle;
    private final String mImageUrl;
    private final String mLink;


    private UiAlbumImpl(final Builder builder) {
        mImageUrl = builder.mImageUrl;
        mTitle = builder.mTitle;
        mSubtitle = builder.mSubtitle;
        mLink = builder.mLink;
    }

    protected UiAlbumImpl(final Parcel in) {
        this.mTitle = in.readString();
        this.mSubtitle = in.readString();
        this.mImageUrl = in.readString();
        this.mLink = in.readString();
    }

    @Override
    public String getImageUrl() {
        return mImageUrl;
    }

    @Override
    public String getSubtitle() {
        return mSubtitle;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mSubtitle);
        dest.writeString(this.mImageUrl);
        dest.writeString(this.mLink);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final UiAlbumImpl copy) {
        final Builder builder = new Builder();
        builder.mImageUrl = copy.mImageUrl;
        builder.mTitle = copy.mTitle;
        builder.mSubtitle = copy.mSubtitle;
        builder.mLink = copy.mLink;
        return builder;
    }

    public static final class Builder {
        private String mImageUrl;
        private String mTitle;
        private String mSubtitle;
        private String mLink;

        private Builder() {
        }

        public Builder withImageUrl(final String val) {
            this.mImageUrl = val;
            return this;
        }

        public Builder withTitle(final String val) {
            this.mTitle = val;
            return this;
        }

        public Builder withSubtitle(final String val) {
            this.mSubtitle = val;
            return this;
        }

        public Builder withLink(final String val) {
            mLink = val;
            return this;
        }

        public UiAlbum build() {
            return new UiAlbumImpl(this);
        }

    }
}
