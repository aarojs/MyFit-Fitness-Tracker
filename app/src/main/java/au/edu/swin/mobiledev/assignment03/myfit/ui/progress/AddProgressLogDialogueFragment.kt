package au.edu.swin.mobiledev.assignment03.myfit.ui.progress

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentAddProgressLogDialogueBinding
import au.edu.swin.mobiledev.assignment03.myfit.ui.workout.WorkoutViewModel


class AddProgressLogDialogueFragment : DialogFragment() {

    private var _binding: FragmentAddProgressLogDialogueBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressLogViewModel: ProgressViewModel
    private var workoutId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProgressLogDialogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutId = arguments?.getInt("workoutId") ?: 0
        progressLogViewModel = ViewModelProvider(requireActivity())[ProgressViewModel::class.java]


        binding.saveBtn.setOnClickListener {
            val duration = binding.workoutDurationInput.text.toString().toIntOrNull() ?: 0
            val notes = binding.logNotes.text.toString()

            val log = ProgressLog(
                workoutId = workoutId,
                duration = duration,
                notes = notes.ifBlank { null }
            )

            progressLogViewModel.insert(log)
            dismiss()
        }
    }


}