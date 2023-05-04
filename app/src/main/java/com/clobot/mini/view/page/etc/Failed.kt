package com.clobot.mini.view.page.etc

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.clobot.mini.R

@Composable
fun Failed() {
    Image(
        painter = painterResource(R.drawable.emergency),
        contentDescription = "emergency",
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PreviewFailed() {
    Failed()
}