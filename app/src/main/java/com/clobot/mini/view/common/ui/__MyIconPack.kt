package com.clobot.mini.view.common.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.clobot.mini.view.common.ui.myiconpack.Actionleft
import com.clobot.mini.view.common.ui.myiconpack.AdminPen
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
    __AllIcons= listOf(Actionleft, AdminPen, Cancel, Logo)
    return __AllIcons!!
  }
