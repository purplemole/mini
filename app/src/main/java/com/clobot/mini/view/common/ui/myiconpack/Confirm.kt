package com.clobot.mini.view.common.ui.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.clobot.mini.view.common.ui.MyIconPack

public val MyIconPack.Confirm: ImageVector
    get() {
        if (_confirm != null) {
            return _confirm!!
        }
        _confirm = Builder(name = "Confirm", defaultWidth = 128.0.dp, defaultHeight = 128.0.dp,
                viewportWidth = 128.0f, viewportHeight = 128.0f).apply {
            path(fill = SolidColor(Color(0xFF18D28F)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(64.0f, 64.0f)
                moveToRelative(-60.0f, 0.0f)
                arcToRelative(60.0f, 60.0f, 0.0f, true, true, 120.0f, 0.0f)
                arcToRelative(60.0f, 60.0f, 0.0f, true, true, -120.0f, 0.0f)
            }
            path(fill = null, stroke = null, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                    strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(22.0f, 24.0f)
                horizontalLineToRelative(83.0f)
                verticalLineToRelative(81.0f)
                horizontalLineToRelative(-83.0f)
                close()
            }
        }
        .build()
        return _confirm!!
    }

private var _confirm: ImageVector? = null
