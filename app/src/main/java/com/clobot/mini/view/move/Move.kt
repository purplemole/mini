package com.clobot.mini.view.move

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R

/**
 * TODO
 *
 * @param pos 1 접수 장소 / 2 대기 장소 / 3 진료실 / 4 호출 장소 ,
 */
@Composable
fun MovePosition(pos: Int = 0) {
    val backgroundImg = when (pos) {
        1 -> painterResource(R.drawable.move_position_1)
        2 -> painterResource(R.drawable.move_position_2)
        3 -> painterResource(R.drawable.move_position_3)
        4 -> painterResource(R.drawable.move_name)
        else -> painterResource(R.drawable.move_position_1)
    }
    Image(
        painter = backgroundImg,
        contentDescription = "move-position_$pos",
        contentScale = ContentScale.Crop
    )
}

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

@Composable
fun Failed() {
    Image(
        painter = painterResource(R.drawable.emergency),
        contentDescription = "emergency",
        contentScale = ContentScale.Crop
    )
}