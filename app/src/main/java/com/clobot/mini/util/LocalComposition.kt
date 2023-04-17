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
val LocalMoveLimitFrom =
    compositionLocalOf<TextFieldState> { error("LocalMoveLimitFrom Error") }
val LocalMoveLimitTo =
    compositionLocalOf<TextFieldState> { error("LocalMoveLimitTo Error") }
val LocalMonTo =
    compositionLocalOf<TextFieldState> { error("LocalMonTo Error") }
val LocalMonFrom =
    compositionLocalOf<TextFieldState> { error("LocalMonFrom Error") }
val LocalTueTo =
    compositionLocalOf<TextFieldState> { error("LocalTueTo Error") }
val LocalTueFrom =
    compositionLocalOf<TextFieldState> { error("LocalTueFrom Error") }
val LocalWedTo =
    compositionLocalOf<TextFieldState> { error("LocalWedTo Error") }
val LocalWedFrom =
    compositionLocalOf<TextFieldState> { error("LocalWedFrom Error") }
val LocalThuTo =
    compositionLocalOf<TextFieldState> { error("LocalThuTo Error") }
val LocalThuFrom =
    compositionLocalOf<TextFieldState> { error("LocalThuFrom Error") }
val LocalFriTo =
    compositionLocalOf<TextFieldState> { error("LocalFriTo Error") }
val LocalFriFrom =
    compositionLocalOf<TextFieldState> { error("LocalFriFrom Error") }
val LocalSatTo =
    compositionLocalOf<TextFieldState> { error("LocalSatTo Error") }
val LocalSatFrom =
    compositionLocalOf<TextFieldState> { error("LocalSatFrom Error") }
val LocalSunTo =
    compositionLocalOf<TextFieldState> { error("LocalSunTo Error") }
val LocalSunFrom =
    compositionLocalOf<TextFieldState> { error("LocalSunFrom Error") }