package com.example.mini.view.hospital

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.mini.data.HospitalMenuDummyData
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.mini.navigation.NavigationGraph
import com.example.mini.navigation.RouteAction
import com.example.mini.ui.theme.MiniTheme
import com.example.mini.view.common.BottomTTSCaption
import com.example.mini.view.common.ImgMenuBtn
import com.example.mini.view.common.HospitalTopBar
import com.example.mini.view.common.Template1

@Composable
fun HospitalHome(routeAction: RouteAction) {
    Scaffold(
        topBar = { HospitalTopBar(routeAction, false) },
        bottomBar = {
            BottomTTSCaption("test")
        },
        content = {
            HomeContent(routeAction = routeAction)
        },
        modifier = Modifier.padding(bottom = 15.dp)
    )
}

@Composable
fun HomeContent(routeAction: RouteAction) {
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