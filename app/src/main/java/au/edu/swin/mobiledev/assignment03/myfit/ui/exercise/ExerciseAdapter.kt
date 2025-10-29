package au.edu.swin.mobiledev.assignment03.myfit.ui.exercise

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.databinding.ItemExerciseBinding

class ExerciseAdapter (
    private val exercises: MutableList<Exercise>,
    private val listener: (Exercise) -> Unit) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemExerciseBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            // Bind Views
            binding.exerciseName.text = exercise.name
            binding.exerciseType.text = exercise.type
            binding.exerciseSets.text = exercise.sets.toString()
            binding.exerciseReps.text = exercise.reps.toString()

            binding.root.setOnClickListener {
                listener(exercise)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseAdapter.ViewHolder {
        val binding = ItemExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ExerciseAdapter.ViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount() = exercises.size


    fun updateData(newData: List<Exercise>) {
        exercises.clear()
        exercises.addAll(newData)
        notifyDataSetChanged()
    }
}



