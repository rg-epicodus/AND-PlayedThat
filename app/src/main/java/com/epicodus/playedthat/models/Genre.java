package com.epicodus.playedthat.models;

/**
 * Created by Guest on 10/19/17.
 */

public class Genre {
    private String name;
    private String image;

    public Genre(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
