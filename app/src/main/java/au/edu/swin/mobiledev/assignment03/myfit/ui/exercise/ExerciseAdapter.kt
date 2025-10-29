package au.edu.swin.mobiledev.assignment03.myfit.ui.exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise

class ExerciseAdapter (
    private val exercises: MutableList<Exercise>,
    private val listener: (Exercise) -> Unit) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.item_exercise, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount() = exercises.size

    override fun onBindViewHolder(holder: ExerciseAdapter.ViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        // Find views
        fun bind(exercise: Exercise) {
            // Assign things to views

            v.setOnClickListener {
                listener(exercise)
            }
        }
    }

    fun updateData(newData: List<Exercise>) {
        exercises.clear()
        exercises.addAll(newData)
        notifyDataSetChanged()
    }
}



