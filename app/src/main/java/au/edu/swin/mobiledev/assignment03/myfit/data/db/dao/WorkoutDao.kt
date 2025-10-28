package au.edu.swin.mobiledev.assignment03.myfit.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.WorkoutWithExercises
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.WorkoutWithLogs

@Dao
interface WorkoutDao {

    // Create queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)

    // Insert multiple workouts?

    // Read queries
    @Query("SELECT * FROM workouts ORDER BY dateCreated DESC")
    fun getAllWorkouts(): LiveData<List<Workout>>

    // If user wants to search by ID?
    // This is useful for a database, does it make sense for a user?
    @Query("SELECT * FROM workouts where id = :id")
    suspend fun getWorkoutById(id: Int): Workout?

    // Fetch relations using transactions
    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkoutWithExercises(id: Int): LiveData<WorkoutWithExercises>

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkoutWithLogs(id: Int): LiveData<WorkoutWithLogs>


    // Update / edit a workout
    @Update
    suspend fun updateWorkout(workout: Workout)

    // Delete a workout, or delete all workouts
    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Query("DELETE FROM workouts")
    suspend fun deleteAllWorkouts()

}