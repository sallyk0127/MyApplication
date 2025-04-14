package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.model.Entity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        adapter = EntityAdapter { selectedEntity: Entity ->
            val action = DashboardFragmentDirections
                .actionDashboardFragmentToDetailsFragment(selectedEntity)
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Fetch keypass from arguments
        val keypass = arguments?.getString("keypass")
        if (keypass != null) {
            viewModel.loadDashboard(keypass)
        } else {
            Toast.makeText(requireContext(), "Missing keypass", Toast.LENGTH_SHORT).show()
        }

        // Collect entities
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.entities.collectLatest { entityList ->
                adapter.updateData(entityList)
            }
        }

        // Collect error messages
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collectLatest { errorMsg: String? ->
                errorMsg?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
