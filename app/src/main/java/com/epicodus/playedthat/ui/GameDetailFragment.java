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
import android.widget.Toast;

import com.epicodus.playedthat.Constants;
import com.epicodus.playedthat.R;
import com.epicodus.playedthat.models.Game;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailFragment extends Fragment implements View.OnClickListener  {
    private static final int MAX_WIDTH = 522;
    private static final int MAX_HEIGHT = 640;
    @Bind(R.id.gameImageView) ImageView mGameImageLabel;
    @Bind(R.id.gameNameTextView) TextView mGameNameLabel;
    @Bind(R.id.gameDeckTextView) TextView mGameDeckLabel;
    @Bind(R.id.gameUrlTextView) TextView mGameUrlLabel;
    @Bind(R.id.saveGameButton) TextView mSaveGameButton;

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
        mGameUrlLabel.setText(mGame.getGameUrl());


//        mGameUrlLabel.setOnClickListener(this);
        mSaveGameButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
//        if (v == mGameUrlLabel) {
//            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mGame.getGameUrl()));
//            startActivity(webIntent);
//        }

        if (v == mSaveGameButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid= user.getUid();

            DatabaseReference gameRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_GAMES)
                    .child(uid);

            DatabaseReference pushRef= gameRef.push();
            String pushId = pushRef.getKey();
            mGame.setPushId(pushId);
            gameRef.push().setValue(mGame);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}
