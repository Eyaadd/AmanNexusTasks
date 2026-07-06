package com.example.week2amantasksxml.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.R
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.week2amantasksxml.applySystemBarsPadding
import com.example.week2amantasksxml.databinding.LoginScreenBinding

class LoginFragment : Fragment() {

    private var _binding: LoginScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.applySystemBarsPadding()

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$")

            when {
                email.isEmpty() -> {
                    binding.tilEmail.error = "Email is required"
                }

                !email.matches(emailRegex) -> {
                    binding.tilEmail.error = "Enter a valid email address"
                }

                password.isEmpty() -> {
                    binding.tilPassword.error = "Password is required"
                }


                else -> {
                    binding.tilEmail.error = null
                    binding.tilPassword.error = null
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRecyclerViewFragment())


                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}