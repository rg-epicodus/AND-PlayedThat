package com.epicodus.playedthat.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.playedthat.Constants;
import com.epicodus.playedthat.R;
import com.epicodus.playedthat.adapters.GameListAdapter;
import com.epicodus.playedthat.models.Game;
import com.epicodus.playedthat.services.APIService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Guest on 10/26/17.
 */

public class ListGamesByNameActivity extends AppCompatActivity {
    public static final String TAG = ListGamesByNameActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private String mRecentGameName;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private GameListAdapter mAdapter;

    public ArrayList<Game> mGames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String gameName = intent.getStringExtra("gameName");

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentGameName = mSharedPreferences.getString(Constants.PREFERENCES_GAMENAME_KEY, null);
//        if (mRecentGameName != null) {
//            getGames(mRecentGameName);
//        }

        getGames(gameName);
    }

    private void getGames(String gameName) {
        final APIService apiService = new APIService();
        apiService.findGames(gameName, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mGames = apiService.processGameResults(response);

                ListGamesByNameActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new GameListAdapter(getApplicationContext(), mGames);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListGamesByNameActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
