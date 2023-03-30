package com.example.mini.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mini.view.hospital.*

@Composable
fun NavigationGraph(startRoute: NavRoute = NavRoute.MAIN) {

    // 네비게이션 컨트롤러
    val navController = rememberNavController()
    // 네비게이션 라우트 액션
    val routeAction = remember(navController) { RouteAction(navController) }

    // NavHost 로 네비게이션 결정
    NavHost(navController, startRoute.routeName) {
//        Log.i("navController", "startRoute is ${startRoute.routeName}")
//        Log.i("navController", "navController is ${navController.graph.navigatorName}")

        // 메인
        composable(NavRoute.MAIN.routeName) { HospitalHome(routeAction) }

        // 신규 고객
        composable(NavRoute.NEW_CUSTOMER.routeName) { NewCustomer(routeAction) }
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