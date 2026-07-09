package com.example.week2amantasksxml.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.week2amantasksxml.R
import com.example.week2amantasksxml.components.CategoryItem
import com.example.week2amantasksxml.components.SettingsSection
import com.example.week2amantasksxml.model.Category
import com.example.week2amantasksxml.model.SettingsItemData
import com.example.week2amantasksxml.theme.poppins
import com.example.week2amantasksxml.theme.primaryText


@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {

    val categories = listOf(
        Category(R.drawable.ic_burger, "Burger"),
        Category(R.drawable.ic_pizza, "Pizza"),
        Category(R.drawable.ic_drinks, "Drinks"),
        Category(R.drawable.ic_desserts, "Dessert"),
        Category(R.drawable.ic_burger, "Burger"),
        Category(R.drawable.ic_pizza, "Pizza"),
        Category(R.drawable.ic_drinks, "Drinks"),
        Category(R.drawable.ic_desserts, "Dessert")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_backward),
                contentDescription = null,
            )
            Text(
                "My account",
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
        Spacer(Modifier.size(30.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                model = "https://i.ibb.co/5XtPCpND/eyad.jpg",
                fallback = painterResource(R.drawable.ic_person),
                placeholder = painterResource(R.drawable.ic_profile),
                error = painterResource(R.drawable.ic_profile),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = "Karan Patil",
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryText
                )
                Text(
                    text = "Ownest member since Jan-2022",
                    fontFamily = poppins,
                    fontWeight = FontWeight.Light,
                    color = primaryText
                )
            }
        }
        Spacer(Modifier.size(30.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(categories) {
                CategoryItem(
                    title = it.title, icon = it.icon
                )
            }
        }
        Spacer(Modifier.size(20.dp))
        SettingsSection(
            sectionTitle = "Manage",
            items = listOf(
                SettingsItemData(
                    icon = R.drawable.ic_my_orders, name = "My orders"
                ),
                SettingsItemData(
                    icon = R.drawable.ic_wallet, name = "My transactions"
                ),
                SettingsItemData(
                    icon = R.drawable.ic_wishlist, name = "My wishlist"
                ),
                SettingsItemData(
                    icon = R.drawable.ic_my_orders, name = "My orders"
                ),
                SettingsItemData(
                    icon = R.drawable.ic_wallet, name = "My transactions"
                ),
                SettingsItemData(
                    icon = R.drawable.ic_wishlist, name = "My wishlist"
                ),

                ),
        )
        Spacer(Modifier.size(30.dp))
        SettingsSection(
            sectionTitle = "Settings",
            items = listOf(
                SettingsItemData(
                    icon = R.drawable.ic_profile, name = "Account settings"
                ),
                SettingsItemData(
                    icon = R.drawable.ic_notification, name = "Notification settings"
                ),
                SettingsItemData(
                    icon = R.drawable.ic_feedback, name = "Feedback"
                ),
            ),
        )
        Spacer(Modifier.size(30.dp))
        SettingsSection(
            sectionTitle = "Others",
            items = listOf(
                SettingsItemData(
                    icon = R.drawable.ic_my_orders, name = "About us"
                ),
                SettingsItemData(
                    icon = R.drawable.ic_faq, name = "FAQ"
                ),
            ),
        )
        Spacer(Modifier.size(30.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(), onClick = {}, shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    tint = Color(0xFF6C737F)
                )
                Text(
                    text = "Delete my account",
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6C737F)
                )
            }
        }
        Spacer(Modifier.size(35.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD25240), contentColor = Color.White
            ),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_log_out), contentDescription = null
                )
                Text(
                    text = "Log out", fontFamily = poppins, fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}







@Preview(showBackground = true)
@Composable
fun SettingsSectionsPreview() {
    SettingsScreen()
}