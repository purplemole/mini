package com.clobot.mini.view.hospital

import android.util.Log
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.clobot.mini.R
import com.clobot.mini.data.page.HospitalMenuDummyData
import com.clobot.mini.util.QrCodeAnalyzer
import com.clobot.mini.view.common.HospitalTopBar
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clobot.mini.view.common.ImgMenuBtn
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.view.common.ui.MyIconPack
import com.clobot.mini.view.common.ui.myiconpack.Confirm
import com.clobot.mini.view.common.ui.myiconpack.Logo
import com.clobot.mini.view.navigation.NavRoute


// 예약 고객 페이지
@Composable
fun ReservationCustomer() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { ReservationCustomerContent() }
    )
}

// 예약 고객 페이지에 띄울 내용
@Composable
private fun ReservationCustomerContent() {
    val reservationMenus =
        remember { HospitalMenuDummyData.reservationMenuList.filter { true } }
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.reservation_customer),
            contentDescription = "reservation_customer background img",
        )
        Box(
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_48)),
            content = {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_24))
                ) {
                    items(reservationMenus) {
                        ImgMenuBtn(menu = it)
                    }
                }
            }
        )
    }

}

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
    var code by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    val camWidth = dimensionResource(R.dimen.qrViewWidth)
    val camHeight = dimensionResource(R.dimen.qrViewHeight)
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
        content = {
            Image(
                painter = painterResource(R.drawable.qr_recognition),
                contentDescription = "reservation_customer background img",
                contentScale = ContentScale.Crop
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
                            Log.i("qrAnalysis", "success!, $code")
                            if(result != "")
                                routeAction.navTo(NavRoute.ReservationConfirm)
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
                modifier = Modifier.size(width = camWidth, height = camHeight)//weight(1f).
            )
            Text(
                text = code,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            )
            Image(
                painter = painterResource(R.drawable.camera),
                contentDescription = "camera",
                modifier = Modifier.size(width = camWidth, height = camHeight),
                contentScale = ContentScale.Crop
            )
        }
    )

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


// 4depth 예약 확인 성공 페이지
@Composable
fun ReservationConfirm() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { ReservationConfirmContent() }
    )
}

/**
 * TODO change called name
 *
 */
@Composable
private fun ReservationConfirmContent() {
    val name = "홍길동"
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center,
        content = {
            Column(
                content = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_48)),
                        content = {
                            Column(content = {
                                Icon(
                                    imageVector = MyIconPack.Confirm,
                                    contentDescription = "confirm"
                                )
                                Text(
                                    text = String.format(
                                        stringResource(id = R.string.reservation_confirm_t1),
                                        name
                                    ),
//                                style = TextStyle(fontResource(fontFamily = ))
                                )
                            })
                            ImgMenuBtn(HospitalMenuDummyData.reservationConfirmMenu)
                        },
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                },
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            )
        })
}


// 4depth 예약 실패 페이지
@Composable
fun ReservationFailed() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { ReservationFailedContent() }
    )
}

@Composable
private fun ReservationFailedContent() {

}

@Composable
fun WaitingArea() {
    Image(
        painter = painterResource(R.drawable.waiting_area),
        contentDescription = "waiting-area",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ReservationInformation() {
    Image(
        painter = painterResource(R.drawable.reservation_information),
        contentDescription = "reservation-information",
        contentScale = ContentScale.Crop
    )
}
