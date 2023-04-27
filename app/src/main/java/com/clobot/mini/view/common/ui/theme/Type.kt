package com.clobot.mini.view.common.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.clobot.mini.R

private val defaultFont = FontFamily(
    Font(R.font.minsans_medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = defaultFont,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val AdminTypography = Typography(
    button = TextStyle(
        textAlign = TextAlign.Center,
        fontSize = 13.sp,
        color = Color.Black,
        lineHeight = 15.sp
    ),
)

val pageTypography = Typography(
    h1 = TextStyle(
        textAlign = TextAlign.Center,
        fontSize = 23.sp,
        fontFamily = FontFamily(Font(R.font.minsans_medium)),
        lineHeight = 29.sp,
        fontWeight = FontWeight.Bold
    )
)