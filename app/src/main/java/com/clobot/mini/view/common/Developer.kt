package com.clobot.mini.view.common

import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.clobot.mini.data.robot.ChargeMode
import com.clobot.mini.util.LocalRobotViewModel

@Composable
fun Developer() {
    Template0(needTopBar = true) {
        LazyColumn(
            content = {
                item { AndroidSetting() }
                item { ChargeTest() }
            },
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
    }
}

@Composable
private fun AndroidSetting() {
    val activityResultLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { }
    Button(onClick = {
        val intent = Intent(Settings.ACTION_SETTINGS)
//                    context.startActivity(Intent(context, Settings.ACTION_SETTINGS::class.java))
        activityResultLauncher.launch(intent)
    }, content = { Text("기본 설정 화면") })
}

@Composable
private fun ChargeTest(){
    val robotViewModel = LocalRobotViewModel.current
    Row(horizontalArrangement = Arrangement.SpaceEvenly){
        Button(onClick = { robotViewModel.chargeController(ChargeMode.Start) }, content = {Text("GO CHARGE")})
        Button(onClick = { robotViewModel.chargeController(ChargeMode.StopLeave) }, content = {Text("STOP GO\nCHARGE")})
        Button(onClick = { robotViewModel.chargeController(ChargeMode.Stop) }, content = {Text("STOP CHARGE")})
        Button(onClick = { robotViewModel.chargeController(ChargeMode.StopLeave) }, content = {Text("LEAVING\nCHARGE DOCK")})
    }
}