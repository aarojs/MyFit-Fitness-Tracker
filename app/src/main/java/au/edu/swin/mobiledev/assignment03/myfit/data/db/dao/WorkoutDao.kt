package au.edu.swin.mobiledev.assignment03.myfit.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.WorkoutWithExercises

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkoutWithExercises(id: Int): LiveData<WorkoutWithExercises>

    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): LiveData<List<Workout>>
}