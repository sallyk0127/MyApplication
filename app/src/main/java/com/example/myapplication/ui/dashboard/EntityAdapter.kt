package com.example.myapplication.ui.dashboard

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Entity

class EntityAdapter(
    private var dataList: List<Entity> = listOf(),
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    inner class EntityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val container: LinearLayout = view.findViewById(R.id.dynamicFieldsContainer)

        fun bind(entity: Entity) {
            container.removeAllViews()

            // Filter out the "description" key
            val dataToShow = entity.data.filterKeys { it != "description" }.entries.toList()

            dataToShow.forEachIndexed { index, entry ->
                val value = when (val raw = entry.value) {
                    is Double -> if (raw == raw.toInt().toDouble()) raw.toInt().toString() else raw.toString()
                    is Float -> if (raw == raw.toInt().toFloat()) raw.toInt().toString() else raw.toString()
                    else -> raw?.toString() ?: "N/A"
                }

                val valueView = TextView(container.context).apply {
                    text = value
                    textSize = 16f
                    setPadding(0, 0, 0, 12)

                    // Make only the first item bold
                    if (index == 0) {
                        setTypeface(null, Typeface.BOLD)
                    }
                }

                container.addView(valueView)
            }

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
