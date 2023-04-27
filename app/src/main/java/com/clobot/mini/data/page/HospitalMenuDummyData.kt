package com.clobot.mini.data.page

import com.clobot.mini.R
import com.clobot.mini.view.navigation.NavRoute

object HospitalMenuDummyData {
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

    val reservationMenuList = listOf(
        HospitalMenu(
            picto = R.drawable.reservation_customer_b1,
            route = NavRoute.QrRecognition,
            menu = "reservation_customer_b1"
        ),
        HospitalMenu(
            picto = R.drawable.reservation_customer_b2,
            route = NavRoute.MovePosition1,
            menu = "reservation_customer_b2"
        ),
    )

    val reservationConfirmMenu = HospitalMenu(
        picto = R.drawable.reservation_confirm_b1,
        route = NavRoute.MovePosition2,
        menu = "reservation_confirm_b1"
    )

    val reservationFailedMenu = listOf(
        HospitalMenu(
            picto = R.drawable.reservation_failed_b1,
            route = NavRoute.MovePosition1,
            menu = "reservation_failed_b1"
        ),
        HospitalMenu(
            picto = R.drawable.reservation_failed_b2,
            route = NavRoute.QrRecognition,
            menu = "reservation_failed_b2"
        ),
    )

    val sitesMenuList = listOf(
        HospitalMenu(
            picto = R.drawable.site_information_b1,
            route = NavRoute.HospitalHours,
            menu = "site_information_b1"
        ),
        HospitalMenu(
            picto = R.drawable.site_information_b2,
            route = NavRoute.ReservationMethod,
            menu = "site_information_b2"
        ),
        HospitalMenu(
            picto = R.drawable.site_information_b3,
            route = NavRoute.Parking,
            menu = "site_information_b3"
        )
    )

    val nameCallingMenu = HospitalMenu(
        picto = R.drawable.name_calling_b1,
        route = NavRoute.NameQr,
        menu = "name_calling_b1"
    )
}