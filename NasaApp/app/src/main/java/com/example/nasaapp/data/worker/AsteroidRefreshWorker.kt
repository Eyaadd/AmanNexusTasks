package com.example.nasaapp.data.worker


import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.nasaapp.NasaApplication
import retrofit2.HttpException
import java.io.IOException
import android.util.Log


class AsteroidRefreshWorker(
    appContext: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(
    appContext,
    workerParameters
) {

    override suspend fun doWork(): Result {
        val application =
            applicationContext as NasaApplication

        Log.d(
            WORKER_TAG,
            "Asteroid refresh started"
        )

        return try {
            application.repository.refreshAsteroids()

            Log.d(
                WORKER_TAG,
                "Asteroid refresh succeeded"
            )

            Result.success()
        } catch (exception: IOException) {
            Log.e(
                WORKER_TAG,
                "Network error. Retrying.",
                exception
            )

            Result.retry()
        } catch (exception: HttpException) {
            Log.e(
                WORKER_TAG,
                "HTTP error: ${exception.code()}",
                exception
            )

            when (exception.code()) {
                408,
                429 -> Result.retry()

                in 500..599 -> Result.retry()

                else -> Result.failure()
            }
        } catch (exception: Exception) {
            Log.e(
                WORKER_TAG,
                "Unexpected refresh error",
                exception
            )

            Result.failure()
        }
    }

    companion object {
        private const val WORKER_TAG =
            "AsteroidRefreshWorker"
    }
}