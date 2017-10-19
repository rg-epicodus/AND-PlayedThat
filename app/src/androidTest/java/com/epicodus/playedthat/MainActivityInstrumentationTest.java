package com.epicodus.playedthat;

import android.support.test.rule.ActivityTestRule;

import com.epicodus.playedthat.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateButton(){
        onView(withId(R.id.findGamesButton)).perform(click());
        onView(withId(R.id.searchByGenreButton)).perform(click());
    }
}
