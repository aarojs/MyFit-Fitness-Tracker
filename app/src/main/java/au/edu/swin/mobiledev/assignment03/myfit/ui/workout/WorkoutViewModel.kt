package au.edu.swin.mobiledev.assignment03.myfit.ui.workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.WorkoutWithExercises
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.WorkoutWithLogs
import au.edu.swin.mobiledev.assignment03.myfit.data.repository.WorkoutRepository
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WorkoutRepository(application)
    val allWorkouts: LiveData<List<Workout>> = repository.allWorkouts

    fun getWorkoutWithExercises(id: Int): LiveData<WorkoutWithExercises> {
        return repository.getWorkoutWithExercises(id)
    }

    fun insert(workout: Workout) = viewModelScope.launch {
        repository.insertWorkout(workout)
    }

    fun update(workout: Workout) = viewModelScope.launch {
        repository.updateWorkout(workout)
    }

    fun delete(workout: Workout) = viewModelScope.launch {
        repository.deleteWorkout(workout)
    }
}