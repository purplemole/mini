package com.clobot.mini.view.page.etc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R
import com.clobot.mini.util.LocalRobotViewModel
import com.clobot.mini.util.LocalRouteAction

@Composable
fun Charging() {
    val robotViewModel = LocalRobotViewModel.current
    val chargeState = robotViewModel.chargingState.collectAsState()
    val route = LocalRouteAction.current

    Box(
        content = {
            Image(
                painter = painterResource(R.drawable.charging),
                contentDescription = "charging",
                contentScale = ContentScale.Crop
            )
        }
    )

    if(!chargeState.value)
        route.goBack()
}

