package com.epicodus.playedthat.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.playedthat.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.findGamesButton) Button mFindGamesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface rubikFont = Typeface.createFromAsset(getAssets(), "fonts/rubikregular.ttf");
        mAppNameTextView.setTypeface(rubikFont);

        mFindGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            Toast.makeText(MainActivity.this, "Find Games Clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, FindGamesActivity.class);
            startActivity(intent);
            }
        });

    }
}
