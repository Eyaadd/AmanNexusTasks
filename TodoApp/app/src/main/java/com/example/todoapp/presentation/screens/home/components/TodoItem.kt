package com.example.todoapp.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.data.source.local.entity.TaskEntity
import com.example.todoapp.presentation.theme.borderColor
import com.example.todoapp.presentation.theme.interBold
import com.example.todoapp.presentation.theme.rowItemColor

@Composable
fun TodoItem(
    task: TaskEntity,
    checkBoxColor: Color,
    onToggleClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(rowItemColor)
            .border(
                1.dp, borderColor, RoundedCornerShape(12.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onToggleClick() },
                colors = CheckboxDefaults.colors(
                    checkedColor = checkBoxColor,
                    uncheckedColor = checkBoxColor,
                    checkmarkColor = Color.White
                )
            )

            Text(
                text = task.title,
                fontFamily = interBold,
                fontSize = 14.sp,
                color = Color.White,
                maxLines = 1
            )

        }

        IconButton(
            onClick = {
                onDeleteClick()
            }) {
            Icon(
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = "Delete task",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }


    }
}


@Composable
@Preview
fun TodoItemPreview() {
    TodoItem(
        task = TaskEntity(
            id = 1, title = "Test", isCompleted = false
        ), onToggleClick = {}, onDeleteClick = {}, checkBoxColor = Color.Red
    )
}