package com.clobot.mini.view.page.etc.move

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clobot.mini.R

@Composable
fun PromoteNormal() {
    Image(
        painter = painterResource(R.drawable.promote_normal),
        contentDescription = "promote-normal",
        contentScale = ContentScale.Crop
    )
}

