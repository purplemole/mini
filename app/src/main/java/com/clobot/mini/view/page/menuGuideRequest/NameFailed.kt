package com.clobot.mini.view.page.menuGuideRequest

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.clobot.mini.R
import com.clobot.mini.data.page.HospitalMenuDummyData
import com.clobot.mini.view.components.HospitalTopBar2
import com.clobot.mini.view.components.ImgMenuBtn
import com.clobot.mini.view.components.ImgMenuBtn2
import com.clobot.mini.view.components.ui.theme.pageTypography

// 본인 확인 실패
@Composable
fun NameFailed() {
    Scaffold(
        topBar = {
            HospitalTopBar2(
                title = stringResource(id = R.string.name_failed_title)
            )
        },
        content = {
            NameFailedContent()
        }
    )
}

@Composable
private fun NameFailedContent() {
    val failedMenus =
        remember { HospitalMenuDummyData.reservationFailedMenu.filter { true } }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
        content = {
            Image(
                painter = painterResource(R.drawable._failed),
                contentDescription = "name-failed background img",
                contentScale = ContentScale.Crop
            )
            Column(
                content = {
                    Text(
                        text = stringResource(id = R.string.name_failed_x1),
                        style = pageTypography.h1
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_64),
                        bottom = dimensionResource(R.dimen.padding_48)
                    )
            )
        }
    )
}