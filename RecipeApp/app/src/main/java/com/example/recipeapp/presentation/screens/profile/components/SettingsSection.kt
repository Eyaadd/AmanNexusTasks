package com.example.recipeapp.presentation.screens.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.presentation.theme.ProfileSecondaryText
import com.example.recipeapp.presentation.theme.RecipeYellow
import com.example.recipeapp.presentation.theme.ScreenTextColor


@Composable
fun SettingsSection(
    title: String,
    items: List<SettingItem>,
    modifier: Modifier = Modifier,
    bottomPadding: Dp = 16.dp
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            color = ScreenTextColor,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        items.forEach { item -> SettingsRow(item) }
        Spacer(Modifier.height(bottomPadding))
    }
}

@Composable
private fun SettingsRow(item: SettingItem) {
    val rowClick = if (item.isChecked != null && item.onCheckedChange != null) {
        { item.onCheckedChange.invoke(!item.isChecked) }
    } else {
        item.onClick
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(onClick = rowClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(item.iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )
        Text(
            text = item.label,
            modifier = Modifier
                .padding(start = 14.dp)
                .weight(1f),
            color = ProfileSecondaryText,
            fontSize = 14.sp
        )

        if (item.isChecked != null && item.onCheckedChange != null) {
            Switch(
                checked = item.isChecked,
                onCheckedChange = item.onCheckedChange,
                modifier = Modifier.size(width = 44.dp, height = 28.dp),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = RecipeYellow,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFFD5D5D5),
                    uncheckedBorderColor = Color.Transparent
                )
            )
        } else {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}
