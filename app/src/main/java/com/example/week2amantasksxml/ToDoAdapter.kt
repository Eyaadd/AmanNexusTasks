package com.example.week2amantasksxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(

): ListAdapter<ToDoData, ToDoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val textView: TextView = itemView.findViewById(R.id.tdtext)
        val checkboxView: CheckBox = itemView.findViewById(R.id.checkboxitem)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TodoViewHolder,
        position: Int
    ) {
        val todo = getItem(position)
        holder.textView.text= todo.title
        holder.checkboxView.isChecked = todo.isChecked
    }

}



class TodoDiffCallback(
) : DiffUtil.ItemCallback<ToDoData>() {


    override fun areItemsTheSame(
        oldItem: ToDoData,
        newItem: ToDoData
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ToDoData,
        newItem: ToDoData
    ): Boolean {
        return oldItem == newItem
    }


}