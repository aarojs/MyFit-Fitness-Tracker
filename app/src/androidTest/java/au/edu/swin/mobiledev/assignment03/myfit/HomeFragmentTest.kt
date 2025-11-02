package au.edu.swin.mobiledev.assignment03.myfit

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import au.edu.swin.mobiledev.assignment03.myfit.ui.home.HomeFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Test
    fun profileInformation_DisplaysCorrectly() {
        launchFragmentInContainer<HomeFragment>(themeResId = R.style.Theme_MyFit)

        // Check default text or placeholder
        onView(withId(R.id.userAge)).check(matches(isDisplayed()))
        onView(withId(R.id.userWeight)).check(matches(isDisplayed()))
        onView(withId(R.id.userGoalWeight)).check(matches(isDisplayed()))
    }

    @Test
    fun kjToCalorieConversion_WorksCorrectly() {
        launchFragmentInContainer<HomeFragment>(themeResId = R.style.Theme_MyFit)

        onView(withId(R.id.kjInput)).perform(androidx.test.espresso.action.ViewActions.replaceText("4184"))
        onView(withId(R.id.calcKjButton)).perform(click())
        onView(withId(R.id.kcalOutput)).check(matches(withText("1000 kcal")))
    }
}