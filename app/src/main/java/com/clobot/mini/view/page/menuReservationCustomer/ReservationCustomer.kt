package com.clobot.mini.view.page.menuReservationCustomer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R
import com.clobot.mini.data.page.HospitalMenuDummyData
import com.clobot.mini.view.components.HospitalTopBar
import com.clobot.mini.view.components.ImgMenuBtn

// 예약 고객 페이지
@Composable
fun ReservationCustomer() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { ReservationCustomerContent() }
    )
}

// 예약 고객 페이지에 띄울 내용
@Composable
private fun ReservationCustomerContent() {
    val reservationMenus =
        remember { HospitalMenuDummyData.reservationMenuList.filter { true } }
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.reservation_customer),
            contentDescription = "reservation_customer background img",
        )
        Box(
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_48)),
            content = {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_24))
                ) {
                    items(reservationMenus) {
                        ImgMenuBtn(menu = it)
                    }
                }
            }
        )
    }

}