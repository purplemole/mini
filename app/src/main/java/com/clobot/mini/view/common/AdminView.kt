package com.clobot.mini.view.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clobot.mini.R
import com.clobot.mini.data.admin.*
import com.clobot.mini.util.*
import com.clobot.mini.util.state.IntFieldState
import com.clobot.mini.util.state.TextFieldState
import com.clobot.mini.view.common.ui.theme.*
import kotlinx.coroutines.launch

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
    val limitFrom = remember { TextFieldState() } // 이동 제한 시간
    limitFrom.setText(dataStore.getLimitFrom.collectAsState(initial = "00:00").value)
    val limitTo = remember { TextFieldState() }
    limitTo.setText(dataStore.getLimitTo.collectAsState(initial = "00:00").value)
    val monFrom = remember {TextFieldState()}
    monFrom.setText(dataStore.getMonFrom.collectAsState(initial = "00:00").value)
    val tueFrom = remember {TextFieldState()}
    tueFrom.setText(dataStore.getTueFrom.collectAsState(initial = "00:00").value)
    val wedFrom = remember {TextFieldState()}
    wedFrom.setText(dataStore.getWedFrom.collectAsState(initial = "00:00").value)
    val thuFrom = remember {TextFieldState()}
    thuFrom.setText(dataStore.getThuFrom.collectAsState(initial = "00:00").value)
    val friFrom = remember {TextFieldState()}
    friFrom.setText(dataStore.getFriFrom.collectAsState(initial = "00:00").value)
    val satFrom = remember {TextFieldState()}
    satFrom.setText(dataStore.getSatFrom.collectAsState(initial = "00:00").value)
    val sunFrom = remember {TextFieldState()}
    sunFrom.setText(dataStore.getSunFrom.collectAsState(initial = "00:00").value)
    val monTo = remember {TextFieldState()}
    monTo.setText(dataStore.getMonTo.collectAsState(initial = "00:00").value)
    val tueTo = remember {TextFieldState()}
    tueTo.setText(dataStore.getTueTo.collectAsState(initial = "00:00").value)
    val wedTo = remember {TextFieldState()}
    wedTo.setText(dataStore.getWedTo.collectAsState(initial = "00:00").value)
    val thuTo = remember {TextFieldState()}
    thuTo.setText(dataStore.getThuTo.collectAsState(initial = "00:00").value)
    val friTo = remember {TextFieldState()}
    friTo.setText(dataStore.getFriTo.collectAsState(initial = "00:00").value)
    val satTo = remember {TextFieldState()}
    satTo.setText(dataStore.getSatTo.collectAsState(initial = "00:00").value)
    val sunTo = remember {TextFieldState()}
    sunTo.setText(dataStore.getSunTo.collectAsState(initial = "00:00").value)

    CompositionLocalProvider(
        LocalDataStore provides dataStore,
        LocalPromoteCycle provides promoteState,
        LocalForceCharging provides forceCharging,
        LocalRobotOperating provides robotOperating,
        LocalMoveLimitTo provides limitTo,
        LocalMoveLimitFrom provides limitFrom,
        LocalMonFrom provides monFrom,
        LocalTueFrom provides tueFrom,
        LocalWedFrom provides wedFrom,
        LocalThuFrom provides thuFrom,
        LocalFriFrom provides friFrom,
        LocalSatFrom provides satFrom,
        LocalSunFrom provides sunFrom,
        LocalMonTo provides monTo,
        LocalTueTo provides tueTo,
        LocalWedTo provides wedTo,
        LocalThuTo provides thuTo,
        LocalFriTo provides friTo,
        LocalSatTo provides satTo,
        LocalSunTo provides sunTo,
    ) {
        Scaffold {
            Column {
                AdminTopArea()// 상단 영역
                Row {
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
    val limitFrom = LocalMoveLimitFrom.current
    val limitTo = LocalMoveLimitTo.current
    val monFrom = LocalMonFrom.current
    val tueFrom = LocalTueFrom.current
    val wedFrom = LocalWedFrom.current
    val thuFrom = LocalThuFrom.current
    val friFrom = LocalFriFrom.current
    val satFrom = LocalSatFrom.current
    val sunFrom = LocalSunFrom.current
    val monTo = LocalMonTo.current
    val tueTo = LocalTueTo.current
    val wedTo = LocalWedTo.current
    val thuTo = LocalThuTo.current
    val friTo = LocalFriTo.current
    val satTo = LocalSatTo.current
    val sunTo = LocalSunTo.current

    // 저장할 곳 (dataStore)
    val dataStore = LocalDataStore.current
    // scope 설정
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            contentAlignment = Alignment.Center,
            content = {
                Text(
                    text = stringResource(id = R.string.admin_x1),
//                    textAlign = TextAlign.Center
                )
            }
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            content = {
                OutlinedButton(onClick = { routeAction.goBack() },
                    shape = AdminRoundedBtn.large,
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = AdminSelect,
                        contentColor = AdminClicked
                    ),
                    border = adminBorder,
                    elevation = elevation(2.dp),
                    modifier = Modifier.width(115.dp),
                    content = {
                        Text(
                            text = stringResource(id = R.string.admin_B5),
                            color = Color.Black
                        )
                    })
                OutlinedButton(
                    onClick = {// 저장 버튼
                        scope.launch {
                            dataStore.saveAllAdminSetting(
                                promote = promoteT.getInt(),
                                forcePer = forcePer.getInt(),
                                operating = operating.getInt(),
                                limitFrom = limitFrom.getText(),
                                limitTo = limitTo.getText(),
                                monOpeFrom = monFrom.getText(),
                                tueOpeFrom = tueFrom.getText(),
                                wedOpeFrom = wedFrom.getText(),
                                thuOpeFrom = thuFrom.getText(),
                                friOpeFrom = friFrom.getText(),
                                satOpeFrom = satFrom.getText(),
                                sunOpeFrom = sunFrom.getText(),
                                monOpeTo = monTo.getText(),
                                tueOpeTo = tueTo.getText(),
                                wedOpeTo = wedTo.getText(),
                                thuOpeTo = thuTo.getText(),
                                friOpeTo = friTo.getText(),
                                satOpeTo = satTo.getText(),
                                sunOpeTo = sunTo.getText(),
                            )
                        }
                        routeAction.goBack()
                    },
                    shape = AdminRoundedBtn.large,
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = AdminSelect,
                        contentColor = AdminClicked
                    ),
                    border = adminBorder,
                    elevation = elevation(2.dp),
                    modifier = Modifier.width(115.dp),
                    content = {
                        Text(
                            text = stringResource(id = R.string.admin_B6),
                            color = Color.Black
                        )
                    })
            }
        )

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
                        .background(Color(0xFFE9E7E7)),
                    content = {
                        val spinMod = Modifier
                            .weight(1f)

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
                )
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