package com.epicodus.playedthat.services;

import android.util.Log;

import com.epicodus.playedthat.BuildConfig;
import com.epicodus.playedthat.Constants;
import com.epicodus.playedthat.models.Genre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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

    public static void findGenres(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
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

}
