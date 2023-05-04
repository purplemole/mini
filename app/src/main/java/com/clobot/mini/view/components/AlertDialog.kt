package com.clobot.mini.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.clobot.mini.util.LocalMainViewModel

@Composable
fun AlertDialog(title: String, message: String, visible: Boolean) {
    if (!visible) return

    Dialog(onDismissRequest = {}) {
        Surface(
            modifier = Modifier
                .width(205.714.dp)
                .wrapContentHeight()
                .background(color = Color.Black),
            shape = RoundedCornerShape(6.857.dp),
            color = Color.Black
        ) {
            DialogContent(title = title, message = message)
        }
    }
}

@Composable
fun DialogContent(title: String, message: String) {
    val viewModel = LocalMainViewModel.current
    Column(
        modifier = Modifier
            .padding(12.dp, 12.dp, 12.dp, 10.dp)
            .wrapContentSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 9.143.dp),
            color = Color.White,
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 9.143.dp),
            color = Color(0xFFB7BBC0),
            text = message,
            fontWeight = FontWeight.Normal,
            fontSize = 8.sp,
        )
        Box(
            modifier = Modifier
                .width(100.286.dp)
                .height(25.143.dp)
                .align(Alignment.End)
                .clip(RoundedCornerShape(4.571.dp))
                .background(color = Color(0xFF7978DE))
                .clickable(enabled = true, onClick = { viewModel.hideAlertState() })
        ) {
            Text(
                modifier = Modifier
                    .padding(0.dp)
                    .align(Alignment.Center),
                text = "메인 화면으로 돌아가기",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp,
            )
        }
    }
}

@Preview
@Composable
fun PreviewAlertDialog() {
    AlertDialog(title = "hi", message = "hi this is message long long long", visible = true)
}