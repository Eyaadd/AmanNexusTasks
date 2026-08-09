package com.example.recipeapp.presentation.screens.profile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapp.R
import com.example.recipeapp.presentation.screens.profile.components.ProfileHeader
import com.example.recipeapp.presentation.screens.profile.components.PermissionSettingsDialog
import com.example.recipeapp.presentation.screens.profile.components.SettingItem
import com.example.recipeapp.presentation.screens.profile.components.SettingsSection
import com.example.recipeapp.presentation.theme.RecipeAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreenContent(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}

@Composable
fun ProfileScreenContent(
    state: ProfileContract.ProfileState,
    onIntent: (ProfileContract.ProfileIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var locationDenialCount by rememberSaveable {
        mutableStateOf(0)
    }
    var notificationDenialCount by rememberSaveable {
        mutableStateOf(0)
    }

    var showLocationSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var showNotificationSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (!isGranted) notificationDenialCount++
            onIntent(
                ProfileContract.ProfileIntent.OnNotificationsChanged(
                    isEnabled = isGranted
                )
            )
        }

    val locationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val coarseGranted =
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            val fineGranted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true

            val locationGranted = coarseGranted || fineGranted

            if (!locationGranted) locationDenialCount++

            onIntent(
                ProfileContract.ProfileIntent.OnLocationChanged(
                    isEnabled = locationGranted
                )
            )
        }

    val openAppSettings = {
        context.startActivity(
            Intent(
                android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:${context.packageName}")
            )
        )
    }

    if (showNotificationSettingsDialog) {
        PermissionSettingsDialog(
            permissionName = "notifications",
            onDismiss = { showNotificationSettingsDialog = false },
            onOpenSettings = {
                showNotificationSettingsDialog = false
                openAppSettings()
            }
        )
    }

    if (showLocationSettingsDialog) {
        PermissionSettingsDialog(
            permissionName = "location",
            onDismiss = { showLocationSettingsDialog = false },
            onOpenSettings = {
                showLocationSettingsDialog = false
                openAppSettings()
            }
        )
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        ProfileHeader(
            modifier = Modifier.align(Alignment.CenterHorizontally),

            )

        SettingsSection(
            title = "Account",
            items = listOf(
                SettingItem(
                    label = "Email",
                    iconRes = R.drawable.ic_email,
                ),
                SettingItem(
                    label = "Notifications",
                    iconRes = R.drawable.ic_notification,
                    isChecked = state.notificationsEnabled,
                    onCheckedChange = { enabled ->
                        if (enabled) {
                            if (hasNotificationPermission(context)) {
                                onIntent(
                                    ProfileContract.ProfileIntent.OnNotificationsChanged(
                                        isEnabled = true
                                    )
                                )
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                if (notificationDenialCount >= MAX_PERMISSION_REQUESTS) {
                                    showNotificationSettingsDialog = true
                                } else {
                                    notificationPermissionLauncher.launch(
                                        Manifest.permission.POST_NOTIFICATIONS
                                    )
                                }
                            }
                        } else {
                            onIntent(
                                ProfileContract.ProfileIntent.OnNotificationsChanged(
                                    isEnabled = false
                                )
                            )
                        }
                    }
                ),
                SettingItem(
                    label = "Location",
                    iconRes = R.drawable.ic_location,
                    isChecked = state.locationEnabled,
                    onCheckedChange = { enabled ->
                        if (enabled) {
                            if (hasLocationPermission(context)) {
                                onIntent(
                                    ProfileContract.ProfileIntent.OnLocationChanged(
                                        isEnabled = true
                                    )
                                )
                            } else {
                                if (locationDenialCount >= MAX_PERMISSION_REQUESTS) {
                                    showLocationSettingsDialog = true
                                } else {
                                    locationPermissionLauncher.launch(
                                        arrayOf(
                                            Manifest.permission.ACCESS_COARSE_LOCATION,
                                            Manifest.permission.ACCESS_FINE_LOCATION
                                        )
                                    )
                                }
                            }
                        } else {
                            onIntent(
                                ProfileContract.ProfileIntent.OnLocationChanged(
                                    isEnabled = enabled
                                )
                            )
                        }
                    }

                )
            )
        )

        SettingsSection(
            title = "Device",
            items = listOf(
                SettingItem("Language", R.drawable.ic_language) {
                },
                SettingItem("Display mode", R.drawable.ic_display_mode) {
                }
            )
        )

        SettingsSection(
            title = "System",
            items = listOf(
                SettingItem("Contact us", R.drawable.ic_contact) {
                },
                SettingItem("Term of use", R.drawable.ic_terms) {
                },
                SettingItem("About", R.drawable.ic_about) {
                },
                SettingItem("Check version", R.drawable.ic_version) {
                },
                SettingItem("Logout", R.drawable.ic_logout) {
                }
            ),
            bottomPadding = 28.dp
        )
    }
}

fun hasNotificationPermission(context: Context): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
}

fun hasLocationPermission(context: Context): Boolean {
    val coarseGranted =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    val fineGranted =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    return coarseGranted || fineGranted
}

private const val MAX_PERMISSION_REQUESTS = 2

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun ProfileScreenPreview() {
    RecipeAppTheme(dynamicColor = false) {
        ProfileScreenContent(
            state = ProfileContract.ProfileState(),
            onIntent = {}
        )
    }
}
