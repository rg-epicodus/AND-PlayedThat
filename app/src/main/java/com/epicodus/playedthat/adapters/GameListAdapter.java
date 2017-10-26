package com.epicodus.playedthat.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.playedthat.R;
import com.epicodus.playedthat.models.Game;
import com.epicodus.playedthat.ui.GameDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 10/26/17.
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {
    private static final int MAX_WIDTH = 130;
    private static final int MAX_HEIGHT = 100;
    private ArrayList<Game> mGames = new ArrayList<>();
    private Context mContext;

    public GameListAdapter(Context context, ArrayList<Game> games) {
        mContext = context;
        mGames = games;
    }

    @Override
    public GameListAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        GameViewHolder viewHolder = new GameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameListAdapter.GameViewHolder holder, int position) {
        holder.bindGame(mGames.get(position));
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }


    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        @Bind(R.id.gameImageView) ImageView mGameImageView;
        @Bind(R.id.gameNameTextView) TextView mGameNameTextView;
        @Bind(R.id.gameDeckTextView) TextView mGameDeckTextView;
//        @Bind(R.id.gameUrlTextView) TextView mGameUrlTextView;


        private Context mContext;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindGame(Game game) {
//            Picasso.with(mContext)
//                    .load(game.getImage())
//                    .resize(MAX_WIDTH, MAX_HEIGHT)
//                    .centerCrop()
//                    .into(mGameImageView);
            mGameNameTextView.setText(game.getName());
            mGameDeckTextView.setText(game.getDeck());
//            mGameUrlTextView.setText(game.getGameUrl());


        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GameDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("games", Parcels.wrap(mGames));
            mContext.startActivity(intent);
        }
    }
}
