package com.clobot.mini.util

import androidx.compose.runtime.staticCompositionLocalOf
import com.clobot.mini.model.MainViewModel
import com.clobot.mini.model.RobotViewModel
import com.clobot.mini.view.navigation.RouteAction


/*
* Composition 계층 내의 모든 Composable 에 제공될 수 있는 로컬 값
*
* 참고 : https://www.charlezz.com/?p=46403
* */

val LocalMainViewModel = staticCompositionLocalOf<MainViewModel> { error("No MainViewModel found!") }
val LocalRouteAction = staticCompositionLocalOf<RouteAction> { error("No RouteActionProvided") }
val LocalRobotViewModel = staticCompositionLocalOf<RobotViewModel> { error("No RobotViewModel found!") }
