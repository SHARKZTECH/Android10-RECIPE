package com.example.recipe;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("filter.php?i")
    Call<MyModel> excuteItems();

}
