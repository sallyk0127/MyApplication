package com.example.myapplication.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailsBinding.bind(view)

        val entity = args.entity

        binding.apply {
            textViewProperty1.text = entity.property1
            textViewProperty2.text = entity.property2
            textViewDescription.text = entity.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
