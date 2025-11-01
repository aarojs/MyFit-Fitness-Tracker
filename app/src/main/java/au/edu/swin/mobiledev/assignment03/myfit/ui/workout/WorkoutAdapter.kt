package au.edu.swin.mobiledev.assignment03.myfit.ui.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.databinding.ItemWorkoutBinding

class WorkoutAdapter (
    private val workouts: MutableList<Workout>,
    private val listener: WorkoutItemListener
) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){

        interface WorkoutItemListener {
            fun onClick(workout: Workout)
            fun onDelete(workout: Workout)
        }

    inner class ViewHolder(val binding: ItemWorkoutBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(workout: Workout){
            binding.workoutName.text = workout.name
            binding.workoutDesc.text = workout.description
            val durationString = "Duration: ${workout.duration} mins"
            binding.workoutDuration.text = durationString

            binding.root.setOnClickListener {
                listener.onClick(workout)
            }

            binding.menuButton.setOnClickListener { view ->
                val popup = PopupMenu(view.context, view)
                popup.inflate(R.menu.item_menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when(menuItem.itemId) {
                        R.id.action_delete -> listener.onDelete(workout)
                        else -> false
                    }
                    true
                }
                popup.show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWorkoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]
        holder.bind(workout)
    }

    override fun getItemCount() = workouts.size

    fun updateData(newData: List<Workout>) {
        workouts.clear()
        workouts.addAll(newData)
        notifyDataSetChanged()
    }
    }

