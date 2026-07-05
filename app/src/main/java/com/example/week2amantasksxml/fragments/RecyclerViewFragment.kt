package com.example.week2amantasksxml.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2amantasksxml.ToDoAdapter
import com.example.week2amantasksxml.ToDoData
import com.example.week2amantasksxml.databinding.RecyclerViewScreenBinding
import kotlin.text.clear
import kotlin.toString

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
        adapter = ToDoAdapter(toDoList)

        binding.rvtodo.adapter = adapter
        binding.rvtodo.layoutManager = LinearLayoutManager(requireContext())

        binding.addbutton.setOnClickListener {
            if (binding.edittext.text.isNotEmpty()) {

                val newItem = ToDoData(
                    id = nextID++,
                    title = binding.edittext.text.toString(),
                    isChecked = false,
                    )

                val newList = toDoList.toMutableList()
                newList.add(newItem)

                toDoList = newList

                adapter.updateList(newList)

                binding.edittext.text.clear()
            }
        }
    }

}