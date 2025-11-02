package au.edu.swin.mobiledev.assignment03.myfit.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout

// Link workout to exercises, so exercises for a given workout can be viewed
data class WorkoutWithExercises(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutId"
    )
    val exercises: List<Exercise>
)