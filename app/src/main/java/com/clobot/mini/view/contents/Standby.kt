package com.clobot.mini.view.contents

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R
import com.clobot.mini.util.LocalRouteAction
import kotlinx.coroutines.delay

/**
 * TODO 초기 위치로 이동 명령.
 *
 */
@Composable
fun Standby() {
    val routeAction = LocalRouteAction.current
    Image(
        painter = painterResource(R.drawable.standby),
        contentDescription = "standby background img",
        contentScale = ContentScale.Crop
    )
    LaunchedEffect(Unit) {
        Log.i("Launched check", "Standby Launched")
        delay(5000)
        routeAction.goMain()
    }
}