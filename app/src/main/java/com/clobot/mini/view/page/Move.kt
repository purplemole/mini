package com.clobot.mini.view.page

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R
import com.clobot.mini.data.robot.MoveReason
import com.clobot.mini.util.LocalRobotViewModel
import com.clobot.mini.util.LocalRouteAction

/**
 * TODO
 *
 * @param pos 1 접수 장소 / 2 대기 장소 / 3 진료실 / 4 호출 장소 ,
 */
@Composable
fun MovePosition(pos: Int = 0) {
    val robotViewModel = LocalRobotViewModel.current
    val moveState = robotViewModel.moveReason.collectAsState()
    val routeAction = LocalRouteAction.current

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

    if(moveState.value == MoveReason.None)
        routeAction.goBack()
}