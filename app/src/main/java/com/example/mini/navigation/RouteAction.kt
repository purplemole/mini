package com.example.mini.navigation

import androidx.navigation.NavHostController

class RouteAction(navHostController: NavHostController) {

    // 특정 라우트로 이동
    val navTo: (NavRoute) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }

    // 뒤로가기 이동
    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }

    val twoStepBack : () -> Unit = {
        navHostController.navigateUp()
        navHostController.navigateUp()
    }

    // main 직전까지 clear
    val goMain: () -> Unit = {
        //navHostController.popBackStack(NavRoute.MAIN.routeName, false)
        navHostController.navigate(NavRoute.MAIN.routeName){
            popUpTo(NavRoute.MAIN.routeName)
        }
    }
}