package com.example.week2amantasksxml.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.week2amantasksxml.components.UserItem
import com.example.week2amantasksxml.models.User
import com.example.week2amantasksxml.repository.UsersRepositoryImp
import com.example.week2amantasksxml.viewmodels.UsersViewModel


@Composable
fun UsersScreen(modifier: Modifier = Modifier) {

    val viewModel = remember { UsersViewModel(UsersRepositoryImp()) }
    val users by viewModel.users.observeAsState(emptyList())
    val search by viewModel.search.collectAsStateWithLifecycle()


    UsersScreenContent(
        modifier = modifier,
        users = users,
        value = search
    ) {
        viewModel.onSearchQueryChanged(it)
    }
}


@Composable
fun UsersScreenContent(
    modifier: Modifier,
    users: List<User>,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = {
                Text("Search users")
            },
            singleLine = true
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(users) { user ->
                UserItem(user)
            }
        }
    }
}


@Preview
@Composable
fun UsersScreenPreview() {
    UsersScreen()
}

