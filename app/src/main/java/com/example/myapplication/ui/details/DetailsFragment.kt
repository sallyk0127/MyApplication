package com.example.myapplication.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import android.graphics.Typeface

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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entity = args.entity
        binding.detailsContainer.removeAllViews()

        // Show all fields except "description"
        entity.data.forEach { (key, value) ->
            if (key != "description") {
                val titleView = TextView(requireContext()).apply {
                    text = key.replaceFirstChar { it.uppercase() }
                    textSize = 16f
                    setTypeface(null, Typeface.BOLD)
                    setPadding(0, 12, 0, 4)
                }

                val valueView = TextView(requireContext()).apply {
                    text = when (value) {
                        is Double -> if (value == value.toInt().toDouble()) value.toInt().toString() else value.toString()
                        is Float -> if (value == value.toInt().toFloat()) value.toInt().toString() else value.toString()
                        else -> value?.toString() ?: "N/A"
                    }
                    textSize = 16f
                    setPadding(0, 0, 0, 8)
                }

                binding.detailsContainer.addView(titleView)
                binding.detailsContainer.addView(valueView)
            }
        }

        // Handle description if present
        entity.description?.let { descriptionText ->
            val descTitle = TextView(requireContext()).apply {
                text = "Description"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setPadding(0, 16, 0, 4)
            }

            val descValue = TextView(requireContext()).apply {
                text = descriptionText
                textSize = 16f
                setPadding(0, 0, 0, 8)
            }

            binding.detailsContainer.addView(descTitle)
            binding.detailsContainer.addView(descValue)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
