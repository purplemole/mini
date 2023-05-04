package com.clobot.mini.view.page.menuReservationCustomer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.clobot.mini.R
import com.clobot.mini.data.page.HospitalMenuDummyData
import com.clobot.mini.view.components.HospitalTopBar
import com.clobot.mini.view.components.ImgMenuBtn
import com.clobot.mini.view.components.ui.theme.pageTypography
import com.clobot.mini.view.components.ui.theme.prc_name
import com.clobot.mini.view.components.ui.theme.prc_white100

// 4depth 예약 확인 성공 페이지
@Composable
fun ReservationConfirm() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { ReservationConfirmContent() }
    )
}

/**
 * TODO change called name
 *
 */
@Composable
private fun ReservationConfirmContent() {
    val name = "홍길동"
    val reservConfirmText = stringResource(id = R.string.reservation_confirm_x1)
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center,
        content = {
            Column(
                content = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_48)),
                        content = {
                            Column(
                                content = {
                                    Image(
                                        painterResource(R.drawable.confirm),
                                        contentDescription = "confirm",
                                        modifier = Modifier.size(37.dp),
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    color = prc_name,
                                                )
                                            ) {
                                                append(name)
                                            }

                                            withStyle(
                                                style = SpanStyle(
                                                    color = prc_white100
                                                )
                                            ) {
                                                append(reservConfirmText)
                                            }
                                        },
                                        style = pageTypography.h1
                                    )
                                },
                                horizontalAlignment = Alignment.CenterHorizontally
                            )
                        },
                    )
                    ImgMenuBtn(HospitalMenuDummyData.reservationConfirmMenu)
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        top = dimensionResource(R.dimen.padding_64),
                        bottom = dimensionResource(R.dimen.padding_48)
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            )
        })
}