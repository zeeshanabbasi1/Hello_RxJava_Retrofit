package mzta.com.hello_rxjava_retrofit.api;

import java.util.List;

import mzta.com.hello_rxjava_retrofit.model.Post;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Zee Abbasi on 1/23/2017.
 */

public interface JsonPlaceHolderApi {

    @GET("posts")
    Observable<List<Post>> getPosts();
}
