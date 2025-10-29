package au.edu.swin.mobiledev.assignment03.myfit.ui.progress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.databinding.ItemProgressLogBinding
import au.edu.swin.mobiledev.assignment03.myfit.ui.exercise.ExerciseAdapter

class ProgressLogAdapter (
    private val progressLogs: MutableList<ProgressLog>,
    private val listener: (ProgressLog) -> Unit) : RecyclerView.Adapter<ProgressLogAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemProgressLogBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(progressLog: ProgressLog) {
            //Bind views
            binding.logDate.text = progressLog.date.toString()
            binding.logDuration.text = progressLog.duration.toString()
            binding.logNotes.text = progressLog.notes

            binding.root.setOnClickListener {
                listener(progressLog)
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


    fun updateData(newData: List<ProgressLog>) {
        progressLogs.clear()
        progressLogs.addAll(newData)
        notifyDataSetChanged()
    }


    }

