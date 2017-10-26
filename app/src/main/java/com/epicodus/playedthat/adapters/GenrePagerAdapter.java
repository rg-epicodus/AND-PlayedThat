package com.epicodus.playedthat.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.playedthat.models.Genre;
import com.epicodus.playedthat.ui.GenreDetailFragment;

import java.util.ArrayList;


public class GenrePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Genre> mGenres;

    public GenrePagerAdapter(FragmentManager fm, ArrayList<Genre> genres) {
        super(fm);
        mGenres = genres;
    }

    @Override
    public Fragment getItem(int position) {
        return GenreDetailFragment.newInstance(mGenres.get(position));
    }

    @Override
    public int getCount() {
        return mGenres.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGenres.get(position).getName();
    }
}
