package com.example.recipeapp.presentation.screens.profile.components

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
fun LanguageSelectionDialog(
    selectedLanguageTag: String,
    onLanguageSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.choose_language)) },
        text = {
            Column {
                LanguageOption(
                    label = stringResource(R.string.language_english),
                    selected = selectedLanguageTag == LANGUAGE_ENGLISH,
                    onClick = { onLanguageSelected(LANGUAGE_ENGLISH) }
                )
                LanguageOption(
                    label = stringResource(R.string.language_arabic),
                    selected = selectedLanguageTag == LANGUAGE_ARABIC,
                    onClick = { onLanguageSelected(LANGUAGE_ARABIC) }
                )
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

@Composable
private fun LanguageOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text = label, modifier = Modifier.padding(start = 8.dp))
    }
}

const val LANGUAGE_ENGLISH = "en"
const val LANGUAGE_ARABIC = "ar"
