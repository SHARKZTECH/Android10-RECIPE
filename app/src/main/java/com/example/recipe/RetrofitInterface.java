package com.example.recipe;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("filter.php?i")
    Call<Meals> excuteItems();

}
