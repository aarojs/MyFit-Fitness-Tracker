package au.edu.swin.mobiledev.assignment03.myfit.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!! // assert that binding is not null when accessed

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pull Data From ViewModels
        // Today's workout
        // Calories burned (from progress log)
        // Steps ?
        binding.welcomeGreeting.text
        binding.textTodayWorkout
        binding.textCalories
        binding.textSteps


        // Navigation shortcut listeners
        binding.btnGoToWorkouts.setOnClickListener {
            findNavController().navigate(R.id.workoutFragment)
        }

        binding.btnGoToProgress.setOnClickListener {
            findNavController().navigate(R.id.progressFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}