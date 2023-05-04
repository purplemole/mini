package com.clobot.mini.view.components.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.clobot.mini.view.components.ui.myiconpack.Actionleft
import com.clobot.mini.view.components.ui.myiconpack.AdminBattery
import com.clobot.mini.view.components.ui.myiconpack.AdminChecked
import com.clobot.mini.view.components.ui.myiconpack.AdminEnd
import com.clobot.mini.view.components.ui.myiconpack.AdminMinus
import com.clobot.mini.view.components.ui.myiconpack.AdminMute
import com.clobot.mini.view.components.ui.myiconpack.AdminPen
import com.clobot.mini.view.components.ui.myiconpack.AdminPlus
import com.clobot.mini.view.components.ui.myiconpack.AdminRestart
import com.clobot.mini.view.components.ui.myiconpack.AdminUnchecked
import com.clobot.mini.view.components.ui.myiconpack.Cancel
import com.clobot.mini.view.components.ui.myiconpack.Logo
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Actionleft, AdminBattery, AdminChecked, AdminEnd, AdminMinus, AdminMute,
        AdminPen, AdminPlus, AdminRestart, AdminUnchecked, Cancel, Logo)
    return __AllIcons!!
  }
