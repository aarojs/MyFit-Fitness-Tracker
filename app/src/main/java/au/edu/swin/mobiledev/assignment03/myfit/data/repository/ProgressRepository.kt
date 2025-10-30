package au.edu.swin.mobiledev.assignment03.myfit.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import au.edu.swin.mobiledev.assignment03.myfit.data.db.MyFitDatabase
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.ProgressLogDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog

class ProgressRepository(application: Application){

    private val progressLogDao: ProgressLogDao

    init {
        val db = MyFitDatabase.getDatabase(application)
        progressLogDao = db.progressLogDao()
    }

    val allLogs: LiveData<List<ProgressLog>> = progressLogDao.getAllLogs()

    fun getLogsForWorkout(id: Int): LiveData<List<ProgressLog>> = progressLogDao.getLogsForWorkout(id)

    suspend fun insertProgressLog(progressLog: ProgressLog) = progressLogDao.insertProgressLog(progressLog)

    suspend fun updateProgressLog(progressLog: ProgressLog) = progressLogDao.updateProgressLog(progressLog)

    suspend fun deleteProgressLog(progressLog: ProgressLog) = progressLogDao.deleteProgressLog(progressLog)

    suspend fun deleteLogsByWorkout(id: Int) = progressLogDao.deleteLogsByWorkout(id)
}