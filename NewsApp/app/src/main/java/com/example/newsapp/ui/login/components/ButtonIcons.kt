package com.example.newsapp.ui.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R

@Composable
fun ButtonIcon(icon : Int){
    Box(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(10.dp))

    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(icon), contentDescription = null
        )
    }
}


@Preview
@Composable
fun ButtonIconPreview(){
    ButtonIcon(
        R.drawable.ic_drawer
    )
}