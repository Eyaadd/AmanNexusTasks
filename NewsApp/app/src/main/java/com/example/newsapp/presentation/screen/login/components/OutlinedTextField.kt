package com.example.newsapp.presentation.screen.login.components


import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.theme.darkGray
import com.example.newsapp.presentation.theme.sourceSans
import com.example.newsapp.presentation.theme.whiteColor


@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    @DrawableRes leadingIcon: Int,
    modifier: Modifier = Modifier,
    @DrawableRes trailingIcon: Int? = null,
    onTrailingIconClick: () -> Unit = {},
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    singleLine: Boolean = true
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            isError = isError,

            placeholder = {
                Text(
                    text = placeHolder,
                    fontFamily = sourceSans,
                    fontWeight = FontWeight.Normal,
                    color = darkGray
                )
            },

            shape = RoundedCornerShape(10.dp),

            leadingIcon = {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = null
                )
            },

            trailingIcon = {
                trailingIcon?.let {

                    IconButton(
                        onClick = onTrailingIconClick
                    ) {
                        Icon(
                            painter = painterResource(it),
                            contentDescription = null
                        )
                    }

                }
            },

            colors = OutlinedTextFieldDefaults.colors(

                focusedContainerColor = whiteColor,
                unfocusedContainerColor = whiteColor,

                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent
            )
        )

        errorMessage?.let {

            Text(
                text = it,
                color = Color.Red,
                fontFamily = sourceSans,
                fontWeight = FontWeight.Normal
            )

        }

    }

}


@Preview(showBackground = true)
@Composable
fun CustomOutlinedTextFieldPreview() {
    CustomOutlinedTextField(
        value = "",
        onValueChange = {},
        placeHolder = "Email",
        leadingIcon = R.drawable.ic_sms,
    )
}