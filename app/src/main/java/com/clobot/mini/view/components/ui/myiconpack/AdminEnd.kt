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

public val MyIconPack.AdminEnd: ImageVector
    get() {
        if (_adminEnd != null) {
            return _adminEnd!!
        }
        _adminEnd = Builder(name = "AdminEnd", defaultWidth = 32.0.dp, defaultHeight = 32.0.dp,
                viewportWidth = 32.0f, viewportHeight = 32.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFFDFDFD)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(24.4803f, 8.8537f)
                curveTo(26.1582f, 10.532f, 27.3007f, 12.6702f, 27.7634f, 14.9979f)
                curveTo(28.2261f, 17.3256f, 27.9882f, 19.7382f, 27.0798f, 21.9307f)
                curveTo(26.1715f, 24.1231f, 24.6334f, 25.9971f, 22.6601f, 27.3155f)
                curveTo(20.6868f, 28.6339f, 18.3669f, 29.3376f, 15.9937f, 29.3376f)
                curveTo(13.6204f, 29.3376f, 11.3005f, 28.6339f, 9.3272f, 27.3155f)
                curveTo(7.3539f, 25.9971f, 5.8158f, 24.1231f, 4.9075f, 21.9307f)
                curveTo(3.9991f, 19.7382f, 3.7612f, 17.3256f, 4.2239f, 14.9979f)
                curveTo(4.6866f, 12.6702f, 5.8292f, 10.532f, 7.507f, 8.8537f)
                moveTo(16.0003f, 2.667f)
                verticalLineTo(16.0003f)
            }
        }
        .build()
        return _adminEnd!!
    }

private var _adminEnd: ImageVector? = null
