package com.clobot.mini.view.components.ui.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.clobot.mini.view.components.ui.MyIconPack

public val MyIconPack.AdminRestart: ImageVector
    get() {
        if (_adminRestart != null) {
            return _adminRestart!!
        }
        _adminRestart = Builder(name = "AdminRestart", defaultWidth = 34.0.dp, defaultHeight =
                32.0.dp, viewportWidth = 34.0f, viewportHeight = 32.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFFDFDFD)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(2.3335f, 5.3331f)
                verticalLineTo(13.3331f)
                moveTo(2.3335f, 13.3331f)
                horizontalLineTo(10.3335f)
                moveTo(2.3335f, 13.3331f)
                lineTo(8.5202f, 7.5198f)
                curveTo(9.9532f, 6.0861f, 11.726f, 5.0387f, 13.6732f, 4.4755f)
                curveTo(15.6205f, 3.9122f, 17.6787f, 3.8514f, 19.6558f, 4.2988f)
                curveTo(21.6329f, 4.7461f, 23.4644f, 5.687f, 24.9795f, 7.0337f)
                curveTo(26.4947f, 8.3803f, 27.6439f, 10.0888f, 28.3202f, 11.9998f)
                moveTo(31.6668f, 26.6664f)
                verticalLineTo(18.6664f)
                moveTo(31.6668f, 18.6664f)
                horizontalLineTo(23.6668f)
                moveTo(31.6668f, 18.6664f)
                lineTo(25.4802f, 24.4798f)
                curveTo(24.0472f, 25.9135f, 22.2743f, 26.9608f, 20.3271f, 27.5241f)
                curveTo(18.3798f, 28.0873f, 16.3216f, 28.1481f, 14.3445f, 27.7008f)
                curveTo(12.3674f, 27.2534f, 10.5359f, 26.3125f, 9.0208f, 24.9659f)
                curveTo(7.5057f, 23.6192f, 6.3564f, 21.9107f, 5.6802f, 19.9998f)
            }
        }
        .build()
        return _adminRestart!!
    }

private var _adminRestart: ImageVector? = null
