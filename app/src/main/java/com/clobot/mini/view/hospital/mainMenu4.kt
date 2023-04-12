package com.clobot.mini.view.hospital

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.clobot.mini.data.page.HospitalMenuDummyData
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.common.ImgMenuBtn
import com.clobot.mini.view.common.Template0

@Composable
fun SitesInformation() {
    val routeAction = LocalRouteAction.current
    Template0(needTopBar = true, templateBody = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 30.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val mainMenus = remember { HospitalMenuDummyData.sitesMenuList.filter { true } }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 70.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(mainMenus) {
                        ImgMenuBtn(menu = it, routeAction)
                    }
                }
            }
        }
    })
}

@Composable
fun HospitalHours() {
    Template0(needTopBar = true, templateBody = {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "진료 시간 - 수정 필요")
        }
    })
}

@Composable
fun ReservationMethod() {
    Template0(needTopBar = true, templateBody = {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.LightGray)
        )
    })
}

@Composable
fun Parking() {
    Template0(needTopBar = true, templateBody = {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.LightGray)
        )
    })
}