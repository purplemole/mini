package com.clobot.mini.view.page.menuReservationCustomer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.clobot.mini.R
import com.clobot.mini.data.page.HospitalMenuDummyData
import com.clobot.mini.view.components.HospitalTopBar
import com.clobot.mini.view.components.ImgMenuBtn
import com.clobot.mini.view.components.ImgMenuBtn2
import com.clobot.mini.view.components.ui.theme.pageTypography

// 4depth 예약 실패 페이지
@Composable
fun ReservationFailed() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { ReservationFailedContent() }
    )
}

@Composable
private fun ReservationFailedContent() {
    val failedMenus =
        remember { HospitalMenuDummyData.reservationFailedMenu.filter { true } }
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize(),
        content = {
            Image(
                painter = painterResource(R.drawable._failed),
                contentDescription = "reservation_failed background img",
            )
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_24)),
                content = {
                    Column(
                        content = {
                            Column(
                                content = {
                                    Text(
                                        text = stringResource(id = R.string.reservation_failed_x1),
                                        style = pageTypography.h1
                                    )
                                    Text(
                                        text = stringResource(id = R.string.reservation_failed_x2),
                                        style = pageTypography.h4
                                    )
                                },
                                verticalArrangement = Arrangement.spacedBy(
                                    dimensionResource(id = R.dimen.padding_48)
                                ),
                            )
                            Row(
                                content = {
                                    ImgMenuBtn(menu = failedMenus[0])
                                    ImgMenuBtn2(menu = failedMenus[1])
                                },
                                horizontalArrangement = Arrangement.spacedBy(
                                    dimensionResource(id = R.dimen.padding_24)
                                )
                            )
                        },
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                }
            )
        }
    )
}