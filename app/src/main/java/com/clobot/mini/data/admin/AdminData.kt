package com.clobot.mini.data.admin

import androidx.compose.runtime.Composable

class AdminData {
    data class DataPair(
        val subText: Int,
        val cosUnit: @Composable () -> Unit
    )

    data class AdminCustomBow(
        var titleText: Int,// = R.string.admin_x15,
        var content: List<DataPair>
    )
}