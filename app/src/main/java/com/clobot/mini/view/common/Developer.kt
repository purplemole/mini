package com.clobot.mini.view.common

import android.content.Intent
import android.provider.Settings
import android.transition.ArcMotion
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
import com.clobot.mini.data.robot.ArcMode
import com.clobot.mini.data.robot.MoveDirection
import com.clobot.mini.data.robot.NavMode
import com.clobot.mini.util.LocalRobotViewModel

@Composable
fun Developer() {
    val robotViewModel = LocalRobotViewModel.current

    Template0(needTopBar = true) {
        LazyColumn(
            content = {
                item { AndroidSetting() }
                item {
                    LazyRow {
                        item { CustomButton("Forward", robotViewModel.basicMotion(MoveDirection.Forward)) }
                        item { CustomButton("Backward", robotViewModel.basicMotion(MoveDirection.Backward)) }
                        item { CustomButton("TurnLeft", robotViewModel.basicMotion(MoveDirection.TurnLeft)) }
                        item { CustomButton("TurnRight", robotViewModel.basicMotion(MoveDirection.TurnRight)) }
                        item { CustomButton("Stop", robotViewModel.basicMotion(MoveDirection.Stop)) }
                    }
                }
//                item {
//                    CustomButton("Arc", robotViewModel.arcMotion(ArcMode.None))
//                    CustomButton("Obstacle", robotViewModel.arcMotion(ArcMode.Obstacle))
//                }
//                item {
//                    CustomButton("Reset", robotViewModel.headMotion("Reset"))
//                    CustomButton("Move", robotViewModel.headMotion("Move"))
//                }
//                item {
//                    CustomButton("Start", robotViewModel.navController(NavMode.Start))
//                    CustomButton("Stop", robotViewModel.navController(NavMode.Stop))
//                    CustomButton("Start", robotViewModel.navController(NavMode.ToPos))
//                    CustomButton("Start", robotViewModel.navController(NavMode.StopToPos))
//                }
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
        },
        content = { Text("기본 설정 화면") },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun CustomButton(
    text: String,
    event: Unit
) {
    val robotViewModel = LocalRobotViewModel.current
    val colors = listOf(Color(0xFFC7C7C7), Color(0xFF030303))
    val paddingValues = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    val widthFraction = 0.68f

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(fraction = widthFraction)
            .border(
                width = 4.dp,
                brush = Brush.horizontalGradient(colors = colors),
                shape = RectangleShape
            )
            .clickable(
//                interactionSource = interactionSource,
//                indication = null
            ) {
                event
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Preview
@Composable
fun DeveloperPreview() {
    Developer()
}
