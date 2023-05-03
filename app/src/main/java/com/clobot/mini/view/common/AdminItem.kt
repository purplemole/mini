package com.clobot.mini.view.common

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clobot.mini.R
import com.clobot.mini.data.admin.AdminData
import com.clobot.mini.data.admin.AdminEnum
import com.clobot.mini.data.robot.ChargeMode
import com.clobot.mini.util.LocalPromoteCycle
import com.clobot.mini.util.LocalRobotViewModel
import com.clobot.mini.util.state.IntFieldState
import com.clobot.mini.util.state.TextFieldState
import com.clobot.mini.view.common.ui.MyIconPack
import com.clobot.mini.view.common.ui.myiconpack.*
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
    Column(modifier = Modifier.padding(start = 18.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        content = {
            if (titleText != 0)
                Text(
                    text = stringResource(id = titleText),
                    style = AdminTypography.subtitle2
                )
            Column(
                modifier = Modifier.background(color = prc_gray900, shape = AdminRoundedBtn.medium),
                content = {
                    contents.forEach {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 7.dp, vertical = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            if (it.subText != 0) {
                                Text(
                                    text = stringResource(id = it.subText),
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    style = AdminTypography.h1
                                )
                            }
                            it.cosUnit()
                        }
                    }
                })
        }
    )
}

@Composable // 이동 홍보 주기 버튼 composable
fun PromoteCycleBtn() {
    val tmpCycle = LocalPromoteCycle.current
    val selectOptionList = listOf(1, 3, 5, 10)
    val selectedOption = tmpCycle.getInt()
    val onSelectionChange = { choose: Int -> tmpCycle.setInt(choose) }
    Row(
        modifier = Modifier
            .background(prc_gray800, shape = AdminRoundedBtn.large)
            .padding(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        content = {
            selectOptionList.forEach {
                Text(
                    text = "${it}분",
                    style = AdminTypography.h2,
                    modifier = Modifier
                        .noRippleClickable { onSelectionChange(it) }
                        .background(
                            color = if (it == selectedOption) prc_gray600 else Color.Transparent,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .width(25.dp),
                )
            }
        })
}

@Composable // 로봇 관리 항목 버튼 composable
fun RobotManagementBtn() {
    val robotViewModel = LocalRobotViewModel.current
    val mgmtItem1 = Triple( // 충전기로 이동
        R.string.admin_B1,
        { robotViewModel.chargeController(ChargeMode.Start) },
        MyIconPack.AdminBattery
    )
    val mgmtItem2 = Triple( // 로봇 운영 시작
        R.string.admin_B2,
        {/*TODO*/ },
        Icons.Outlined.PlayArrow
    )
    val mgmtItem3 = Triple( // 프로그램 재시작
        R.string.admin_B3,
        {/*TODO*/ },
        MyIconPack.AdminRestart
    )
    val mgmtItem4 = Triple( // 로봇 종료
        R.string.admin_B4,
        {/*TODO*/ },
        MyIconPack.AdminEnd
    )

    val robotAction = listOf(mgmtItem1, mgmtItem2, mgmtItem3, mgmtItem4)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        content = {
            itemsIndexed(robotAction) { _, item ->
                Row(
                    content = {
                        Icon(
                            imageVector = item.third,
                            contentDescription = null,
                            tint = prc_white800,
                            modifier = Modifier.size(9.dp)
                        )
                        Spacer(Modifier.size(4.dp))
                        Text(
                            text = stringResource(id = item.first),
                            style = AdminTypography.button,
                            fontSize = 7.sp
                        )
                    },
                    modifier = Modifier
                        .height(24.dp)
                        .width(54.dp)
                        .background(
                            shape = AdminRoundedBtn.medium,
                            color = if (item == mgmtItem4) prc_danger else prc_gray600
                        )
                        .clickable { item.second },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                )
            }
        },
        modifier = Modifier.width(238.dp)
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
fun FromToPicker(fromTo: TextFieldState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTimePicker(fromTo, true)
        Text("~")
        CustomTimePicker(fromTo, false)
    }
}

// 타임 피커
@Composable
fun CustomTimePicker(storeT: TextFieldState, isFrom: Boolean) {
    val originT = storeT.getText().split("~")
    val calendar = Calendar.getInstance()
    val timeState =
        if (isFrom) originT[0] else originT[1]//remember { mutableStateOf(storeT.getText()) }
    val timePickerDialog = CustomTimePickerDialog(
        LocalContext.current,
        { _, hourOfDay, minute ->
            if (isFrom)
                storeT.setText("${String.format("%02d : %02d", hourOfDay, minute)}~${originT[1]}")
            else
                storeT.setText("${originT[0]}~${String.format("%02d : %02d", hourOfDay, minute)}")
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
    Image(
        imageVector = when (state) {
            AdminEnum.AudioState.UP -> MyIconPack.AdminPlus
            AdminEnum.AudioState.DOWN -> MyIconPack.AdminMinus
            AdminEnum.AudioState.MUTE -> MyIconPack.AdminMute
        },
        contentDescription = null,
        modifier = Modifier
            .size(18.dp)
            .noRippleClickable {
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
            }
    )
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
        Text(
            text = currentVolume.value.toString(),
            modifier = Modifier
                .width(25.dp)
                .align(Alignment.CenterVertically),
            style = AdminTypography.h2,
        )
        CustomVolumeButton(audioManager, currentVolume, AdminEnum.AudioState.DOWN)
        Spacer(modifier = Modifier.size(7.dp))
        CustomVolumeButton(audioManager, currentVolume, AdminEnum.AudioState.MUTE)
    }
}