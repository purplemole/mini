package com.example.mini.view.common

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextClock
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mini.data.HospitalMenu
import com.example.mini.navigation.NavRoute
import com.example.mini.navigation.RouteAction
import com.example.mini.view.hospital.HospitalBarIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.skydoves.landscapist.glide.GlideImage

/**
 * 하단 자막 영역
 * @param tts
 */
@Composable
fun BottomTTSCaption(tts: String) {
    val scrollState = rememberScrollState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(Color(0x27353838))
    ) {
        Text(
            text = tts,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 17.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        )
    }
}

/**
 * 직사각형 버튼 (Column (이미지, text))
 * @param menu
 * @param routeAction
 */
@Composable
fun ImgMenuBtn(menu: HospitalMenu, routeAction: RouteAction) {
    Card(
        modifier = Modifier
            .size(width = 150.dp, height = 200.dp)
            .padding(horizontal = 20.dp)
            .clickable {
                routeAction.navTo(menu.route)
            },
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(10.dp)//,
                .size(110.dp, 150.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
//                    GlideImage(imageModel = )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = menu.menu,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                )
            }
        }
    }
}

/**
 * Scaffold 내부에서 사용 가능한 TopAppBar
 * TODO : title - 날짜 - 시간 - 배터리 표시
 * @param routeAction
 * @param canNavigate
 */
@SuppressLint("InvalidColorHexValue") // 투명 색 - 임시
@Composable
fun HospitalTopBar(routeAction: RouteAction, canNavigate: Boolean = true) {
    TopAppBar(
        title = {
//                Text(text = "Logo")
        },
        backgroundColor = Color(0xff00000000),
        elevation = 0.dp,
        contentColor = Color.DarkGray,
        navigationIcon = {
            HospitalBarIcon(
                Modifier
                    .clip(CircleShape)
                    .background(Color.Yellow)
                    .clickable {
                        if (canNavigate)
                            routeAction.goMain()
                    },
                FaIcons.Home,
            )
        },
        actions = {
            HospitalBarIcon(
                Modifier
                    .clip(CircleShape)
                    .background(Color(0xFFC5E7E7))
                    .clickable {
                        routeAction.goBack()
                    }, FaIcons.ArrowLeft
            )
        }
    )
}

/**
 * TODO 분기 /
 *
 * @param
 */
@Composable
fun GlideImgView(
    routeAction: RouteAction,
    nextRoute: NavRoute,
    imgModel: String
) {
    GlideImage(
        imageModel = imgModel,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .height(250.dp)
            .width(250.dp)
            .clip(CircleShape)
            .border(
                shape = CircleShape,
                border = BorderStroke(
                    width = 3.dp,
                    color = Color.LightGray
                )
            )
            .clickable {
                routeAction.navTo(nextRoute)
            },
    )
}

/**
 * TODO 작동 확인 필요/parameter 수정 필요(timeZone/context/format12Hour)
 *
 * @param modifier
 * @param format12Hour "yyyy-MM-dd hh:mm:ss a"꼴
 * @param format24Hour "hh:mm:ss" 꼴
 * @param timeZone Asia/Seoul 확인 필요
 * @param mContext 호출하는 곳의 current context 전달 필요!
 *
 * 참고 : https://stackoverflow.com/questions/68025651/whats-the-equivalent-of-textclock-in-jetpack-compose
 */
@Composable
private fun TextClock(
    modifier: Modifier,
    format12Hour: String? = null,
    format24Hour: String? = null,
    timeZone: String? = null,
    mContext: Context
) {
    AndroidView(
        factory = { _ -> //it
            TextClock(mContext).apply {
                format12Hour?.let { this.format12Hour = it }
                format24Hour?.let { this.format24Hour = it }
                timeZone?.let { this.timeZone = it }
            }
        },
        modifier = modifier
    )
}