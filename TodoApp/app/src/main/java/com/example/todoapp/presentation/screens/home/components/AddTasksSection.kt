package com.example.todoapp.presentation.screens.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R

private val InputBackground = Color(0xFF0F0F0F)
private val InputBorder = Color(0xFF2B2B2B)
private val InputTextColor = Color.White
private val PlaceholderColor = Color(0xFF414147)

@Composable
fun AddTaskSection(
    taskTitle: String,
    onTaskTitleChange: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = taskTitle,
            onValueChange = onTaskTitleChange,
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .background(
                    color = InputBackground, shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp, color = InputBorder, shape = RoundedCornerShape(8.dp)
                ),
            textStyle = TextStyle(
                color = InputTextColor, fontSize = 14.sp
            ),
            cursorBrush = SolidColor(Color.White),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (taskTitle.isEmpty()) {
                        Text(
                            text = "input a new task", color = PlaceholderColor, fontSize = 13.sp
                        )
                    }

                    innerTextField()
                }
            })

        Spacer(
            modifier = Modifier.size(20.dp)
        )

        IconButton(
            onClick = onAddTaskClick, modifier = Modifier
                .background(
                    color = Color.White, shape = CircleShape
                )
                .size(32.dp)

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = "Add task",
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111111)
@Composable
private fun AddTaskSectionPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111111))
            .padding(16.dp)
    ) {
        AddTaskSection(taskTitle = "", onTaskTitleChange = {}, onAddTaskClick = {})
    }
}