package uk.co.alt236.thejsonappyouaskedfor.net.api;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;

/**
 *
 */
public interface PhotoApi {
    @Headers({
            "Accept: application/json",
    })

    @GET("photos")
    Call<List<Album>> getPhotos(@Query("_limit") String limit);

}
