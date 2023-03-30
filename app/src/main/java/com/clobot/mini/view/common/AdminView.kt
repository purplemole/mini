package com.clobot.mini.view.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.clobot.mini.navigation.RouteAction

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
//            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "로봇 환경 설정(관리자 화면)",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            OutlineTextBtn({ routeAction.goBack() }, "취소")
            OutlineTextBtn({ routeAction.goBack() }, "저장 후 닫기")
        }
        // 주 영역
        Row() {
            // 좌측 Column
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "● 로봇 이름",
                    fontWeight = Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF6F6F6)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "설치장소 및 순번",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    TextField(
                        value = "야탑 치과_001",
                        onValueChange = { },
                        readOnly = true
                    )
                }
            }
            // 점선
            Canvas(modifier = Modifier
                .fillMaxHeight()) {
                drawLine(
                    color = Color.Red,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }
            // 우측 Column
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "test!!!")
            }
        }
    }
}