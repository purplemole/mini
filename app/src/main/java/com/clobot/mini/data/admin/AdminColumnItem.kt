package com.clobot.mini.data.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.clobot.mini.R
import com.clobot.mini.view.common.CustomButton
import com.clobot.mini.view.common.CustomTextField
import com.clobot.mini.view.common.CustomTimePicker
import com.clobot.mini.view.common.CustomVolumeButton

object AdminColumnItem {

    // 로봇 이름
    private val leftItem1 = AdminCustomBow(
        R.string.admin_x2,
        listOf(
            DataPair(
                subText = R.string.admin_x3,
                cosUnit = @Composable {
                    CustomTextField(
                        text = R.string.admin_x4,
                        enabled = true
                    )
                }
            )
        )
    )

    // 기본 설정
    private val leftItem2 = AdminCustomBow(
        R.string.admin_x5,
        listOf(
            DataPair(
                subText = R.string.admin_x6,
                cosUnit = @Composable {
                    CustomVolumeButton()
                }
            ),
            DataPair(
                subText = R.string.admin_x7,
                cosUnit = @Composable {
                    CustomTextField(
                        text = R.string.admin_x8,
                        enabled = false
                    )
                }
            ),
            DataPair(
                subText = R.string.admin_x9,
                cosUnit = @Composable {
                    CustomButton()
                }
            )
        )
    )

    // 배터리 상태 정보
    private val leftItem3 = AdminCustomBow(
        R.string.admin_x10,
        listOf(
            DataPair(
                subText = R.string.admin_x11,
                cosUnit = @Composable {
                    TextField(
                        value = String.format(stringResource(id = R.string.admin_x12), 7, 3, 3),
                        onValueChange = {},
                        enabled = false,
                        readOnly = true
                    )
                }
            )
        )
    )

    // 이동 제한 및 강제 충전 시간
    private val rightItem1 = AdminCustomBow(
        R.string.admin_x14,
        listOf(
            DataPair(
                subText = R.string.admin_x15,
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
                subText = R.string.admin_x16,
                cosUnit = @Composable {
                    CustomTimePicker()
                }
            ),
        )
    )

    // 요일 별 로봇 운영 시간
    private val rightItem2Data = DataPair(
        subText = R.string.admin_x20,
        cosUnit = @Composable {
            CustomTimePicker()
        }
    )
    private val rightItem2 = rightItem1.copy(
        titleText = R.string.admin_x17,
        content = listOf(
            rightItem2Data,
            rightItem2Data.copy(subText = R.string.admin_x21),
            rightItem2Data.copy(subText = R.string.admin_x22),
            rightItem2Data.copy(subText = R.string.admin_x23),
            rightItem2Data.copy(subText = R.string.admin_x24),
            rightItem2Data.copy(subText = R.string.admin_x25),
            rightItem2Data.copy(subText = R.string.admin_x26),
        )
    )

    val leftItemList = listOf(leftItem1, leftItem2, leftItem3)
    val rightItemList = listOf(rightItem1, rightItem2)
}