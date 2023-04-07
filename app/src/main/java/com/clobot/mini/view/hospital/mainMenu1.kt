package com.clobot.mini.view.hospital

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.clobot.mini.view.navigation.NavRoute
import com.clobot.mini.view.navigation.RouteAction
import com.clobot.mini.view.common.GlideImgView
import com.clobot.mini.view.common.Template0

// 신규 고객 페이지
@Composable
fun NewCustomer(routeAction: RouteAction) {
    // 23.04.06 tk test code ..
    val lifecycleOwner = LocalLifecycleOwner.current
    val textState = remember { mutableStateOf("") }

    DisposableEffect(Unit){
        val observer = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResume() {
                if (textState.value == ""){
                    textState.value = "created"
                    Log.i("lifecycle check", "onResume")
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                // Clear the text state when the fragment is destroyed
                Log.i("lifecycle check", "onDestroy")
                textState.value = ""
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            Log.i("lifecycle check", "NewCustomer onDispose")
        }
    }
    // .. test code end

    LaunchedEffect(Unit) {
        Log.i("Launched check", "NewCustomer Launched")
    }

    Template0(
        routeAction = routeAction,
        needTopBar = true,
        templateBody = { NewCustomerContent(routeAction = routeAction) })
}

// 신규 고객 페이지 컨텐츠
@Composable
fun NewCustomerContent(routeAction: RouteAction) {

    val customerImg =
        "https://post-phinf.pstatic.net/MjAyMTAxMTFfMzUg/MDAxNjEwMzQ2NDM3MjU0.tzHNY9U11yeRQdbqR3WruRdGUdeYNb9eOmdq9gO7mTMg.tJvrPzH8ZNBAh5qlGmthoiP9uBpI5PPIOGB0YrQ4Nccg.PNG/4.png?type=w1200"
    GlideImgView(
        routeAction = routeAction,
        nextRoute = NavRoute.NewInformation,
        imgModel = customerImg
    )
}

// 접수 방법 안내 페이지
@Composable
fun NewInformation(routeAction: RouteAction) {
    LaunchedEffect(Unit) {
        Log.i("Launched check", "NewInformation Launched")
    }
    Template0(
        routeAction = routeAction,
        needTopBar = true,
        caption = "안내데스크에 있는 접수증에 이름, 주민등록번호, 전화번호, 주소를 작성 후 담당자에게 전달해 주세요.",
        modifier = Modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFBDF3BF)),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NewInformationContent(routeAction)
            }
        }
    }
}

// 접수 방법 안내 페이지 컨텐츠
@Composable
fun NewInformationContent(routeAction: RouteAction) {
    val newInfoImg = "https://cdn.maily.so/7o7grtuc8937i1sd30x6ezf68b4g"
    GlideImgView(
        routeAction = routeAction,
        nextRoute = NavRoute.Standby,
        imgModel = newInfoImg
    )
}