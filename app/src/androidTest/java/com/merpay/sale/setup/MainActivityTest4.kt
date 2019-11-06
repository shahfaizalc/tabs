package com.merpay.sale.setup


import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewInteraction
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*

import com.merpay.sale.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import java.lang.Thread.sleep
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest4 {

    @Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(InterruptedException::class)
    fun mainActivityTest4() {

        val appCompatEditText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                1),
                        1),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("sample1@gmail.com"), closeSoftKeyboard())

        Thread.sleep(1000)

        val appCompatEditText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                2),
                        1),
                        isDisplayed()))
        appCompatEditText2.perform(replaceText("sample1@gmail.com"), closeSoftKeyboard())

        Thread.sleep(1000)

        val appCompatButton = onView(
                allOf(withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fl_reg_fragment_container),
                                        0),
                                3),
                        isDisplayed()))
        appCompatButton.perform(click())
        Thread.sleep(5000)

        val appCompatImageView = onView(
                allOf(withId(R.id.editProfile),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()))
        appCompatImageView.perform(click())

        Thread.sleep(2000)

        //        ViewInteraction appCompatButton2 = onView(
        //                allOf(withText("Choose File"),
        //                        childAtPosition(
        //                                childAtPosition(
        //                                        withId(R.id.scroll),
        //                                        0),
        //                                2)));
        //        appCompatButton2.perform(scrollTo(), click());
        //
        //        Thread.sleep(1000);
        //
        //        ViewInteraction appCompatButton3 = onView(
        //                allOf(withText("Upload"),
        //                        childAtPosition(
        //                                childAtPosition(
        //                                        withId(R.id.scroll),
        //                                        0),
        //                                3)));
        //        appCompatButton3.perform(scrollTo(), click());
        //
        //        Thread.sleep(10000);

        val appCompatEdit = onView(
                allOf(
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                17)))
        appCompatEdit.perform(replaceText("doctor"), closeSoftKeyboard())

        Thread.sleep(1000)

        val appCompatSpinner = onView(
                allOf(withId(R.id.religion_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                25)))

        appCompatSpinner.perform(scrollTo(), click())

        Thread.sleep(5000)


        val linearLayout = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(`is`("android.widget.PopupWindow\$PopupBackgroundView")),
                        0))
                .atPosition(3)
        linearLayout.perform(click())

        Thread.sleep(1000)


        val appCompatButton4 = onView(
                allOf(withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                30)))
        appCompatButton4.perform(scrollTo(), click())

        Thread.sleep(5000)

        val appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(`is`("android.support.design.widget.AppBarLayout")),
                                                0)),
                                0),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        Thread.sleep(5000)

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
