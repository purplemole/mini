package com.clobot.mini.view.common

import android.content.Context.AUDIO_SERVICE
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import androidx.compose.foundation.*
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
import androidx.compose.ui.platform.LocalContext
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
import com.clobot.mini.util.TextFieldState
import kotlinx.coroutines.launch

private val LocalTextFieldState = staticCompositionLocalOf<TextFieldState> {
    error("No TextFieldState provided")
}

@Composable
fun AdminView() {
    val routeAction = LocalRouteAction.current

    Scaffold(content = { AdminContent(routeAction) })
}

/**
 * @see tae
 * TODO : DataStore 작업 (1. stringState / 2. composable 연결 / 3. 저장 버튼에 연결 / 4. 값 불러 오기)
 *
 * @param routeAction : 병원 별로 사용 중인 navRouteAction
 */
@Composable
fun AdminContent(routeAction: RouteAction) {
    // DataStore - preferences 사용을 위한 context
    val context = LocalContext.current
    // scope 설정
    val scope = rememberCoroutineScope()
    // Datastore
    val dataStore = StoreAdminSetting(context)

    val leftItems = AdminColumnItem.leftItemList

    val textState = remember { TextFieldState() }
    textState.setText(dataStore.getTimeCycle.collectAsState(initial = "").value.toString())

    CompositionLocalProvider(LocalTextFieldState provides textState) {
        Column {
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
                OutlineTextBtn({
                    scope.launch {
                        dataStore.saveTimeCycle(textState.text.value)
                    }

                    routeAction.goBack()
                }, stringResource(id = R.string.admin_B6))
            }
            // 주 영역
            Row {
                val columnModifier = Modifier
                    .weight(1f)
                    .padding(5.dp)

                // 좌측 Column
                LazyColumn(modifier = columnModifier, content = {
                    items(leftItems) {
                        CustomBox(titleText = it.titleText, contents = it.content)
                    }
                })

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