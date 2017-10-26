package com.epicodus.playedthat.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.playedthat.R;
import com.epicodus.playedthat.models.Genre;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GenreDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 180;
    @Bind(R.id.genreImageView) ImageView mGenreImageLabel;
    @Bind(R.id.genreNameTextView) TextView mGenreNameLabel;
    @Bind(R.id.genreDeckTextView) TextView mGenreDeckLabel;
    @Bind(R.id.genreUrlTextView) TextView mGenreUrlLabel;

    private Genre mGenre;

    public static GenreDetailFragment newInstance(Genre genre) {
        GenreDetailFragment genreDetailFragment = new GenreDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("genre", Parcels.wrap(genre));
        genreDetailFragment.setArguments(args);
        return genreDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGenre = Parcels.unwrap(getArguments().getParcelable("genre"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mGenre.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mGenreImageLabel);
        mGenreNameLabel.setText(mGenre.getName());
        if (mGenre.getDeck().equals("null")) {
            mGenreDeckLabel.setText("Description not available");
        } else {
            mGenreDeckLabel.setText(mGenre.getDeck());
            mGenreDeckLabel.setVisibility(View.VISIBLE);
        }

        mGenreUrlLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mGenreUrlLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mGenre.getGenreUrl()));
            startActivity(webIntent);
        }

    }

}
