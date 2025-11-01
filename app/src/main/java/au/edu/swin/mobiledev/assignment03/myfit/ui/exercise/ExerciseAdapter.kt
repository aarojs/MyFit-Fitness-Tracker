package au.edu.swin.mobiledev.assignment03.myfit.ui.exercise

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.databinding.ItemExerciseBinding

class ExerciseAdapter (
    private val exercises: MutableList<Exercise>,
    private val listener: ExerciseItemListener
) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>(){

    interface ExerciseItemListener {
        fun onDelete(exercise: Exercise)
    }

    inner class ViewHolder(val binding: ItemExerciseBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            // Bind Views
            val setsString = "${exercise.sets} sets"
            val repsString = "${exercise.reps} reps"
            binding.exerciseName.text = exercise.name
            binding.exerciseType.text = exercise.type
            binding.exerciseSets.text = setsString
            binding.exerciseReps.text = repsString


            binding.menuButton.setOnClickListener { view ->
                val popup = PopupMenu(view.context, view)
                popup.inflate(R.menu.item_menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when(menuItem.itemId) {
                        R.id.action_delete -> listener.onDelete(exercise)
                        else -> false
                    }
                    true
                }
                popup.show()
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



