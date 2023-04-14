package com.clobot.mini.view.common

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.clobot.mini.R
import com.clobot.mini.data.admin.*
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.util.state.IntFieldState
import kotlinx.coroutines.launch
import java.util.*

// Admin 에서만 사용할 Local 값.
private val LocalDataStore =
    staticCompositionLocalOf<StoreAdminSetting> { error("No dataStore File!") }
private val LocalPromoteCycle =
    compositionLocalOf<IntFieldState> { error("LocalPromoteCycleField Error") }
private val LocalForceCharging =
    compositionLocalOf<IntFieldState> { error("LocalForceCharging Error") }
private val LocalRobotOperating =
    compositionLocalOf<IntFieldState> { error("LocalRobotOperating Error") }

/**
 * @see tae
 * TODO : DataStore 작업 (1. stringState / 2. composable 연결 / 3. 저장 버튼에 연결 / 4. 값 불러 오기)
 */
@Composable
fun AdminView() {
    val context = LocalContext.current
    // Datastore
    val dataStore = StoreAdminSetting(context)

    // 저장된 설정 값 setting
    val promoteState = remember { IntFieldState() } // 이동 홍보
    promoteState.setInt(dataStore.getPromoteCycle.collectAsState(initial = 1).value)
    val forceCharging = remember { IntFieldState() } // 강제 충전 시작 퍼센트
    forceCharging.setInt(dataStore.getForceCharging.collectAsState(initial = 10).value)
    val robotOperating = remember { IntFieldState() } // 로봇 운영 시작 퍼센트
    robotOperating.setInt(dataStore.getOperatingPer.collectAsState(initial = 40).value)

    CompositionLocalProvider(
        LocalDataStore provides dataStore,
        LocalPromoteCycle provides promoteState,
        LocalForceCharging provides forceCharging,
        LocalRobotOperating provides robotOperating,
    ) {
        Scaffold {
            Column {
                AdminTopArea()// 상단 영역
                Row() {
                    val modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                    LeftArea(modifier)
                    DottedLine()
                    RightArea(modifier)
                }
            }
        }
    }
}

@Composable // 상단 영역 composable
fun AdminTopArea() {
    val routeAction = LocalRouteAction.current
    // 저장이 필요한 값
    val promoteT = LocalPromoteCycle.current
    val forcePer = LocalForceCharging.current
    val operating = LocalRobotOperating.current

    // 저장할 곳 (dataStore)
    val dataStore = LocalDataStore.current
    // scope 설정
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.Center,
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
                dataStore.saveAllAdminSetting(
                    promote = promoteT.getInt(),
                    forcePer = forcePer.getInt(),
                    operating = operating.getInt(),
                )
            }
            routeAction.goBack()
        }, stringResource(id = R.string.admin_B6))
    }
}

@Composable
fun LeftArea(modifier: Modifier) {
    // 좌측 Column
    LazyColumn(modifier = modifier, content = {
        val leftItems = AdminColumnItem.leftItemList
        items(leftItems) {
            CustomBox(titleText = it.titleText, contents = it.content)
        }
    })
}

@Composable // 가운데 구분 선
fun DottedLine() {
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
}

@Composable
fun RightArea(modifier: Modifier) {
    val rightItems = AdminColumnItem.rightItemList

    LazyColumn(
        modifier = modifier,
        content = {
            item {
                CustomBox(
                    titleText = rightItems[0].titleText,
                    contents = rightItems[0].content
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .background(Color(0xFFE9E7E7))
                ) {
                    val spinMod = Modifier
                        .weight(1f)
                        .padding(vertical = 3.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        RightPerSpinner(
                            modifier = spinMod,
                            spinName = R.string.admin_x16,
                            tmpPer = LocalForceCharging.current,
                            perList = listOf(10, 20, 30)
                        )
                        RightPerSpinner(
                            modifier = spinMod,
                            spinName = R.string.admin_x17,
                            tmpPer = LocalRobotOperating.current,
                            perList = listOf(40, 30, 20)
                        )
                    }
                    Text(
                        stringResource(id = R.string.admin_x18),
                        modifier = Modifier
                            .padding(start = 30.dp)
                            .padding(vertical = 5.dp),
                        style = TextStyle(fontSize = 10.sp)
                    )
                }
            }
            item {
                CustomBox(
                    titleText = rightItems[1].titleText,
                    contents = rightItems[1].content
                )
            }
        }
    )
}

@Composable // 이동 홍보 주기 버튼 composable
fun PromoteCycleBtn() {
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
                            Color(0xA9F7BA7A)
                        else
                            Color.White,
                    )
                    .padding(4.dp),
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
    }
}

@Composable // 강제 충전 시작, 로봇 운영 시작
fun RightPerSpinner(modifier: Modifier, spinName: Int, tmpPer: IntFieldState, perList: List<Int>) {
    val selectedItem = tmpPer.getInt()
    val onItemSelected = { select: Int -> tmpPer.setInt(select) }
    val expanded = rememberSaveable { mutableStateOf(false) }

    Row(modifier = modifier,
        content = {
            Text(
                text = stringResource(spinName),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
//, enabled = percentageList.isNotEmpty()
            OutlinedButton(
                onClick = { expanded.value = true },
                modifier = Modifier
                    .width(90.dp)
                    .height(35.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(2.dp, Color.Gray),
            ) {
                Text(
                    text = "${selectedItem}%",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.DarkGray
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                offset = DpOffset(x = (120).dp, y = (-2).dp)
            ) {
                perList.forEach {
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        onItemSelected(it)
                    }) {
                        Text(text = "${it}%")
                    }
                }
            }
        })
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