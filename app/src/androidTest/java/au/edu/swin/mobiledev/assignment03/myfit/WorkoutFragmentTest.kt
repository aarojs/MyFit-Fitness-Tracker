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
import au.edu.swin.mobiledev.assignment03.myfit.ui.workout.WorkoutFragment
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WorkoutFragmentTest {

    @Test
    fun workoutList_DisplaysRecyclerView() {
        launchFragmentInContainer<WorkoutFragment>(themeResId = R.style.Theme_MyFit)

        onView(withId(R.id.workoutRecycler)).check(matches(isDisplayed()))
    }

    @Test
    fun addWorkoutDialog_OpensSuccessfully() {
        launchFragmentInContainer<WorkoutFragment>(themeResId = R.style.Theme_MyFit)

        onView(withId(R.id.addWorkoutFab)).perform(click())
        onView(withText("Add Workout")).check(matches(isDisplayed()))
    }
}