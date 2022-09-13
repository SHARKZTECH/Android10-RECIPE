package com.example.recipe;

public class MyModel {
    private String title;
    private int image;

    public MyModel(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
