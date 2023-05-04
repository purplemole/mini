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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clobot.mini.R
import com.clobot.mini.data.admin.*
import com.clobot.mini.util.*
import com.clobot.mini.util.state.IntFieldState
import com.clobot.mini.util.state.TextFieldState
import com.clobot.mini.view.components.CustomBox
import com.clobot.mini.view.components.noRippleClickable
import com.clobot.mini.view.components.ui.MyIconPack
import com.clobot.mini.view.components.ui.myiconpack.AdminChecked
import com.clobot.mini.view.components.ui.myiconpack.AdminUnchecked
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
            .height(26.dp)
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

@Composable
fun RightArea(modifier: Modifier) {
    val rightItems = AdminColumnItem.rightItemList
    Column(
        modifier = modifier.padding(start = 7.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                content = {
                    Text(
                        text = stringResource(id = rightItems[0].titleText),
                        style = AdminTypography.subtitle2
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = prc_gray900, shape = AdminRoundedBtn.medium)
                            .padding(horizontal = 7.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                content = {
                                    Text(
                                        text = stringResource(id = rightItems[0].content[0].subText),
                                        style = AdminTypography.h1
                                    )
                                    rightItems[0].content[0].cosUnit()
                                }
                            )
                            Row(
                                content = {
                                    Row(
                                        modifier = Modifier.weight(1f),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        content = {
                                            Text(
                                                text = stringResource(id = rightItems[0].content[1].subText),
                                                style = AdminTypography.h1
                                            )
                                            rightItems[0].content[1].cosUnit()
                                        })
                                    Spacer(modifier = Modifier.size(9.dp))
                                    Row(
                                        modifier = Modifier.weight(1f),
                                        Arrangement.SpaceBetween,
                                        content = {
                                            Text(
                                                text = stringResource(id = rightItems[0].content[2].subText),
                                                style = AdminTypography.h1
                                            )
                                            rightItems[0].content[2].cosUnit()
                                        })
                                })
                            Text(
                                text = stringResource(id = R.string.admin_x18),
                                style = AdminTypography.h4
                            )
                        }
                    )
                }
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                content = {
                    Text(
                        text = stringResource(id = rightItems[1].titleText),
                        style = AdminTypography.subtitle2
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = prc_gray900, shape = AdminRoundedBtn.medium)
                            .padding(horizontal = 7.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        content = {
                            items(rightItems[1].content) {
                                val checkedStatus = remember { mutableStateOf(false) }
                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    content = {
                                        Row(content = {
                                            RoundedCornerCheckBox(checkedStatus.value) { isChecked ->
                                                checkedStatus.value = isChecked
                                            }
                                            Spacer(modifier = Modifier.size(4.dp))
                                            Text(
                                                text = stringResource(id = it.subText),
                                                modifier = Modifier.align(Alignment.CenterVertically),
                                                style = AdminTypography.h1
                                            )
                                        })
                                        it.cosUnit()
                                    })
                            }
                        }
                    )
                })

        }
    )
}

@Composable
fun RoundedCornerCheckBox(
    isChecked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
) {
    Box(
        modifier = Modifier
            .size(14.dp)
            .noRippleClickable { onCheckedChange(!isChecked) },
        content = {
            Image(
                imageVector = if (isChecked) MyIconPack.AdminChecked else MyIconPack.AdminUnchecked,
                contentDescription = null
            )
        }
    )
}