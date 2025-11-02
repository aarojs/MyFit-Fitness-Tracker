package au.edu.swin.mobiledev.assignment03.myfit

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import au.edu.swin.mobiledev.assignment03.myfit.ui.exercise.ExerciseFragment
import au.edu.swin.mobiledev.assignment03.myfit.ui.home.HomeFragment
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExerciseFragmentTest {

    @Test
    fun exerciseList_DisplaysCorrectly() {
        launchFragmentInContainer<ExerciseFragment>(themeResId = R.style.Theme_MyFit)

        onView(withId(R.id.exerciseRecycler)).check(matches(isDisplayed()))
    }

    @Test
    fun addExerciseDialog_OpensSuccessfully() {
        launchFragmentInContainer<ExerciseFragment>(themeResId = R.style.Theme_MyFit)

        onView(withId(R.id.addExerciseFab)).perform(click())
        onView(withText("Add Exercise")).check(matches(isDisplayed()))
    }
}