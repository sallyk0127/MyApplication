package com.example.myapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Entity

class DashboardViewModel : ViewModel() {

    private val _entities = MutableLiveData<List<Entity>>()
    val entities: LiveData<List<Entity>> get() = _entities

    fun loadDashboard() {
        // TEMPORARY STATIC DATA — Replace with actual API logic later
        _entities.value = listOf(
            Entity("Value 1", "Value 2", "Some description"),
            Entity("Another 1", "Another 2", "Another desc")
        )
    }
}
