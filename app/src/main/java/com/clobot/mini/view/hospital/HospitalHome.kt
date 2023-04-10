package com.clobot.mini.view.hospital

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.clobot.mini.data.HospitalMenuDummyData
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.clobot.mini.view.navigation.NavigationGraph
import com.clobot.mini.view.navigation.RouteAction
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.clobot.mini.view.common.BottomTTSCaption
import com.clobot.mini.view.common.ImgMenuBtn
import com.clobot.mini.view.common.HospitalTopBar
import com.clobot.mini.view.navigation.LocalRouteAction

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
        topBar = { HospitalTopBar(false) },
        bottomBar = {
            BottomTTSCaption("test")
        },
        content = {
            HomeContent()
        },
        modifier = Modifier.padding(bottom = 15.dp)
    )
}

@Composable
fun HomeContent() {
    val routeAction = LocalRouteAction.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 30.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
//            NavigationGraph()
            val mainMenus = remember { HospitalMenuDummyData.mainMenuList.filter { true } }
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
            //BottomTTSCaption("로봇이 발화하는 멘트를 텍스트로 표시...")
        }
    }
}

@Composable
fun HospitalBarIcon(modifier: Modifier = Modifier, faIcon: FaIconType) {
    Card(
        elevation = 5.dp,
        modifier = modifier
            .clip(CircleShape)
    ) {
        Box(
            modifier = modifier.padding(5.dp)
        ) {
            FaIcon(faIcon = faIcon)
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