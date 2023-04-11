package com.clobot.mini.view.common

import android.content.Context.AUDIO_SERVICE
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clobot.mini.view.navigation.RouteAction
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.clobot.mini.view.navigation.LocalRouteAction
import java.util.*
import com.clobot.mini.R
import com.clobot.mini.data.admin.*

@Composable
fun AdminView() {
    val routeAction = LocalRouteAction.current
    Template0(
        needTopBar = false,
        templateBody = { AdminContent(routeAction = routeAction) }
    )
}

/**
 * TODO 볼륨 설정 변경 / 로봇 상태 값 / 저장 버튼 onClick 동작 / DataStore 작업 / 뷰 반복 수정 / 배터리 사용
 *
 * @param routeAction : 병원 별로 사용 중인 navRouteAction
 */
@Composable
fun AdminContent(routeAction: RouteAction) {
//    // DataStore - preferences 사용을 위한 context
//    val context = LocalContext.current
//    // scope 설정
//    val scope = rememberCoroutineScope()
//    // Datastore
//    val dataStore = StoreAdminSetting(context)

    Column() {
        // 상단 영역
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
//            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.admin_x1),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            OutlineTextBtn({ routeAction.goBack() }, stringResource(id = R.string.admin_B5))
            OutlineTextBtn({ routeAction.goBack() }, stringResource(id = R.string.admin_B6))
        }
        // 주 영역
        Row() {
            val columnModifier = Modifier
                .weight(1f)
                .padding(5.dp)
            // 좌측 Column
            LazyColumn(
                modifier = columnModifier
            ) {
                items(AdminColumnItem.leftItemList) {
                    CustomBox(it.titleText, it.content)
                }
            }

            // 점선
            Canvas(
                modifier = Modifier.fillMaxHeight()
            ) {
                drawLine(
                    color = Color.Red,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }

            // 우측 Column
            LazyColumn(
                modifier = columnModifier
            ) {
                items(AdminColumnItem.rightItemList) {
                    CustomBox(it.titleText, it.content)
                }
            }
        }
    }
}

// data\admin 으로 이동
// 소단원
//data class DataPair(val subText: String, val cosUnit: @Composable () -> Unit)

@Composable
fun CustomBox(
    titleText: Int,
    contents: List<DataPair>,
) {
    Column(
        modifier = Modifier
            .padding(5.dp),
    ) {
        Text(
            text = "● ${stringResource(titleText)}",
            fontWeight = Bold
        )
        contents.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFC8C8C8)),
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

// 볼륨 조절
@Composable
fun CustomVolumeButton() {
    val audioManager = LocalContext.current.getSystemService(AUDIO_SERVICE) as AudioManager
    // 시스템 사양상 15가 최대(애뮬레이터 기준)
    val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
//    val maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
//    val volumePercent = (volumeLevel.toFloat() / maxVolumeLevel * 100).toInt()
    val currentVolume = remember { mutableStateOf(volumeLevel) }

    Row {
        OutlinedButton(
            onClick = {
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE,
                    0
                )
                val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
                currentVolume.value = volumeLevel
            },
            border = BorderStroke(1.dp, Color.White),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.defaultMinSize(
                minWidth = 25.dp,
                minHeight = 30.dp
            )
        ) {
            Text(text = "▲")
        }
        Text(
            text = currentVolume.value.toString(),
            fontWeight = Bold,
            fontSize = 20.sp
        )
        OutlinedButton(
            onClick = {
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER,
                    0
                )
                val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
                currentVolume.value = volumeLevel
            },
            border = BorderStroke(1.dp, Color.White),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.defaultMinSize(
                minWidth = 25.dp,
                minHeight = 30.dp
            )
        ) {
            Text(text = "▼")
        }
    }
}

// 라디오 토글 버튼
@Composable
fun CustomButton() {
    val options = listOf(
        "1분",
        "3분",
        "5분",
        "10분",
    )
    val selectedOption = remember { mutableStateOf("3분") }
    val onSelectionChange = { text: String ->
        selectedOption.value = text
    }
    Row(
        modifier = Modifier
            .padding(
                all = 8.dp,
            ),
    ) {
        options.forEach {
            Text(
                text = it,
                modifier = Modifier
                    .clickable {
                        onSelectionChange(it)
                    }
                    .background(
                        if (it == selectedOption.value) {
                            Color(0xFFF7BA7A)
                        } else {
                            Color.White
                        }
                    )
                    .padding(4.dp),
            )
        }
    }
}

// 타임 피커
@Composable
fun CustomTimePicker(
    betweenText: String = "~"
) {
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

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
@Composable
fun AdminPreview() {
    MiniTheme {
        Column {
            CustomTextField(text = R.string.admin_x4, enabled = true)
        }
    }
}

//class AdminProvider : PreviewParameterProvider<RouteAction> {
//    override val values: Sequence<RouteAction>
//        get() = TODO("Not yet implemented")
//}