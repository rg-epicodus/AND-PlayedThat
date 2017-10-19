package com.epicodus.playedthat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.playedthat.R;
import com.epicodus.playedthat.adapters.GenreListAdapter;
import com.epicodus.playedthat.models.Genre;
import com.epicodus.playedthat.services.APIService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class SearchByGenreActivity extends AppCompatActivity {
    public static final String TAG = SearchByGenreActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private GenreListAdapter mAdapter;

    public ArrayList<Genre> genres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbygenre);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String genre = intent.getStringExtra("genre");

        getGenres(genre);

    }

    private void getGenres(String genre) {
        final APIService apiService = new APIService();
        apiService.findGenres(genre, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                genres = apiService.processResults(response);
                SearchByGenreActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new GenreListAdapter(getApplicationContext(), genres);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(SearchByGenreActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);                    }
                });
            }

        });
    }

    // validating email id
    private boolean isValidEmail(String userEmail) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(userEmail);
        return matcher.matches();
    }

}
