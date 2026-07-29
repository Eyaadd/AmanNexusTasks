package com.example.nasaapp.data.worker


import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object AsteroidSyncScheduler {

    private const val UNIQUE_WORK_NAME = "asteroid_periodic_refresh"

    fun schedulePeriodicRefresh(context: Context) {
        val constraints = Constraints.Builder().setRequiredNetworkType(
            NetworkType.CONNECTED
        ).setRequiresBatteryNotLow(true).build()

        val periodicRequest = PeriodicWorkRequestBuilder<AsteroidRefreshWorker>(
            15, TimeUnit.MINUTES
        ).setConstraints(constraints).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            UNIQUE_WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, periodicRequest
        )
    }
}