package com.example.mini.view.hospital

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mini.navigation.RouteAction
import com.example.mini.view.common.OutlineTextBtn
import com.example.mini.view.common.Template0

@Composable
fun AdminView(routeAction: RouteAction) {
    Template0(
        routeAction = routeAction,
        needTopBar = false,
        templateBody = { AdminContent(routeAction = routeAction) }
    )
}

@Composable
fun AdminContent(routeAction: RouteAction) {
    Column() {
        // 상단 영역
        Row() {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text = "로봇 환경 설정(관리자 화면)")
            }
            OutlineTextBtn({ routeAction.goBack() }, "취소")
            OutlineTextBtn({ routeAction.goBack() }, "다음 단계 진행")
        }
        // 주 영역
        Row() {
            // 좌측 Column
            Column() {

            }
            // 우측 Column
            Column() {

            }
        }
    }
}