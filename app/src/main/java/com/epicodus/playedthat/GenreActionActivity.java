package com.epicodus.playedthat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by OIG on 10/13/2017.
 */

public class GenreActionActivity extends AppCompatActivity {
    @Bind(R.id.listView) ListView mListView;

    private String[] actionGenre = new String[] {"Action Genre Game 1", "Action Genre Game 2", "Action Genre Game 3", "Action Genre Game 4", "Action Genre Game 5", "Action Genre Game 6" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genreaction);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.custom_textwhite, actionGenre);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String drink = ((TextView)view).getText().toString();
                Log.d("GenreActionActivity", "In the onItemClickListener!");
                Toast.makeText(GenreActionActivity.this, drink, Toast.LENGTH_LONG).show();
            }
        });

    }
}