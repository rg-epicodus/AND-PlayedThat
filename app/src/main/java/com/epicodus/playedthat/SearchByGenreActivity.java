package com.epicodus.playedthat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SearchByGenreActivity extends AppCompatActivity {
    @Bind(R.id.userEmailEditText) EditText mUserEmailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbygenre);
        ButterKnife.bind(this);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                final String userEmail = mUserEmailEditText.getText().toString();
                if (isValidEmail(userEmail)) {
                    Intent intent = new Intent(SearchByGenreActivity.this, GenreActionActivity.class);
                    intent.putExtra("userEmail", userEmail);
                    startActivity(intent);
                } else {
                    mUserEmailEditText.setError("Invalid Email");
                }
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
