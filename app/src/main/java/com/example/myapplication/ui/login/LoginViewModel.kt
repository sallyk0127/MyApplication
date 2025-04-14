package com.example.myapplication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _keypass = MutableStateFlow<String?>(null)
    val keypass: StateFlow<String?> = _keypass

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(campus: String, username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = apiService.login(campus, mapOf("username" to username, "password" to password))
                _keypass.value = response.keypass
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Login failed: ${e.message}"
            }

            catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Login failed: ${e.localizedMessage ?: "Unknown error"}"
            }

        }
    }
}
