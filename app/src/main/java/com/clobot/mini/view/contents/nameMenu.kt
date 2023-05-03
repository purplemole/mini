package com.clobot.mini.view.contents

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
import androidx.compose.material.Scaffold
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
import com.clobot.mini.view.common.HospitalTopBar
import com.clobot.mini.view.common.HospitalTopBar2
import com.clobot.mini.view.common.ImgMenuBtn
import com.clobot.mini.view.common.ImgMenuBtn2
import com.clobot.mini.view.common.ui.theme.pageTypography
import com.clobot.mini.view.common.ui.theme.prc_birth
import com.clobot.mini.view.common.ui.theme.prc_name
import com.clobot.mini.view.common.ui.theme.prc_white100
import com.clobot.mini.view.navigation.NavRoute
import kotlinx.coroutines.delay

/*
* 진료실 안내 요청을 통해 들어 오는 서비스 메뉴들
* 기본 예약 고객과 차이점 : 환자 정보 값들이 표시
* !화면 디자인 변경 시 아래 composable 들은 image만 교체하면 안 됨!
* */

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

// 본인 확인 페이지
@Composable
fun NameQr() {
    Scaffold(
        topBar = {
            HospitalTopBar2(
                goBackEnable = true,
                title = stringResource(id = R.string.name_qr_x1)
            )
        },
        content = {
            NameQrContent()
        }
    )
}

@Composable
private fun NameQrContent() {
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
        content = {
            Image(
                painter = painterResource(R.drawable.qr_recognition),
                contentDescription = "reservation_customer background img",
                contentScale = ContentScale.Crop
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = prc_birth)) {
                        append(
                            "nn년 m월"
                        )
                    }
                    append(" ")
                    withStyle(style = SpanStyle(color = prc_name)) {
                        append("홍길동")
                    }
                    append(stringResource(id = R.string.name_qr_x2))
                },
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

// 본인 확인 완료
@Composable
fun NameConfirm() {
    Scaffold(
        topBar = {
            HospitalTopBar2(
                title = stringResource(id = R.string.name_confirm_title)
            )
        },
        content = {
            NameConfirmContent()
        }
    )
}

@Composable
private fun NameConfirmContent() {
    val name = "홍길동"
    Box(
        content = {
            Image(
                painter = painterResource(R.drawable.variant4),
                contentDescription = "name-confirm background img",
                contentScale = ContentScale.Crop
            )
            Column(
                content = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = prc_name)) {
                                append(
                                    name
                                )
                            }
                            withStyle(style = SpanStyle(color = prc_white100)) {
                                append(stringResource(id = R.string.name_confirm_x1))
                            }
                        },
                        style = pageTypography.h1,
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.padding_64),
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.name_confirm_x2),
                        style = pageTypography.h4,
                        color = prc_white100
                    )
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_48))
            )
        },
    )
}

@Composable
fun TreatmentMethod() {
    Scaffold(
        topBar = {
            HospitalTopBar(false)
        },
        content = {
            TreatmentMethodContent()
        }
    )
}

@Composable
private fun TreatmentMethodContent() {
    val name = "홍길동"
    Box(
        content = {
            Image(
                painter = painterResource(R.drawable.variant4),
                contentDescription = "name-confirm background img",
                contentScale = ContentScale.Crop
            )
            Column(
                content = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = prc_name)) {
                                append(
                                    name
                                )
                            }
                            withStyle(style = SpanStyle(color = prc_white100)) {
                                append(stringResource(id = R.string.treatment_method_x1))
                            }
                        },
                        style = pageTypography.h1,
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.padding_64),
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.treatment_method_x2),
                        style = pageTypography.h4,
                        color = prc_white100
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_48))
            )
        },
    )
}

// 본인 확인 실패
@Composable
fun NameFailed() {
    Scaffold(
        topBar = {
            HospitalTopBar2(
                title = stringResource(id = R.string.name_failed_title)
            )
        },
        content = {
            NameFailedContent()
        }
    )
}

@Composable
private fun NameFailedContent() {
    val failedMenus =
        remember { HospitalMenuDummyData.reservationFailedMenu.filter { true } }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
        content = {
            Image(
                painter = painterResource(R.drawable._failed),
                contentDescription = "name-failed background img",
                contentScale = ContentScale.Crop
            )
            Column(
                content = {
                    Text(
                        text = stringResource(id = R.string.name_failed_x1),
                        style = pageTypography.h1
                    )
                    Row(
                        content = {
                            ImgMenuBtn(menu = failedMenus[0])
                            ImgMenuBtn2(menu = failedMenus[1])
                        },
                        horizontalArrangement = Arrangement.spacedBy(
                            dimensionResource(id = R.dimen.padding_24)
                        )
                    )
                },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_64),
                        bottom = dimensionResource(R.dimen.padding_48)
                    )
            )
        }
    )
}

// 환자 안내 (본인 확인 실패 -> 로봇 이동)
@Composable
fun PatientInformation() {
    Scaffold(
        topBar = {
            HospitalTopBar(false)
        },
        content = {
            PatientInformationContent()
        }
    )
}

@Composable
private fun PatientInformationContent() {
    val name = "홍길동"
    Box(content = {
        Image(
            painter = painterResource(R.drawable.variant4),
            contentDescription = "patient-information background img",
            contentScale = ContentScale.Crop
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = prc_white100,
                    )
                ) {
                    append(text = stringResource(id = R.string.patient_information_x1_1))
                }
                withStyle(style = SpanStyle(color = prc_name)) { append(name) }
                withStyle(style = SpanStyle(color = prc_white100)) { append(stringResource(id = R.string.patient_information_x1_2)) }
            },
            style = pageTypography.h1,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = dimensionResource(id = R.dimen.padding_48))
        )
    }, modifier = Modifier.fillMaxSize())
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun NameMenuPreview() {
    NameConfirmContent()
}
