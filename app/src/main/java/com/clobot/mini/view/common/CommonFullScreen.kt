package com.clobot.mini.view.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @param modifier Modifier
 * @param needTopBar TopAppBar 표시 여부
 * @param templateBody content 내용 (Composable)
 */
@Composable
fun Template0(
    modifier: Modifier = Modifier,
    needTopBar: Boolean,
    templateBody: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            if (needTopBar)
                HospitalTopBar()
        },
        modifier = modifier.padding(bottom = 15.dp),
        content = { templateBody() }
    )
}