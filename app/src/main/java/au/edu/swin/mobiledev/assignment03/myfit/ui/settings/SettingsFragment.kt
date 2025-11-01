package au.edu.swin.mobiledev.assignment03.myfit.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentSettingsBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SettingsViewModel::class.java]


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userSettings.collectLatest { settings ->

                val name = settings["name"] as? String
                val weight = settings["weight"] as? Float
                val goalWeight = settings["goal_weight"] as? Float
                val age = settings["age"] as? Int

                if (!name.isNullOrEmpty()) binding.profileName.setText(name)
                if (weight != null && weight != 0f) binding.profileWeight.setText(weight.toString())
                if (goalWeight != null && goalWeight != 0f) binding.profileGoalWeight.setText(goalWeight.toString())
                if (age != null && age != 0) binding.profileAge.setText(age.toString())
            }
        }

        binding.settingsSaveBtn.setOnClickListener {
            val name = binding.profileName.text.toString()
            val weight = binding.profileWeight.text.toString().toFloatOrNull() ?: 0f
            val goalWeight = binding.profileGoalWeight.text.toString().toFloatOrNull() ?: 0f
            val age = binding.profileAge.text.toString().toIntOrNull() ?: 0

            viewModel.saveSettings(name, weight, goalWeight, age)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
