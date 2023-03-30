package com.example.mini.navigation

import com.example.mini.viewModel.NavigationViewModel

class RouteAction(private val viewModel: NavigationViewModel) {
    // 특정 라우트 이동
    fun navTo(route: NavRoute) {
        viewModel.navTo(route)
    }

    // 뒤로 가기
    fun goBack() {
        viewModel.goBack()
    }

    // 메인 화면 이동
    fun goMain() {
        viewModel.goMain()
    }
}