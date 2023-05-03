package com.clobot.mini.view.contents

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R

@Composable
fun PromoteNormal() {
    Image(
        painter = painterResource(R.drawable.promote_normal),
        contentDescription = "promote-normal",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Promote1() {
    // 화면 전환 후 TTS 발화가 끝나면 로봇이 주행을 시작.
    Image(
        painter = painterResource(R.drawable.promote_1),
        contentDescription = "promote_1",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Promote2() {
    Image(
        painter = painterResource(R.drawable.promote_2),
        contentDescription = "promote_2",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Promote3() {
    Image(
        painter = painterResource(R.drawable.promote_3),
        contentDescription = "promote_3",
        contentScale = ContentScale.Crop
    )
}