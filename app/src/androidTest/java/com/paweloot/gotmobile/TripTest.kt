package com.paweloot.gotmobile


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TripTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun tripTest() {
        val mtnRange = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.mtn_range_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        mtnRange.perform(click())

        val mtnGroup = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.mtn_group_list),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        mtnGroup.perform(click())

        val startPoint = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.point_list),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        startPoint.perform(click())

        val overflowMenuButton = onView(
            allOf(
                withContentDescription("More options"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        overflowMenuButton.perform(click())

        val addViaPointOption = onView(
            allOf(
                withId(R.id.title), withText("Dodaj punkt pośredni"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        addViaPointOption.perform(click())

        val viaPoint = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.point_list),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viaPoint.perform(click())

        val endPoint = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.point_list),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        endPoint.perform(click())

        val saveButton = onView(
            allOf(
                withId(R.id.save_button), withText("Zapisz"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        saveButton.perform(click())

        val calendarOkButton = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        calendarOkButton.perform(scrollTo(), click())

        val continueButton = onView(
            allOf(
                withId(android.R.id.button1), withText("Kontynuuj"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        continueButton.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
