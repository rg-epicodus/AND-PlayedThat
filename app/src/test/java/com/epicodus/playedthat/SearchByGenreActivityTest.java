package com.epicodus.playedthat;

import android.os.Build;
import android.widget.TextView;

import com.epicodus.playedthat.ui.SearchByGenreActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class SearchByGenreActivityTest {
    private SearchByGenreActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(SearchByGenreActivity.class);
    }

    @Test
    public void validateTextViewContent() {
        TextView mAppNameTextView = (TextView) activity.findViewById(R.id.appNameTextView);
        assertTrue("Select a Genre".equals(mAppNameTextView.getText().toString()));
    }
}
