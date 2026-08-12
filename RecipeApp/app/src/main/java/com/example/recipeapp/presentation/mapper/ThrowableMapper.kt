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

        is AppException.InvalidEmail ->
            "Enter a valid email address."

        is AppException.InvalidCredentials ->
            "The email or password is incorrect."

        is AppException.EmailAlreadyInUse ->
            "An account already exists for this email."

        is AppException.WeakPassword ->
            "Choose a stronger password."

        is AppException.UserDisabled ->
            "This account has been disabled."

        is AppException.TooManyRequests ->
            "Too many attempts. Please try again later."

        is AppException.Authentication ->
            "Authentication failed. Please try again."

        is AppException.Unknown ->
            "Something went wrong."

        else ->
            message ?: "Something went wrong."
    }
}
