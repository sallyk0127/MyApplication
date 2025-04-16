package com.example.myapplication

import com.example.myapplication.model.DashboardResponse
import com.example.myapplication.model.Entity
import com.example.myapplication.network.ApiService
import com.example.myapplication.ui.dashboard.DashboardViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    private val apiService: ApiService = mockk()
    private lateinit var viewModel: DashboardViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val testEntities = listOf(
        Entity(
            exerciseName = "Push Ups",
            muscleGroup = "Chest",
            equipment = "Bodyweight",
            difficulty = "Medium",
            caloriesBurnedPerHour = 300,
            description = "Great for upper body"
        ),
        Entity(
            exerciseName = "Squats",
            muscleGroup = "Legs",
            equipment = "None",
            difficulty = "Easy",
            caloriesBurnedPerHour = 250,
            description = "Lower body workout"
        )
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        coEvery { apiService.getDashboardData("fitness") } returns DashboardResponse(
            entities = testEntities,
            entityTotal = 2
        )

        viewModel = DashboardViewModel(apiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadDashboard sets entities correctly`() = runTest {
        viewModel.loadDashboard("fitness")
        advanceUntilIdle()

        assertEquals(2, viewModel.entities.value.size)
        assertEquals("Push Ups", viewModel.entities.value[0].exerciseName)
    }

    @Test
    fun `loadDashboard handles errors properly`() = runTest {
        coEvery { apiService.getDashboardData(any()) } throws Exception("API Error")

        viewModel.loadDashboard("fitness")
        advanceUntilIdle()

        assertEquals(emptyList<Entity>(), viewModel.entities.value)
        assertEquals("Failed to load dashboard: API Error", viewModel.error.value)
    }
}
