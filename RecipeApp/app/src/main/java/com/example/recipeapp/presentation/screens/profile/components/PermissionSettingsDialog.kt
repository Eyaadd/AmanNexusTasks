package com.example.recipeapp.presentation.screens.profile.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.recipeapp.presentation.theme.RecipeYellow

@Composable
fun PermissionSettingsDialog(
    permissionName: String,
    onDismiss: () -> Unit,
    onOpenSettings: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Permission required") },
        text = {
            Text(
                "The $permissionName permission has been denied twice. " +
                    "You can enable it from the app settings."
            )
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onOpenSettings) {
                Text("Open settings", color = RecipeYellow)
            }
        }
    )
}
