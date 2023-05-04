package com.clobot.mini.view.page.menuReservationCustomer

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import com.clobot.mini.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*

@Composable
fun ReservationInformation() {
    Image(
        painter = painterResource(R.drawable.reservation_information),
        contentDescription = "reservation-information",
        contentScale = ContentScale.Crop
    )
}
