package com.clobot.mini.view.common

import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.widget.TextClock
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.clobot.mini.R
import com.clobot.mini.data.page.HospitalMenu
import com.clobot.mini.util.ContinuousClickHelper
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.common.ui.MyIconPack
import com.clobot.mini.view.common.ui.myiconpack.Logo
import com.clobot.mini.view.common.ui.theme.prc_white100
import com.clobot.mini.view.navigation.NavRoute
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import java.util.*


/**
 * 직사각형 버튼 (Column (이미지, text))
 * @param menu
 * @param routeAction
 */
@Composable
fun ImgMenuBtn(menu: HospitalMenu) {
    val routeAction = LocalRouteAction.current

    Image(
        painter = painterResource(menu.picto),
        contentDescription = menu.menu,
        modifier = Modifier.clickable {
            routeAction.navTo(menu.route)
        })
}

/**
 * Scaffold 내부에서 사용 가능한 TopAppBar
 * TODO : title - 날짜 - 시간 - 배터리 표시
 * @param routeAction
 * @param canNavigate
 */
@Composable
fun HospitalTopBar(goBackEnable: Boolean = true) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(27.dp),
        backgroundColor = Color.Black,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                content = { TopAppBarTitle(goBackEnable) }
            )
        },
        elevation = 0.dp,
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
fun TopAppBarTitle(goBackEnable : Boolean = true) {
    val routeAction = LocalRouteAction.current
    val backModifier = if (!goBackEnable) Modifier.alpha(0f)
    else Modifier
        .noRippleClickable { routeAction.goBack() }
        .alpha(1f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val ccHelper = ContinuousClickHelper()
        val arrowIcon = Icons.Default.ArrowBack
        Icon(
            imageVector = arrowIcon,
            contentDescription = "back",
            modifier = backModifier.size(14.dp),
            tint = prc_white100
        )
        Spacer(modifier = Modifier.width(167.dp))
        Box(
            modifier = Modifier
                .noRippleClickable { ccHelper.touch(routeAction) }
                .size(50.dp),
        )
        Icon(
            imageVector = MyIconPack.Logo,
            modifier = Modifier.size(width = 63.dp, height = 14.dp),
            contentDescription = "logo",
            tint = Color.White
        )
        Box(
            modifier = Modifier
                .size(50.dp)
                .noRippleClickable { routeAction.navTo(NavRoute.Developer) },
        )
        Spacer(modifier = Modifier.width(98.dp)) //148.dp
        Row(
            verticalAlignment = Alignment.CenterVertically,
            content = {
                GnBClock()
            })
    }
}

@Composable
fun GnBClock() {
    AndroidView(
        factory = { context ->
            val mTypeface = ResourcesCompat.getFont(context, R.font.minsans_bold)
            TextClock(context).apply {
                format12Hour?.let { this.format12Hour = "hh:mm aa" }
                timeZone?.let { this.timeZone = "Asia/Seoul" }
                setTextColor(prc_white100.toArgb())
                typeface = mTypeface
                setTextSize(TypedValue.COMPLEX_UNIT_PX, 32f)
                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        s?.let {
                            val regex = "(오전|오후)".toRegex()
                            val replacedText =
                                regex.replace(s.toString()) { matchResult ->
                                    if (matchResult.value == "오전") "AM" else "PM"
                                }
                            if (s.toString() != replacedText) {
                                s.replace(0, s.length, replacedText)
                            }
                        }
                    }
                })
            }
        },
    )
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}
