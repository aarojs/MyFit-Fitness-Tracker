package au.edu.swin.mobiledev.assignment03.myfit.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        val scrollView = binding.homeScrollView

        // Ensuring scrollview sits above nav bar
        view.post {
            val bottomNav = requireActivity().findViewById<View>(R.id.bottom_nav)
            val navHeight = bottomNav?.height ?: 0

            scrollView.setPadding(
                scrollView.paddingLeft,
                scrollView.paddingTop,
                scrollView.paddingRight,
                navHeight + 32 // add extra space (in pixels) if needed
            )
        }

        // Navigate to today's workout
        // How to display today's workout though?
        binding.startWorkoutBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_workoutDetailFragment)
        }

        // Timer Card Fragment
        binding.timerCard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_timerFragment)
        }

        // KJ to calories converter
        binding.calcKjButton.setOnClickListener {
            val kj = binding.kjInput.text.toString().toDoubleOrNull() ?: 0.0
            val kcal = kj / 4.184 // kj to calorie conversion
            val calString = "${kcal.toInt()} kcal"
            binding.kcalOutput.text = calString
        }

        // Steps to calories converter
        binding.calcStepsButton.setOnClickListener {
            val steps = binding.stepsInput.text.toString().toIntOrNull() ?: 0
            val calories = steps * 0.04 // roughly 0.04 calories in a step
            val calString = "${calories.toInt()} kcal"
            binding.stepsCaloriesOutput.text = calString

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}