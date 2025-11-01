package au.edu.swin.mobiledev.assignment03.myfit.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import au.edu.swin.mobiledev.assignment03.myfit.data.UserPreferencesKeys
import au.edu.swin.mobiledev.assignment03.myfit.data.dataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.datastore.preferences.core.edit

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = application.dataStore

    val userSettings = dataStore.data.map { prefs ->
        mapOf(
            "name" to (prefs[UserPreferencesKeys.PROFILE_NAME]),
            "weight" to (prefs[UserPreferencesKeys.WEIGHT]),
            "goal_weight" to (prefs[UserPreferencesKeys.GOAL_WEIGHT]),
            "age" to (prefs[UserPreferencesKeys.AGE])
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyMap())

    fun saveSettings(name: String, weight: Float, goalWeight: Float, age: Int) {
        viewModelScope.launch {
            dataStore.edit { prefs ->
                prefs[UserPreferencesKeys.PROFILE_NAME] = name
                prefs[UserPreferencesKeys.WEIGHT] = weight
                prefs[UserPreferencesKeys.GOAL_WEIGHT] = goalWeight
                prefs[UserPreferencesKeys.AGE] = age
            }
        }
    }
}