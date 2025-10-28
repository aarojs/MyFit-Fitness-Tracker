package au.edu.swin.mobiledev.assignment03.myfit.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "progress_logs",
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["id"],
            childColumns = ["workoutId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["workoutId"])]
)
data class ProgressLog (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val workoutId: Int, // FK link to workout
    val date: Long = System.currentTimeMillis(),
    val duration: Int, // minutes
    val notes: String? = null // user can leave notes about their workout

)