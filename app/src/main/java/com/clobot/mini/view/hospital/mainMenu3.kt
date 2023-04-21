package com.clobot.mini.view.hospital

import android.util.Log
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.navigation.NavRoute
import com.clobot.mini.util.QrCodeAnalyzer
import com.clobot.mini.view.common.Template0

// 예약 고객 페이지
@Composable
fun ReservationCustomer() {
    LaunchedEffect(Unit) {
        Log.i("Launched check", "ReservationCustomer Launched")
    }
    Template0(
        needTopBar = true,
        templateBody = { ReservationCustomerContent() })
}

// 예약 고객 페이지에 띄울 내용
@Composable
fun ReservationCustomerContent() {
    val routeAction = LocalRouteAction.current
    var code by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    Box(
        modifier = Modifier
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "예약 고객 - 페이지&기능",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            )
            AndroidView(
                factory = { context ->
                    val previewView = PreviewView(context)
                    val preview = Preview.Builder().build()
                    val selector =
                        CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                            .build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    val imageAnalysis = ImageAnalysis.Builder().setTargetResolution(
                        Size(
                            previewView.width,
                            previewView.height
                        )
                    ).setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST).build()
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QrCodeAnalyzer { result ->
                            code = result
                        }
                    )
                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    previewView
                },
                modifier = Modifier.weight(1f)
            )
            Text(
                text = code,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    content = { Text(text = "다음 단계 진행") },
                    onClick = { routeAction.navTo(NavRoute.QrRecognition) })
                OutlinedButton(
                    content = { Text(text = "접수 장소로 이동") },
                    onClick = {  })
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraProviderFuture.get().unbindAll()
            Log.i(
                "reservation-customer",
                "cameraProviderFuture is Done : ${cameraProviderFuture.isDone}"
            )
        }
    }
}

// 3depth
// 예약 확인 페이지
@Composable
fun QrRecognition() {
    Template0(
        needTopBar = true,
        templateBody = {}
    )
}