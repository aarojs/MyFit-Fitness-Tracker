package au.edu.swin.mobiledev.assignment03.myfit.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.ExerciseDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.ProgressLogDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.WorkoutDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise



@Database(
    entities = [Workout::class, Exercise::class, ProgressLog::class],
    version = 1,
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
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}