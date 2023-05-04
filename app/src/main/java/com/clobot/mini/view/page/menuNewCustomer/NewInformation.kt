package com.clobot.mini.view.page.menuNewCustomer

import androidx.compose.foundation.Image
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.components.HospitalTopBar
import com.clobot.mini.view.navigation.NavRoute
import kotlinx.coroutines.delay


// 접수 방법 안내 페이지
@Composable
fun NewInformation() {
    Scaffold(
        topBar = { HospitalTopBar(false) },
        content = { NewInformationContent() }
    )
}

/** 접수 방법 안내 페이지 컨텐츠
 * TODO LaunchedEffect
 *
 */
@Composable
private fun NewInformationContent() {
    val routeAction = LocalRouteAction.current
    Image(
        painter = painterResource(R.drawable.new_information),
        contentDescription = "new_customer background img",
        contentScale = ContentScale.Crop
    )

    // 1. 화면 활성화
    LaunchedEffect(Unit) {
        // 2. tts 발화

        // 3. 발화 후 5초 대기
        delay(5000)

        // 4. 대기 화면으로 이동
        routeAction.navTo(NavRoute.Standby)
    }
}