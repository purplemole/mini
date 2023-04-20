package com.clobot.mini.view.common

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.clobot.mini.R
import com.clobot.mini.data.admin.AdminData
import com.clobot.mini.data.admin.AdminEnum
import com.clobot.mini.data.robot.ChargeMode
import com.clobot.mini.util.LocalPromoteCycle
import com.clobot.mini.util.LocalRobotViewModel
import com.clobot.mini.util.state.IntFieldState
import com.clobot.mini.util.state.TextFieldState
import com.clobot.mini.view.common.ui.theme.*
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import java.util.*

// 소단원
@Composable
fun CustomBox(
    titleText: Int,
    contents: List<AdminData.DataPair>,
) {
    Column(
        modifier = Modifier
            .padding(top = 5.dp, start = 5.dp, end = 5.dp),
    ) {
        FieldName(titleText)
        contents.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE9E7E7))
                    .padding(horizontal = 10.dp, vertical = 3.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (it.subText != 0) {
                    Text(
                        text = stringResource(id = it.subText),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                it.cosUnit()
            }
        }
    }
}

// 텍스트 필드
@Composable
fun FieldName(stringInt: Int) {
    if (stringInt != 0) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            FaIcon(faIcon = FaIcons.Circle, size = 12.dp)
            Text(
                text = stringResource(id = stringInt), style = TextStyle(
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable // 이동 홍보 주기 버튼 composable
fun PromoteCycleBtn() {
    val tmpCycle = LocalPromoteCycle.current
    val selectOptionList = listOf(1, 3, 5, 10)
    val selectedOption = tmpCycle.getInt()
    val onSelectionChange = { choose: Int -> tmpCycle.setInt(choose) }
    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        selectOptionList.forEach {
            OutlinedButton(
                onClick = { onSelectionChange(it) },
                content = {
                    Text(
                        text = "${it}분",
                        style = TextStyle(textAlign = TextAlign.Center, color = Color.Black)
                    )
                },
                shape = AdminRoundedBtn.small,
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = if (it == selectedOption) AdminSelect else Color.White,
                    contentColor = AdminClicked
                ),
                modifier = Modifier
                    .padding(4.dp),
                border = adminBorder,
                elevation = ButtonDefaults.elevation(1.dp)
            )
        }
    }
}

@Composable // 로봇 관리 항목 버튼 composable
fun RobotManagementBtn() {
    val robotViewModel = LocalRobotViewModel.current
    val pairItem1 = Pair((R.string.admin_B1)) { robotViewModel.chargeController(ChargeMode.Start) }
    val pairItem2 = Pair((R.string.admin_B2)) {/*TODO*/ }
    val pairItem3 = Pair((R.string.admin_B3)) {/*TODO*/ }
    val pairItem4 = Pair((R.string.admin_B4)) {/*TODO*/ }

    val robotAction = listOf(pairItem1, pairItem2, pairItem3, pairItem4)

    LazyRow(
        horizontalArrangement = Arrangement.SpaceAround,
        content = {

            itemsIndexed(robotAction) { _, item ->
                OutlinedButton(
                    onClick = item.second,
                    content = {
                        Text(
                            text = stringResource(id = item.first),
                            style = AdminTypography.button,
                        )
                    },
                    shape = AdminRoundedBtn.medium,
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = AdminSelect,
                        contentColor = AdminClicked
                    ),
                    border = adminBorder,
                    modifier = Modifier.height(50.dp),
                    elevation = elevation(2.dp)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    )
}

@Composable // 강제 충전 시작, 로봇 운영 시작
fun RightPerSpinner(modifier: Modifier, spinName: Int, tmpPer: IntFieldState, perList: List<Int>) {
    val selectedItem = tmpPer.getInt()
    val onItemSelected = { select: Int -> tmpPer.setInt(select) }
    val expanded = rememberSaveable { mutableStateOf(false) }

    Row(modifier = modifier,
        content = {
            Text(
                text = stringResource(spinName),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
//, enabled = percentageList.isNotEmpty()
            OutlinedButton(
                onClick = { expanded.value = true },
                modifier = Modifier
                    .width(90.dp)
                    .height(35.dp),
                shape = AdminRoundedBtn.medium,
                border = adminBorder,
            ) {
                Text(
                    text = "${selectedItem}%",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.DarkGray
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                offset = DpOffset(x = (120).dp, y = (-2).dp)
            ) {
                perList.forEach {
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        onItemSelected(it)
                    }) {
                        Text(text = "${it}%")
                    }
                }
            }
        })
}

@Composable
fun FromToPicker(from: TextFieldState, to: TextFieldState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTimePicker(from)
        Text("~")
        CustomTimePicker(to)
    }
}

// 타임 피커
@Composable
fun CustomTimePicker(storeT: TextFieldState) {
    val calendar = Calendar.getInstance()
    val timeState = storeT.getText()//remember { mutableStateOf(storeT.getText()) }
    val timePickerDialog = CustomTimePickerDialog(
        LocalContext.current,
        { _, hourOfDay, minute ->
            storeT.setText(String.format("%02d : %02d", hourOfDay, minute))
        },
        calendar[Calendar.HOUR_OF_DAY],
        calendar[Calendar.MINUTE],
        false
    )
    // 뒷배경 제거
    timePickerDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
    timePickerDialog.setButton(
        DialogInterface.BUTTON_POSITIVE,
        stringResource(id = R.string.confirm),
        timePickerDialog
    )
    timePickerDialog.setButton(
        DialogInterface.BUTTON_NEGATIVE,
        stringResource(id = R.string.cancel),
        timePickerDialog
    )
    timePickerDialog.setTitle("test")

    OutlinedButton(
        onClick = { timePickerDialog.show() },
        modifier = Modifier.defaultMinSize(
            minWidth = 30.dp,
            minHeight = 30.dp
        ),
        shape = AdminRoundedBtn.medium,
        border = adminBorder,
    ) {
        Text(text = timeState, color = Color.Black)
        FaIcon(
            FaIcons.CaretDown,
            tint = Color.DarkGray,
        )
    }
}

// 볼륨 버튼
@Composable
fun CustomVolumeButton(
    audioManager: AudioManager,
    currentVolume: MutableState<Int>,
    state: AdminEnum.AudioState
) {
    OutlinedButton(
        onClick = {
            audioManager.adjustStreamVolume(
                AudioManager.STREAM_MUSIC,
                when (state) {
                    AdminEnum.AudioState.UP -> AudioManager.ADJUST_RAISE
                    AdminEnum.AudioState.DOWN -> AudioManager.ADJUST_LOWER
                    AdminEnum.AudioState.MUTE -> AudioManager.ADJUST_TOGGLE_MUTE
                },
                0
            )
            val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
            currentVolume.value = volumeLevel
        },
        border = BorderStroke(1.dp, Color.Black),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.defaultMinSize(25.dp, 30.dp),
        elevation = elevation(1.dp)
    ) {
        FaIcon(
            faIcon = when (state) {
                AdminEnum.AudioState.UP -> FaIcons.CaretUp
                AdminEnum.AudioState.DOWN -> FaIcons.CaretDown
                AdminEnum.AudioState.MUTE -> FaIcons.VolumeMute
            },
            tint = if (currentVolume.value == 0) {
                if (state == AdminEnum.AudioState.DOWN) Color.LightGray
                else Color.Black
            } else if (currentVolume.value == audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) {
                if (state == AdminEnum.AudioState.UP) Color.Black
                else Color.LightGray
            } else {
                if (state == AdminEnum.AudioState.MUTE) Color.LightGray
                else Color.Black
            }
        )
    }
}

// 볼륨 조절
@Composable
fun VolumeStage() {
    val audioManager = LocalContext.current.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    // 시스템 사양상 15가 최대(애뮬레이터 기준) - 백분위 할 지 협의 필요.
//    val maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
//    val volumePercent = (volumeLevel.toFloat() / maxVolumeLevel * 100).toInt()
    val currentVolume = remember { mutableStateOf(volumeLevel) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        CustomVolumeButton(audioManager, currentVolume, AdminEnum.AudioState.UP)
        Text(text = currentVolume.value.toString())
        CustomVolumeButton(audioManager, currentVolume, AdminEnum.AudioState.DOWN)
        CustomVolumeButton(audioManager, currentVolume, AdminEnum.AudioState.MUTE)
    }
}