package com.epicodus.playedthat.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.playedthat.R;
import com.epicodus.playedthat.models.Genre;
import com.epicodus.playedthat.ui.GenreDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 10/19/17.
 */

public class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.GenreViewHolder> {
    private ArrayList<Genre> mGenres = new ArrayList<>();
    private Context mContext;

    public GenreListAdapter(Context context, ArrayList<Genre> genres) {
        mContext = context;
        mGenres = genres;
    }

    @Override
    public GenreListAdapter.GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_list_item, parent, false);
        GenreViewHolder viewHolder = new GenreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GenreListAdapter.GenreViewHolder holder, int position) {
        holder.bindGenre(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }


    public class GenreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.genreImageView) ImageView mGenreImageView;
        @Bind(R.id.genreNameTextView) TextView mGenreNameTextView;
//        @Bind(R.id.genreDeckTextView) TextView mGenreDeckTextView;
//        @Bind(R.id.genreUrlTextView) TextView mGenreUrlTextView;


        private Context mContext;

        public GenreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindGenre(Genre genre) {
            Picasso.with(mContext).load(genre.getImage()).into(mGenreImageView);
            mGenreNameTextView.setText(genre.getName());
//            mGenreDeckTextView.setText(genre.getDeck());
//            mGenreUrlTextView.setText(genre.getGenreUrl());


        }

        @Override
        public void onClick(View v) {
            Log.d("click listener", "working!");
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GenreDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("genres", Parcels.wrap(mGenres));
            mContext.startActivity(intent);
        }
    }
}
