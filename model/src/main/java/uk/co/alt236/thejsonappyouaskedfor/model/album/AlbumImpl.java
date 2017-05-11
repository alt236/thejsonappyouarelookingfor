package uk.co.alt236.thejsonappyouaskedfor.model.album;

import com.google.gson.annotations.SerializedName;

public class AlbumImpl implements Album {

    @SerializedName("albumId")
    private Integer albumId;
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    private AlbumImpl() {

    }

    private AlbumImpl(final Builder builder) {
        albumId = builder.albumId;
        id = builder.id;
        title = builder.title;
        url = builder.url;
        thumbnailUrl = builder.thumbnailUrl;
    }

    @Override
    public Integer getAlbumId() {
        return albumId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "AlbumImpl{" +
                "albumId=" + albumId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final AlbumImpl copy) {
        final Builder builder = new Builder();
        builder.albumId = copy.albumId;
        builder.id = copy.id;
        builder.title = copy.title;
        builder.url = copy.url;
        builder.thumbnailUrl = copy.thumbnailUrl;
        return builder;
    }

    public static final class Builder {
        private Integer albumId;
        private Integer id;
        private String title;
        private String url;
        private String thumbnailUrl;

        private Builder() {
        }

        public Builder withAlbumId(final Integer val) {
            albumId = val;
            return this;
        }

        public Builder withId(final Integer val) {
            id = val;
            return this;
        }

        public Builder withTitle(final String val) {
            title = val;
            return this;
        }

        public Builder withUrl(final String val) {
            url = val;
            return this;
        }

        public Builder withThumbnailUrl(final String val) {
            thumbnailUrl = val;
            return this;
        }

        public Album build() {
            return new AlbumImpl(this);
        }
    }
}