package au.edu.swin.mobiledev.assignment03.myfit.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.db.SupportSQLiteDatabase
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.ExerciseDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.ProgressLogDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.WorkoutDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(
    entities = [Workout::class, Exercise::class, ProgressLog::class],
    version = 6,
    exportSchema = false
)
abstract class MyFitDatabase: RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun progressLogDao(): ProgressLogDao

    companion object {
        @Volatile
        private var INSTANCE: MyFitDatabase? = null

        fun getDatabase(context: Context): MyFitDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyFitDatabase::class.java,
                    "myfit_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                //Populate database only once when it is created, run in a Coroutine
                CoroutineScope(Dispatchers.IO).launch {
                    val database = getDatabase(context)
                    val exerciseDao = database.exerciseDao()
                    val workoutDao = database.workoutDao()

                    // Seed data
                    // Create workouts first
                    // Then create exercises
                    // Then link using workout ID

                    // Insert Workouts
                    val workouts = listOf(
                        Workout(0, "Leg Day", "Leg Workout with Cardio", 45),
                        Workout(0, "Pull Day", "Back and Arms workout", 60),
                        Workout(0, "Push Day", "Chest, Shoulders and Triceps", 60)
                    )

                    workoutDao.insertWorkouts(workouts)

                    // Insert exercises, assign to workouts listed above
                    val exercises = listOf(
                        Exercise(name = "Lat Pull-down", type = "Strength", reps = 8, sets = 3, workoutId = 2),
                        Exercise(name = "Bicep Curls", type = "Strength", reps = 10, sets = 3, workoutId = 2),
                        Exercise(name = "Squats", type = "Bodyweight", reps = 15, sets = 3, workoutId = 1),
                        Exercise(name = "Jump Rope", type = "Cardio", reps = 100, sets = 3, workoutId = 1),
                        Exercise(name = "Bench Press", type = "Strength", reps = 10, sets = 3, workoutId = 3),
                        Exercise(name = "Push-ups", type = "Bodyweight", reps = 12, sets = 3, workoutId = 3)
                    )

                    exerciseDao.insertExercises(exercises)

                    Log.d("SEEDING", "Entries seeded into database")

                }
            }
        }
    }
}