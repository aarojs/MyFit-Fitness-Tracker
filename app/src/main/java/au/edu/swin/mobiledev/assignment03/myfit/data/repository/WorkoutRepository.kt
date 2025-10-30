package au.edu.swin.mobiledev.assignment03.myfit.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import au.edu.swin.mobiledev.assignment03.myfit.data.db.MyFitDatabase
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.WorkoutDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.WorkoutWithExercises
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.WorkoutWithLogs

class WorkoutRepository(application: Application) {

    private val workoutDao: WorkoutDao

    init {
        val db = MyFitDatabase.getDatabase(application)
        workoutDao = db.workoutDao()
    }

    val allWorkouts: LiveData<List<Workout>> = workoutDao.getAllWorkouts()

    fun getWorkoutWithExercises(id: Int): LiveData<WorkoutWithExercises> = workoutDao.getWorkoutWithExercises(id)

    fun getWorkoutWithLogs(id: Int): LiveData<WorkoutWithLogs> = workoutDao.getWorkoutWithLogs(id)

    suspend fun insertWorkout(workout: Workout) = workoutDao.insertWorkout(workout)

    suspend fun updateWorkout(workout: Workout) = workoutDao.updateWorkout(workout)

    suspend fun deleteWorkout(workout: Workout) = workoutDao.deleteWorkout(workout)



}