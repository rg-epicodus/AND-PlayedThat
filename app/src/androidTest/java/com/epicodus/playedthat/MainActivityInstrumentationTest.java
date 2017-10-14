package com.epicodus.playedthat;

import android.support.test.rule.ActivityTestRule;

import com.epicodus.playedthat.ui.MainActivity;

import org.junit.Rule;

import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

}
