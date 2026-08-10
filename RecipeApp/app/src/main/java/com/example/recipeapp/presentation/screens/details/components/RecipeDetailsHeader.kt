package com.example.recipeapp.presentation.screens.details.components

import android.text.Spanned
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat

@Composable
fun RecipeDetailsHeader(
    title: String,
    description: String,
    rating: Double?,
    modifier: Modifier = Modifier
) {
    val formattedDescription = remember(description) {
        description.toAnnotatedStringFromHtml()
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = Color(0xFF222222),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = rating
                    ?.let { "★ ${String.format("%.1f", it)}" }
                    ?: "★ --",
                color = Color(0xFFFFB800),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = formattedDescription,
            color = Color.Gray,
            fontSize = 14.sp,
            lineHeight = 21.sp
        )
    }
}

fun String.toAnnotatedStringFromHtml(): AnnotatedString {
    if (isBlank()) {
        return AnnotatedString("No description available.")
    }

    val spanned = HtmlCompat.fromHtml(
        this,
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )

    return buildAnnotatedString {
        append(spanned.toString())

        spanned.getSpans(
            0,
            spanned.length,
            Any::class.java
        ).forEach { span ->
            val start = spanned.getSpanStart(span)
            val end = spanned.getSpanEnd(span)

            when (span) {
                is android.text.style.StyleSpan -> {
                    when (span.style) {
                        android.graphics.Typeface.BOLD -> {
                            addStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                start = start,
                                end = end
                            )
                        }

                        android.graphics.Typeface.ITALIC -> {
                            addStyle(
                                style = SpanStyle(
                                    fontStyle = FontStyle.Italic
                                ),
                                start = start,
                                end = end
                            )
                        }

                        android.graphics.Typeface.BOLD_ITALIC -> {
                            addStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic
                                ),
                                start = start,
                                end = end
                            )
                        }
                    }
                }
            }
        }
    }
}