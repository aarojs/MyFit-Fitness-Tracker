package au.edu.swin.mobiledev.assignment03.myfit.ui.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.databinding.ItemWorkoutBinding

class WorkoutAdapter (
    private val workouts: MutableList<Workout>,
    private val listener: (Workout) -> Unit) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemWorkoutBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(workout: Workout){
            binding.workoutName.text = workout.name
            binding.workoutDesc.text = workout.description
            binding.workoutDuration.text = workout.duration.toString()

            binding.root.setOnClickListener {
                listener(workout)
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

