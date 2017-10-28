package com.epicodus.playedthat.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.playedthat.Constants;
import com.epicodus.playedthat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 10/26/17.
 */

public class SearchByNameActivity extends AppCompatActivity implements View.OnClickListener{
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedGameNameReference;
    private ValueEventListener mSearchedGameNameReferenceListener;

    @Bind(R.id.findByNameButton) Button mFindByNameButton;
    @Bind(R.id.searchByNameEditText) EditText mSearchByNameEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedGameNameReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_GAMENAME);

        mSearchedGameNameReferenceListener = mSearchedGameNameReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot gameNameSnapshot : dataSnapshot.getChildren()) {
                    String gameName = gameNameSnapshot.getValue().toString();
                    Log.d("GameNames updated", "gameName: " + gameName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbyname);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindByNameButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mFindByNameButton) {
            String gameName = mSearchByNameEditText.getText().toString();
            saveGameNameToFirebase(gameName);

//            if(!(gameName).equals("")) {
//                addToSharedPreferences(gameName);
//            }
            Intent intent = new Intent(SearchByNameActivity.this, ListGamesByNameActivity.class);
            intent.putExtra("gameName", gameName);
            startActivity(intent);
        }
    }

    public void saveGameNameToFirebase(String gameName) {
        mSearchedGameNameReference.push().setValue(gameName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedGameNameReference.removeEventListener(mSearchedGameNameReferenceListener);
    }

//    private void addToSharedPreferences(String gameName) {
//        mEditor.putString(Constants.PREFERENCES_GAMENAME_KEY, gameName).apply();
//    }

}
