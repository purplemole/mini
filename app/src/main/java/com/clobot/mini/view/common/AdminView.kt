package com.clobot.mini.view.common

import android.app.TimePickerDialog
import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun AdminView() {
    val routeAction = LocalRouteAction.current
    Template0(
        needTopBar = false,
        templateBody = { AdminContent(routeAction = routeAction) }
    )
}

@Composable
fun AdminContent(routeAction: RouteAction) {
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
                text = "로봇 환경 설정(관리자 화면)",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            OutlineTextBtn({ routeAction.goBack() }, "취소")
            OutlineTextBtn({ routeAction.goBack() }, "저장 후 닫기")
        }
        // 주 영역
        Row() {
            // 좌측 Column
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
            ) {
                item {
                    CustomBox(
                        titleText = "로봇 이름",
                        contents = listOf (
                            DataPair(
                                subText = "설치장소 및 순번",
                                cosUnit = @Composable {
                                    CustomTextField(
                                        text = "야탑 치과_001",
                                        enabled = true
                                    )
                                }
                            )
                        )
                    )
                    CustomBox(
                        titleText = "기본 설정",
                        contents = listOf (
                            DataPair(
                                subText = "로봇 볼륨",
                                cosUnit = @Composable {
                                    CustomVolumeButton(LocalContext.current)
                                }
                            ),
                            DataPair(
                                subText = "로봇 상태",
                                cosUnit = @Composable {
                                    CustomTextField(
                                        text = "충전 중",
                                        enabled = false
                                    )
                                }
                            ),
                            DataPair(
                                subText = "로봇 이동 홍보 주기",
                                cosUnit = @Composable {
                                    CustomButton()
                                }
                            )
                        )
                    )
                    CustomBox(
                        titleText = "상태 정보",
                        contents = listOf (
                            DataPair(
                                subText = "배터리",
                                cosUnit = @Composable {
                                    CustomTextField(
                                        text = "000 %, 00시간 00분 사용 가능",
                                        enabled = false
                                    )
                                }
                            )
                        )
                    )
                }
            }
            // 점선
            Canvas(modifier = Modifier
                .fillMaxHeight()) {
                drawLine(
                    color = Color.Red,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }
            // 우측 Column
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
            ) {
                item {
                    CustomBox(
                        titleText = "이동 제한 및 강제 충전 시간",
                        contents = listOf (
                            DataPair(
                                subText = "이동 제한 시간",
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                },
                            ),
                            DataPair(
                                subText = "강제 충전 시작",
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                        ),
                    )
                    CustomBox(
                        titleText = "로봇 운영 시간",
                        contents = listOf (
                            DataPair(
                                subText = "월요일",
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = "화요일",
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = "수요일",
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = "목요일",
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = "금요일",
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = "토요일",
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = "일요일",
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                        ),
                    )
                }
            }
        }
    }
}

// 소단원
data class DataPair(val subText: String, val cosUnit: @Composable () -> Unit)
@Composable
fun CustomBox(
    titleText: String,
    contents: List<DataPair>,
) {
    Column(
        modifier = Modifier
            .padding(5.dp),
    ) {
        Text(
            text = "● $titleText",
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
                    text = it.subText,
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
    text: String,
    enabled: Boolean
) {
    TextField(
        value = text,
        onValueChange = {},
        enabled = enabled,
        readOnly = true
    )
}

// 볼륨 조절
@Composable
fun CustomVolumeButton(context: Context) {
    val audioManager = context.getSystemService(AUDIO_SERVICE) as AudioManager
    // 시스템 사양상 15가 최대(애뮬레이터 기준)
    val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
//    val maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
//    val volumePercent = (volumeLevel.toFloat() / maxVolumeLevel * 100).toInt()
    val currentVolume = remember { mutableStateOf(volumeLevel) }

    Row {
        OutlinedButton(
            onClick = {
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0)
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
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0)
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
    var selectedOption by remember { mutableStateOf("3분") }
    val onSelectionChange = { text: String ->
        selectedOption = text
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
                        if (it == selectedOption) {
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
    var timeState by remember { mutableStateOf("") }
    val context = LocalContext.current
    val timePickerDialog = TimePickerDialog(
        context,
        {
                view, hourOfDay, minute ->
            timeState = "$hourOfDay : $minute"
        },
        calendar[Calendar.HOUR_OF_DAY],
        calendar[Calendar.MINUTE],
        false
    )

    Row() {
        Button(onClick = {
            timePickerDialog.show()
        }) {
            Text(text = "Pick time")
        }
        Text(text = timeState)
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
@Composable
fun AdminPreview(
    @PreviewParameter(AdminProvider::class) routeAction: RouteAction
) {
    MiniTheme {
        Column {
            CustomTextField(text = "야탑 치과_001", enabled = true)
        }
    }
}

class AdminProvider : PreviewParameterProvider<RouteAction> {
    override val values: Sequence<RouteAction>
        get() = TODO("Not yet implemented")
}