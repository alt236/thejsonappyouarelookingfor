package uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders;

import java.util.List;

import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonCallback;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;

public abstract class Loader {
    protected abstract void getPhotos(CommonCallback<List<Album>> masterCallback);
}
