package com.example.newsapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ui.home.models.Post
import com.example.newsapp.ui.home.repository.FakePostsRepository
import com.example.newsapp.ui.home.repository.PostsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: PostsRepository = FakePostsRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostsUiState>(PostsUiState.Loading)

    val uiState = _uiState.asStateFlow()


    init {
        loadPosts()
    }


    private fun loadPosts() {

        viewModelScope.launch {

            try {
                updateLoadingState()

                updateSuccessState(repository.getPosts())

            } catch (e: Exception) {

                updateErrorState(e)
            }
        }
    }

    private fun updateLoadingState() {
        _uiState.value = PostsUiState.Loading

    }

    private fun updateSuccessState(posts: List<Post>) {
        _uiState.value = PostsUiState.Success(posts)

    }

    private fun updateErrorState(e: Exception) {
        _uiState.value = PostsUiState.Error(
            e.message ?: "Unknown error"
        )
    }
}