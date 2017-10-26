package com.epicodus.playedthat.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.playedthat.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 10/26/17.
 */

public class SearchByNameActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.findByNameButton) Button mFindByNameButton;
    @Bind(R.id.searchByNameEditText) EditText mSearchByNameEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbyname);
        ButterKnife.bind(this);


        mFindByNameButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mFindByNameButton) {
            String gameName = mSearchByNameEditText.getText().toString();
            Intent intent = new Intent(SearchByNameActivity.this, ListGamesByNameActivity.class);
            intent.putExtra("gameName", gameName);
            startActivity(intent);
        }
    }

}
