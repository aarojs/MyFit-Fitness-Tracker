package au.edu.swin.mobiledev.assignment03.myfit.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.WorkoutWithExercises

@Dao
interface ExerciseDao {

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<Exercise>)

    // Read
    @Query("SELECT * FROM exercises ORDER BY name ASC")
    fun getAllExercises(): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE workoutId = :workoutId")
    fun getExercisesForWorkout(workoutId: Int): LiveData<List<Exercise>>

    // Not used in repository
    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun getExerciseById(id: Int): Exercise?

    // Update
    @Update
    suspend fun updateExercise(exercise: Exercise)

    // Delete
    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    // Not Used in Repository
    @Query("DELETE FROM exercises WHERE workoutId = :workoutId")
    suspend fun deleteExercisesByWorkout(workoutId: Int)
}