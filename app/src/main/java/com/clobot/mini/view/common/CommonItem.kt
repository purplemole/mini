package com.clobot.mini.view.common

import android.annotation.SuppressLint
import android.widget.TextClock
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.clobot.mini.data.page.HospitalMenu
import com.clobot.mini.util.ContinuousClickHelper
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.navigation.NavRoute
import com.clobot.mini.view.navigation.RouteAction
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
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
                    text = stringResource(id = menu.menu),
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
fun HospitalTopBar(canNavigate: Boolean = true) {
    val routeAction = LocalRouteAction.current
    TopAppBar(
        title = {
//            Text(
//                text = "Logo",
//                modifier = Modifier.fillMaxWidth(),
//                style = TextStyle(textAlign = TextAlign.Center)
//            )
            Row(modifier = Modifier.fillMaxWidth()) {
                TopAppBarTitle()
            }

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
                        if (canNavigate && routeAction.getCurPage() != "main")
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
                        if (routeAction.getCurPage() != "main")
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
    nextRoute: NavRoute,
    imgModel: String
) {
    val routeAction = LocalRouteAction.current
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

@Composable
fun CoilImgView(
    nextRoute: NavRoute,
    imgModel: String
) {
    val routeAction = LocalRouteAction.current
    CoilImage(
        imageModel = imgModel,
        shimmerParams = ShimmerParams(
            baseColor = MaterialTheme.colors.background,
            highlightColor = Color.LightGray,
            durationMillis = 350,
            dropOff = 0.65f,
            tilt = 20f
        ),
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
        failure = {
            Text(
                "image request failed",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        },
    )
}

@Composable
fun TopAppBarTitle() {
    val routeAction = LocalRouteAction.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val ccHelper = ContinuousClickHelper()
        Box(
            modifier = Modifier
                .background(Color(0xFFF7E9E9))
                .width(100.dp)
                .noRippleClickable { ccHelper.touch(routeAction) },
            content = {
                Text(text = "click!!")
            }
        )
        Box(
            modifier = Modifier
                .background(Color(0xFFAD8484))
                .width(100.dp)
                .noRippleClickable { routeAction.navTo(NavRoute.Developer) },
            content = {
                Text(text = "테스트")
            }
        )
        Text(
            "로고",
            modifier = Modifier
                .background(Color(0xFFFDE1B6))
        )
        AndroidView(
            factory = { context ->
                TextClock(context).apply {
                    format12Hour?.let { this.format12Hour = "hh:mm a" }
                    timeZone?.let { this.timeZone = "Asia/Seoul" }
                }
            },
            modifier = Modifier.background(Color(0xF1DBAFFF))
        )
    }
}

@Composable
fun HospitalBarIcon(modifier: Modifier = Modifier, faIcon: FaIconType) {
    Card(
        elevation = 5.dp,
        modifier = modifier
            .clip(CircleShape)
    ) {
        Box(
            modifier = modifier.padding(5.dp)
        ) {
            FaIcon(faIcon = faIcon)
        }
    }
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}