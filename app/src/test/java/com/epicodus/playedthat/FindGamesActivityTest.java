package com.epicodus.playedthat;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import com.epicodus.playedthat.ui.FindGamesActivity;
import com.epicodus.playedthat.ui.GenreListActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class FindGamesActivityTest {
    private FindGamesActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(FindGamesActivity.class);
    }

    @Test
    public void validateTextViewContent() {
        TextView mAppNameTextView = (TextView) activity.findViewById(R.id.appNameTextView);
        assertTrue("Search Games By:".equals(mAppNameTextView.getText().toString()));
    }

    @Test
    public void thirdActivityStarted() {
        activity.findViewById(R.id.searchByGenreButton).performClick();
        Intent expectedIntent = new Intent(activity, GenreListActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

}
