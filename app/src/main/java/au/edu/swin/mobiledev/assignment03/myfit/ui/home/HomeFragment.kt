package au.edu.swin.mobiledev.assignment03.myfit.ui.home

import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentHomeBinding
import au.edu.swin.mobiledev.assignment03.myfit.ui.settings.SettingsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!! // assert that binding is not null when accessed
    private lateinit var settingsViewModel: SettingsViewModel

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
        settingsViewModel = ViewModelProvider(requireActivity())[SettingsViewModel::class.java]

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

        // user Profile
        viewLifecycleOwner.lifecycleScope.launch {
            settingsViewModel.userSettings.collectLatest { settings ->
                val name = settings["name"] ?: ""
                val weight = settings["weight"] ?: 0f
                val goalWeight = settings["goal_weight"] ?: 0f
                val age = settings["age"] ?: 0

                val nameString = "Name: $name"
                val weightString = "Weight: $weight kg"
                val goalWeightString = "Goal Weight: $goalWeight kg"
                val ageString = "Age: $age"
                binding.userName.text = nameString
                binding.userWeight.text = weightString
                binding.userGoalWeight.text = goalWeightString
                binding.userAge.text = ageString
            }
        }

        // Timer Card Fragment
        binding.timerStartBtn.setOnClickListener {
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