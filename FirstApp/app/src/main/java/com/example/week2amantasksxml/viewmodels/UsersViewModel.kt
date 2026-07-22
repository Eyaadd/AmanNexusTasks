package com.example.week2amantasksxml.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week2amantasksxml.models.User
import com.example.week2amantasksxml.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class UsersViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {
    private var allUsers: List<User> = emptyList()
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _search = MutableStateFlow("")

    val search = _search


    init {
        observeSearch()
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            allUsers = usersRepository.fetchUsers()
            // What if I want to update data in IO Thread, will use something instead of .value
            _users.value = allUsers
        }
    }


    fun onSearchQueryChanged(query: String) {
        _search.value = query
    }

    private fun filterUsers(query: String) {
        _users.value =
            if (query.isBlank()) {
                allUsers
            } else {
                allUsers.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearch() {
        viewModelScope.launch {
            _search
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    filterUsers(query)
                }
        }
    }
}