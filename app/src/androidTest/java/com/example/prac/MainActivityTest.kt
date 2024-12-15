package com.example.prac
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testInputCoordinatesAndDrawLine() {

        onView(withId(R.id.btnInput)).perform(click())
        onView(withId(R.id.etX1)).perform(typeText("100"))
        onView(withId(R.id.etY1)).perform(typeText("100"))
        onView(withId(R.id.etX2)).perform(typeText("200"))
        onView(withId(R.id.etY2)).perform(typeText("200"))
        onView(withId(R.id.rbGreen)).perform(click())
        onView(withText("OK")).perform(click())
        onView(withId(R.id.btnDraw)).perform(click())
        onView(withId(R.id.tvCoordinates)).check(matches(withText("Coordinates: (100.0, 100.0) to (200.0, 200.0)")))
    }

    @Test
    fun testClearCanvas() {
        onView(withId(R.id.btnClear)).perform(click())
        onView(withId(R.id.tvCoordinates)).check(matches(withText("Coordinates")))
    }
}