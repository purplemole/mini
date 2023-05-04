package com.clobot.mini.view.common

import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clobot.mini.data.robot.ChargeMode
import com.clobot.mini.data.robot.MoveDirection
import com.clobot.mini.data.robot.MoveReason
import com.clobot.mini.data.robot.NavMode
import com.clobot.mini.util.LocalRobotViewModel

@Composable
fun Developer() {
    val robotViewModel = LocalRobotViewModel.current
    val placeList = robotViewModel.placeList.collectAsState(initial = ArrayList())
    val textLog = robotViewModel.textLog.collectAsState(initial = "")

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
                            CustomButton("StartNav") { robotViewModel.navController(NavMode.Start, "안내 대기 장소") }
                            CustomButton("StopNav") { robotViewModel.navController(NavMode.Stop) }
                            CustomButton("RotateToPos") { robotViewModel.navController(NavMode.ToPos) }
                            CustomButton("StopRotate") { robotViewModel.navController(NavMode.StopToPos) }
                        }
                    }
                    LazyRow {
                        item {
                            Text("Location")
                            CustomButton("List") { robotViewModel.locController("List") }
                            CustomButton("Set") { robotViewModel.locController("Set") }
                            CustomButton("Get") { robotViewModel.locController("Get") }
                            CustomButton("Remove") { robotViewModel.locController("Remove") }
                            CustomButton("Edit") { robotViewModel.locController("Edit") }
                            CustomButton("IsPos") { robotViewModel.locController("IsPos") }
                            CustomButton("Dist") { robotViewModel.locController("Dist") }
                        }
                    }
                    LazyRow {
                        item {
                            Text("Navi")
                            robotViewModel.refreshPlaceList()
                            placeList.value.forEach {
                                CustomButton(it) { robotViewModel.moveFromReason(MoveReason.Guide, it) }
                            }
                        }
                    }
                    LazyRow {
                        item {
                            Text("Charge")
                            CustomButton("GoCharge") { robotViewModel.chargeController(ChargeMode.GoCharge) }
//                            CustomButton("Start") { robotViewModel.chargeController(ChargeMode.Start) }
//                            CustomButton("Stop") { robotViewModel.chargeController(ChargeMode.Stop) }
                            CustomButton("Auto") { robotViewModel.moveFromReason(MoveReason.Docking) }
                            CustomButton("StopAuto") { robotViewModel.chargeController(ChargeMode.StopAuto) }
//                            CustomButton("Leave") { robotViewModel.chargeController(ChargeMode.Leave) }
                            CustomButton("StopLeave") { robotViewModel.chargeController(ChargeMode.StopLeave) }
                            CustomButton("Exits") { robotViewModel.chargeController(ChargeMode.Exits) }
                            CustomButton("Status") { robotViewModel.chargeController(ChargeMode.Status) }
                            CustomButton("Level") { robotViewModel.chargeController(ChargeMode.Level) }
//                            CustomButton("Door") { robotViewModel.chargeController(ChargeMode.Door) }
//                            CustomButton("RemainBattTime") { robotViewModel.chargeController(ChargeMode.RemainBattTime) }
//                            CustomButton("RemainChargeTime") { robotViewModel.chargeController(ChargeMode.RemainChargeTime) }
                        }
                    }
                    LazyRow {
                        item {
                            Text("System")
                            CustomButton("Lock") { robotViewModel.systemController("Lock") }
                            CustomButton("DisEmerg") { robotViewModel.systemController("DisEmerg") }
                            CustomButton("EnEmerg") { robotViewModel.systemController("EnEmerg") }
                            CustomButton("DisBatt") { robotViewModel.systemController("DisBatt") }
                            CustomButton("EnBatt") { robotViewModel.systemController("EnBatt") }
//                            CustomButton("ResetSystem") { robotViewModel.systemController("ResetSystem") }
//                            CustomButton("Reboot") { robotViewModel.systemController("Reboot") }
//                            CustomButton("Standby") { robotViewModel.systemController("Standby") }
//                            CustomButton("Wake") { robotViewModel.systemController("Wake") }
//                            CustomButton("FullCheck") { robotViewModel.systemController("FullCheck") }
                            CustomButton("Emerg") { robotViewModel.systemController("Emerg") }
                            CustomButton("Status") { robotViewModel.systemController("Status") }
                            CustomButton("Update") { robotViewModel.systemController("Update") }
                        }
                    }
                    Row {
                        LogView(textLog.value)
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
private fun LogView(log: String) {
    LazyRow {
        item {
            Text("Log: $log")
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    val colors = listOf(Color(0xFF00195F), Color(0xFF253A6F))
    val paddingValues = PaddingValues(horizontal = 5.dp, vertical = 5.dp)
    val widthFraction = 0.68f

    Box(
        modifier = Modifier
            .fillMaxWidth(fraction = widthFraction)
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(colors = colors),
                shape = RectangleShape
            )
            .clickable {
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
