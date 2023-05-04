package com.clobot.mini.view.page.menuExsitingCustomer

import androidx.compose.foundation.Image
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.components.HospitalTopBar
import com.clobot.mini.view.navigation.NavRoute
import kotlinx.coroutines.delay

// 예약 방법 안내 페이지
/**
 * TODO routeAction 을 통해 다음 페이지 이동 시키기(STANDBY, 자동으로)
 *
 */
@Composable
fun ExistingInformation() {
    Scaffold(
        topBar = { HospitalTopBar(false) },
        content = { ExistingInformationContent() }
    )
}

// 예약 방법 안내 페이지
@Composable
private fun ExistingInformationContent() {
    val routeAction = LocalRouteAction.current
    Image(
        painter = painterResource(R.drawable.existing_information),
        contentDescription = "existing_information background img",
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