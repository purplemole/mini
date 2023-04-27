package com.clobot.mini.view.common.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.clobot.mini.view.common.ui.myiconpack.Logo
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Logo)
    return __AllIcons!!
  }
