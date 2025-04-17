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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.model.Entity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// DashboardFragment displays a list of entities fetched from the API using the keypass.

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    // ViewBinding to access layout views safely
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    // ViewModel injected using Hilt
    private val viewModel: DashboardViewModel by viewModels()

    // navArgs to retrieve arguments passed from LoginFragment
    private val args: DashboardFragmentArgs by navArgs()

    // RecyclerView adapter to display the list of entities
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

        // Setup RecyclerView with adapter and layout manager
        adapter = EntityAdapter { selectedEntity: Entity ->
            val action = DashboardFragmentDirections
                .actionDashboardFragmentToDetailsFragment(selectedEntity)
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Use keypass received from LoginFragment
        viewModel.loadDashboard(args.keypass)

        // Observe the list of entities and update UI
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.entities.collectLatest { entityList ->
                adapter.updateData(entityList)
            }
        }

        // Observe and show any error messages
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
