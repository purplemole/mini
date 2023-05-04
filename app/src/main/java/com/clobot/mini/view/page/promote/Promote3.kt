package com.clobot.mini.view.page.promote

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R

@Composable
fun Promote3() {
    Image(
        painter = painterResource(R.drawable.promote_3),
        contentDescription = "promote_3",
        contentScale = ContentScale.Crop
    )
}