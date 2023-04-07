package com.clobot.mini.view.move

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.clobot.mini.view.common.Template0
import com.clobot.mini.view.navigation.RouteAction
import kotlinx.coroutines.delay

@Composable
fun Standby(routeAction: RouteAction) {

    LaunchedEffect(Unit){
        Log.i("Launched check", "Standby Launched")
        delay(5000)
        routeAction.goMain()
    }

    Template0(routeAction = routeAction, needTopBar = false, templateBody = {
        Text("Standby 페이지")
    })
}