package com.clobot.mini.navigation

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

    val route : NavigationViewModel = hiltViewModel()
    route.setNavController(navController)
    val routeAction = RouteAction(route)

    NavHost(navController, startDestination = NavRoute.MAIN.routeName){
        // 메인
        composable(NavRoute.MAIN.routeName) { HospitalHome(routeAction) }

        // 신규 고객
        composable(NavRoute.NEW_CUSTOMER.routeName, content = { NewCustomer(routeAction) })
        // 접수 방법 안내
        composable(NavRoute.NEW_INFORMATION.routeName) { NewInformation(routeAction) }

        // 당일 방문 고객
        composable(NavRoute.EXISTING_CUSTOMER.routeName) { ExistingCustomer(routeAction) }
        // 예약 방법 안내
        composable(NavRoute.EXISTING_INFORMATION.routeName) { ExistingInformation(routeAction) }

        // 예약 고객
        composable(NavRoute.RESERVATION_CUSTOMER.routeName) { ReservationCustomer(routeAction = routeAction) }
        // 예약 확인
        composable(NavRoute.QR_RECOGNITION.routeName) { QrRecognition(routeAction = routeAction) }

        // 이용 안내
        composable(NavRoute.SITES_INFORMATION.routeName) { SitesInformation(routeAction = routeAction)}
        // 진료 시간
        composable(NavRoute.HOSPITAL_HOURS.routeName) { HospitalHours(routeAction)}
        // 예약 방법
        composable(NavRoute.RESERVATION_METHOD.routeName) { ReservationMethod(routeAction)}
        // 주차 안내
        composable(NavRoute.PARKING.routeName) { Parking(routeAction = routeAction)}

        // 관리자 화면
        composable(NavRoute.ADMIN.routeName) { AdminView(routeAction = routeAction) }
    }
}