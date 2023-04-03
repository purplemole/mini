package com.clobot.mini.view.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clobot.mini.view.common.AdminView
import com.clobot.mini.model.NavigationViewModel
import com.clobot.mini.view.hospital.*

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    val route: NavigationViewModel = hiltViewModel()
    route.setNavController(navController)
    val routeAction = RouteAction(route)

    NavHost(navController, startDestination = NavRoute.Main.routeName) {
        // 메인
        composable(NavRoute.Main.routeName) { HospitalHome(routeAction) }

        // 신규 고객
        composable(NavRoute.NewCustomer.routeName, content = { NewCustomer(routeAction) })
        // 접수 방법 안내
        composable(NavRoute.NewInformation.routeName) { NewInformation(routeAction) }

        // 당일 방문 고객
        composable(NavRoute.ExistingCustomer.routeName) { ExistingCustomer(routeAction) }
        // 예약 방법 안내
        composable(NavRoute.ExistingInformation.routeName) { ExistingInformation(routeAction) }

        // 예약 고객
        composable(NavRoute.ReservationCustomer.routeName) { ReservationCustomer(routeAction = routeAction) }
        // 예약 확인
        composable(NavRoute.QrRecognition.routeName) { QrRecognition(routeAction = routeAction) }

        // 이용 안내
        composable(NavRoute.SitesInformation.routeName) { SitesInformation(routeAction = routeAction) }
        // 진료 시간
        composable(NavRoute.HospitalHours.routeName) { HospitalHours(routeAction) }
        // 예약 방법
        composable(NavRoute.ReservationMethod.routeName) { ReservationMethod(routeAction) }
        // 주차 안내
        composable(NavRoute.Parking.routeName) { Parking(routeAction = routeAction) }

        // 관리자 화면
        composable(NavRoute.Admin.routeName) { AdminView(routeAction = routeAction) }
    }
}