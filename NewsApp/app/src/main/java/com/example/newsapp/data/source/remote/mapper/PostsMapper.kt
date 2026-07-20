package com.example.newsapp.data.source.remote.mapper

import com.example.newsapp.data.source.remote.dtos.PostsDTO
import com.example.newsapp.data.source.remote.models.Post

fun PostsDTO.toDomain(): Post = Post(
    id = id,
    title = title,
    body = body,
    tags = tags,
    likeCount = reactions.likes,
    dislikeCount = reactions.dislikes,
    views = views
)