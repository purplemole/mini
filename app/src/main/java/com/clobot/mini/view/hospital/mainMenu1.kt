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
import com.clobot.mini.view.common.CoilImgView
import com.clobot.mini.view.navigation.NavRoute
import com.clobot.mini.view.common.Template0

// 신규 고객 페이지
@Composable
fun NewCustomer() {
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
        needTopBar = true,
        templateBody = { NewCustomerContent() })
}

// 신규 고객 페이지 컨텐츠
@Composable
fun NewCustomerContent() {
    val customerImg =
        "https://post-phinf.pstatic.net/MjAyMTAxMTFfMzUg/MDAxNjEwMzQ2NDM3MjU0.tzHNY9U11yeRQdbqR3WruRdGUdeYNb9eOmdq9gO7mTMg.tJvrPzH8ZNBAh5qlGmthoiP9uBpI5PPIOGB0YrQ4Nccg.PNG/4.png?type=w1200"
    CoilImgView(
        nextRoute = NavRoute.NewInformation,
        imgModel = customerImg
    )
}

// 접수 방법 안내 페이지
@Composable
fun NewInformation() {
    LaunchedEffect(Unit) {
        Log.i("Launched check", "NewInformation Launched")
    }
    Template0(
        needTopBar = true,
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
                NewInformationContent()
            }
        }
    }
}

// 접수 방법 안내 페이지 컨텐츠
@Composable
fun NewInformationContent() {
    val newInfoImg = "https://cdn.maily.so/7o7grtuc8937i1sd30x6ezf68b4g"
    CoilImgView(
        nextRoute = NavRoute.Standby,
        imgModel = newInfoImg
    )
}