package com.clobot.mini.view.page.menuNewCustomer

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
        routeAction.navTo(NavRoute.MovePosition1)
    }
}