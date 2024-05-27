package com.example.android_lab.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Styles {
    val headerText = TextStyle(
        fontSize = 33.sp,
        fontWeight = FontWeight(700),
        letterSpacing = 0.6.sp,
    )
    val mainText = TextStyle(
        color = SecondaryWhite,
        fontSize = 30.sp,
        fontWeight = FontWeight(700),
        letterSpacing = 0.4.sp,
    )
    val secondaryText = TextStyle(
        color = SecondaryWhite,
        fontSize = 20.sp,
        fontWeight = FontWeight(700),
        letterSpacing = 0.4.sp,
    )
}