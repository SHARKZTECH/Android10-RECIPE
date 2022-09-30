package com.example.recipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("filter.php?i")
    Call<Meals> excuteItems();

    @GET("lookup.php")
    Call<Meals> excuteItem(@Query("i") String id);

}
