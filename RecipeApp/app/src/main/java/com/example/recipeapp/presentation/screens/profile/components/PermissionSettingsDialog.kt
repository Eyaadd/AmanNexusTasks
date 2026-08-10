package com.example.recipeapp.presentation.screens.profile.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.annotation.StringRes
import com.example.recipeapp.R
import com.example.recipeapp.presentation.theme.RecipeYellow

@Composable
fun PermissionSettingsDialog(
    @StringRes permissionNameRes: Int,
    onDismiss: () -> Unit,
    onOpenSettings: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.permission_required)) },
        text = {
            Text(stringResource(R.string.permission_settings_message, stringResource(permissionNameRes)))
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = onOpenSettings) {
                Text(stringResource(R.string.open_settings), color = RecipeYellow)
            }
        }
    )
}
