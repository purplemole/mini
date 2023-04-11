package com.clobot.mini.data.admin

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.clobot.mini.R
import com.clobot.mini.view.common.CustomButton
import com.clobot.mini.view.common.CustomTextField
import com.clobot.mini.view.common.CustomVolumeButton

object AdminLeftItem {
    // 로봇 이름
    private val item1 = AdminCustomBow(
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
    private val item2 = AdminCustomBow(
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

    private val item3 = AdminCustomBow(
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

    val leftItemList = listOf(item1, item2, item3)
}