package com.example.week2amantasksxml.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.week2amantasksxml.R
import com.example.week2amantasksxml.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // Send List that has 5 users, and then display them in the recycler view screen without using singleton

        binding.btnNext.setOnClickListener {
            val action =
                HomeFragmentDirections
                    .actionHomeFragmentToDetailsFragment(
                        binding.usernameEdtText.text.toString().trim()
                    )

            findNavController().navigate(action)
        }
        binding.btnCall.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}