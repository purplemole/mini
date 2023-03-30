package com.example.mini.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mini.viewModel.NavigationViewModel
import com.example.mini.view.hospital.*

/**
 * TODO routeAction 을 viewModel 로 빼는 게 가능?
 *
 * @param startRoute
 */
@Composable
fun NavigationGraph(startRoute: NavRoute = NavRoute.MAIN) {
    /**
     * 각각 다른 navController 와 routeAction 이 있으면 NavHost graph 를 다르게 사용 가능
     * => 병원 여러 개? 그래프 따로..
     * val navControllerVer2 = rememberNavController()
     * NavHost(navControllerVer2, ..) {//..}
     *
     * NavHost 의 역할은 네비게이션 결정 => 인자 다르게 해서 여러 개 선언 가능
     */

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