package com.clobot.mini.data.page

import com.clobot.mini.R
import com.clobot.mini.view.navigation.NavRoute

object HospitalMenuDummyData {
    private val sampleSitesMenu = HospitalMenu(
        picto = R.drawable.ic_launcher_foreground,
        route = NavRoute.HospitalHours
    )

    val mainMenuList = listOf(
        HospitalMenu(
            picto = R.drawable.main_b1,
            route = NavRoute.NewCustomer,
            menu = "main_b1",
        ),
        HospitalMenu(
            picto = R.drawable.main_b2,
            route = NavRoute.ExistingCustomer,
            menu = "main_b2",
        ),
        HospitalMenu(
            picto = R.drawable.main_b3,
            route = NavRoute.ReservationCustomer,
            menu = "main_b3",
        ),
        HospitalMenu(
            picto = R.drawable.main_b4,
            route = NavRoute.SitesInformation,
            menu = "main_b4",
        )
    )

    val sitesMenuList = listOf(
        sampleSitesMenu,
        sampleSitesMenu.copy(
            route = NavRoute.ReservationMethod
        ),
        sampleSitesMenu.copy(
            route = NavRoute.Parking
        )
    )
}