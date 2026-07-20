package com.example.newsapp.presentation.screen.post


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.data.repository.posts.PostsRepositoryImp
import com.example.newsapp.data.source.remote.NetworkModule
import com.example.newsapp.data.source.remote.models.Post
import com.example.newsapp.presentation.screen.post.components.PostCard

@Composable
fun PostsScreen(
    modifier: Modifier = Modifier,
    onPostClick: (Post) -> Unit = {},
    viewModel: PostViewModel = viewModel(
        factory = PostViewModelFactory(
            repository = PostsRepositoryImp(api = NetworkModule.postApi)
        )
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()



    when {
        uiState.isLoading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        uiState.onError -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(uiState.errorMessage ?: "Something went wrong")
            }
        }
        uiState.success.isEmpty() -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No posts found")
            }
        }
        else -> {
            PostsScreenContent(
                posts = uiState.success,
                modifier = modifier,
                onPostClick = onPostClick
            )
        }
    }
}
@Composable
fun PostsScreenContent(
    posts: List<Post>,
    modifier: Modifier = Modifier,
    onPostClick: (Post) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = posts,
            key = { post -> post.id }
        ) { post ->
            PostCard(
                post = post,
                onClick = {
                    onPostClick(post)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostsScreenPreview() {
        PostsScreenContent(
            posts = listOf(
                Post(
                    id = 1,
                    title = "World Cup",
                    body = "Cristiano Ronaldo delivers another historic performance for Portugal.",
                    tags = listOf("Football", "World Cup", "Portugal"),
                    likeCount = 12_458,
                    dislikeCount = 317,
                    views = 256_431
                ),
                Post(
                    id = 2,
                    title = "Jetpack Compose",
                    body = "Jetpack Compose simplifies Android UI development using declarative programming.",
                    tags = listOf("Android", "Kotlin", "Compose"),
                    likeCount = 3_210,
                    dislikeCount = 42,
                    views = 28_905
                )
            )
        )
}