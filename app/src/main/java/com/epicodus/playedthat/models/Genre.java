package com.epicodus.playedthat.models;

import org.parceler.Parcel;

@Parcel
public class Genre {
    String name;
    String image;
    String deck;
    String genreUrl;

    public Genre() {}

    public Genre(String name, String image, String deck, String genreUrl) {
        this.name = name;
        this.image = getLargeImageUrl(image);
        this.deck = deck;
        this.genreUrl = genreUrl;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDeck() {
        return deck;
    }
    public String getGenreUrl() {
        return genreUrl;
    }

    public String getLargeImageUrl(String image) {
        return image;
    }
}
