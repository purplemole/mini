package com.clobot.mini.view.move

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.common.Template0
import kotlinx.coroutines.delay

@Composable
fun Standby() {
    val routeAction = LocalRouteAction.current

    LaunchedEffect(Unit){
        Log.i("Launched check", "Standby Launched")
        delay(5000)
        routeAction.goMain()
    }

    Template0(needTopBar = false, templateBody = {
        Text("Standby 페이지")
    })
}