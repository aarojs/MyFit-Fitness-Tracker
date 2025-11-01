package au.edu.swin.mobiledev.assignment03.myfit.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout


// This relation is not implemented in this version of my app
// User could view specific progress logs containing a specified workout
data class WorkoutWithLogs(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutId"
    )
    val logs: List<ProgressLog>
)

