package com.example.myapplication

import com.example.myapplication.model.Entity
import com.example.myapplication.ui.dashboard.EntityAdapter
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EntityAdapterTest {

    private lateinit var adapter: EntityAdapter
    private val mockCallback: (Entity) -> Unit = {}

    private val sampleEntity = Entity(
        exerciseName = "Push Ups",
        muscleGroup = "Chest",
        equipment = "Bodyweight",
        difficulty = "Medium",
        caloriesBurnedPerHour = 300,
        description = "A great upper body exercise"
    )

    @Before
    fun setUp() {
        adapter = spyk(EntityAdapter(mockCallback))
    }

    @Test
    fun `getItemCount returns correct size`() {
        val entityList = listOf(
            sampleEntity,
            sampleEntity.copy(exerciseName = "Sit Ups"),
            sampleEntity.copy(exerciseName = "Squats")
        )
        adapter.updateData(entityList)
        assertEquals(3, adapter.itemCount)
    }

    @Test
    fun `setData updates dataList and notifies adapter`() {
        val entityList = listOf(
            sampleEntity.copy(exerciseName = "Plank"),
            sampleEntity.copy(exerciseName = "Burpees")
        )

        adapter.updateData(entityList)

        assertEquals(2, adapter.itemCount)
        verify { adapter.notifyDataSetChanged() }
    }
}
