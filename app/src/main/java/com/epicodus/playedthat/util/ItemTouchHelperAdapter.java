package com.epicodus.playedthat.util;

/**
 * Created by Guest on 10/30/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
