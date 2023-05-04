package com.clobot.mini.view.page.menuReservationCustomer

import android.util.Log
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.clobot.mini.R
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.util.QrCodeAnalyzer
import com.clobot.mini.view.components.HospitalTopBar
import com.clobot.mini.view.components.ui.theme.pageTypography
import com.clobot.mini.view.components.ui.theme.prc_white100
import com.clobot.mini.view.navigation.NavRoute
import kotlinx.coroutines.delay

// 3depth
// 예약 확인 페이지
@Composable
fun QrRecognition() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { QrRecognitionContent() }
    )
}

@Composable
private fun QrRecognitionContent() {
    val routeAction = LocalRouteAction.current
    val code = remember { mutableStateOf("") }
    val failed = remember { mutableStateOf(false) }
    if (code.value != "" && !failed.value)
        routeAction.navTo(NavRoute.ReservationConfirm)
    if (failed.value)
        routeAction.navTo(NavRoute.ReservationFailed)
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    val camWidth = dimensionResource(R.dimen.qrViewWidth)
    val camHeight = dimensionResource(R.dimen.qrViewHeight)
    Box(
        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.BottomEnd,
        content = {
            Image(
                painter = painterResource(R.drawable.qr_recognition),
                contentDescription = "reservation_customer background img",
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = R.string.qr_recognition_x1),
                style = pageTypography.h3,
                color = prc_white100,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_64),
                    start = dimensionResource(id = R.dimen.padding_64)
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
                            700,
                            500
                        )
                    ).setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build()
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QrCodeAnalyzer { result ->
                            code.value = result
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
                modifier = Modifier
                    .size(width = camWidth, height = camHeight)
                    .align(Alignment.BottomEnd)//weight(1f).
            )
//            Text(
//                text = code,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(32.dp)
//            )
            Image(
                painter = painterResource(R.drawable.camera),
                contentDescription = "camera",
                modifier = Modifier
                    .size(width = camWidth, height = camHeight)
                    .align(Alignment.BottomEnd),
                contentScale = ContentScale.Crop
            )
        }
    )

    LaunchedEffect(Unit) {
        delay(7000)
        failed.value = true
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