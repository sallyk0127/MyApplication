package com.example.myapplication

import com.example.myapplication.model.LoginResponse
import com.example.myapplication.ui.login.AuthRepository
import com.example.myapplication.ui.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val repository: AuthRepository = mockk()
    private lateinit var viewModel: LoginViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        coEvery {
            repository.login("sydney", "Sally", "s4684282")
        } returns LoginResponse(key = "fitness")

        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login success sets keypass correctly`() = runTest {
        viewModel.login("sydney", "Sally", "s4684282")
        advanceUntilIdle()

        assertEquals("fitness", viewModel.keypass.value)
        assertNull(viewModel.error.value)
    }

    @Test
    fun `login failure sets error`() = runTest {
        coEvery {
            repository.login(any(), any(), any())
        } throws Exception("Network error")

        viewModel.login("sydney", "Sally", "wrongpass")
        advanceUntilIdle()

        assertEquals("Login failed: Network error", viewModel.error.value)
        assertNull(viewModel.keypass.value)
    }
}
