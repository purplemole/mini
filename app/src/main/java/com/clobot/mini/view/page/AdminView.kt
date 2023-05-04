package com.clobot.mini.view.page

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
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
import com.clobot.mini.view.components.CustomBox
import com.clobot.mini.view.components.RightPerSpinner
import com.clobot.mini.view.components.ui.MyIconPack
import com.clobot.mini.view.components.ui.myiconpack.Cancel
import com.clobot.mini.view.components.ui.theme.*
import com.clobot.mini.view.navigation.NavRoute
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
    val restrictFromTo = remember { TextFieldState() }
    restrictFromTo.setText(dataStore.getMoveRestrict.collectAsState(initial = "00:00~00:00").value)
    val monFromTo = remember { TextFieldState() }
    monFromTo.setText(dataStore.getMonFromTo.collectAsState(initial = "00:00~00:00").value)
    val tueFromTo = remember { TextFieldState() }
    tueFromTo.setText(dataStore.getTueFromTo.collectAsState(initial = "00:00~00:00").value)
    val wedFromTo = remember { TextFieldState() }
    wedFromTo.setText(dataStore.getWedFromTo.collectAsState(initial = "00:00~00:00").value)
    val thuFromTo = remember { TextFieldState() }
    thuFromTo.setText(dataStore.getThuFromTo.collectAsState(initial = "00:00~00:00").value)
    val friFromTo = remember { TextFieldState() }
    friFromTo.setText(dataStore.getFriFromTo.collectAsState(initial = "00:00~00:00").value)
    val satFromTo = remember { TextFieldState() }
    satFromTo.setText(dataStore.getSatFromTo.collectAsState(initial = "00:00~00:00").value)
    val sunFromTo = remember { TextFieldState() }
    sunFromTo.setText(dataStore.getSunFromTo.collectAsState(initial = "00:00~00:00").value)

    CompositionLocalProvider(
        LocalDataStore provides dataStore,
        LocalPromoteCycle provides promoteState,
        LocalForceCharging provides forceCharging,
        LocalRobotOperating provides robotOperating,

        LocalRestrictFromTo provides restrictFromTo,

        LocalMonFromTo provides monFromTo,
        LocalTueFromTo provides tueFromTo,
        LocalWedFromTo provides wedFromTo,
        LocalThuFromTo provides thuFromTo,
        LocalFriFromTo provides friFromTo,
        LocalSatFromTo provides satFromTo,
        LocalSunFromTo provides sunFromTo,
    ) {
        Scaffold {
            Column {
                AdminTopArea()// 상단 영역
                Row {
                    val modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                    LeftArea(modifier)
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
    val restrictT = LocalRestrictFromTo.current
    val monFromTo = LocalMonFromTo.current
    val tueFromTo = LocalTueFromTo.current
    val wedFromTo = LocalWedFromTo.current
    val thuFromTo = LocalThuFromTo.current
    val friFromTo = LocalFriFromTo.current
    val satFromTo = LocalSatFromTo.current
    val sunFromTo = LocalSunFromTo.current

    // 저장할 곳 (dataStore)
    val dataStore = LocalDataStore.current
    // scope 설정
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
            .padding(horizontal = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .clickable { routeAction.popTo(NavRoute.Main) }
                .background(
                    color = prc_gray800, shape = AdminRoundedBtn.small
                )
                .size(width = 38.dp, height = 19.dp)
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = {
                Icon(
                    imageVector = MyIconPack.Cancel,
                    contentDescription = "save",
                    modifier = Modifier
                        .size(9.dp)
                        .align(Alignment.CenterVertically),
                    tint = prc_white100
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(id = R.string.admin_B5),
                    style = AdminTypography.button,
                )
            }
        )
        Text(
            text = stringResource(id = R.string.admin_x1),
            style = AdminTypography.subtitle1
        )
        Row(
            modifier = Modifier
                .clickable {
                    scope.launch {
                        dataStore.saveAllAdminSetting(
                            promote = promoteT.getInt(),
                            forcePer = forcePer.getInt(),
                            operating = operating.getInt(),
                            restrictT = restrictT.getText(),
                            monT = monFromTo.getText(),
                            tueT = tueFromTo.getText(),
                            wedT = wedFromTo.getText(),
                            thuT = thuFromTo.getText(),
                            friT = friFromTo.getText(),
                            satT = satFromTo.getText(),
                            sunT = sunFromTo.getText(),
                        )
                    }
                    routeAction.goBack()
                }
                .background(
                    color = prc_check, shape = AdminRoundedBtn.small
                )
                .size(width = 63.dp, height = 19.dp)
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "save",
                    modifier = Modifier.size(9.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(id = R.string.admin_B6),
                    style = AdminTypography.button,
                )
            }
        )
    }
}

@Composable
fun LeftArea(modifier: Modifier) {
    // 좌측 Column
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(9.dp),
        content = {
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