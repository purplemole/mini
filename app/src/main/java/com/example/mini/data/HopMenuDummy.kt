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
    private val sampleMainMenu = HospitalMenu(
        picto = R.drawable.ic_launcher_foreground,
        menu = "신규 고객",
        page = "main",
        route = NavRoute.NEW_CUSTOMER
    )

    private val sampleSitesMenu = HospitalMenu(
        picto = R.drawable.ic_launcher_foreground,
        menu = "진료 시간",
        page = "sites-information",
        route = NavRoute.HOSPITAL_HOURS
    )

    val mainMenuList = listOf(
        sampleMainMenu,
        sampleMainMenu.copy(
            menu = "당일방문고객",
            route = NavRoute.EXISTING_CUSTOMER
        ),
        sampleMainMenu.copy(
            menu = "예약 고객",
            route = NavRoute.RESERVATION_CUSTOMER
        ),
        sampleMainMenu.copy(
            menu = "이용 안내",
            route = NavRoute.SITE_INFORMATION
        )
    )

    val sitesMenuList = listOf(
        sampleSitesMenu,
        sampleSitesMenu.copy(
            menu = "예약 방법",
            route = NavRoute.RESERVATION_METHOD
        ),
        sampleSitesMenu.copy(
            menu = "주차 안내",
            route = NavRoute.PARKING
        )
    )
}