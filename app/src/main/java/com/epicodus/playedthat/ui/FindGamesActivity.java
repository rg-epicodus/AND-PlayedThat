package com.epicodus.playedthat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.epicodus.playedthat.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindGamesActivity extends AppCompatActivity {
    @Bind(R.id.searchByGenreButton) Button mSearchByGenreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ButterKnife.bind(this);

        mSearchByGenreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindGamesActivity.this, SearchByGenreActivity.class);
                startActivity(intent);
            }
        });

    }
}

