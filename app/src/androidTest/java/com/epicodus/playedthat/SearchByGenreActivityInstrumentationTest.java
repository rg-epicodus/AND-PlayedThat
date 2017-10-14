package com.epicodus.playedthat;

import android.support.test.rule.ActivityTestRule;

import com.epicodus.playedthat.ui.MainActivity;
import com.epicodus.playedthat.ui.SearchByGenreActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class SearchByGenreActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<SearchByGenreActivity> activityTestRule =
            new ActivityTestRule<>(SearchByGenreActivity.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.userEmailEditText)).perform(typeText("test@epicodus.com"))
                .check(matches(withText("test@epicodus.com")));
    }
}
