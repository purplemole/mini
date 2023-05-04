package com.clobot.mini.view.page.promote

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R

@Composable
fun Promote2() {
    Image(
        painter = painterResource(R.drawable.promote_2),
        contentDescription = "promote_2",
        contentScale = ContentScale.Crop
    )
}