package com.clobot.mini.util

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.clobot.mini.data.admin.StoreAdminSetting
import com.clobot.mini.model.MainViewModel
import com.clobot.mini.model.RobotViewModel
import com.clobot.mini.util.state.IntFieldState
import com.clobot.mini.util.state.TextFieldState
import com.clobot.mini.view.navigation.RouteAction


/*
* Composition 계층 내의 모든 Composable 에 제공될 수 있는 로컬 값
*
* 참고 : https://www.charlezz.com/?p=46403
* */

val LocalMainViewModel =
    staticCompositionLocalOf<MainViewModel> { error("No MainViewModel found!") }
val LocalRouteAction = staticCompositionLocalOf<RouteAction> { error("No RouteActionProvided") }
val LocalRobotViewModel =
    staticCompositionLocalOf<RobotViewModel> { error("No RobotViewModel found!") }

// Admin 에서만 사용할 Local 값.
val LocalDataStore =
    staticCompositionLocalOf<StoreAdminSetting> { error("No dataStore File!") }
val LocalPromoteCycle =
    compositionLocalOf<IntFieldState> { error("LocalPromoteCycleField Error") }
val LocalForceCharging =
    compositionLocalOf<IntFieldState> { error("LocalForceCharging Error") }
val LocalRobotOperating =
    compositionLocalOf<IntFieldState> { error("LocalRobotOperating Error") }
val LocalRestrictFromTo = compositionLocalOf<TextFieldState> { error("LocalRestrictFromTo Error") }
val LocalMonFromTo =
    compositionLocalOf<TextFieldState> { error("LocalMonFromTo Error") }
val LocalTueFromTo =
    compositionLocalOf<TextFieldState> { error("LocalTueFromTo Error") }
val LocalWedFromTo =
    compositionLocalOf<TextFieldState> { error("LocalWedFromTo Error") }
val LocalThuFromTo =
    compositionLocalOf<TextFieldState> { error("LocalThuFromTo Error") }
val LocalFriFromTo =
    compositionLocalOf<TextFieldState> { error("LocalFriFromTo Error") }
val LocalSatFromTo =
    compositionLocalOf<TextFieldState> { error("LocalSatFromTo Error") }
val LocalSunFromTo =
    compositionLocalOf<TextFieldState> { error("LocalSunFromTo Error") }