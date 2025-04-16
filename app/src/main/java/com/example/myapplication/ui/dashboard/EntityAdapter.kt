package com.example.myapplication.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Entity

// RecyclerView Adapter displays a list of Entity objects in the Dashboard.

class EntityAdapter(
    private var dataList: List<Entity> = listOf(),
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    // ViewHolder for each entity item. Binds data to the item layout views
    inner class EntityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val prop1: TextView = view.findViewById(R.id.exerciseNameEntity)
        private val prop2: TextView = view.findViewById(R.id.muscleGroupEntity)
        private val prop3: TextView = view.findViewById(R.id.equipmentEntity)
        private val prop4: TextView = view.findViewById(R.id.difficultyEntity)
        private val prop5: TextView = view.findViewById(R.id.caloriesBurnedPerHourEntity)

        // Click listener that calls the provided onItemClick callback
        fun bind(entity: Entity) {
            prop1.text = entity.exerciseName
            prop2.text = entity.muscleGroup
            prop3.text = entity.equipment
            prop4.text = entity.difficulty
            prop5.text = entity.caloriesBurnedPerHour.toString()

            itemView.setOnClickListener {
                onItemClick(entity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newDataList: List<Entity>) {
        dataList = newDataList
        notifyDataSetChanged()
    }
}