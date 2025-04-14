package com.example.myapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entity = args.entity

        binding.apply {
            exerciseNameTitle.text = entity.exerciseName
            muscleGroupTitle.text = entity.muscleGroup
            equipmentDescription.text = entity.equipment
            difficultyDescription.text = entity.difficulty
            caloriesBurnedPerHourDescription.text = entity.caloriesBurnedPerHour.toString()
            description.text = entity.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
