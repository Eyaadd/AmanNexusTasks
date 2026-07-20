package com.example.newsapp.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.news.FakeNewsRepository
import com.example.newsapp.data.repository.news.NewsRepository
import com.example.newsapp.presentation.uimodel.PostModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val repository: NewsRepository = FakeNewsRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchContract.SearchState())
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _effect = MutableSharedFlow<SearchContract.SearchEffect>()
    val effect = _effect.asSharedFlow()

    private var allPosts: List<PostModel> = emptyList()

    init {
        loadPosts()
        observeSearchQuery()
    }

    fun onIntent(intent: SearchContract.SearchIntent) {
        when (intent) {

            is SearchContract.SearchIntent.OnSearchQueryChanged -> {
                _searchQuery.value = intent.query
            }
        }
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    filterPosts(query)
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

                allPosts = posts
                updateSuccessState()
            } catch (exception: Exception) {
                updateErrorState()
            }
        }
    }

    private fun filterPosts(query: String) {
        val trimmedQuery = query.trim()

        val filteredPosts = if (trimmedQuery.isBlank()) {
            emptyList()
        } else {
            allPosts.filter { post ->
                post.title.contains(
                    other = trimmedQuery,
                    ignoreCase = true
                ) ||
                        post.author.contains(
                            other = trimmedQuery,
                            ignoreCase = true
                        ) ||
                        post.category.contains(
                            other = trimmedQuery,
                            ignoreCase = true
                        )
            }
        }

        _uiState.update {
            it.copy(postModel = filteredPosts)
        }
    }

    private fun updateLoadingState() {
        _uiState.update {
            it.copy(
                isLoading = true,
                isLoaded = false,
                isError = false
            )
        }
    }

    private fun updateSuccessState() {
        _uiState.update {
            it.copy(
                isLoading = false,
                postModel = emptyList(),
                isLoaded = true,
                isError = false
            )
        }
    }

    private fun updateErrorState() {
        _uiState.update {
            it.copy(
                isLoading = false,
                isLoaded = false,
                isError = true
            )
        }
    }


}