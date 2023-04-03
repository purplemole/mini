package com.clobot.mini.data

import com.clobot.mini.R
import com.clobot.mini.view.navigation.NavRoute

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
        route = NavRoute.NewCustomer
    )

    private val sampleSitesMenu = HospitalMenu(
        picto = R.drawable.ic_launcher_foreground,
        menu = R.string.sites_information_b1,
        page = "sites-information",
        route = NavRoute.HospitalHours
    )

    val mainMenuList = listOf(
        sampleMainMenu,
        sampleMainMenu.copy(
            menu = R.string.main_b2,
            route = NavRoute.ExistingCustomer
        ),
        sampleMainMenu.copy(
            menu = R.string.main_b3,
            route = NavRoute.ReservationCustomer
        ),
        sampleMainMenu.copy(
            menu = R.string.main_b4,
            route = NavRoute.SitesInformation
        )
    )

    val sitesMenuList = listOf(
        sampleSitesMenu,
        sampleSitesMenu.copy(
            menu = R.string.sites_information_b2,
            route = NavRoute.ReservationMethod
        ),
        sampleSitesMenu.copy(
            menu = R.string.sites_information_b3,
            route = NavRoute.Parking
        )
    )
}