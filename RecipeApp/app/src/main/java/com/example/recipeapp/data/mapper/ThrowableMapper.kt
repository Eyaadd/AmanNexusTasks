package com.example.recipeapp.data.mapper

import android.database.sqlite.SQLiteException
import com.example.recipeapp.domain.error.AppException
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import java.net.ConnectException
import java.net.UnknownHostException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

fun Throwable.toAppException(): AppException {
    return when (this) {
        is AppException -> this

        is FirebaseAuthWeakPasswordException ->
            AppException.WeakPassword(this)

        is FirebaseAuthUserCollisionException ->
            AppException.EmailAlreadyInUse(this)

        is FirebaseAuthInvalidUserException -> {
            if (errorCode == "ERROR_USER_DISABLED") {
                AppException.UserDisabled(this)
            } else {
                AppException.InvalidCredentials(this)
            }
        }

        is FirebaseAuthInvalidCredentialsException -> {
            if (errorCode == "ERROR_INVALID_EMAIL") {
                AppException.InvalidEmail(this)
            } else {
                AppException.InvalidCredentials(this)
            }
        }

        is FirebaseTooManyRequestsException ->
            AppException.TooManyRequests(this)

        is FirebaseNetworkException ->
            AppException.NoInternet(this)

        is FirebaseAuthException ->
            AppException.Authentication(this)

        is ClientRequestException -> {
            when (response.status) {
                HttpStatusCode.Unauthorized ->
                    AppException.Unauthorized(this)

                HttpStatusCode.Forbidden ->
                    AppException.Forbidden(this)

                HttpStatusCode.NotFound ->
                    AppException.NotFound(this)

                else ->
                    AppException.Unknown(this)
            }
        }

        is ServerResponseException ->
            AppException.Server(this)

        is HttpRequestTimeoutException,
        is ConnectTimeoutException,
        is SocketTimeoutException ->
            AppException.Timeout(this)

        is ConnectException,
        is UnknownHostException ->
            AppException.NoInternet(this)

        is SQLiteException ->
            AppException.Database(this)

        else ->
            AppException.Unknown(this)
    }
}
