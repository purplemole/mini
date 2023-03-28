package com.example.mini.view.hospital

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mini.navigation.NavRoute
import com.example.mini.navigation.RouteAction
import com.example.mini.view.common.GlideImgView
import com.example.mini.view.common.Template0
import com.example.mini.view.common.Template2

// 당일 방문 고객 페이지
@Composable
fun ExistingCustomer(routeAction: RouteAction) {
    Template0(
        routeAction = routeAction,
        needTopBar = true,
        templateBody = { ExistingCustomerContent(routeAction) })
}

// 당일 방문 고객 페이지 컨텐츠
@Composable
fun ExistingCustomerContent(routeAction: RouteAction) {
    val existingCustomerImg = "https://cdn.maily.so/7o7grtuc8937i1sd30x6ezf68b4g"
    val contentText = "재 방문을 환영합니다.\n제가 접수 장소까지 안내해 드리겠습니다."
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        GlideImgView(
            routeAction = routeAction,
            nextRoute = NavRoute.EXISTING_INFORMATION,
            imgModel = existingCustomerImg
        )
        Text(text = contentText, style = TextStyle(textAlign = TextAlign.Center, fontSize = 30.sp))
    }
}

// 예약 방법 안내 페이지
/**
 * TODO routeAction 을 통해 다음 페이지 이동 시키기(STANDBY, 자동으로)
 *
 * @param routeAction
 */
@Composable
fun ExistingInformation(routeAction: RouteAction) {
    Template0(
        routeAction = routeAction,
        needTopBar = true,
        templateBody = { ExistingInformationContent() }
    )
}

// 예약 방법 안내 페이지
@Composable
fun ExistingInformationContent() {
    val textContent = "1. 담당자에게 성함을 말씀해 주세요.\n2. 본인 확인을 위해서 생년 월을 추가로 말씀해 주세요"
    val customTextStyle = TextStyle(
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline,
        fontSize = 20.sp,
        lineHeight = 100.sp
    )
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