package com.epicodus.playedthat.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by OIG on 10/13/2017.
 */

public class GenreArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mGameName;
    private String[] mNumPlayers;
    private String[] mTheme;
    private String[] mPlatform;
    private String[] mFirstReleaseDate;
    private String[] mRating;
    private String[] mESRB;


    public GenreArrayAdapter(Context mContext, int resource, String[] mGameName, String[] mNumPlayers, String[] mTheme, String[] mPlatform, String[] mFirstReleaseDate, String[] mRating, String[] mESRB ) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mGameName = mGameName;
        this.mNumPlayers = mNumPlayers;
        this.mTheme = mTheme;
        this.mPlatform = mPlatform;
        this.mFirstReleaseDate = mFirstReleaseDate;
        this.mRating = mRating;
        this.mESRB = mESRB;

    }

    @Override
    public Object getItem(int position) {
        String gameName = mGameName[position];
        String numPlayers = mNumPlayers[position];
        String theme = mTheme[position];
        String platform = mPlatform[position];
        String firstReleaseDate = mFirstReleaseDate[position];
        String rating = mRating[position];
        String ESRB = mESRB[position];

        return String.format("%s \nPlayers: %s \nGame Theme: %s \nPlatform: %s \nFirst Released: %s \nUser Rating: %s \nESRB Rating: %s ", gameName, numPlayers, theme, platform, firstReleaseDate, rating, ESRB);
    }

    @Override
    public int getCount() {
        return mGameName.length;
    }
}
