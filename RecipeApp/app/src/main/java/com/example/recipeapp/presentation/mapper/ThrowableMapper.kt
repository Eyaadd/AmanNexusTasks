package com.example.recipeapp.presentation.mapper

import com.example.recipeapp.domain.error.AppException

fun Throwable.toUiMessage(): String {
    return when (this) {

        is AppException.Unauthorized ->
            "Unauthorized request. Please check the API key."

        is AppException.Forbidden ->
            "Access denied or API quota exceeded."

        is AppException.NotFound ->
            "The requested recipe could not be found."

        is AppException.NoInternet ->
            "Couldn't connect to the server. Check your internet connection."

        is AppException.Timeout ->
            "The request timed out. Please try again."

        is AppException.Server ->
            "The server is currently unavailable. Please try again."

        is AppException.Database ->
            "Unable to update your favorites."

        is AppException.Unknown ->
            "Something went wrong."

        else ->
            message ?: "Something went wrong."
    }
}