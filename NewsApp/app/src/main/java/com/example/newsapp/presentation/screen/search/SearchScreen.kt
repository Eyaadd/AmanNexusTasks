package com.example.newsapp.presentation.screen.search

import androidx.compose.foundation.background
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsapp.R
import com.example.newsapp.data.source.remote.models.Post
import com.example.newsapp.presentation.screen.home.components.ButtonIcon
import com.example.newsapp.presentation.screen.search.components.NewsSearchBar
import com.example.newsapp.presentation.screen.search.SearchViewModel
import com.example.week2amantasksxml.ui.login.components.NewsFeedCard

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(),
    onBackClick: () -> Unit,

    ) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    SearchScreenContent(
        uiState = uiState,
        searchQuery = searchQuery,
        onSearchQueryChange = { query ->
            viewModel.onIntent(
                SearchContract.SearchIntent.OnSearchQueryChanged(query)
            )
        },
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@Composable
private fun SearchScreenContent(
    uiState: SearchContract.SearchState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .padding(horizontal = 18.dp)
    ) {
        SearchHeader(
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.padding(top = 20.dp))

        when {
            uiState.isLoading -> {
                LoadingContent()
            }

            uiState.isError -> {
                ErrorContent()
            }


            else -> {
                SearchResults(posts = uiState.posts)
            }
        }
    }
}

@Composable
private fun SearchHeader(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonIcon(
            icon = R.drawable.ic_arrow_left,
            onClick = onBackClick
        )

        NewsSearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SearchResults(
    posts: List<Post>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = posts,
        ) { post ->
            NewsFeedCard(
                image = post.image,
                category = post.category,
                title = post.title,
                authorIcon = post.authorIcon,
                author = post.author,
                publishDate = post.publishDate
            )
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Something went wrong",
            fontSize = 16.sp,
            color = Color.Red
        )
    }
}

@Composable
private fun EmptySearchContent(
    searchQuery: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No results found for \"$searchQuery\"",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreenContent(
        uiState = SearchContract.SearchState(
            posts = emptyList(),
            isLoaded = true
        ),
        searchQuery = "",
        onSearchQueryChange = {},
        onBackClick = {}
    )
}