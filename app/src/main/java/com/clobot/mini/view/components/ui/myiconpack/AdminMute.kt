package com.clobot.mini.view.components.ui.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.clobot.mini.view.components.ui.MyIconPack

public val MyIconPack.AdminMute: ImageVector
    get() {
        if (_adminMute != null) {
            return _adminMute!!
        }
        _adminMute = Builder(name = "AdminMute", defaultWidth = 64.0.dp, defaultHeight = 64.0.dp,
                viewportWidth = 64.0f, viewportHeight = 64.0f).apply {
            path(fill = SolidColor(Color(0xFF36383C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(32.0f, 0.0f)
                lineTo(32.0f, 0.0f)
                arcTo(32.0f, 32.0f, 0.0f, false, true, 64.0f, 32.0f)
                lineTo(64.0f, 32.0f)
                arcTo(32.0f, 32.0f, 0.0f, false, true, 32.0f, 64.0f)
                lineTo(32.0f, 64.0f)
                arcTo(32.0f, 32.0f, 0.0f, false, true, 0.0f, 32.0f)
                lineTo(0.0f, 32.0f)
                arcTo(32.0f, 32.0f, 0.0f, false, true, 32.0f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFB7BBC0)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(30.6665f, 22.667f)
                lineTo(23.9998f, 28.0003f)
                horizontalLineTo(18.6665f)
                verticalLineTo(36.0003f)
                horizontalLineTo(23.9998f)
                lineTo(30.6665f, 41.3337f)
                verticalLineTo(22.667f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFB7BBC0)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(46.6665f, 28.0003f)
                lineTo(38.6665f, 36.0003f)
                moveTo(38.6665f, 28.0003f)
                lineTo(46.6665f, 36.0003f)
                moveTo(30.6665f, 22.667f)
                lineTo(23.9998f, 28.0003f)
                horizontalLineTo(18.6665f)
                verticalLineTo(36.0003f)
                horizontalLineTo(23.9998f)
                lineTo(30.6665f, 41.3337f)
                verticalLineTo(22.667f)
                close()
            }
        }
        .build()
        return _adminMute!!
    }

private var _adminMute: ImageVector? = null
