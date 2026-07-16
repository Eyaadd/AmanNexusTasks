package com.example.week2amantasksxml.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week2amantasksxml.R
import com.example.week2amantasksxml.theme.poppins
import com.example.week2amantasksxml.theme.primaryText

@Composable
fun SettingsItem(modifier: Modifier = Modifier, prefixIcon: Int, sectionName: String) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                painter = painterResource(prefixIcon),
                contentDescription = null,
            )
            Text(
                text = sectionName,
                fontFamily = poppins,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                color = primaryText,
            )
        }
        Icon(
            painter = painterResource(R.drawable.ic_forward), contentDescription = null
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsItemPreview() {
    SettingsItem(
        prefixIcon = R.drawable.ic_my_orders,
        sectionName = "My Orders"
    )
}