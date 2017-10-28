package com.epicodus.playedthat.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.playedthat.models.Game;
import com.epicodus.playedthat.ui.GameDetailFragment;
import android.support.v4.app.Fragment;
import java.util.ArrayList;


public class GamePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Game> mGames;

    public GamePagerAdapter(FragmentManager fm, ArrayList<Game> games) {
        super(fm);
        mGames = games;
    }

    @Override
    public Fragment getItem(int position) {
        return GameDetailFragment.newInstance(mGames.get(position));
    }

    @Override
    public int getCount() {
        return mGames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGames.get(position).getName();
    }
}
