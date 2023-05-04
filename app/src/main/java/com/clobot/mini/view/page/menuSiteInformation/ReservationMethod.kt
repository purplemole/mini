package com.clobot.mini.view.page.menuSiteInformation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R
import com.clobot.mini.view.components.HospitalTopBar

@Composable
fun ReservationMethod() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = {
            Image(
                painter = painterResource(R.drawable.reservation_method),
                contentDescription = "reservation-method",
                contentScale = ContentScale.Crop
            )
        },
        modifier = Modifier.fillMaxSize()
    )
}