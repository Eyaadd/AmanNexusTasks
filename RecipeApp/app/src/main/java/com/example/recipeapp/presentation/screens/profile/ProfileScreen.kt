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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapp.R
import com.example.recipeapp.presentation.screens.profile.components.ProfileHeader
import com.example.recipeapp.presentation.screens.profile.components.LanguageSelectionDialog
import com.example.recipeapp.presentation.screens.profile.components.DisplayModeDialog
import com.example.recipeapp.presentation.screens.profile.components.DISPLAY_MODE_DARK
import com.example.recipeapp.presentation.screens.profile.components.DISPLAY_MODE_LIGHT
import com.example.recipeapp.presentation.screens.profile.components.DISPLAY_MODE_SYSTEM
import com.example.recipeapp.presentation.screens.profile.components.PermissionSettingsDialog
import com.example.recipeapp.presentation.screens.profile.components.SettingItem
import com.example.recipeapp.presentation.screens.profile.components.SettingsSection
import com.example.recipeapp.presentation.theme.RecipeAppTheme
import org.koin.androidx.compose.koinViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(viewModel) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ProfileContract.ProfileEffect.ChangeLanguage -> {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags(effect.languageTag)
                    )
                }

                is ProfileContract.ProfileEffect.ChangeDisplayMode -> {
                    val nightMode = when (effect.mode) {
                        DISPLAY_MODE_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                        DISPLAY_MODE_DARK -> AppCompatDelegate.MODE_NIGHT_YES
                        else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
                    AppCompatDelegate.setDefaultNightMode(nightMode)
                }

                is ProfileContract.ProfileEffect.ShowMessage -> Unit
                ProfileContract.ProfileEffect.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }

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
    val selectedLanguageTag =
        AppCompatDelegate.getApplicationLocales()[0]?.language
            ?: java.util.Locale.getDefault().language
    val selectedDisplayMode = when (AppCompatDelegate.getDefaultNightMode()) {
        AppCompatDelegate.MODE_NIGHT_NO -> DISPLAY_MODE_LIGHT
        AppCompatDelegate.MODE_NIGHT_YES -> DISPLAY_MODE_DARK
        else -> DISPLAY_MODE_SYSTEM
    }

    var locationDenialCount by rememberSaveable {
        mutableStateOf(0)
    }
    var notificationDenialCount by rememberSaveable {
        mutableStateOf(0)
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

    if (state.showNotificationSettingsDialog) {
        PermissionSettingsDialog(
            permissionNameRes = R.string.permission_notifications,
            onDismiss = {
                onIntent(
                    ProfileContract.ProfileIntent.ShowNotificationSettingsDialog(
                        false
                    )
                )
            },
            onOpenSettings = {
                onIntent(ProfileContract.ProfileIntent.ShowNotificationSettingsDialog(false))
                openAppSettings()
            }
        )
    }

    if (state.showLocationSettingsDialog) {
        PermissionSettingsDialog(
            permissionNameRes = R.string.permission_location,
            onDismiss = {
                onIntent(ProfileContract.ProfileIntent.ShowLocationSettingsDialog(false))
            },
            onOpenSettings = {
                onIntent(ProfileContract.ProfileIntent.ShowLocationSettingsDialog(false))
                openAppSettings()
            }
        )
    }

    if (state.showLanguageDialog) {
        LanguageSelectionDialog(
            selectedLanguageTag = selectedLanguageTag,
            onLanguageSelected = { languageTag ->
                onIntent(ProfileContract.ProfileIntent.OnLanguageSelected(languageTag))
            },
            onDismiss = {
                onIntent(ProfileContract.ProfileIntent.ShowLanguageDialog(false))
            }
        )
    }

    if (state.showDisplayModeDialog) {
        DisplayModeDialog(
            selectedMode = selectedDisplayMode,
            onModeSelected = { mode ->
                onIntent(ProfileContract.ProfileIntent.OnDisplayModeSelected(mode))
            },
            onDismiss = {
                onIntent(ProfileContract.ProfileIntent.ShowDisplayModeDialog(false))
            }
        )
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        ProfileHeader(
            modifier = Modifier.align(Alignment.CenterHorizontally),

            )

        SettingsSection(
            titleRes = R.string.profile_account,
            items = listOf(
                SettingItem(
                    labelRes = R.string.profile_email,
                    iconRes = R.drawable.ic_email,
                ),
                SettingItem(
                    labelRes = R.string.profile_notifications,
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
                                    onIntent(ProfileContract.ProfileIntent.ShowNotificationSettingsDialog(true))
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
                    labelRes = R.string.profile_location,
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
                                    onIntent(ProfileContract.ProfileIntent.ShowLocationSettingsDialog(true))
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
            titleRes = R.string.profile_device,
            items = listOf(
                SettingItem(R.string.profile_language, R.drawable.ic_language) {
                    onIntent(ProfileContract.ProfileIntent.ShowLanguageDialog(true))
                },
                SettingItem(R.string.profile_display_mode, R.drawable.ic_display_mode) {
                    onIntent(ProfileContract.ProfileIntent.ShowDisplayModeDialog(true))
                }
            )
        )

        SettingsSection(
            titleRes = R.string.profile_system,
            items = listOf(
                SettingItem(R.string.profile_contact_us, R.drawable.ic_contact) {
                },
                SettingItem(R.string.profile_terms_of_use, R.drawable.ic_terms) {
                },
                SettingItem(R.string.profile_about, R.drawable.ic_about) {
                },
                SettingItem(R.string.profile_check_version, R.drawable.ic_version) {
                },
                SettingItem(R.string.profile_logout, R.drawable.ic_logout) {
                    onIntent(ProfileContract.ProfileIntent.OnLogoutClicked)
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
