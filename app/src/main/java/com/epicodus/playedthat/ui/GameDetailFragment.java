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
import com.epicodus.playedthat.models.Game;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailFragment extends Fragment  {
    private static final int MAX_WIDTH = 522;
    private static final int MAX_HEIGHT = 640;
    @Bind(R.id.gameImageView) ImageView mGameImageLabel;
    @Bind(R.id.gameNameTextView) TextView mGameNameLabel;
    @Bind(R.id.gameDeckTextView) TextView mGameDeckLabel;
    @Bind(R.id.gameUrlTextView) TextView mGameUrlLabel;

    private Game mGame;

    public static GameDetailFragment newInstance(Game game) {
        GameDetailFragment gameDetailFragment = new GameDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("game", Parcels.wrap(game));
        gameDetailFragment.setArguments(args);
        return gameDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGame = Parcels.unwrap(getArguments().getParcelable("game"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mGame.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mGameImageLabel);
        mGameNameLabel.setText(mGame.getName());
        if (mGame.getDeck().equals("null")) {
            mGameDeckLabel.setText("Description not available");
        } else {
            mGameDeckLabel.setText(mGame.getDeck());
            mGameDeckLabel.setVisibility(View.VISIBLE);
        }
//        mGameDeckLabel.setText(mGame.getDeck());
//        mGameUrlLabel.setOnClickListener(this);
        mGameUrlLabel.setText(mGame.getGameUrl());

        return view;
    }

//    @Override
//    public void onClick(View v) {
//        if (v == mGameUrlLabel) {
//            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mGame.getGameUrl()));
//            startActivity(webIntent);
//        }
//
//    }
}
