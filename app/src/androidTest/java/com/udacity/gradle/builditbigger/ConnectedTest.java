package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class ConnectedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void verifyBackendResponseString() {

        // click the "Tell Joke" button in the main activity fragment
        onView(withId(R.id.b_tell_joke)).perform(click());

        // this triggers an asynctask, which espresso waits to finish by default

        // verify that that the asynctask successfully retrieves a non-empty string
        onView(withId(R.id.tv_display_joke)).check(matches(not(withText(""))));
    }
}
