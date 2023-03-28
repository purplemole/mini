package com.example.mini.view.hospital

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mini.navigation.RouteAction
import com.example.mini.view.common.Template0

// 예약 고객 페이지
@Composable
fun ReservationCustomer(routeAction: RouteAction) {
    Template0(
        routeAction = routeAction,
        needTopBar = true,
        templateBody = { ReservationCustomerContent() })
}

// 예약 고객 페이지에 띄울 내용
@Composable
fun ReservationCustomerContent() {
    Box(
        modifier = Modifier
            .background(Color.Yellow)
    ) {
        Text(
            text = "Hello, World!", style = TextStyle(
                fontSize = 100.sp
            )
        )
    }
}