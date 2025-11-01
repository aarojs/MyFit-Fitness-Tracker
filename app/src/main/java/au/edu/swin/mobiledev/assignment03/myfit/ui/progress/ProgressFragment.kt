package au.edu.swin.mobiledev.assignment03.myfit.ui.progress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.ProgressLog
import au.edu.swin.mobiledev.assignment03.myfit.data.db.relations.ProgressWithWorkout
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentExerciseBinding
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentProgressBinding


class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProgressViewModel
    private lateinit var adapter: ProgressLogAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialise ViewModel
        viewModel = ViewModelProvider(this)[ProgressViewModel::class.java]

        // Initialise adapter with empty list and click handler
        adapter = ProgressLogAdapter(mutableListOf(), object: ProgressLogAdapter.ProgressLogItemListener {
            override fun onDelete(progressLog: ProgressWithWorkout) {
                viewModel.delete(progressLog.progressLog)
            }
        })

        // Setup recycle view
        binding.progressRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.progressRecycler.adapter = adapter

        // Observe LiveData from ViewModel
        viewModel.allLogsWithWorkout.observe(viewLifecycleOwner) { progressLogs ->
            adapter.updateData(progressLogs)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}