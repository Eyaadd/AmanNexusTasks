package com.example.newsapp.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.R
import com.example.newsapp.data.repository.news.FakeNewsRepository
import com.example.newsapp.presentation.theme.activeButtonColor
import com.example.newsapp.presentation.theme.darkGray
import com.example.newsapp.presentation.theme.roboto
import com.example.newsapp.presentation.theme.sourceSans
import com.example.newsapp.presentation.screen.home.components.BottomBar
import com.example.newsapp.presentation.screen.home.components.ButtonIcon
import com.example.newsapp.presentation.screen.home.components.HomeScreenShimmer
import com.example.newsapp.presentation.screen.home.components.NewsCard
import com.example.week2amantasksxml.ui.login.components.NewsFeedCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onNavigateToSearch: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            repository = FakeNewsRepository()
        )
    )
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                HomeContract.HomeEffect.NavigateToSearch -> {
                    onNavigateToSearch()
                }

                is HomeContract.HomeEffect.ShowError -> {
                }
            }
        }
    }

    HomeScreenContent(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}

@Composable
fun HomeScreenContent(
    state: HomeContract.HomeState,
    onIntent: (HomeContract.HomeIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .statusBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonIcon(
                    icon = R.drawable.ic_drawer
                ){}

                ButtonIcon(
                    icon = R.drawable.ic_bell
                ){}
            }
        },
        bottomBar = {
            BottomBar(
                currentRoute = state.selectedRoute,
                onItemClick = { route ->
                    onIntent(
                        HomeContract.HomeIntent.BottomNavItemClicked(route)
                    )
                }
            )
        }
    ) { innerPadding ->

        when {
            state.isLoading -> {
                HomeScreenShimmer(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 18.dp)
                )
            }

            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(18.dp),
                    color = darkGray
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 18.dp)
                ) {
                    item {
                        Spacer(Modifier.height(24.dp))

                        Text(
                            text = "Welcome back, Tyler!",
                            fontFamily = roboto,
                            fontSize = 24.sp,
                            color = activeButtonColor
                        )

                        Spacer(Modifier.height(6.dp))

                        Text(
                            text = "Discover a world of news that matters to you",
                            color = darkGray,
                            fontFamily = sourceSans
                        )
                    }

                    item {
                        Spacer(Modifier.height(24.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Trending news",
                                fontFamily = roboto,
                                color = activeButtonColor
                            )

                            Text(
                                text = "See all",
                                fontFamily = sourceSans,
                                color = darkGray
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(
                                items = state.posts,
                                key = { post -> post.title }
                            ) { post ->
                                NewsCard(
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

                    item {
                        Spacer(Modifier.height(32.dp))
                        Text(
                            text = "Recommendation",
                            fontFamily = roboto,
                            color = activeButtonColor
                        )

                        Spacer(Modifier.height(16.dp))
                    }

                    items(
                        items = state.posts,
                        key = { post -> post.title }
                    ) { post ->
                        NewsFeedCard(
                            image = post.image,
                            category = post.category,
                            title = post.title,
                            authorIcon = post.authorIcon,
                            author = post.author,
                            publishDate = post.publishDate
                        )

                        Spacer(Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenLoadingPreview() {
    HomeScreenContent(
        state = HomeContract.HomeState(
            isLoading = false
        ),
        onIntent = {}
    )
}