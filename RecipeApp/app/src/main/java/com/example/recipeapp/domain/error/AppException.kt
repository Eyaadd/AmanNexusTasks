package com.example.recipeapp.domain.error

sealed class AppException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause) {

    class Unauthorized(
        cause: Throwable? = null
    ) : AppException(
        message = "Unauthorized request",
        cause = cause
    )

    class Forbidden(
        cause: Throwable? = null
    ) : AppException(
        message = "Access denied",
        cause = cause
    )

    class NotFound(
        cause: Throwable? = null
    ) : AppException(
        message = "Resource not found",
        cause = cause
    )

    class NoInternet(
        cause: Throwable? = null
    ) : AppException(
        message = "No internet connection",
        cause = cause
    )

    class Timeout(
        cause: Throwable? = null
    ) : AppException(
        message = "Request timed out",
        cause = cause
    )

    class Server(
        cause: Throwable? = null
    ) : AppException(
        message = "Server error",
        cause = cause
    )

    class Database(
        cause: Throwable? = null
    ) : AppException(
        message = "Database error",
        cause = cause
    )

    class InvalidEmail(cause: Throwable? = null) : AppException(
        message = "Invalid email address",
        cause = cause
    )

    class InvalidCredentials(cause: Throwable? = null) : AppException(
        message = "Invalid email or password",
        cause = cause
    )

    class EmailAlreadyInUse(cause: Throwable? = null) : AppException(
        message = "Email address is already in use",
        cause = cause
    )

    class WeakPassword(cause: Throwable? = null) : AppException(
        message = "Password is too weak",
        cause = cause
    )

    class UserDisabled(cause: Throwable? = null) : AppException(
        message = "This account has been disabled",
        cause = cause
    )

    class TooManyRequests(cause: Throwable? = null) : AppException(
        message = "Too many attempts. Try again later",
        cause = cause
    )

    class Authentication(cause: Throwable? = null) : AppException(
        message = "Authentication failed",
        cause = cause
    )

    class Unknown(
        cause: Throwable? = null
    ) : AppException(
        message = "Unknown error",
        cause = cause
    )
}
