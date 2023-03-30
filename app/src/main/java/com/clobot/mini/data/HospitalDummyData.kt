package com.clobot.mini.data

import com.clobot.mini.R

object HospitalDummyData {
    private val originHop = Hospital(
        hospital = "test",
        bootImg = R.drawable.ic_launcher_background
    )

    val hopList = listOf(
        originHop,
        originHop.copy(
            hospital = "test2",
            bootImg = R.drawable.ic_launcher_foreground
        )
    )
}