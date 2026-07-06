package com.example.week2amantasksxml.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week2amantasksxml.ToDoAdapter
import com.example.week2amantasksxml.ToDoData
import com.example.week2amantasksxml.applySystemBarsPadding
import com.example.week2amantasksxml.databinding.RecyclerViewScreenBinding

class RecyclerViewFragment : Fragment() {


    private var _binding: RecyclerViewScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ToDoAdapter
    private var toDoList = mutableListOf(
        ToDoData(1, "Understand The Recycler View", true),
        ToDoData(2, "Go To The Gym", false),
        ToDoData(3, "Do The Laundry", false),
        ToDoData(4, "Go To Work", true),
        ToDoData(5, "Do The Dishes", false),
    )
    private var nextID = 6


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecyclerViewScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.applySystemBarsPadding()
        adapter = ToDoAdapter()
        adapter.submitList(toDoList)
        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    if (viewHolder.bindingAdapterPosition != RecyclerView.NO_POSITION) {
                        val position = viewHolder.bindingAdapterPosition
                        deleteItem(position)

                    }
                }

            })
        binding.apply {
            rvtodo.adapter = adapter
            rvtodo.layoutManager = LinearLayoutManager(requireContext())
            addbutton.setOnClickListener {
                if (binding.edittext.text.isNotEmpty()) {

                    val newItem = ToDoData(
                        id = nextID++,
                        title = binding.edittext.text.toString(),
                        isChecked = false,
                    )

                    val newList = adapter.currentList.toMutableList()
                    newList.add(newItem)

                    toDoList = newList

                    adapter.submitList(newList)

                    binding.edittext.text.clear()
                }
            }
            itemTouchHelper.attachToRecyclerView(rvtodo)
        }


    }

    private fun deleteItem(position: Int) {
        val newList = adapter.currentList.toMutableList()
        newList.removeAt(position)
        toDoList = newList
        adapter.submitList(newList)
    }

}

