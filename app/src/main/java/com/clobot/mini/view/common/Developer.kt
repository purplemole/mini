package com.clobot.mini.view.common

import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clobot.mini.data.robot.MoveDirection
import com.clobot.mini.data.robot.NavMode
import com.clobot.mini.util.LocalRobotViewModel

@Composable
fun Developer() {
    val robotViewModel = LocalRobotViewModel.current
    val placeList = robotViewModel.getPlaceList()

    Template0(needTopBar = true) {
        LazyColumn(
            content = {
                item { AndroidSetting() }
                item {
                    LazyRow {
                        item {
                            Text("Motion")
                            CustomButton("Forward") { robotViewModel.basicMotion(MoveDirection.Forward) }
                            CustomButton("Backward") { robotViewModel.basicMotion(MoveDirection.Backward) }
                            CustomButton("TurnLeft") { robotViewModel.basicMotion(MoveDirection.TurnLeft) }
                            CustomButton("TurnRight") { robotViewModel.basicMotion(MoveDirection.TurnRight) }
                            CustomButton("Stop") { robotViewModel.basicMotion(MoveDirection.Stop) }
                            CustomButton("HeadReset") { robotViewModel.headMotion("Reset") }
                            CustomButton("HeadMove") { robotViewModel.headMotion("Move") }
                        }
                    }
                    LazyRow {
                        item {
                            Text("Navigation")
                            CustomButton("StartNav") { robotViewModel.navController(NavMode.Start) }
                            CustomButton("StopNav") { robotViewModel.navController(NavMode.Stop) }
                            CustomButton("RotateToPos") { robotViewModel.navController(NavMode.ToPos) }
                            CustomButton("StopRotate") { robotViewModel.navController(NavMode.StopToPos) }
                        }
                    }
                    LazyRow {
                        item {
                            Text("Charge")
                            CustomButton("List") { robotViewModel.locController("List") }
                            CustomButton("Set") { robotViewModel.locController("Set") }
                            CustomButton("Get") { robotViewModel.locController("Get") }
                            CustomButton("Remove") { robotViewModel.locController("Remove") }
                            CustomButton("Edit") { robotViewModel.locController("Edit") }
                            CustomButton("IsPos") { robotViewModel.locController("IsPos") }
                        }
                    }
                    LazyRow {
                        item {
                            Text("Navi")
                            placeList.forEach {
                                CustomButton(it) { robotViewModel.navController(NavMode.Start, it) }
                            }
                        }
                    }
                    LazyRow {
                        item {
                            Text("Status")
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        )
    }
}

@Composable
private fun AndroidSetting() {
    val activityResultLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { }
    Button(onClick = {
            val intent = Intent(Settings.ACTION_SETTINGS)
//            context.startActivity(Intent(context, Settings.ACTION_SETTINGS::class.java))
            activityResultLauncher.launch(intent)
        }, content = { Text("기본 설정 화면") },
    )
}

@Composable
private fun logView() {
    LazyRow {
        item {
            Text("Status")
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    val robotViewModel = LocalRobotViewModel.current
    val colors = listOf(Color(0xFF00195F), Color(0xFF253A6F))
    val paddingValues = PaddingValues(horizontal = 5.dp, vertical = 5.dp)
    val widthFraction = 0.68f

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(fraction = widthFraction)
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(colors = colors),
                shape = RectangleShape
            )
            .clickable(
//                interactionSource = interactionSource,
//                indication = null
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 8.sp,
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Preview
@Composable
fun DeveloperPreview() {
    Developer()
}
