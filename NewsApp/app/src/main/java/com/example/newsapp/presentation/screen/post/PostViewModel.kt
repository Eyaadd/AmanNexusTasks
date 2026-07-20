package com.example.newsapp.presentation.screen.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.posts.PostsRepository
import com.example.newsapp.data.source.remote.models.Post
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PostViewModel(
    private val repository: PostsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostContract.PostState>(PostContract.PostState())

    val uiState = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        updateLoadingState()
        viewModelScope.launch {
            try {
                val response = repository.getPosts()
                updateSuccessState(response)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                updateErrorState(e.message ?: "Something Went Wrong")
            }
        }
    }


    private fun updateLoadingState() {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
                onError = false
            )
        }
    }

    private fun updateSuccessState(posts: List<Post>) {
        _uiState.update {
            it.copy(
                isLoading = false,
                onError = false,
                errorMessage = null,
                success = posts
            )
        }
    }

    private fun updateErrorState(e: String) {
        _uiState.update {
            it.copy(
                isLoading = false,
                errorMessage = e,
                onError = true
            )
        }
    }

}