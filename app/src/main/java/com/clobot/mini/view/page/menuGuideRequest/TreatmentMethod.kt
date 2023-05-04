package com.clobot.mini.view.page.menuGuideRequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    val arriveText = stringResource(id = R.string.treatment_method_x1)

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
                                append(arriveText)
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