package com.example.recipe;

import com.google.gson.annotations.SerializedName;

public class MealsModel {
    @SerializedName("strMeal")
    private String name;
    @SerializedName("idMeal")
    private  String id;
    @SerializedName("strMealThumb")
    private  String image;
    @SerializedName("strInstructions")
    private String instruc;
    @SerializedName("strYoutube")
    private  String yt;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getInstruc() {
        return instruc;
    }

    public String getYt() {
        return yt;
    }
}
