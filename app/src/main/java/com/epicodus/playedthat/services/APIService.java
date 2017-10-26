package com.epicodus.playedthat.services;

import com.epicodus.playedthat.Constants;
import com.epicodus.playedthat.models.Game;
import com.epicodus.playedthat.models.Genre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 10/19/17.
 */

public class APIService {

    public static void findGenres(String genre, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(7, TimeUnit.SECONDS)
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_KEY_PARAMETER, Constants.API_KEY);
        urlBuilder.addQueryParameter(Constants.FIELD_LIST_QUERY_PARAMETER, "name,image,deck,site_detail_url");
        urlBuilder.addQueryParameter(Constants.FORMAT_JSON_PARAMETER, "json");
        String url = urlBuilder.build().toString();
        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Genre> processResults(Response response) {
        ArrayList<Genre> genres = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject apiJSON = new JSONObject(jsonData);
            JSONArray resultsJSON = apiJSON.getJSONArray("results");
            for (int i = 0; i < resultsJSON.length(); i++) {
                JSONObject genreJSON = resultsJSON.getJSONObject(i);
                String name = genreJSON.getString("name");
                String image = genreJSON.getJSONObject("image").getString("small_url");
                String deck = genreJSON.optString("deck");
                String genreUrl = genreJSON.optString("site_detail_url");

                Genre genre = new Genre(name, image, deck, genreUrl);
                genres.add(genre);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return genres;
    }

    public static void findGames(String game, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GAME_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_KEY_PARAMETER, Constants.API_KEY);
        urlBuilder.addQueryParameter(Constants.FIELD_LIST_QUERY_PARAMETER, "name,image,deck,site_detail_url");
        urlBuilder.addQueryParameter(Constants.FORMAT_JSON_PARAMETER, "json");
        urlBuilder.addQueryParameter(Constants.FILTER_PARAMETER, "name:" + game);

        String url = urlBuilder.build().toString();
        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Game> processGameResults(Response response) {
        ArrayList<Game> games = new ArrayList<>();

        try {
            String jsonGameData = response.body().string();
            JSONObject apiJSON = new JSONObject(jsonGameData);
            JSONArray resultsJSON = apiJSON.getJSONArray("results");
            for (int i = 0; i < resultsJSON.length(); i++) {
                JSONObject gameJSON = resultsJSON.getJSONObject(i);
                String name = gameJSON.getString("name");
                String image = gameJSON.getJSONObject("image").getString("small_url");
                String deck = gameJSON.optString("deck");
                String gameUrl = gameJSON.optString("site_detail_url");

                Game game = new Game(name, image, deck, gameUrl);
                games.add(game);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return games;
    }


}
