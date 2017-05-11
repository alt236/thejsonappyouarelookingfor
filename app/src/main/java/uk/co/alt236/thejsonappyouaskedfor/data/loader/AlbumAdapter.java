package uk.co.alt236.thejsonappyouaskedfor.data.loader;

import java.util.ArrayList;
import java.util.List;

import uk.co.alt236.thejsonappyouaskedfor.data.models.album.UiAlbum;
import uk.co.alt236.thejsonappyouaskedfor.data.models.album.UiAlbumImpl;
import uk.co.alt236.thejsonappyouaskedfor.loader.utils.DataUtils;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;
import uk.co.alt236.thejsonappyouaskedfor.utils.color.ColorUtils;

/**
 * Adapter for creating ui objects of type {@link UiAlbum} form {@link Album}
 */
@SuppressWarnings("MethodMayBeStatic")
/*package*/ final class AlbumAdapter {


    public AlbumAdapter() {
        // nothing to see here
    }

    public List<UiAlbum> createUiAlbumList(final List<Album> albums) {

        final List<UiAlbum> uiAlbums = new ArrayList<>();

        for (final Album album : albums) {
            uiAlbums.add(createUiAlbum(album));
        }
        return uiAlbums;
    }

    public UiAlbum createUiAlbum(final Album album) {
        return UiAlbumImpl.newBuilder()
                .withTitle(getTitle(album))
                .withSubtitle(album.getTitle())
                .withImageUrl(album.getThumbnailUrl())
                .withLink(album.getUrl())
                .build();
    }

    protected String getTitle(final Album album) {
        final String hex = DataUtils.getLastPathSegment(album.getUrl());
        return ColorUtils.getColorNameFromHex(hex);
    }

}
