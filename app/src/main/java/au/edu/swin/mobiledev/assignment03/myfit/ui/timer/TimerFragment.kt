package au.edu.swin.mobiledev.assignment03.myfit.ui.timer

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentTimerBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TimerFragment : Fragment() {

    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    private var isRunning = false
    private var elapsedTime = 0L // in milliseconds
    private var startTime = 0L
    // Using Coroutine job for timer functionality
    private var timerJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startBtn.setOnClickListener {
            if (!isRunning) startTimer()
        }

        binding.pauseBtn.setOnClickListener {
            if (isRunning) pauseTimer()
        }

        binding.resetBtn.setOnClickListener {
            resetTimer()
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun startTimer() {
        isRunning = true
        startTime = SystemClock.elapsedRealtime() - elapsedTime

        timerJob = viewLifecycleOwner.lifecycleScope.launch {
            while (isActive) {
                elapsedTime = SystemClock.elapsedRealtime() - startTime
                updateTimerText()
                delay(100L)
            }
        }
    }

    private fun pauseTimer() {
        isRunning = false
        timerJob?.cancel() // cancel job if paused
    }

    private fun resetTimer() {
        isRunning = false
        timerJob?.cancel() // cancel job if reset
        elapsedTime = 0L
        updateTimerText()
    }

    private fun updateTimerText() {
        val seconds = (elapsedTime / 1000) % 60
        val minutes = (elapsedTime / 1000 / 60) % 60
        val hours = (elapsedTime / 1000 / 3600)
        binding.timerText.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timerJob?.cancel()
        _binding = null
    }
}