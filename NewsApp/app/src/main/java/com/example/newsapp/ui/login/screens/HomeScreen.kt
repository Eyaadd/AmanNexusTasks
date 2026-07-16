package com.example.newsapp.ui.login.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.theme.activeButtonColor
import com.example.newsapp.theme.darkGray
import com.example.newsapp.theme.roboto
import com.example.newsapp.theme.sourceSans
import com.example.newsapp.ui.login.components.BottomBar
import com.example.newsapp.ui.login.components.ButtonIcon
import com.example.newsapp.ui.login.components.NewsCard
import com.example.week2amantasksxml.ui.login.components.NewsFeedCard
import com.example.week2amantasksxml.models.NewsCardModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var currentRoute by remember {
        mutableStateOf("home")
    }
    val listOfNewsCard = listOf(
        NewsCardModel(
            image = R.drawable.news_image,
            category = "Sports",
            title = "Manchester City signs another world class midfielder for the upcoming season.",
            authorIcon = R.drawable.ic_person,
            author = "John Doe",
            publishDate = "2h ago"
        ),

        NewsCardModel(
            image = R.drawable.news_image,
            category = "Technology",
            title = "Google unveils its latest AI model with significant improvements in reasoning.",
            authorIcon = R.drawable.ic_person,
            author = "Sarah Williams",
            publishDate = "1h ago"
        ),

        NewsCardModel(
            image = R.drawable.news_image,
            category = "Business",
            title = "Global markets rise as investors react positively to quarterly earnings reports.",
            authorIcon = R.drawable.ic_person,
            author = "Michael Brown",
            publishDate = "4h ago"
        ),

        NewsCardModel(
            image = R.drawable.news_image,
            category = "Health",
            title = "Researchers discover promising treatment that could improve heart disease recovery.",
            authorIcon = R.drawable.ic_person,
            author = "Emma Wilson",
            publishDate = "6h ago"
        ),

        NewsCardModel(
            image = R.drawable.news_image,
            category = "Entertainment",
            title = "Award-winning director announces a new science fiction movie set for next summer.",
            authorIcon = R.drawable.ic_person,
            author = "Olivia Taylor",
            publishDate = "8h ago"
        ),

        NewsCardModel(
            image = R.drawable.news_image,
            category = "Science",
            title = "NASA releases stunning images captured by its newest deep-space telescope mission.",
            authorIcon = R.drawable.ic_person,
            author = "David Clark",
            publishDate = "10h ago"
        ),

        NewsCardModel(
            image = R.drawable.news_image,
            category = "Politics",
            title = "World leaders gather to discuss international climate and energy agreements.",
            authorIcon = R.drawable.ic_person,
            author = "Sophia Johnson",
            publishDate = "12h ago"
        ), NewsCardModel(
            image = R.drawable.news_image,
            category = "Travel",
            title = "Top destinations to visit this summer according to experienced travel experts.",
            authorIcon = R.drawable.ic_person,
            author = "Daniel Anderson",
            publishDate = "1d ago"
        )
    )

    Scaffold(

        bottomBar = {
            BottomBar(
                currentRoute = currentRoute
            ) {
                currentRoute = it
            }
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .scrollable(
                    rememberScrollState(), orientation = Orientation.Vertical
                )
                .padding(innerPadding)
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonIcon(
                    icon = R.drawable.ic_drawer
                )
                ButtonIcon(
                    icon = R.drawable.ic_bell
                )
            }
            Spacer(Modifier.size(24.dp))
            Column() {
                Text(
                    text = "Welcome back, Tyler!",
                    fontFamily = roboto,
                    fontSize = 24.sp,
                    color = activeButtonColor
                )
                Spacer(Modifier.size(6.dp))
                Text(
                    text = "Discover a world of news that matters to you",
                    color = darkGray,
                    fontFamily = sourceSans
                )
            }
            Spacer(Modifier.size(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Trending news", fontFamily = roboto, color = activeButtonColor
                )
                Text(
                    text = "See all", fontFamily = sourceSans, color = darkGray
                )
            }
            Spacer(Modifier.size(16.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = listOfNewsCard) { item ->
                    NewsCard(
                        image = item.image,
                        category = item.category,
                        title = item.title,
                        authorIcon = item.authorIcon,
                        author = item.author,
                        publishDate = item.publishDate
                    )

                }
            }
            Spacer(Modifier.size(32.dp))
            Text(
                text = "Recommendation", fontFamily = roboto, color = activeButtonColor
            )
            Spacer(Modifier.size(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listOfNewsCard) { item ->
                    NewsFeedCard(
                        image = item.image,
                        category = item.category,
                        title = item.title,
                        authorIcon = item.authorIcon,
                        author = item.author,
                        publishDate = item.publishDate
                    )
                }
            }
        }
    }

}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}