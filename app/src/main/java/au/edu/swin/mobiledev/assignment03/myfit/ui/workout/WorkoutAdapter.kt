package au.edu.swin.mobiledev.assignment03.myfit.ui.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout

class WorkoutAdapter (
    private val workouts: MutableList<Workout>,
    private val listener: (Workout) -> Unit) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.item_workout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount() = workouts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]
        holder.bind(workout)
    }


    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v){
        fun bind(workout: Workout){

            v.setOnClickListener {
                listener(workout)
            }

        }
    }

    fun updateData(newData: List<Workout>) {
        workouts.clear()
        workouts.addAll(newData)
        notifyDataSetChanged()
    }
    }

