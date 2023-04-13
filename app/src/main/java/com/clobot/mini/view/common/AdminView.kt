package com.clobot.mini.view.common

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clobot.mini.view.navigation.RouteAction
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.clobot.mini.R
import com.clobot.mini.data.admin.*
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.util.state.IntFieldState
import com.clobot.mini.util.state.TextFieldState
import kotlinx.coroutines.launch
import java.util.*

private val LocalDataStore = compositionLocalOf<StoreAdminSetting> { error("No dataStore File!") }
private val LocalTextFieldState = compositionLocalOf<TextFieldState> {
    error("No TextFieldState provided")
}
private val LocalPromoteCycle =
    compositionLocalOf<IntFieldState> { error("No LocalPromoteCycleField") }

@Composable
fun AdminView() {
    val routeAction = LocalRouteAction.current
    // DataStore - preferences 사용을 위한 context
    val context = LocalContext.current
    // Datastore
    val dataStore = StoreAdminSetting(context)

    CompositionLocalProvider(LocalDataStore provides dataStore) {
        Scaffold(content = { AdminContent(routeAction) })
    }
}

/**
 * @see tae
 * TODO : DataStore 작업 (1. stringState / 2. composable 연결 / 3. 저장 버튼에 연결 / 4. 값 불러 오기)
 *
 * @param routeAction : 병원 별로 사용 중인 navRouteAction
 */
@Composable
fun AdminContent(routeAction: RouteAction) {
    val dataStore = LocalDataStore.current
    // scope 설정
    val scope = rememberCoroutineScope()

    val leftItems = AdminColumnItem.leftItemList

    //////// 저장된 설정 값 setting
    val textState = remember { TextFieldState() }
    textState.setText(dataStore.getTimeCycle.collectAsState(initial = "").value)

    val promoteState = remember { IntFieldState() }
    promoteState.setInt(dataStore.getPromoteCycle.collectAsState(initial = 1).value)


    CompositionLocalProvider(
        LocalTextFieldState provides textState,
        LocalPromoteCycle provides promoteState
    ) {
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
                    // 저장 버튼
                    scope.launch {
//                        dataStore.saveTimeCycle(textState.getText())
                        dataStore.saveAllAdminSetting(promoteState.int.value)
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

// 라디오 버튼
@Composable
fun CustomRadioButton() {
    val tmpCycle = LocalPromoteCycle.current
    val selectOptionList = listOf(1, 3, 5, 10)
    val selectedOption = tmpCycle.getInt()
    val onSelectionChange = { choose: Int -> tmpCycle.setInt(choose) }
    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        selectOptionList.forEach {
            Text(
                text = "${it}분",
                modifier = Modifier
                    .noRippleClickable { onSelectionChange(it) }
                    .width(40.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(
                        shape = RoundedCornerShape(6.dp),
                        color =
                        if (it == selectedOption)
                            Color(0xFFF7BA7A)
                        else
                            Color.White,
                        )
                    .padding(4.dp),
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
    }
}

// 타임 피커
@Composable
fun CustomTimePicker() {
    val calendar = Calendar.getInstance()
    var timeState by remember { mutableStateOf("00:00") }
    val timePickerDialog = CustomTimePickerDialog(
        LocalContext.current,
        { _, hourOfDay, minute ->
            timeState = String.format("%02d : %02d", hourOfDay, minute)
        },
        calendar[Calendar.HOUR_OF_DAY],
        calendar[Calendar.MINUTE],
        false
    )
    // 뒷배경 제거
    timePickerDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

    OutlinedButton(
        onClick = { timePickerDialog.show() },
        border = BorderStroke(1.dp, Color.White),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.defaultMinSize(
            minWidth = 25.dp,
            minHeight = 30.dp
        )
    ) {
        Text(text = timeState)
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
@Composable
fun AdminPreview() {
    MiniTheme {
        Column {
            //CustomTextField(text = R.string.admin_x4, enabled = true)
        }
    }
}