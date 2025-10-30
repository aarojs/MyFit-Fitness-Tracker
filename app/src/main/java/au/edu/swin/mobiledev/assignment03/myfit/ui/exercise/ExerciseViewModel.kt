package au.edu.swin.mobiledev.assignment03.myfit.ui.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.swin.mobiledev.assignment03.myfit.data.db.MyFitDatabase
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ExerciseRepository(application)
    val allExercises: LiveData<List<Exercise>> = repository.allExercises

    fun getExercisesForWorkout(id: Int): LiveData<List<Exercise>> {
        return repository.getExercisesForWorkout(id)
    }

    fun insert(exercise: Exercise) = viewModelScope.launch {
        repository.insertExercise(exercise)
    }

    fun update(exercise: Exercise) = viewModelScope.launch {
        repository.updateExercise(exercise)
    }

    fun delete(exercise: Exercise) = viewModelScope.launch {
        repository.deleteExercise(exercise)
    }






}