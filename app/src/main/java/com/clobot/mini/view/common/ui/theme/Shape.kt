package com.clobot.mini.view.common.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(7.dp)
)

val AdminRoundedBtn = Shapes(
    small = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(12.dp)
)


val adminBorder = BorderStroke(2.dp, prc_gray800)
val adminBorder_gray1 = BorderStroke(1.dp, prc_gray800)
val adminBorder_skyBlue = BorderStroke(1.dp, prc_check)