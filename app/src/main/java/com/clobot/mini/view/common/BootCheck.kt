package com.clobot.mini.view.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.clobot.mini.view.common.ui.theme.testColor
import com.clobot.mini.view.navigation.NavigationGraph
import com.clobot.mini.view.navigation.RouteAction

@Composable
fun BootCheck() {
    val shouldShowNavigationGraph = remember { mutableStateOf(false) }
    Column() {
        Text("BootCheck Page - 04.03", modifier = Modifier.background(testColor).clickable {
            shouldShowNavigationGraph.value = true
        })
    }
    if (shouldShowNavigationGraph.value)
        NavigationGraph()
}
