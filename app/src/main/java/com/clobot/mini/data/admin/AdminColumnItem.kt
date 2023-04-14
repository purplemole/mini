package com.clobot.mini.data.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clobot.mini.R
import com.clobot.mini.data.admin.AdminData.*
import com.clobot.mini.view.common.*

object AdminColumnItem {

    // 로봇 이름
    private val leftItem1 = AdminCustomBow(
        R.string.admin_x2,
        listOf(
            DataPair(
                subText = R.string.admin_x3,
                cosUnit = @Composable {
                    Text(
                        text = stringResource(id = R.string.admin_x4),
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(15.dp)
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
                    VolumeStage()
                }
            ),
            DataPair(
                subText = R.string.admin_x7,
                cosUnit = @Composable {
                    val isCharging =
                        "on off"//if (RobotApi.getInstance().chargeStatus) "ON" else "OFF"
                    Text(
                        text = String.format(stringResource(id = R.string.admin_x8), isCharging),
                        modifier = Modifier
                            .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(15.dp)
                    )
                }
            ),
            DataPair(
                subText = R.string.admin_x9,
                cosUnit = @Composable {
                    PromoteCycleBtn()
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
                    Text(
                        text = String.format(stringResource(id = R.string.admin_x12), 0, 0, 0),
                        modifier = Modifier
                            .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(15.dp)
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
//            DataPair(
//                subText = R.string.admin_x16,
//                cosUnit = @Composable {
//                    CustomTimePicker()
//                }
//            ),
        )
    )

    // 요일 별 로봇 운영 시간
    private val rightItem3Data = DataPair(
        subText = R.string.admin_x20,
        cosUnit = @Composable {
            CustomTimePicker()
        }
    )
    private val rightItem3 = rightItem1.copy(
        titleText = R.string.admin_x17,
        content = listOf(
            rightItem3Data,
            rightItem3Data.copy(subText = R.string.admin_x21),
            rightItem3Data.copy(subText = R.string.admin_x22),
            rightItem3Data.copy(subText = R.string.admin_x23),
            rightItem3Data.copy(subText = R.string.admin_x24),
            rightItem3Data.copy(subText = R.string.admin_x25),
            rightItem3Data.copy(subText = R.string.admin_x26),
        )
    )

    val leftItemList = listOf(leftItem1, leftItem2, leftItem3)
    val rightItemList = listOf(rightItem1, rightItem3)
}