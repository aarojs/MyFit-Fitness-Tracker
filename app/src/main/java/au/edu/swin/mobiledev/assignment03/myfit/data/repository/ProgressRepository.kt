package au.edu.swin.mobiledev.assignment03.myfit.data.repository

import androidx.lifecycle.LiveData
import au.edu.swin.mobiledev.assignment03.myfit.data.db.dao.ProgressLogDao
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog

class ProgressRepository(private val progressLogDao: ProgressLogDao){
    val allLogs: LiveData<List<ProgressLog>> = progressLogDao.getAllLogs()

    fun getLogsForWorkout(id: Int): LiveData<List<ProgressLog>> = progressLogDao.getLogsForWorkout(id)

    suspend fun insertProgressLog(progressLog: ProgressLog) = progressLogDao.insertProgressLog(progressLog)

    suspend fun updateProgressLog(progressLog: ProgressLog) = progressLogDao.updateProgressLog(progressLog)

    suspend fun deleteProgressLog(progressLog: ProgressLog) = progressLogDao.deleteProgressLog(progressLog)

    suspend fun deleteLogsByWorkout(id: Int) = progressLogDao.deleteLogsByWorkout(id)
}