package com.epicodus.playedthat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.playedthat.R;
import com.epicodus.playedthat.adapters.GenreArrayAdapter;
import com.epicodus.playedthat.services.APIService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by OIG on 10/13/2017.
 */

public class GenreActionActivity extends AppCompatActivity {

    @Bind(R.id.listView) ListView mListView;
//    @Bind(R.id.actionGenreTextView) TextView mActionGenreTextView;
//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//    private GameListAdapter mAdapter;

    private String[] gameName = new String[] {"Day Z", "Airships", "EverQuest", "Assassin's Creed 14", "Shadowrun", "MetalGear Solid 5" };
    private String[] numPlayers = new String[] {"Single Player", "Multi-Player", "Multi-Player", "Multi-Player", "Single Player, Multi-Player", "Single Player"};
    private String[] theme = new String[] {"Zombie", "Steampunk", "Fantasy", "Espionage", "Cyberpunk", "Espionage"};
    private String[] platform = new String[] {"PC/PS4/XBONE", "PC/PS4/XBONE", "PC", "PC", "PC/PS4/XBONE", "PC"};
    private String[] firstReleaseDate = new String[] {"January 1, 2012", "December 16, 2002", "June 13, 2014", "February 14, 1998", "March 1, 2009", "April 1, 2017"};
    private String[] rating = new String[] {"9.9", "7.3", "4", "8", "9", "9"};
    private String[] ESRB = new String[] {"T", "T", "T", "T", "M", "T"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genreaction);
        ButterKnife.bind(this);


        GenreArrayAdapter adapter = new GenreArrayAdapter(this, android.R.layout.simple_list_item_1, gameName, numPlayers, theme, platform, firstReleaseDate, rating, ESRB);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String action = ((TextView)view).getText().toString();
                Log.d("GenreActionActivity", "In the onItemClickListener!");
                Toast.makeText(GenreActionActivity.this, action, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");

//        mActionGenreTextView.setText("Thank you for your Email of: " + userEmail);
    }


}