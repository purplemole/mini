package com.clobot.mini.view.hospital

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.clobot.mini.data.page.HospitalMenuDummyData
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.clobot.mini.R
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.navigation.NavigationGraph
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.clobot.mini.view.common.ImgMenuBtn
import com.clobot.mini.view.common.HospitalTopBar

@Composable
fun HospitalHome() {
    /**
     * TODO : SEQUENCIAL 상황 동작 (TTS...) 추후에 아래 LaunchedEffect 에 추가 필요
     * Composable launched tag name은 "Launched check"로 동일하게 (로그 보기 쉽게..)
     */
    LaunchedEffect(Unit) {
        Log.i("Launched check", "Main Launched")
    }

    Scaffold(
        topBar = { HospitalTopBar() },
        content = {
            HomeContent()
        }
    )
}

@Composable
private fun HomeContent() {
    val routeAction = LocalRouteAction.current
    val backgroundImg = painterResource(R.drawable.variant7)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = backgroundImg),
    ) {

        val mainMenus = remember { HospitalMenuDummyData.mainMenuList.filter { true } }
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 71.dp)
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(mainMenus) {
                ImgMenuBtn(menu = it, routeAction)
            }
        }

    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
@Composable
fun HopHomePreview() {
    MiniTheme {
        Column {
            NavigationGraph()
        }
    }
}