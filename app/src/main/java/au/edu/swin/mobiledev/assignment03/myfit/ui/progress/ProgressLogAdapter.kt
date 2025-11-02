package au.edu.swin.mobiledev.assignment03.myfit.ui.progress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.ProgressWithWorkout
import au.edu.swin.mobiledev.assignment03.myfit.databinding.ItemProgressLogBinding
import au.edu.swin.mobiledev.assignment03.myfit.ui.exercise.ExerciseAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProgressLogAdapter (
    // As logs are tied to a workout, the relation data class ProgressWithWorkout is used
    private val progressLogs: MutableList<ProgressWithWorkout>,
    private val listener: ProgressLogItemListener
) : RecyclerView.Adapter<ProgressLogAdapter.ViewHolder>(){

    // Listener for menu item
    interface ProgressLogItemListener {
        fun onDelete(progressLog: ProgressWithWorkout)
    }

    inner class ViewHolder(val binding: ItemProgressLogBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(progressWithWorkout: ProgressWithWorkout) {
            //Bind views
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(Date(progressWithWorkout.progressLog.date))
            val dateString = "Date: $formattedDate"
            val durationString = "Completed Duration: ${progressWithWorkout.progressLog.duration} mins"

            binding.workoutName.text = progressWithWorkout.workout.name
            binding.logDate.text = dateString
            binding.logDuration.text = durationString
            binding.logNotes.text = progressWithWorkout.progressLog.notes


            // Popup menu
            binding.menuButton.setOnClickListener { view ->
                val popup = PopupMenu(view.context, view)
                popup.inflate(R.menu.item_menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when(menuItem.itemId) {
                        R.id.action_delete -> listener.onDelete(progressWithWorkout)
                        else -> false
                    }
                    true
                }
                popup.show()
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

