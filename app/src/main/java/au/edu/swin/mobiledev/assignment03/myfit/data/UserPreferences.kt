package au.edu.swin.mobiledev.assignment03.myfit.data


import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "user_prefs")

object UserPreferencesKeys {
    val PROFILE_NAME = stringPreferencesKey("profile_name")
    val WEIGHT = floatPreferencesKey("weight")
    val GOAL_WEIGHT = floatPreferencesKey("goal_weight")
    val AGE = intPreferencesKey("age")
}