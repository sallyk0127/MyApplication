package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EntityAdapter
    private var keypass: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keypass = arguments?.getString("keypass") // Pass key from Login screen via bundle

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = EntityAdapter { selectedEntity ->
            val bundle = Bundle().apply {
                putSerializable("entity", selectedEntity)
            }
            findNavController().navigate(R.id.action_dashboard_to_details, bundle)
        }
        recyclerView.adapter = adapter

        // Simulated API fetch
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
}
