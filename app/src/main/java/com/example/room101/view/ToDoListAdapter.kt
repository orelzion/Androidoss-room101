package com.example.room101.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room101.R
import com.example.room101.viewmodel.ToDoItemViewData

class ToDoAdapter(private val clickListener: (item: ToDoItemViewData) -> Unit) :
    ListAdapter<ToDoItemViewData, ToDoViewHolder>(ToDoDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_todo_item, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView: TextView = itemView.findViewById(R.id.text)
    private val checkBox: CheckBox = itemView.findViewById(R.id.completedCheckBox)

    fun bind(item: ToDoItemViewData, clickListener: (item: ToDoItemViewData) -> Unit) {
        textView.text = item.text
        checkBox.isChecked = item.isDone
        checkBox.setOnCheckedChangeListener { compoundButton, b ->
            if (b != item.isDone) {
                clickListener.invoke(item.copy(isDone = b))
            }
        }
    }
}

class ToDoDiffUtil : DiffUtil.ItemCallback<ToDoItemViewData>() {
    override fun areItemsTheSame(oldItem: ToDoItemViewData, newItem: ToDoItemViewData): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ToDoItemViewData, newItem: ToDoItemViewData): Boolean {
        return oldItem == newItem
    }
}