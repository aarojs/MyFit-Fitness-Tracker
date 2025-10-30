package au.edu.swin.mobiledev.assignment03.myfit.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import au.edu.swin.mobiledev.assignment03.myfit.data.db.MyFitDatabase
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.ExerciseDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise

class ExerciseRepository(application: Application) {

    private val exerciseDao: ExerciseDao

    init {
        val db = MyFitDatabase.getDatabase(application)
        exerciseDao = db.exerciseDao()
    }


    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAllExercises()

    fun getExercisesForWorkout(id: Int) = exerciseDao.getExercisesForWorkout(id)

    suspend fun insertExercise(exercise: Exercise) = exerciseDao.insertExercise(exercise)

    suspend fun updateExercise(exercise: Exercise) = exerciseDao.updateExercise(exercise)

    suspend fun deleteExercise(exercise: Exercise) = exerciseDao.deleteExercise(exercise)

}