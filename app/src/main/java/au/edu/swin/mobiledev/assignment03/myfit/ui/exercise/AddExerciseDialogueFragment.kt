package au.edu.swin.mobiledev.assignment03.myfit.ui.exercise

import androidx.fragment.app.DialogFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentAddExerciseDialogueBinding
import au.edu.swin.mobiledev.assignment03.myfit.ui.workout.WorkoutViewModel


class AddExerciseDialogueFragment : DialogFragment() {

    private var _binding: FragmentAddExerciseDialogueBinding? = null
    private val binding get() = _binding!!

    private lateinit var workoutViewModel: WorkoutViewModel
    private lateinit var exerciseViewModel: ExerciseViewModel

    private var workoutsList: List<Workout> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddExerciseDialogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // uses workout viewmodel to display list of workouts
        // uses exercise viewmodel to insert into  db
        workoutViewModel = ViewModelProvider(requireActivity())[WorkoutViewModel::class.java]
        exerciseViewModel = ViewModelProvider(requireActivity())[ExerciseViewModel::class.java]

        // Load workouts for spinner
        workoutViewModel.allWorkouts.observe(viewLifecycleOwner) { workouts ->
            workoutsList = workouts
            // Map Workout names to workout ID
            val workoutNames = workouts.map { it.name }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                workoutNames
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.workoutSpinner.adapter = adapter
        }

        // Save Button will get edit text input and create an exercise entity
        binding.saveBtn.setOnClickListener {
            val name = binding.exerciseNameInput.text.toString().trim()
            val type = binding.exerciseTypeInput.text.toString().trim()
            val reps = binding.exerciseRepsInput.text.toString().toIntOrNull() ?: 0
            val sets = binding.exerciseSetsInput.text.toString().toIntOrNull() ?: 0
            val workoutId = workoutsList.getOrNull(binding.workoutSpinner.selectedItemPosition)?.id
                ?: 0

            if (name.isNotEmpty() && type.isNotEmpty() && workoutId != 0) {
                val exercise = Exercise(
                    name = name,
                    type = type,
                    reps = reps,
                    sets = sets,
                    workoutId = workoutId
                )
                // Use Exercise ViewModel to insert into database
                exerciseViewModel.insert(exercise)
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelBtn.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}