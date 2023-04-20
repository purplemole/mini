package com.clobot.mini.view.move

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.clobot.mini.util.LocalRobotViewModel
import com.clobot.mini.util.LocalRouteAction

@Composable
fun Charging() {

    Box(
        content = {
            Text("충전 중")
        }
    )
    if (!LocalRobotViewModel.current.dockingState.collectAsState(
            initial = false
        ).value
    ) LocalRouteAction.current.goBack()
}

@Composable
fun MoveCharge() {

}