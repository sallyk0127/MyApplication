package com.example.myapplication.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Entity
import com.example.myapplication.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _entities = MutableStateFlow<List<Entity>>(emptyList())
    val entities: StateFlow<List<Entity>> = _entities

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            try {
                //Fetch dashboard data using the keypass received from login
                val response = apiService.getDashboardData(keypass)
                Log.d("DashboardViewModel", "Response: $response")
                _entities.value = response.entities
                _error.value = null
            } catch (e: Exception) {
                _entities.value = emptyList()
                _error.value = "Failed to load dashboard: ${e.message}"
                Log.e("DashboardViewModel", "Error loading dashboard", e)
            }
        }
    }
}
