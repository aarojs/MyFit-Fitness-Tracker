package au.edu.swin.mobiledev.assignment03.myfit.ui.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Workout
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentAddWorkoutDialogueBinding
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentWorkoutBinding

class AddWorkoutDialogueFragment : DialogFragment() {


    private var _binding: FragmentAddWorkoutDialogueBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddWorkoutDialogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use Same WorkoutViewModel from the WorkoutFragment
        viewModel = ViewModelProvider(requireActivity())[WorkoutViewModel::class.java]

        // Save button will create a new workout from the edit text input
        binding.saveBtn.setOnClickListener {
            val name = binding.workoutNameInput.text.toString().trim()
            val desc = binding.workoutDescInput.text.toString().trim()
            val duration = binding.workoutDurationInput.text.toString().toIntOrNull() ?: 0

            if (name.isNotEmpty() && desc.isNotEmpty()) {
                val workout = Workout(name = name, description = desc, duration = duration)
                // insert newly created workout into database
                viewModel.insert(workout)
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelBtn.setOnClickListener { dismiss() }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    }




