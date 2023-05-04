package com.clobot.mini.view.page.menuReservationCustomer

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R

@Composable
fun WaitingArea() {
    Image(
        painter = painterResource(R.drawable.waiting_area),
        contentDescription = "waiting-area",
        contentScale = ContentScale.Crop
    )
}