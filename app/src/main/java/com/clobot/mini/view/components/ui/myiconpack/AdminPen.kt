package com.clobot.mini.view.components.ui.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.clobot.mini.view.components.ui.MyIconPack

public val MyIconPack.AdminPen: ImageVector
    get() {
        if (_adminPen != null) {
            return _adminPen!!
        }
        _adminPen = Builder(name = "AdminPen", defaultWidth = 64.0.dp, defaultHeight = 64.0.dp,
                viewportWidth = 64.0f, viewportHeight = 64.0f).apply {
            path(fill = SolidColor(Color(0xFF36383C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.0f, 0.0f)
                lineTo(48.0f, 0.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 64.0f, 16.0f)
                lineTo(64.0f, 48.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 48.0f, 64.0f)
                lineTo(16.0f, 64.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 0.0f, 48.0f)
                lineTo(0.0f, 16.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 16.0f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFB7BBC0)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(20.0f, 45.3337f)
                horizontalLineTo(44.0f)
                moveTo(34.6667f, 18.667f)
                lineTo(40.0f, 24.0003f)
                lineTo(25.3333f, 38.667f)
                horizontalLineTo(20.0f)
                verticalLineTo(33.3337f)
                lineTo(34.6667f, 18.667f)
                close()
            }
        }
        .build()
        return _adminPen!!
    }

private var _adminPen: ImageVector? = null
