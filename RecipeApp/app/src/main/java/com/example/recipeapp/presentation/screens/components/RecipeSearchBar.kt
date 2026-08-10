package com.example.recipeapp.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R
import com.example.recipeapp.presentation.theme.RecipeYellow
import com.example.recipeapp.presentation.theme.SearchBorderColor

@Composable
fun RecipeSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEditable: Boolean = true
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChanged,
            modifier = Modifier.fillMaxWidth(),
            readOnly = !isEditable,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_recipes),
                    color = SearchBorderColor
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(R.string.search),
                    tint = RecipeYellow
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {}
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = RecipeYellow,
                unfocusedBorderColor = SearchBorderColor,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                cursorColor = RecipeYellow
            )
        )

        if (!isEditable) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {
                        onClick()
                    }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun RecipeSearchBarPreview() {
    RecipeSearchBar(
        query = "", onQueryChanged = {}, modifier = Modifier.padding(16.dp), onClick = {}
    )
}
