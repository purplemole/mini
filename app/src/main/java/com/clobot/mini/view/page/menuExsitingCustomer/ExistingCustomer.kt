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

// 당일 방문 고객 페이지
@Composable
fun ExistingCustomer() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { ExistingCustomerContent() }
    )
}

// 당일 방문 고객 페이지 컨텐츠
@Composable
private fun ExistingCustomerContent() {
    val routeAction = LocalRouteAction.current
    Image(
        painter = painterResource(R.drawable.existing_customer),
        contentDescription = "existing-customer background img",
        contentScale = ContentScale.Crop
    )
    LaunchedEffect(Unit) {
        delay(5000)
        routeAction.navTo(NavRoute.ExistingInformation)
    }
}