package com.epicodus.playedthat.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 10/26/17.
 */

@Parcel
public class Game {
    String name;
    String image;
    String deck;
    String gameUrl;
    private String pushId;
    String index;


    public Game() {}

    public Game(String name, String image, String deck, String gameUrl) {
        this.name = name;
        this.image = image;
        this.deck = deck;
        this.gameUrl = gameUrl;
        this.index = "not_specified";
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

    public String getGameUrl() {
        return gameUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
