package com.example.newsapp.presentation.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.news.FakePostsRepository
import com.example.newsapp.data.source.remote.models.Post
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: FakePostsRepository = FakePostsRepository(),
    private val savedStateHandle: SavedStateHandle = SavedStateHandle()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeContract.HomeState()
    )
    val uiState = _uiState.asStateFlow()
    private val _effect = MutableSharedFlow<HomeContract.HomeEffect>()
    val effect = _effect.asSharedFlow()

    init {
        loadPosts()
    }

    fun onIntent(intent: HomeContract.HomeIntent) {
        when (intent) {
            HomeContract.HomeIntent.LoadPosts -> {
                loadPosts()
            }


            is HomeContract.HomeIntent.BottomNavItemClicked -> {
                onBottomNavItemClicked(intent.route)
            }
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            updateLoadingState()

            try {
                val posts = repository.getPosts()
                updateSuccessState(posts)
            } catch (e: Exception) {
                updateErrorState(e)
            }
        }
    }

    private fun updateLoadingState() {
        _uiState.update {
            it.copy(
                isLoading = true, errorMessage = null
            )
        }
    }

    private fun updateSuccessState(posts: List<Post>) {
        _uiState.update {
            it.copy(
                isLoading = false, posts = posts, errorMessage = null
            )
        }
    }

    private fun updateErrorState(exception: Exception) {
        _uiState.update {
            it.copy(
                isLoading = false, errorMessage = exception.message ?: "Something went wrong"
            )
        }
    }

    private fun onBottomNavItemClicked(route: String) {
        when (route) {
            "news" -> {
                viewModelScope.launch {
                    _effect.emit(
                        HomeContract.HomeEffect.NavigateToSearch
                    )
                }
            }

            else -> {
                _uiState.update {
                    it.copy(selectedRoute = route)
                }
            }
        }
    }
}