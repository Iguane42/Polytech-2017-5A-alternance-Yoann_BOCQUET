package com.bocquet.yoann.tp1.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Epulapp on 30/11/2017.
 */

public interface IBeerAPI {
    @GET("beers")
    Call<List<Beer>> listBeers(@Query("page") int page, @Query("per_page") int perPage);
}
