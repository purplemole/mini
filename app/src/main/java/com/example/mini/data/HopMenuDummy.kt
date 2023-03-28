package com.example.mini.data

import com.example.mini.R
import com.example.mini.navigation.NavRoute

data class HospitalMenu(
    val picto: Int,
    val menu: Int, //Int (R.string...)
    val page: String,
    val route: NavRoute
)

object HospitalMenuDummyData {
    private val sampleMainMenu = HospitalMenu(
        picto = R.drawable.ic_launcher_foreground,
        menu = R.string.main_b1,
        page = "main",
        route = NavRoute.NEW_CUSTOMER
    )

    private val sampleSitesMenu = HospitalMenu(
        picto = R.drawable.ic_launcher_foreground,
        menu = R.string.sites_information_b1,
        page = "sites-information",
        route = NavRoute.HOSPITAL_HOURS
    )

    val mainMenuList = listOf(
        sampleMainMenu,
        sampleMainMenu.copy(
            menu = R.string.main_b2,
            route = NavRoute.EXISTING_CUSTOMER
        ),
        sampleMainMenu.copy(
            menu = R.string.main_b3,
            route = NavRoute.RESERVATION_CUSTOMER
        ),
        sampleMainMenu.copy(
            menu = R.string.main_b4,
            route = NavRoute.SITES_INFORMATION
        )
    )

    val sitesMenuList = listOf(
        sampleSitesMenu,
        sampleSitesMenu.copy(
            menu = R.string.sites_information_b2,
            route = NavRoute.RESERVATION_METHOD
        ),
        sampleSitesMenu.copy(
            menu = R.string.sites_information_b3,
            route = NavRoute.PARKING
        )
    )
}