package au.edu.swin.mobiledev.assignment03.myfit.ui.progress

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.ProgressWithWorkout
import au.edu.swin.mobiledev.assignment03.myfit.data.repository.ProgressRepository
import kotlinx.coroutines.launch

class ProgressViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProgressRepository(application)
    val allLogsWithWorkout: LiveData<List<ProgressWithWorkout>> = repository.getProgressWithWorkout()

    fun getLogsForWorkout(id: Int): LiveData<List<ProgressLog>> {
        return repository.getLogsForWorkout((id))
    }

    fun insert(progressLog: ProgressLog) = viewModelScope.launch {
        repository.insertProgressLog(progressLog)
    }

    fun update(progressLog: ProgressLog) = viewModelScope.launch {
        repository.updateProgressLog(progressLog)
    }

    fun delete(progressLog: ProgressLog) = viewModelScope.launch {
        repository.deleteProgressLog(progressLog)
    }

    fun deleteByWorkout(id: Int) = viewModelScope.launch {
        repository.deleteLogsByWorkout(id)
    }

}