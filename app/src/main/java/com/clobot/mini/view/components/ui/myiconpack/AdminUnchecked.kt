package com.clobot.mini.view.components.ui.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.clobot.mini.view.components.ui.MyIconPack

public val MyIconPack.AdminUnchecked: ImageVector
    get() {
        if (_adminUnchecked != null) {
            return _adminUnchecked!!
        }
        _adminUnchecked = Builder(name = "AdminUnchecked", defaultWidth = 64.0.dp, defaultHeight =
                64.0.dp, viewportWidth = 64.0f, viewportHeight = 64.0f).apply {
            path(fill = SolidColor(Color(0xFF36383C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(56.0f, 24.0f)
                curveTo(56.0f, 15.1634f, 48.8366f, 8.0f, 40.0f, 8.0f)
                horizontalLineTo(24.0f)
                curveTo(15.1634f, 8.0f, 8.0f, 15.1634f, 8.0f, 24.0f)
                verticalLineTo(40.0f)
                curveTo(8.0f, 48.8366f, 15.1634f, 56.0f, 24.0f, 56.0f)
                horizontalLineTo(40.0f)
                curveTo(48.8366f, 56.0f, 56.0f, 48.8366f, 56.0f, 40.0f)
                verticalLineTo(24.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(45.7487f, 29.1213f)
                curveTo(46.9203f, 27.9497f, 46.9203f, 26.0503f, 45.7487f, 24.8787f)
                curveTo(44.5772f, 23.7071f, 42.6777f, 23.7071f, 41.5061f, 24.8787f)
                lineTo(29.4853f, 36.8995f)
                lineTo(23.1213f, 30.5355f)
                curveTo(21.9497f, 29.364f, 20.0503f, 29.364f, 18.8787f, 30.5355f)
                curveTo(17.7071f, 31.7071f, 17.7071f, 33.6066f, 18.8787f, 34.7782f)
                lineTo(27.364f, 43.2635f)
                curveTo(27.9266f, 43.8261f, 28.6896f, 44.1421f, 29.4853f, 44.1421f)
                curveTo(30.2809f, 44.1421f, 31.044f, 43.8261f, 31.6066f, 43.2635f)
                lineTo(45.7487f, 29.1213f)
                close()
            }
        }
        .build()
        return _adminUnchecked!!
    }

private var _adminUnchecked: ImageVector? = null
