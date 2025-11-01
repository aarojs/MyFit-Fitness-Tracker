package au.edu.swin.mobiledev.assignment03.myfit.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userSettings.collectLatest { settings ->
                binding.profileName.setText(settings["name"].toString())
                binding.profileWeight.setText(settings["weight"].toString())
                binding.profileGoalWeight.setText(settings["weight"].toString())
                binding.profileAge.setText(settings["age"].toString())
            }

            binding.settingsSaveBtn.setOnClickListener {
                val name = binding.profileName.text.toString()
                val weight = binding.profileWeight.text.toString().toFloatOrNull() ?: 0f
                val goalWeight = binding.profileGoalWeight.text.toString().toFloatOrNull() ?: 0f
                val age = binding.profileAge.text.toString().toIntOrNull() ?: 0

                viewModel.saveSettings(name, weight, goalWeight, age)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
