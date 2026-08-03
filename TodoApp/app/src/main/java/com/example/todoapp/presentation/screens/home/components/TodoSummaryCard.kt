package com.example.todoapp.presentation.screens.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.presentation.theme.summaryBackground
import com.example.todoapp.presentation.theme.summaryBorder
import com.example.todoapp.presentation.theme.summaryTextColor
import com.example.todoapp.presentation.theme.interBold
import com.example.todoapp.presentation.theme.screenColor


@Composable
fun TodoSummaryCard(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = screenColor, shape = RoundedCornerShape(40.dp)
            )
            .border(
                width = 1.dp, color = summaryBorder, shape = RoundedCornerShape(40.dp)
            )
            .padding(
                horizontal = 20.dp, vertical = 40.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Todo Done",
                color = summaryTextColor,
                fontSize = 30.sp,
                fontFamily = interBold,
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "12.09.2025",
                color = summaryTextColor,
                fontSize = 20.sp,
                fontFamily = interBold,
            )
        }

        Column(
            modifier = Modifier
                .size(116.dp)
                .background(color = Color.White, shape = CircleShape),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "5/8", color = Color.Black, fontSize = 40.sp, fontFamily = interBold,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111111, showSystemUi = true)
@Composable
private fun TodoSummaryCardPreview() {
    TodoSummaryCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}