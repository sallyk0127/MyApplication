package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.model.Entity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: EntityAdapter
    private var keypass: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keypass = arguments?.getString("keypass")

        adapter = EntityAdapter { selectedEntity ->
            val action = DashboardFragmentDirections
                .actionDashboardFragmentToDetailsFragment(selectedEntity)
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter

        // Simulated API response for testing
        val entities = getFakeEntities()
        adapter.updateData(entities)
    }

    private fun getFakeEntities(): List<Entity> {
        return listOf(
            Entity("Title 1", "Subtitle 1", "Desc 1"),
            Entity("Title 2", "Subtitle 2", "Desc 2"),
            Entity("Title 3", "Subtitle 3", "Desc 3")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
