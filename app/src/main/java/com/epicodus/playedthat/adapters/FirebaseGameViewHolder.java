package com.epicodus.playedthat.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.playedthat.Constants;
import com.epicodus.playedthat.R;
import com.epicodus.playedthat.models.Game;
import com.epicodus.playedthat.ui.GameDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by OIG on 10/28/2017.
 */

public class FirebaseGameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mGameImageView;

    public FirebaseGameViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindGame(Game game) {
        mGameImageView = (ImageView) mView.findViewById(R.id.gameImageView);
        TextView gameNameTextView = (TextView) mView.findViewById(R.id.gameNameTextView);
        TextView gameDeckTextView = (TextView) mView.findViewById(R.id.gameDeckTextView);

        Picasso.with(mContext)
                .load(game.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mGameImageView);

        gameNameTextView.setText(game.getName());
        gameDeckTextView.setText(game.getDeck());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Game> games = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GAMES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    games.add(snapshot.getValue(Game.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, GameDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("games", Parcels.wrap(games));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
