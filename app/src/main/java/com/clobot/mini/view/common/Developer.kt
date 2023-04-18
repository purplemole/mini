package com.clobot.mini.view.common

import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Developer() {
    Template0(needTopBar = true) {
        LazyColumn(
            content = {
                item { AndroidSetting() }

            },
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
    }
}

@Composable
fun AndroidSetting() {
    val activityResultLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { }
    Button(onClick = {
        val intent = Intent(Settings.ACTION_SETTINGS)
//                    context.startActivity(Intent(context, Settings.ACTION_SETTINGS::class.java))
        activityResultLauncher.launch(intent)
    }, content = { Text("기본 설정 화면") })
}