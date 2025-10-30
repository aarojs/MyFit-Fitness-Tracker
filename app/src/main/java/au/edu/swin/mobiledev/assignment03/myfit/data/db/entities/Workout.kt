package au.edu.swin.mobiledev.assignment03.myfit.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String,
    val duration: Int = 0, // minutes
    val dateCreated: Long = System.currentTimeMillis(),
    val caloriesBurned: Int = 0,
    val steps: Int = 0
)