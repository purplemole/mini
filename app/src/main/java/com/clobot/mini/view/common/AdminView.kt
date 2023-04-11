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
    //val context = LocalContext.current

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
            // 좌측 Column
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
            ) {
                item {
                    CustomBox(
                        titleText = stringResource(id = R.string.admin_x2),
                        contents = listOf(
                            DataPair(
                                subText = stringResource(id = R.string.admin_x3),
                                cosUnit = @Composable {
                                    CustomTextField(
                                        text = stringResource(id = R.string.admin_x4),
                                        enabled = true
                                    )
                                }
                            )
                        )
                    )
                    CustomBox(
                        titleText = stringResource(id = R.string.admin_x5),
                        contents = listOf(
                            DataPair(
                                subText = stringResource(id = R.string.admin_x6),
                                cosUnit = @Composable {
                                    CustomVolumeButton()
                                }
                            ),
                            DataPair(
                                subText = stringResource(id = R.string.admin_x7),
                                cosUnit = @Composable {
                                    CustomTextField(
                                        text = "충전 중",
                                        enabled = false
                                    )
                                }
                            ),
                            DataPair(
                                subText = stringResource(id = R.string.admin_x9),
                                cosUnit = @Composable {
                                    CustomButton()
                                }
                            )
                        )
                    )
                    CustomBox(
                        titleText = "상태 정보",
                        contents = listOf(
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
            Canvas(
                modifier = Modifier
                    .fillMaxHeight()
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
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
            ) {
                item {
                    CustomBox(
                        titleText = stringResource(id = R.string.admin_x14),
                        contents = listOf(
                            DataPair(
                                subText = stringResource(id = R.string.admin_x15),
                                cosUnit = @Composable {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        CustomTimePicker()
                                        Text("~")
                                        CustomTimePicker()
                                    }
                                },
                            ),
                            DataPair(
                                subText = stringResource(id = R.string.admin_x16),
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                        ),
                    )
                    CustomBox(
                        titleText = "로봇 운영 시간",
                        contents = listOf(
                            DataPair(
                                subText = stringResource(id = R.string.admin_x20),
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = stringResource(id = R.string.admin_x21),
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = stringResource(id = R.string.admin_x22),
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = stringResource(id = R.string.admin_x23),
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = stringResource(id = R.string.admin_x24),
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = stringResource(id = R.string.admin_x25),
                                cosUnit = @Composable {
                                    CustomTimePicker()
                                }
                            ),
                            DataPair(
                                subText = stringResource(id = R.string.admin_x26),
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