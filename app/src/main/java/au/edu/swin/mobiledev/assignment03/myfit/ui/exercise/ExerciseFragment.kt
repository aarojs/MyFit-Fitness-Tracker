package au.edu.swin.mobiledev.assignment03.myfit.ui.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.swin.mobiledev.assignment03.myfit.R
import au.edu.swin.mobiledev.assignment03.myfit.data.db.entities.Exercise
import au.edu.swin.mobiledev.assignment03.myfit.databinding.FragmentExerciseBinding


class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExerciseViewModel
    private lateinit var adapter: ExerciseAdapter

    private var exercises = mutableListOf<Exercise>() // you might need this idk


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using View Binding
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialise ViewModel
        viewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]

        // Initialise adapter with empty list and click handler
        adapter = ExerciseAdapter(mutableListOf()) {handleClick(it)}

        // Setup Recycle View
        binding.exerciseRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.exerciseRecycler.adapter = adapter

        // Observe LiveData from Viewmodel
        // implement this when youve done viewmodels

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleClick(exercise: Exercise){

    }
}