package com.clobot.mini.view.page.etc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.clobot.mini.R

/**
 * TODO 하단 배터리 표시
 *
 */

@Composable
fun Emergency() {
    Box(
        content = {
            Image(
                painter = painterResource(R.drawable.emergency),
                contentDescription = "Emergency",
                contentScale = ContentScale.Crop
            )
        },
        contentAlignment = Alignment.BottomCenter
    )
}

@Preview
@Composable
fun PreviewEmergency() {
    Emergency()
}