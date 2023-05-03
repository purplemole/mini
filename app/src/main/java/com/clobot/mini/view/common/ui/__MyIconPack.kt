package com.clobot.mini.view.common.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.clobot.mini.view.common.ui.myiconpack.Actionleft
import com.clobot.mini.view.common.ui.myiconpack.AdminBattery
import com.clobot.mini.view.common.ui.myiconpack.AdminEnd
import com.clobot.mini.view.common.ui.myiconpack.AdminMinus
import com.clobot.mini.view.common.ui.myiconpack.AdminMute
import com.clobot.mini.view.common.ui.myiconpack.AdminPen
import com.clobot.mini.view.common.ui.myiconpack.AdminPlus
import com.clobot.mini.view.common.ui.myiconpack.AdminRestart
import com.clobot.mini.view.common.ui.myiconpack.Cancel
import com.clobot.mini.view.common.ui.myiconpack.Logo
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Actionleft, AdminBattery, AdminEnd, AdminMinus, AdminMute, AdminPen,
        AdminPlus, AdminRestart, Cancel, Logo)
    return __AllIcons!!
  }
