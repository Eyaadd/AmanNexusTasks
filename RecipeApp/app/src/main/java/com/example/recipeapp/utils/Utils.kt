package com.example.recipeapp.utils

import com.example.recipeapp.data.mapper.toAppException
import kotlinx.coroutines.CancellationException

suspend inline fun <T> safeCall(
    crossinline operation: suspend () -> T
): Result<T> {
    return try {
        Result.success(operation())
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Result.failure(exception.toAppException())
    }
}
