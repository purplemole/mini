package com.clobot.mini.view.hospital

import androidx.compose.foundation.Image
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.common.HospitalTopBar
import com.clobot.mini.view.navigation.NavRoute
import kotlinx.coroutines.delay

// 신규 고객 페이지
@Composable
fun NewCustomer() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { NewCustomerContent() }
    )
}

/** 신규 고객 페이지 컨텐츠
 * TODO 다음 페이지 넘어 가는 방법 => 접수 장소 이동 후 페이지 전환
 *
 */
@Composable
private fun NewCustomerContent() {
    val routeAction = LocalRouteAction.current
    Image(
        painter = painterResource(R.drawable.new_customer),
        contentDescription = "new_customer background img",
        contentScale = ContentScale.Crop
    )
    LaunchedEffect(Unit) {
        delay(5000)
        routeAction.navTo(NavRoute.NewInformation)
    }
}

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