package com.example.week2amantasksxml.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterScreenViewModel : ViewModel() {
    private val _counter = MutableStateFlow(0)

    val counter = _counter.asStateFlow()

    fun updateCounter() {
        _counter.update {
            it + 1
        }
    }

}