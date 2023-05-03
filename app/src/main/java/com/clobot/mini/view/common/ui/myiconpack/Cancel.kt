package com.clobot.mini.view.common.ui.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.clobot.mini.view.common.ui.MyIconPack

public val MyIconPack.Cancel: ImageVector
    get() {
        if (_cancel != null) {
            return _cancel!!
        }
        _cancel = Builder(name = "Cancel", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveToRelative(338.0f, 672.0f)
                lineToRelative(142.0f, -142.0f)
                lineToRelative(142.0f, 142.0f)
                lineToRelative(50.0f, -50.0f)
                lineToRelative(-142.0f, -142.0f)
                lineToRelative(142.0f, -142.0f)
                lineToRelative(-50.0f, -50.0f)
                lineToRelative(-142.0f, 142.0f)
                lineToRelative(-142.0f, -142.0f)
                lineToRelative(-50.0f, 50.0f)
                lineToRelative(142.0f, 142.0f)
                lineToRelative(-142.0f, 142.0f)
                lineToRelative(50.0f, 50.0f)
                close()
                moveTo(480.138f, 905.0f)
                quadToRelative(-88.138f, 0.0f, -165.625f, -33.084f)
                quadToRelative(-77.488f, -33.083f, -135.417f, -91.012f)
                reflectiveQuadTo(88.084f, 645.625f)
                quadTo(55.0f, 568.276f, 55.0f, 480.138f)
                quadTo(55.0f, 391.0f, 88.084f, 313.513f)
                quadToRelative(33.083f, -77.488f, 90.855f, -134.969f)
                quadToRelative(57.772f, -57.482f, 135.195f, -91.013f)
                quadTo(391.557f, 54.0f, 479.779f, 54.0f)
                quadToRelative(89.221f, 0.0f, 166.827f, 33.454f)
                quadToRelative(77.605f, 33.453f, 135.012f, 90.802f)
                quadToRelative(57.407f, 57.349f, 90.895f, 134.877f)
                quadTo(906.0f, 390.66f, 906.0f, 480.0f)
                quadToRelative(0.0f, 88.276f, -33.531f, 165.747f)
                quadToRelative(-33.531f, 77.471f, -91.013f, 135.278f)
                quadToRelative(-57.481f, 57.808f, -134.831f, 90.891f)
                quadTo(569.276f, 905.0f, 480.138f, 905.0f)
                close()
                moveTo(480.0f, 811.0f)
                quadToRelative(138.0f, 0.0f, 234.5f, -96.372f)
                reflectiveQuadTo(811.0f, 480.0f)
                quadToRelative(0.0f, -138.0f, -96.5f, -234.5f)
                reflectiveQuadToRelative(-235.0f, -96.5f)
                quadToRelative(-137.5f, 0.0f, -234.0f, 96.5f)
                reflectiveQuadToRelative(-96.5f, 235.0f)
                quadToRelative(0.0f, 137.5f, 96.372f, 234.0f)
                reflectiveQuadTo(480.0f, 811.0f)
                close()
                moveTo(480.0f, 480.0f)
                close()
            }
        }
        .build()
        return _cancel!!
    }

private var _cancel: ImageVector? = null
