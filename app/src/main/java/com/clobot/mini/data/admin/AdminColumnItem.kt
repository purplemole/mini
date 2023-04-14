package com.clobot.mini.data.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clobot.mini.R
import com.clobot.mini.data.admin.AdminData.*
import com.clobot.mini.view.common.*
import com.clobot.mini.view.common.ui.theme.*

object AdminColumnItem {
    // 로봇 이름
    private val leftItem1 = AdminCustomBow(
        R.string.admin_x2,
        listOf(
            DataPair(
                subText = R.string.admin_x3,
                cosUnit = {
                    Text(
                        text = stringResource(id = R.string.admin_x4),
                        modifier = Modifier
                            .background(Color.White, shape = AdminRoundedBtn.medium)
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = AdminRoundedBtn.medium
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
                cosUnit = {
                    VolumeStage()
                }
            ),
            DataPair(
                subText = R.string.admin_x7,
                cosUnit = {
                    val isCharging =
                        "on off"//if (RobotApi.getInstance().chargeStatus) "ON" else "OFF"
                    Text(
                        text = String.format(stringResource(id = R.string.admin_x8), isCharging),
                        modifier = Modifier
                            .background(Color.LightGray, shape = AdminRoundedBtn.medium)
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = AdminRoundedBtn.medium
                            )
                            .padding(15.dp)
                    )
                }
            ),
            DataPair(
                subText = R.string.admin_x9,
                cosUnit = {
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
                cosUnit = {
                    Text(
                        text = String.format(stringResource(id = R.string.admin_x12), 0, 0, 0),
                        modifier = Modifier
                            .background(Color.LightGray, shape = AdminRoundedBtn.medium)
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = AdminRoundedBtn.medium
                            )
                            .padding(15.dp)
                    )
                }
            )
        )
    )

    // <Pair<Int, () -> Unit>>s
    private val pairItem1 = Pair((R.string.admin_B1)) {/*TODO*/ }
    private val pairItem2 = Pair((R.string.admin_B2)) {/*TODO*/ }
    private val pairItem3 = Pair((R.string.admin_B3)) {/*TODO*/ }
    private val pairItem4 = Pair((R.string.admin_B4)) {/*TODO*/ }

    private val robotAction = listOf(pairItem1, pairItem2, pairItem3, pairItem4)

    private val leftItem4 = AdminCustomBow(
        titleText = R.string.admin_x13,
        content = listOf(
            DataPair(
                subText = 0,
                cosUnit = {
                    RobotManagementBtn(robotAction)
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
                cosUnit = {
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
        cosUnit = {
            CustomTimePicker()
        }
    )
    private val rightItem3 = rightItem1.copy(
        titleText = R.string.admin_x19,
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

    val leftItemList = listOf(leftItem1, leftItem2, leftItem3, leftItem4)
    val rightItemList = listOf(rightItem1, rightItem3)
}