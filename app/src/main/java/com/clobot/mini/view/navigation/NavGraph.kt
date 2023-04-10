package com.clobot.mini.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clobot.mini.view.common.AdminView
import com.clobot.mini.model.NavigationViewModel
import com.clobot.mini.view.hospital.*
import com.clobot.mini.view.move.Standby

/*
* Composition 계층 내의 모든 Composable 에 제공될 수 있는 로컬 값
* */
val LocalRouteAction = compositionLocalOf<RouteAction> {error("No RouteActionProvided")}
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    val route: NavigationViewModel = hiltViewModel()
    route.setNavController(navController)
    val routeAction = RouteAction(route)

    // 해당 로컬 변수와 함께 제공 되는 모든 composable 은 routeAction 접근 가능 (매개 변수 없이)
    CompositionLocalProvider(LocalRouteAction provides routeAction) {
        NavHost(navController, startDestination = NavRoute.Main.routeName) {
            // 메인
            composable(NavRoute.Main.routeName) { HospitalHome() }

            // 신규 고객
            composable(NavRoute.NewCustomer.routeName) { NewCustomer() }
            // 접수 방법 안내
            composable(NavRoute.NewInformation.routeName) { NewInformation() }

            // 당일 방문 고객
            composable(NavRoute.ExistingCustomer.routeName) { ExistingCustomer() }
            // 예약 방법 안내
            composable(NavRoute.ExistingInformation.routeName) { ExistingInformation() }

            // 예약 고객
            composable(NavRoute.ReservationCustomer.routeName) { ReservationCustomer() }
            // 예약 확인
            composable(NavRoute.QrRecognition.routeName) { QrRecognition() }

            // 이용 안내
            composable(NavRoute.SitesInformation.routeName) { SitesInformation() }
            // 진료 시간
            composable(NavRoute.HospitalHours.routeName) { HospitalHours() }
            // 예약 방법
            composable(NavRoute.ReservationMethod.routeName) { ReservationMethod() }
            // 주차 안내
            composable(NavRoute.Parking.routeName) { Parking() }

            // 관리자 화면
            composable(NavRoute.Admin.routeName) { AdminView() }

            // 스탠바이
            composable(NavRoute.Standby.routeName) { Standby() }
        }
    }

}