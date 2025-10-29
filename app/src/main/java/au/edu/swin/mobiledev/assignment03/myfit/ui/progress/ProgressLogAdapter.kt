package au.edu.swin.mobiledev.assignment03.myfit.ui.progress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.ui.exercise.ExerciseAdapter

class ProgressLogAdapter (
    private val progressLogs: MutableList<ProgressLog>,
    private val listener: (ProgressLog) -> Unit) : RecyclerView.Adapter<ProgressLogAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.item_progress_log, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount() = progressLogs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val progressLog = progressLogs[position]
        holder.bind(progressLog)
    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v){
        // Find views go here
        fun bind(progressLog: ProgressLog) {
            // Assign context to views here

            v.setOnClickListener {
                listener(progressLog)
            }
        }
    }

    fun updateData(newData: List<ProgressLog>) {
        progressLogs.clear()
        progressLogs.addAll(newData)
        notifyDataSetChanged()
    }


    }

