package com.clobot.mini.view.common.ui.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.clobot.mini.view.common.ui.MyIconPack

public val MyIconPack.AdminBattery: ImageVector
    get() {
        if (_adminBattery != null) {
            return _adminBattery!!
        }
        _adminBattery = Builder(name = "AdminBattery", defaultWidth = 34.0.dp, defaultHeight =
                32.0.dp, viewportWidth = 34.0f, viewportHeight = 32.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFFDFDFD)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(7.6668f, 24.0f)
                horizontalLineTo(5.0002f)
                curveTo(4.2929f, 24.0f, 3.6146f, 23.719f, 3.1145f, 23.219f)
                curveTo(2.6144f, 22.7189f, 2.3335f, 22.0406f, 2.3335f, 21.3333f)
                verticalLineTo(10.6667f)
                curveTo(2.3335f, 9.9594f, 2.6144f, 9.2811f, 3.1145f, 8.781f)
                curveTo(3.6146f, 8.2809f, 4.2929f, 8.0f, 5.0002f, 8.0f)
                horizontalLineTo(9.2535f)
                moveTo(21.0002f, 8.0f)
                horizontalLineTo(23.6668f)
                curveTo(24.3741f, 8.0f, 25.0523f, 8.2809f, 25.5524f, 8.781f)
                curveTo(26.0525f, 9.2811f, 26.3335f, 9.9594f, 26.3335f, 10.6667f)
                verticalLineTo(21.3333f)
                curveTo(26.3335f, 22.0406f, 26.0525f, 22.7189f, 25.5524f, 23.219f)
                curveTo(25.0523f, 23.719f, 24.3741f, 24.0f, 23.6668f, 24.0f)
                horizontalLineTo(19.4135f)
                moveTo(31.6668f, 17.3333f)
                verticalLineTo(14.6667f)
                moveTo(15.6668f, 8.0f)
                lineTo(10.3335f, 16.0f)
                horizontalLineTo(18.3335f)
                lineTo(13.0002f, 24.0f)
            }
        }
        .build()
        return _adminBattery!!
    }

private var _adminBattery: ImageVector? = null
