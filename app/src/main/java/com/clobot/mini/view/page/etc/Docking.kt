package com.clobot.mini.view.page.etc

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R

@Composable
fun Docking() {
    Image(
        painter = painterResource(id = R.drawable.docking),
        contentDescription = "docking",
        contentScale = ContentScale.Crop
    )
}