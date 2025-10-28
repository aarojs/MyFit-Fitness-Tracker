package au.edu.swin.mobiledev.assignment03.myfit.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String,
    val duration: Int = 0, // minuteswh
    val dateCreated: Long = System.currentTimeMillis()
)