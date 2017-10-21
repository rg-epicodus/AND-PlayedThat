package com.epicodus.playedthat.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.playedthat.R;
import com.epicodus.playedthat.adapters.GenrePagerAdapter;
import com.epicodus.playedthat.models.Genre;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by OIG on 10/20/2017.
 */

public class GenreDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private GenrePagerAdapter adapterViewPager;
    ArrayList<Genre> mGenres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_detail);
        ButterKnife.bind(this);

        mGenres = Parcels.unwrap(getIntent().getParcelableExtra("genres"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new GenrePagerAdapter(getSupportFragmentManager(), mGenres);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
