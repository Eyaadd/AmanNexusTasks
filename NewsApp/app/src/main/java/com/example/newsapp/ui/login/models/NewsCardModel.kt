package com.example.week2amantasksxml.models


import androidx.annotation.DrawableRes

data class NewsCardModel(
    @DrawableRes
    val image: Int,

    val category: String,

    val title: String,

    @DrawableRes
    val authorIcon: Int,

    val author: String,

    val publishDate: String
)
