package au.edu.swin.mobiledev.assignment03.myfit.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["id"], // id column from workout table 'id'
            childColumns = ["workoutId"], // tied to child workoutId column in exercises
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["workoutId"])]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String,
    val reps: Int,
    val sets: Int,
    val workoutId: Int // Foreign key reference to Workout
)