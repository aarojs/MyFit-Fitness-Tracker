package au.edu.swin.mobiledev.assignment03.myfit.ui.workout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentWorkoutBinding


class WorkoutFragment : Fragment() {
    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WorkoutViewModel
    private lateinit var adapter: WorkoutAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init ViewModel
        viewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        // Init adapter
        adapter = WorkoutAdapter(mutableListOf(), object: WorkoutAdapter.WorkoutItemListener {
            override fun onClick(workout: Workout) {
                val action = WorkoutFragmentDirections
                    .actionWorkoutFragmentToWorkoutDetailFragment(workout.id)
                findNavController().navigate(action)
            }

            override fun onDelete(workout: Workout) {
                viewModel.delete(workout)
            }
        })


        // Setup recycle view
        binding.workoutRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.workoutRecycler.adapter = adapter

        // Access ViewModel
        viewModel.allWorkouts.observe(viewLifecycleOwner) { workouts ->
            adapter.updateData(workouts)
        }

        // Add workout button
        binding.addWorkoutFab.setOnClickListener {
            val dialog = AddWorkoutDialogueFragment()
            dialog.show(parentFragmentManager, "AddWorkoutDialog")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}