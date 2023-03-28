package com.example.mini.view.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.mini.navigation.NavRoute
import com.example.mini.navigation.RouteAction
import com.skydoves.landscapist.glide.GlideImage

/**
 * @param modifier Modifier
 * @param routeAction routeAction
 * @param caption 하단 자막 내용
 * @param needTopBar TopAppBar 표시 여부
 * @param templateBody content 내용 (Composable)
 */
@Composable
fun Template0(
    modifier: Modifier = Modifier,
    routeAction: RouteAction,
    caption: String = "",
    needTopBar: Boolean,
    templateBody: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            if (needTopBar)
                HospitalTopBar(routeAction)
        },
        bottomBar = {
            if (caption.isNotBlank())
                BottomTTSCaption(caption)
        },
        modifier = modifier.padding(bottom = 15.dp),
    ) {
        templateBody()
    }
}

/**
 * TODO imgContent - Int 수정
 * 상단 바 + Column( center picto + text) + 하단 자막 바
 * @param modifier 페이지 별 추가 레이아웃 요소
 * @param routeAction 이동 액션
 * @param nextRoute 이동할 다음 페이지?
 * @param caption 하단부 자막
 * @param imgContent 보여 줄 img
 * @param textContent 본문 내용
 * @param needTopBar 상단 바 여부  = true (필수? 선택?)
 */
@Composable
fun Template1(
    modifier: Modifier = Modifier,
    routeAction: RouteAction,
    nextRoute: NavRoute,
    caption: String = "",
    imgContent: String = "https://cdn.maily.so/7o7grtuc8937i1sd30x6ezf68b4g",
    textContent: String = "",
    needTopBar: Boolean,
) {
    Scaffold(
        topBar = {
            if (needTopBar)
                HospitalTopBar(routeAction)
        },
        bottomBar = {
            if (caption.isNotBlank())
                BottomTTSCaption(caption)
        },
        modifier = modifier.padding(bottom = 15.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImgView(
                    routeAction = routeAction,
                    nextRoute = nextRoute,
                    imgModel = imgContent
                )
                if (textContent.isNotBlank())
                    Text("TEMPLATE 1번")
            }
        }
    }
}

/**
 * topAppBar - text - bottom 자막
 * @param modifier 페이지 별 추가 레이아웃 요소
 * @param routeAction 이동 액션
 * @param textContent 화면에 표시 될 text
 * @param textStyle textStyle 값
 * @param needTopBar 상단 바 여부
 * @param caption 하단부 자막
 */
@Composable
fun Template2(
    modifier: Modifier = Modifier,
    routeAction: RouteAction,
    textContent: String = "Template2번",
    customTextStyle: TextStyle,
    needTopBar: Boolean,
    caption: String = ""
) {
    Scaffold(
        topBar = {
            if (needTopBar)
                HospitalTopBar(routeAction)
        },
        bottomBar = {
            if (caption.isNotBlank())
                BottomTTSCaption(caption)
        },
        modifier = modifier
            .padding(bottom = 15.dp)
            .fillMaxSize(),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = textContent,
                    modifier = Modifier
                        .background(Color(0xFFE1D8F1))
                        .padding(50.dp),
                    style = customTextStyle,
                )
            }
        }
    )
}