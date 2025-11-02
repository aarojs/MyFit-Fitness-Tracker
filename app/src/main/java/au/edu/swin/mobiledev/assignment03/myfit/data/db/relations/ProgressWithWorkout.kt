package au.edu.swin.mobiledev.assignment03.myfit.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout

// Link progress Log to a workout, so workout can be displayed within a progress log entry
data class ProgressWithWorkout (
    @Embedded val progressLog: ProgressLog,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "id"
    )
    val workout: Workout
)