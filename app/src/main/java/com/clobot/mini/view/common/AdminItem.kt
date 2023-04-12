package com.clobot.mini.view.common

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clobot.mini.R
import com.clobot.mini.data.admin.AdminData
import com.clobot.mini.data.admin.AdminEnum
import com.clobot.mini.view.common.ui.theme.MiniTheme
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
            .padding(5.dp),
    ) {
        FieldName(titleText)
        contents.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE9E7E7))
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(id = it.subText),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                it.cosUnit()
            }
        }
    }
}

// 텍스트 필드
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: Int,
    enabled: Boolean
) {
    TextField(
        modifier = modifier,
        value = stringResource(text),
        onValueChange = {},
        enabled = enabled,
        readOnly = true
    )
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
        border = BorderStroke(1.dp, Color.White),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.defaultMinSize(25.dp, 30.dp)
    ) {
        FaIcon(
            faIcon = when (state) {
                AdminEnum.AudioState.UP -> FaIcons.CaretUp
                AdminEnum.AudioState.DOWN -> FaIcons.CaretDown
                AdminEnum.AudioState.MUTE -> FaIcons.VolumeMute
            },
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

// 라디오 버튼
@Composable
fun CustomRadioButton() {
    val selectedOption = remember { mutableStateOf(AdminEnum.TravelCycle.THREE) }
    val onSelectionChange = { choose: AdminEnum.TravelCycle ->
        selectedOption.value = choose
    }
    Row(
        modifier = Modifier
            .padding(
                all = 8.dp,
            ),
    ) {
        AdminEnum.TravelCycle.values().forEach {
            Text(
                text = "${it.cycle}분",
                modifier = Modifier
                    .clickable {
                        onSelectionChange(it)
                    }
                    .background(
                        if (it == selectedOption.value)
                            Color(0xFFF7BA7A)
                        else
                            Color.White
                    )
                    .padding(4.dp),
            )
        }
    }
}

// 타임 피커
@Composable
fun CustomTimePicker() {
    val calendar = Calendar.getInstance()
    var timeState by remember { mutableStateOf("00:00") }
    val timePickerDialog = CustomTimePickerDialog(
        LocalContext.current,
        { _, hourOfDay, minute ->
            timeState = String.format("%02d : %02d", hourOfDay, minute)
        },
        calendar[Calendar.HOUR_OF_DAY],
        calendar[Calendar.MINUTE],
        false
    )
    // 뒷배경 제거
    timePickerDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

    OutlinedButton(
        onClick = { timePickerDialog.show() },
        border = BorderStroke(1.dp, Color.White),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.defaultMinSize(
            minWidth = 25.dp,
            minHeight = 30.dp
        )
    ) {
        Text(text = timeState)
    }
}

//////////////////////23.04.12 tk
@Composable
fun FieldName(stringInt: Int) {
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

@Composable
fun FieldContent(contents: List<AdminData.DataPair>) {

    if (contents.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color(0xFFE9E7E7))
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            contents.forEach {
                Row() {
                    Text(
                        text = stringResource(id = it.subText),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    it.cosUnit
                }
            }
        }
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
@Composable
fun AdminItemPreview() {
    MiniTheme {
        Column {
            CustomTextField(text = R.string.admin_x4, enabled = true)
        }
    }
}