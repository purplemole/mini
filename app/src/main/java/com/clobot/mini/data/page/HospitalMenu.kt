package com.clobot.mini.data.page

import com.clobot.mini.view.navigation.NavRoute

data class HospitalMenu(
    val picto: Int,
    val menu: Int, //Int (R.string...)
    val page: String,
    val route: NavRoute
)
