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

    class Unknown(
        cause: Throwable? = null
    ) : AppException(
        message = "Unknown error",
        cause = cause
    )
}