package com.clobot.mini.data.page

import com.clobot.mini.view.navigation.NavRoute

data class HospitalMenu(
    val picto: Int,
    val route: NavRoute,
    val menu : String = ""
)
