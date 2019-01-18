package com.example.bigbo.revvversi;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.bigbo.revvversi.ActivityMenu.forInstrumentalTest;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InstrumentalGameTest {
    @Rule
    public ActivityTestRule<ActivityMenu> mActivityRule
            = new ActivityTestRule<>(ActivityMenu.class);

    @Test
    public void mainTest(){

        forInstrumentalTest = false;

        Espresso.onView(ViewMatchers.withId(R.id.startFirstScreen))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.textView4))
                .check(ViewAssertions.matches(ViewMatchers.withText("2")));

        Espresso.onView(ViewMatchers.withId(R.id.textView5))
                .check(ViewAssertions.matches(ViewMatchers.withText("2")));

        Espresso.onView(ViewMatchers.withId(R.id.textView6))
                .check(ViewAssertions.matches(ViewMatchers.withText("black")));

        Espresso.onView(ViewMatchers.withId(R.id.main_l))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.backGameScreen))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.pressBack();

        Espresso.onView(ViewMatchers.withId(R.id.optionsFirstScreen))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.wallpaper1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.wallpaper2))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.wallpaper3))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.backOptionsScreen))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.pressBack();

        Espresso.onView(ViewMatchers.withId(R.id.aboutFirstScreen))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.backAboutScreen))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        forInstrumentalTest = true;

    }

}