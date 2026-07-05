package com.example.week2amantasksxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(
   private var toDoList: List<ToDoData>
): RecyclerView.Adapter<ToDoAdapter.TodoViewHolder>() {

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
        holder.textView.text= toDoList[position].title
        holder.checkboxView.isChecked = toDoList[position].isChecked
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    fun updateList(newList: List<ToDoData>) {

        val diffCallback = TodoDiffUtil(toDoList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        toDoList = newList
        diffResult.dispatchUpdatesTo(this)
    }

}



class TodoDiffUtil(
    private val oldList: List<ToDoData>,
    private val newList: List<ToDoData>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition].id ==
                newList[newItemPosition].id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] ==
                newList[newItemPosition]
    }
}