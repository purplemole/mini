package com.clobot.mini.view.hospital

import android.util.Log
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.clobot.mini.R
import com.clobot.mini.data.page.HospitalMenuDummyData
import com.clobot.mini.util.LocalRouteAction
import com.clobot.mini.util.QrCodeAnalyzer
import com.clobot.mini.view.common.ImgMenuBtn
import com.clobot.mini.view.common.ui.MyIconPack
import com.clobot.mini.view.common.ui.myiconpack.Actionleft
import com.clobot.mini.view.common.ui.theme.pageTypography
import com.clobot.mini.view.common.ui.theme.prc_birth
import com.clobot.mini.view.common.ui.theme.prc_name
import com.clobot.mini.view.common.ui.theme.prc_white100
import com.clobot.mini.view.navigation.NavRoute
import kotlinx.coroutines.delay

@Composable
fun NameCalling() {
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
                            Column(
                                content = {
                                    Image(
                                        painterResource(R.drawable.confirm),
                                        contentDescription = "confirm",
                                        modifier = Modifier.size(37.dp),
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    color = prc_name,
                                                )
                                            ) {
                                                append(name)
                                            }

                                            withStyle(
                                                style = SpanStyle(
                                                    color = prc_white100
                                                )
                                            ) {
                                                append(stringResource(id = R.string.name_calling_x1))
                                            }
                                        },
                                        style = pageTypography.h1
                                    )
                                },
                                horizontalAlignment = Alignment.CenterHorizontally
                            )
                        },
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_64))
                    )
                    ImgMenuBtn(HospitalMenuDummyData.nameCallingMenu)
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        top = dimensionResource(R.dimen.padding_64),
                        bottom = dimensionResource(R.dimen.padding_48)
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            )
        })
}

@Composable
fun NameQr() {
    val routeAction = LocalRouteAction.current
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val code = remember { mutableStateOf("") }
    val failed = remember { mutableStateOf(false) }
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }

    if (code.value != "" && !failed.value)
        routeAction.navTo(NavRoute.NameConfirm)
    if (failed.value)
        routeAction.navTo(NavRoute.NameFailed)

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
            Box(
                content = {
                    Text(
                        text = stringResource(id = R.string.name_qr_x1),
                        modifier = Modifier.align(Alignment.Center),
                        style = pageTypography.h6
                    )
                    Button(
                        onClick = { routeAction.goBack() },
                        content = {
                            Image(
                                imageVector = MyIconPack.Actionleft,
                                modifier = Modifier.size(width = 37.dp, height = 14.dp),
                                contentDescription = "name-qr cancel button",
                            )
                        },
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_20))
                            .align(Alignment.TopStart)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.gnb_height))
            )

//            Text(
//                text = buildAnnotatedString {
//                    withStyle(style = SpanStyle(color = prc_name)) {
//                        append(
//                            "nn년 m월"
//                        )
//                    }
//                    append(" ")
//                    withStyle(style = SpanStyle(color = prc_birth)) {
//                        append("홍길동")
//                    }
//                    append(stringResource(id = R.string.name_calling_x1))
//                },
//                style = pageTypography.h2,
//                color = prc_white100,
//                modifier = Modifier.padding(
//                    top = dimensionResource(id = R.dimen.padding_160),
//                    start = dimensionResource(id = R.dimen.padding_64)
//                )
//            )
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
                modifier = Modifier.size(width = camWidth, height = camHeight)//weight(1f).
            )
            Image(
                painter = painterResource(R.drawable.camera),
                contentDescription = "camera",
                modifier = Modifier.size(width = camWidth, height = camHeight),
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

@Composable
fun NameConfirm() {
    Box(
        content = {
            Image(
                painter = painterResource(R.drawable.variant4),
                contentDescription = "name-confirm background img",
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = R.string.name_confirm_title),
                Modifier.align(Alignment.TopCenter)
            )
        }
    )
//    Box(content = {
//        Text(text=)
//    })
}

@Composable
fun NameFailed() {

}