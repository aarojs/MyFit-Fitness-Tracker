package au.edu.swin.mobiledev.assignment03.myfit.ui.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentWorkoutDetailBinding
import au.edu.swin.mobiledev.assignment03.myfit.ui.exercise.ExerciseAdapter


class WorkoutDetailFragment : Fragment() {

    private var _binding: FragmentWorkoutDetailBinding? = null
    private val binding get() = _binding!!


    private var workoutId: Int = 0
    private lateinit var viewModel: WorkoutViewModel
    private lateinit var adapter: ExerciseAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init adapter
        adapter = ExerciseAdapter(mutableListOf()) {}

        // Setup recycle view
        binding.exerciseRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.exerciseRecycler.adapter = adapter

        // Retrieve workout ID argument
        workoutId = arguments?.let { WorkoutDetailFragmentArgs.fromBundle(it).workoutId } ?: 0

        // Init viewmodel
        viewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        // Get workout with exercises
        viewModel.getWorkoutWithExercises(workoutId).observe(viewLifecycleOwner) { workoutWithExercises ->
            // Populate UI
            binding.workoutName.text = workoutWithExercises.workout.name
            binding.workoutDesc.text = workoutWithExercises.workout.description

            // Use RecyclerView for exercises
            adapter.updateData(workoutWithExercises.exercises)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}