package com.example.newsapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.news.NewsRepository
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.data.models.PostModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: NewsRepository,
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

            is HomeContract.HomeIntent.BottomNavItemClicked -> {
                onBottomNavItemClicked(intent.route)
            }
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            updateLoadingState()

            try {
                val posts = repository.getPosts().map {
                    PostModel(
                        image = it.image,
                        category = it.category,
                        title = it.title,
                        authorIcon = it.authorIcon,
                        author = it.author,
                        publishDate = it.publishDate
                    )
                }
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

    private fun updateSuccessState(postDTOS: List<PostModel>) {
        _uiState.update {
            it.copy(
                isLoading = false, posts = postDTOS, errorMessage = null
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

    private fun onBottomNavItemClicked(destination: Screen) {
        when (destination) {
            Screen.Search -> {
                sendEffect(
                    HomeContract.HomeEffect.NavigateToSearch
                )
            }

            else -> {
                _uiState.update { currentState ->
                    currentState.copy(
                    )
                }
            }
        }
    }

    private fun sendEffect(
        effect: HomeContract.HomeEffect
    ) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}

