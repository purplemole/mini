package com.clobot.mini.view.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clobot.mini.view.navigation.RouteAction
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.clobot.mini.R
import com.clobot.mini.data.admin.*
import com.clobot.mini.util.LocalRouteAction

@Composable
fun AdminView() {
    val routeAction = LocalRouteAction.current
    Template0(
        needTopBar = false,
        templateBody = { AdminContent(routeAction = routeAction) }
    )
}

/**
 * TODO 볼륨 설정 변경 / 로봇 상태 값 / 저장 버튼 onClick 동작 / DataStore 작업 / 뷰 반복 수정 / 배터리 사용
 *
 * @param routeAction : 병원 별로 사용 중인 navRouteAction
 */
@Composable
fun AdminContent(routeAction: RouteAction) {
//    // DataStore - preferences 사용을 위한 context
//    val context = LocalContext.current
//    // scope 설정
//    val scope = rememberCoroutineScope()
//    // Datastore
//    val dataStore = StoreAdminSetting(context)

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
                text = stringResource(id = R.string.admin_x1),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            OutlineTextBtn({ routeAction.goBack() }, stringResource(id = R.string.admin_B5))
            OutlineTextBtn({ routeAction.goBack() }, stringResource(id = R.string.admin_B6))
        }
        // 주 영역
        Row() {
            val columnModifier = Modifier
                .weight(1f)
                .padding(5.dp)
            // 좌측 Column
            LazyColumn(
                modifier = columnModifier
            ) {
                items(AdminColumnItem.leftItemList) {
                    CustomBox(it.titleText, it.content)
                }
            }

            // 점선
            Canvas(
                modifier = Modifier.fillMaxHeight()
            ) {
                drawLine(
                    color = Color.Red,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }

            // 우측 Column
            LazyColumn(
                modifier = columnModifier
            ) {
                items(AdminColumnItem.rightItemList) {
                    CustomBox(it.titleText, it.content)
                }
            }
        }
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
@Composable
fun AdminPreview() {
    MiniTheme {
        Column {
            CustomTextField(text = R.string.admin_x4, enabled = true)
        }
    }
}

//class AdminProvider : PreviewParameterProvider<RouteAction> {
//    override val values: Sequence<RouteAction>
//        get() = TODO("Not yet implemented")
//}