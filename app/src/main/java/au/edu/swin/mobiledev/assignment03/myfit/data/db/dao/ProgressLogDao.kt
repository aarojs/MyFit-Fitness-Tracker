package au.edu.swin.mobiledev.assignment03.myfit.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.ProgressWithWorkout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.WorkoutWithExercises

@Dao
interface ProgressLogDao {

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgressLog(log: ProgressLog)

    // Read
    @Query("SELECT * FROM progress_logs ORDER BY date DESC")
    fun getAllLogs(): LiveData<List<ProgressLog>>

    @Query("SELECT * FROM progress_logs WHERE workoutId = :workoutId ORDER BY date DESC")
    fun getLogsForWorkout(workoutId: Int): LiveData<List<ProgressLog>>

    @Query("SELECT * FROM progress_logs WHERE id = :id")
    suspend fun getLogById(id: Int): ProgressLog?

    @Transaction
    @Query("SELECT * FROM progress_logs ORDER BY date DESC")
    fun getProgressWithWorkout(): LiveData<List<ProgressWithWorkout>>

    // Update
    @Update
    suspend fun updateProgressLog(log: ProgressLog)


    // Delete
    @Delete
    suspend fun deleteProgressLog(log: ProgressLog)

    @Query("DELETE FROM progress_logs WHERE workoutId = :workoutId")
    suspend fun deleteLogsByWorkout(workoutId: Int)

}