package com.clobot.mini.view.common

import android.content.Context
import android.media.AudioManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clobot.mini.data.admin.AdminData
import com.clobot.mini.data.admin.AdminEnum
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons

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