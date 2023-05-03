package com.clobot.mini.data.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clobot.mini.R
import com.clobot.mini.data.admin.AdminData.*
import com.clobot.mini.util.*
import com.clobot.mini.view.common.*
import com.clobot.mini.view.common.ui.MyIconPack
import com.clobot.mini.view.common.ui.myiconpack.AdminPen
import com.clobot.mini.view.common.ui.myiconpack.Cancel
import com.clobot.mini.view.common.ui.theme.*

object AdminColumnItem {
    // 로봇 이름
    private val leftItem1 = AdminCustomBow(
        R.string.admin_x2,
        listOf(
            DataPair(
                subText = R.string.admin_x3,
                cosUnit = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            Text(
                                text = stringResource(id = R.string.admin_x4),
                                style = AdminTypography.h3
                            )
                            Image(
                                imageVector = MyIconPack.AdminPen,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        }
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
                cosUnit = { VolumeStage() }
            ),
            DataPair(
                subText = R.string.admin_x7,
                cosUnit = {
                    val isCharging =
                        "on off"//if (RobotApi.getInstance().chargeStatus) "ON" else "OFF"
                    Text(
                        text = String.format(stringResource(id = R.string.admin_x8), isCharging),
                        modifier = Modifier
                            .background(prc_gray800, shape = AdminRoundedBtn.large)
                            .padding(horizontal = 7.dp, vertical = 4.dp),
                        style = AdminTypography.h3
                    )
                }
            ),
            DataPair(
                subText = R.string.admin_x9,
                cosUnit = { PromoteCycleBtn() }
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
                            .background(prc_gray800, shape = AdminRoundedBtn.large)
                            .padding(vertical = 4.dp, horizontal = 7.dp),
                        style = AdminTypography.h3
                    )
                }
            )
        )
    )

    private val leftItem4 = AdminCustomBow(
        titleText = R.string.admin_x13,
        content = listOf(
            DataPair(
                subText = 0,
                cosUnit = { RobotManagementBtn() }
            )
        )
    )

    // 이동 제한 및 강제 충전 시간
    private val rightItem1 = AdminCustomBow(
        R.string.admin_x14,
        listOf(
            DataPair(
                subText = R.string.admin_x15,
                cosUnit = { FromToPicker(LocalRestrictFromTo.current) },
            ),
        )
    )

    // 요일 별 로봇 운영 시간
    private val rightItem3Data = DataPair(
        subText = R.string.admin_x20,
        cosUnit = { FromToPicker(LocalMonFromTo.current) })

    private val rightItem3 = rightItem1.copy(
        titleText = R.string.admin_x19,
        content = listOf(
            rightItem3Data,
            rightItem3Data.copy(
                subText = R.string.admin_x21,
                cosUnit = { FromToPicker(LocalTueFromTo.current) }),
            rightItem3Data.copy(
                subText = R.string.admin_x22,
                cosUnit = { FromToPicker(LocalWedFromTo.current) }),
            rightItem3Data.copy(subText = R.string.admin_x23,
                cosUnit = { FromToPicker(LocalThuFromTo.current) }),
            rightItem3Data.copy(subText = R.string.admin_x24,
                cosUnit = { FromToPicker(LocalFriFromTo.current) }),
            rightItem3Data.copy(subText = R.string.admin_x25,
                cosUnit = { FromToPicker(LocalSatFromTo.current) }),
            rightItem3Data.copy(subText = R.string.admin_x26,
                cosUnit = { FromToPicker(LocalSunFromTo.current) }),
        )
    )

    val leftItemList = listOf(leftItem1, leftItem2, leftItem3, leftItem4)
    val rightItemList = listOf(rightItem1, rightItem3)
}