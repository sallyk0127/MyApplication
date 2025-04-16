package com.example.myapplication.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val campus = when (binding.radioGroup.checkedRadioButtonId) {
                binding.radioFootscray.id -> "footscray"
                binding.radioSydney.id -> "sydney"
                binding.radioOrt.id -> "ort"
                else -> null
            }

            if (username.isEmpty() || password.isEmpty() || campus == null) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("LoginFragment", "Login pressed: $campus / $username")
            // Calls AuthRepository to authenticate and calls keypass or error
            viewModel.login(campus, username, password)
        }

        // Observe successful login
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.keypass.collectLatest { keypass ->
                if (keypass != null) {
                    Log.d("LoginFragment", "Login successful. Keypass: $keypass")
                    // Observe keypass using SafeArgs and navigate to Dashboard when login is successful
                    val action = LoginFragmentDirections.actionLoginToDashboard(keypass)
                    findNavController().navigate(action)
                }
            }
        }

        // Observe login errors
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collectLatest { errorMsg ->
                errorMsg?.let {
                    Log.e("LoginFragment", "Login failed: $it")
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
