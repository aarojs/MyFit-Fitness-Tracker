package au.edu.swin.mobiledev.assignment03.myfit

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import au.edu.swin.mobiledev.assignment03.myfit.ui.home.HomeFragment
import au.edu.swin.mobiledev.assignment03.myfit.ui.settings.SettingsFragment
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SettingsFragmentTest {

    @Test
    fun settingsForm_DisplaysAndSaves() {
        launchFragmentInContainer<SettingsFragment>(themeResId = R.style.Theme_MyFit)

        // Fill in fields
        onView(withId(R.id.profileName)).perform(replaceText("Aaron"))
        onView(withId(R.id.profileWeight)).perform(replaceText("75"))
        onView(withId(R.id.profileGoalWeight)).perform(replaceText("70"))
        onView(withId(R.id.profileAge)).perform(replaceText("25"))

        // Save button click
        onView(withId(R.id.settingsSaveBtn)).perform(click())

        // Assert save button still visible (UI didn’t crash)
        onView(withId(R.id.settingsSaveBtn)).check(matches(isDisplayed()))
    }
}