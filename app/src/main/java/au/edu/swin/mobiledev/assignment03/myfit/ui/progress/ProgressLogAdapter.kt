package au.edu.swin.mobiledev.assignment03.myfit.ui.progress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.ProgressWithWorkout
import au.edu.swin.mobiledev.assignment03.myfit.databinding.ItemProgressLogBinding
import au.edu.swin.mobiledev.assignment03.myfit.ui.exercise.ExerciseAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProgressLogAdapter (
    private val progressLogs: MutableList<ProgressWithWorkout>,
    private val listener: (ProgressWithWorkout) -> Unit) : RecyclerView.Adapter<ProgressLogAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemProgressLogBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(progressWithWorkout: ProgressWithWorkout) {
            //Bind views
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(Date(progressWithWorkout.progressLog.date))
            val durationString = "${progressWithWorkout.progressLog.duration} mins"

            binding.workoutName.text = progressWithWorkout.workout.name
            binding.logDate.text = formattedDate.toString()
            binding.logDuration.text = durationString
            binding.logNotes.text = progressWithWorkout.progressLog.notes

            binding.root.setOnClickListener {
                listener(progressWithWorkout)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProgressLogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val progressLog = progressLogs[position]
        holder.bind(progressLog)
    }

    override fun getItemCount() = progressLogs.size


    fun updateData(newData: List<ProgressWithWorkout>) {
        progressLogs.clear()
        progressLogs.addAll(newData)
        notifyDataSetChanged()
    }


    }

