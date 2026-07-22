package com.example.newsapp.data.repository.news

import com.example.newsapp.R
import com.example.newsapp.data.source.remote.dtos.ArticlesDTO
import kotlinx.coroutines.delay

class FakeNewsRepository : NewsRepository {

    override suspend fun getPosts(): List<ArticlesDTO> {

        delay(2000)

        return listOf(
            ArticlesDTO(
                image = R.drawable.news_image,
                category = "Sports",
                title = "Manchester City signs another world class midfielder for the upcoming season.",
                authorIcon = R.drawable.ic_person,
                author = "John Doe",
                publishDate = "2h ago"
            ),

            ArticlesDTO(
                image = R.drawable.news_image,
                category = "Technology",
                title = "Google unveils its latest AI model with significant improvements in reasoning.",
                authorIcon = R.drawable.ic_person,
                author = "Sarah Williams",
                publishDate = "1h ago"
            ),

            ArticlesDTO(
                image = R.drawable.news_image,
                category = "Business",
                title = "Global markets rise as investors react positively to quarterly earnings reports.",
                authorIcon = R.drawable.ic_person,
                author = "Michael Brown",
                publishDate = "4h ago"
            ),

            ArticlesDTO(
                image = R.drawable.news_image,
                category = "Health",
                title = "Researchers discover promising treatment that could improve heart disease recovery.",
                authorIcon = R.drawable.ic_person,
                author = "Emma Wilson",
                publishDate = "6h ago"
            ),

            ArticlesDTO(
                image = R.drawable.news_image,
                category = "Entertainment",
                title = "Award-winning director announces a new science fiction movie.",
                authorIcon = R.drawable.ic_person,
                author = "Olivia Taylor",
                publishDate = "8h ago"
            ),

            ArticlesDTO(
                image = R.drawable.news_image,
                category = "Science",
                title = "NASA releases stunning images captured by its newest telescope.",
                authorIcon = R.drawable.ic_person,
                author = "David Clark",
                publishDate = "10h ago"
            ),

            ArticlesDTO(
                image = R.drawable.news_image,
                category = "Politics",
                title = "World leaders gather to discuss climate agreements.",
                authorIcon = R.drawable.ic_person,
                author = "Sophia Johnson",
                publishDate = "12h ago"
            ),

            ArticlesDTO(
                image = R.drawable.news_image,
                category = "Travel",
                title = "Top destinations to visit this summer.",
                authorIcon = R.drawable.ic_person,
                author = "Daniel Anderson",
                publishDate = "1d ago"
            ),

            ArticlesDTO(
                image = R.drawable.news_image,
                category = "Gaming",
                title = "New gaming console announced with powerful hardware.",
                authorIcon = R.drawable.ic_person,
                author = "Alex Brown",
                publishDate = "1d ago"
            ),

            ArticlesDTO(
                image = R.drawable.news_image,
                category = "World",
                title = "Major events shaping the world today.",
                authorIcon = R.drawable.ic_person,
                author = "Emily Davis",
                publishDate = "2d ago"
            )
        )
    }
}