package com.epicodus.playedthat.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.epicodus.playedthat.Constants;
import com.epicodus.playedthat.R;
import com.epicodus.playedthat.adapters.FirebaseGameListAdapter;
import com.epicodus.playedthat.adapters.FirebaseGameViewHolder;
import com.epicodus.playedthat.models.Game;
import com.epicodus.playedthat.util.OnStartDragListener;
import com.epicodus.playedthat.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedGameListActivity extends AppCompatActivity implements OnStartDragListener {
    public static final String TAG = LoginActivity.class.getSimpleName();
    private DatabaseReference mGameReference;
    private FirebaseGameListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gamelist);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: in it");
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Log.d(TAG, "setUpFirebaseAdapter: in it");
        Log.d(TAG, "setUpFirebaseAdapter: " + uid);


        mGameReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_GAMES)
                .child(uid);
        Log.d(TAG, "setUpFirebaseAdapter: " + mGameReference);

        mFirebaseAdapter = new FirebaseGameListAdapter(Game.class,
                R.layout.game_list_item_drag, FirebaseGameViewHolder.class,
                mGameReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper= new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
