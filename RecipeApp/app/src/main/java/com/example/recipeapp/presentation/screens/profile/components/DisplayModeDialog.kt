package com.example.recipeapp.presentation.screens.profile.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R

@Composable
fun DisplayModeDialog(
    selectedMode: String,
    onModeSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.choose_display_mode)) },
        text = {
            Column {
                DisplayModeOption(R.string.display_mode_system, DISPLAY_MODE_SYSTEM, selectedMode, onModeSelected)
                DisplayModeOption(R.string.display_mode_light, DISPLAY_MODE_LIGHT, selectedMode, onModeSelected)
                DisplayModeOption(R.string.display_mode_dark, DISPLAY_MODE_DARK, selectedMode, onModeSelected)
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
        }
    )
}

@Composable
private fun DisplayModeOption(
    @StringRes labelRes: Int,
    mode: String,
    selectedMode: String,
    onModeSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onModeSelected(mode) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = mode == selectedMode, onClick = { onModeSelected(mode) })
        Text(stringResource(labelRes), Modifier.padding(start = 8.dp))
    }
}

const val DISPLAY_MODE_SYSTEM = "system"
const val DISPLAY_MODE_LIGHT = "light"
const val DISPLAY_MODE_DARK = "dark"
