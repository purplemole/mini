package com.clobot.mini.view.common

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clobot.mini.R
import com.clobot.mini.util.DeviceStorage
//import com.clobot.mini.util.DeviceStorage2
import com.clobot.mini.util.getCurTimeInfo
import com.clobot.mini.util.network.NetworkState
import com.clobot.mini.view.common.ui.theme.testColor
import com.clobot.mini.view.navigation.NavigationGraph

@SuppressLint("SimpleDateFormat")
@Composable
fun BootCheck(networkState: NetworkState) {
    val shouldShowNavigationGraph = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 70.dp)
    ) {
        Text(
            stringResource(id = R.string.boot_check_t1), modifier = Modifier
                .background(testColor)
                .clickable {
                    shouldShowNavigationGraph.value = true
                })
        // Network
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                stringResource(R.string.book_check_x2),
                modifier = Modifier.background(Color(0xFFE0C2F8))
            )
            val netStateStr = if (networkState == NetworkState.Connected)
                stringResource(id = R.string.success)
            else stringResource(id = R.string.fail)
            Text(text = netStateStr, modifier = Modifier.background(Color(0xFFE0C2F8)))
            Text(text = getCurTimeInfo(4))
        }
        // DeviceStorage
        Row() {
            Text(
                stringResource(id = R.string.book_check_x4)
            )
            val deviceStorage = DeviceStorage()
            val total = deviceStorage.getTotalCapacity()
            val inUse = deviceStorage.getCapacityInUse()
            Text("total is $total, using $inUse")
        }
//        val deviceStorage2 = DeviceStorage2(LocalContext.current).showVolumeStates()
//        Text(text = "1: ${deviceStorage2.first}, 2: ${deviceStorage2.second}, 3: ${deviceStorage2.third}")
    }
    if (shouldShowNavigationGraph.value)
        NavigationGraph()
}