package au.edu.swin.mobiledev.assignment03.myfit.data.repository

import androidx.lifecycle.LiveData
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.ExerciseDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise

class ExerciseRepository(private val exerciseDao: ExerciseDao) {


    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAllExercises()

    fun getExercisesForWorkout(id: Int) = exerciseDao.getExercisesForWorkout(id)

    suspend fun insertExercise(exercise: Exercise) = exerciseDao.insertExercise(exercise)

    suspend fun updateExercise(exercise: Exercise) = exerciseDao.updateExercise(exercise)

    suspend fun deleteExercise(exercise: Exercise) = exerciseDao.deleteExercise(exercise)

}