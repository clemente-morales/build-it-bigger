package com.udacity.gradle.builditbigger;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by moracl6 on 7/7/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class JokesTest {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickAddJokeButton_opensJokeUi() throws Exception {
        // Click on the get joke button
        onView(withId(R.id.jokeButton)).perform(click());

        // Check if the joke is displayed
        onView(withId(R.id.jokeTextView)).check(matches(isDisplayed()));
    }
}
