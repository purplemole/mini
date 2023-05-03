package com.clobot.mini.view.common

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clobot.mini.MainApplication
import com.clobot.mini.R
import com.clobot.mini.data.network.NetworkState
import com.clobot.mini.util.*
import com.clobot.mini.view.common.ui.theme.*
import com.clobot.mini.view.navigation.NavRoute

@SuppressLint("SimpleDateFormat")
@Composable
fun BootCheck() {
    // 화면이 넘어가지 않을 경우를 대비한 임시 값
    val shouldShowNavigationGraph = remember { mutableStateOf(false) }
    val routeAction = LocalRouteAction.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_28))
    ) {
        Text(
            text = stringResource(id = R.string.boot_check_t1),
            modifier = Modifier.clickable { shouldShowNavigationGraph.value = true },
            style = pageTypography.h2,
            color = prc_white100
        )
        BootCheckContent()
    }

    if (shouldShowNavigationGraph.value) {
        routeAction.navTo(NavRoute.Main)
        routeAction.leftPop()
    }

}

@Composable
private fun BootCheckContent() {
    //val robotViewModel = LocalRobotViewModel.current
    Column(
        modifier = Modifier
            .width(width = 304.dp)
            .background(
                color = prc_gray900,
                shape = Shapes.large
            )
            .padding(dimensionResource(id = R.dimen.padding_48)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_24)),
        content = {
            DockConnect()
            NetConnect()
            RelatedProcess()
            GetStorage()
            FinalBoot()
        }
    )
}

@Composable
private fun DockConnect() {
    val robotViewModel = LocalRobotViewModel.current
    val dock = robotViewModel.dockingState.collectAsState(initial = false)

// Docking Station
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            stringResource(R.string.book_check_x1),
            style = pageTypography.h5,
            color = prc_white100
        )

        BootTimeItem(dock.value)
    }
}

@Composable
private fun NetConnect() {
    val viewModel = LocalMainViewModel.current
    // Network
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            Text(
                stringResource(R.string.book_check_x2),
                style = pageTypography.h5,
                color = prc_white100
            )
            val networkState by viewModel.networkState.collectAsState(initial = NetworkState.None)
            BootTimeItem(networkState == NetworkState.Connected)
        }
    )
}

@Composable
private fun RelatedProcess() {
// Process
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            Text(
                stringResource(R.string.book_check_x3),
                style = pageTypography.h5,
                color = prc_white100
            )
            BootTimeItem(MainApplication.getInstance() != null)
        }
    )
}

@Composable
private fun GetStorage() {
    val deviceStorage = DeviceStorage()
    val total = deviceStorage.getTotalCapacity().toInt()
    val inUse = deviceStorage.getCapacityInUse().toInt()
    val usePer = if(inUse != 0) total / inUse else total
// DeviceStorage
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(9.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Text(
                        stringResource(id = R.string.book_check_x4),
                        style = pageTypography.h5,
                        color = prc_white100
                    )
                    Text(
                        String.format(
                            stringResource(id = R.string.boot_check_storage1),
                            inUse,
                            usePer
                        ), style = pageTypography.h6, color = prc_gray700
                    )
                })
            Text(
                String.format(stringResource(id = R.string.boot_check_storage2), total.toInt()),
                style = pageTypography.h6,
                color = prc_gray700
            )
        }
    )
}

@Composable
private fun FinalBoot() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            Text(
                stringResource(R.string.book_check_x5),
                style = pageTypography.h5,
                color = prc_white100
            )
            BootTimeItem(true)
        }
    )
}

// 성공,실패 여부 & 시간 표시
@Composable
private fun BootTimeItem(isSuccess: Boolean) {
    val result =
        if (isSuccess) stringResource(id = R.string.success) else stringResource(id = R.string.fail)
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(result, style = pageTypography.h5, color = prc_birth)
            Text(getCurTimeInfo(DateFormat.HOUR24, System.currentTimeMillis()), style = pageTypography.h6, color = prc_gray700)
        })
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
@Composable
fun BootCheckPreview() {
    BootCheckContent()
}