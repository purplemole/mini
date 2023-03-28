package com.example.mini.data

import com.example.mini.R
import com.example.mini.navigation.NavRoute

data class HospitalMenu(
    val picto: Int,
    val menu: String, //Int (R.string...)
    val page: String,
    val route: NavRoute
)

object HospitalMenuDummyData {
    private val sampleMenu = HospitalMenu(
        picto = R.drawable.ic_launcher_foreground,
        menu = "신규 고객",
        page = "main",
        route = NavRoute.NEW_CUSTOMER
    )

    val mainMenuList = listOf(
        sampleMenu,
        sampleMenu.copy(
            menu = "당일방문고객",
            route = NavRoute.EXISTING_CUSTOMER
        ),
        sampleMenu.copy(
            menu = "예약 고객",
            route = NavRoute.RESERVATION_CUSTOMER
        ),
        sampleMenu.copy(
            menu = "이용 안내",
            route = NavRoute.SITE_INFORMATION
        )
    )
}