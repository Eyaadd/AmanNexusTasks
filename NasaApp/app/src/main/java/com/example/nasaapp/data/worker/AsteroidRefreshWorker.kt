package com.example.nasaapp.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.nasaapp.data.repository.AsteroidRepository
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CancellationException

class AsteroidRefreshWorker(
    appContext: Context,
    workerParameters: WorkerParameters,
    private val repository: AsteroidRepository,
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {

        return try {
            repository.refreshAsteroids()

            Log.d(TAG, "Asteroids refreshed successfully")
            Result.success()

        } catch (exception: CancellationException) {
            throw exception

        } catch (exception: ServerResponseException) {
            Log.e(
                TAG,
                "NASA server unavailable: ${exception.response.status}. Retrying...",
                exception
            )

            Result.retry()

        } catch (exception: ClientRequestException) {
            Log.e(
                TAG,
                "NASA request rejected: ${exception.response.status}",
                exception
            )

            Result.failure()

        } catch (exception: IOException) {
            Log.e(
                TAG,
                "Network connection failed. Retrying...",
                exception
            )

            Result.retry()

        } catch (exception: Exception) {
            Log.e(
                TAG,
                "Unexpected refresh error",
                exception
            )

            Result.failure()
        }
    }

    companion object {
        private const val TAG = "AsteroidRefreshWorker"
    }
}