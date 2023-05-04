package com.clobot.mini.view.page.menuGuideRequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.clobot.mini.R
import com.clobot.mini.view.components.HospitalTopBar
import com.clobot.mini.view.components.ui.theme.pageTypography
import com.clobot.mini.view.components.ui.theme.prc_name
import com.clobot.mini.view.components.ui.theme.prc_white100

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
    val patientInfoTextb = stringResource(id = R.string.patient_information_x1_1);
    val patientInfoTexte = stringResource(id = R.string.patient_information_x1_2)

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
                    append(text = patientInfoTextb)
                }
                withStyle(style = SpanStyle(color = prc_name)) { append(name) }
                withStyle(style = SpanStyle(color = prc_white100)) { append(patientInfoTexte) }
            },
            style = pageTypography.h1,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = dimensionResource(id = R.dimen.padding_48))
        )
    }, modifier = Modifier.fillMaxSize())
}

