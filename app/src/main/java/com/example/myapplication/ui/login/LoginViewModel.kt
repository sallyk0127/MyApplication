package com.example.myapplication.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.LoginRequest
import com.example.myapplication.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for the Login screen.

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService // Injected from Hilt (provided by NetworkModule)
) : ViewModel() {

    // Holds the keypass returned by the API on successful login
    private val _keypass = MutableStateFlow<String?>(null)
    val keypass: StateFlow<String?> = _keypass

    // Holds any error messages to be displayed in the UI
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    /**
     * Called when user taps the Login button.
     * Sends a POST request to the login endpoint using Retrofit.
     * On success: sets the keypass.
     * On failure: sets an error message.
     */
    fun login(campus: String, username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = apiService.login(campus, LoginRequest(username, password))
                _keypass.value = response.keypass
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Login failed: ${e.message}"

                Log.e("LoginViewModel", "Login failed", e)
            }
        }
    }
}
