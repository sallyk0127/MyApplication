package com.example.myapplication.ui.dashboard

import android.annotation.SuppressLint
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

        @SuppressLint("SetTextI18n")
        fun bind(entity: Entity) {
            container.removeAllViews()

            entity.data.forEach { (key, value) ->
                val textView = TextView(container.context)
                textView.text = "${key.replaceFirstChar { it.uppercaseChar() }}: ${value ?: "N/A"}"
                container.addView(textView)
            }

            itemView.setOnClickListener {
                onItemClick(entity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity_dynamic, parent, false)
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
