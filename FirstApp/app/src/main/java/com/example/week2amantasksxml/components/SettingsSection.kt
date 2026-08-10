package com.example.week2amantasksxml.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week2amantasksxml.R
import com.example.week2amantasksxml.models.SettingsItemData
import com.example.week2amantasksxml.theme.poppins
import com.example.week2amantasksxml.theme.primaryText

@Composable
fun SettingsSection(
    modifier: Modifier = Modifier, sectionTitle: String, items: List<SettingsItemData>
) {
    Column(
        modifier = modifier
    ) {
        Text(
            sectionTitle,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = primaryText
        )
        Spacer(Modifier.size(16.dp))
        Box(
            modifier = Modifier.background(
                Color.White, shape = RoundedCornerShape(10.dp)
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(15.dp)
                    .height(50.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items) { item ->
                    SettingsItem(
                        prefixIcon = item.icon,
                        sectionName = item.name
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SettingsSectionPreview() {
    SettingsSection(
        sectionTitle = "Manage",
        items = listOf(
            SettingsItemData(
                icon = R.drawable.ic_my_orders,
                name = "My Orders"
            ),
            SettingsItemData(
                icon = R.drawable.ic_wallet,
                name = "My Transactions"
            ),
            SettingsItemData(
                icon = R.drawable.ic_wishlist,
                name = "My Wishlist"
            )
        )
    )
}