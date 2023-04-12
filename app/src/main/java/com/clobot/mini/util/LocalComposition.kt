package com.clobot.mini.util

import androidx.compose.runtime.compositionLocalOf
import com.clobot.mini.model.MainViewModel
import com.clobot.mini.model.RobotViewModel
import com.clobot.mini.view.navigation.RouteAction


/*
* Composition 계층 내의 모든 Composable 에 제공될 수 있는 로컬 값
* */

val LocalMainViewModel = compositionLocalOf<MainViewModel> { error("No MainViewModel found!") }
val LocalRouteAction = compositionLocalOf<RouteAction> { error("No RouteActionProvided") }
val LocalRobotViewModel = compositionLocalOf<RobotViewModel> { error("No RobotViewModel found!") }