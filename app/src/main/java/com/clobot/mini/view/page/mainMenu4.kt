package com.clobot.mini.view.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.clobot.mini.R
import com.clobot.mini.data.page.HospitalMenuDummyData
import com.clobot.mini.view.components.HospitalTopBar
import com.clobot.mini.view.components.ImgMenuBtn

@Composable
fun SitesInformation() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = { SitesInformationContent() },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun SitesInformationContent() {
    val backgroundImg = painterResource(R.drawable.variant7)
    Box(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            Image(
                painter = backgroundImg,
                contentDescription = "sites-information",
                contentScale = ContentScale.Crop
            )

            Box(
                content = {
                    val sitesMenus =
                        remember { HospitalMenuDummyData.sitesMenuList.filter { true } }
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        content = {
                            items(sitesMenus) {
                                ImgMenuBtn(menu = it)
                            }
                        }
                    )
                },
                contentAlignment = Alignment.Center
            )
        },
        contentAlignment = Alignment.Center
    )
}

@Composable
fun HospitalHours() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = {
            Image(
                painter = painterResource(R.drawable.hospital_hours),
                contentDescription = "hospital-hours",
                contentScale = ContentScale.Crop
            )
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ReservationMethod() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = {
            Image(
                painter = painterResource(R.drawable.reservation_method),
                contentDescription = "reservation-method",
                contentScale = ContentScale.Crop
            )
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun Parking() {
    Scaffold(
        topBar = { HospitalTopBar() },
        content = {
            Image(
                painter = painterResource(R.drawable.parking),
                contentDescription = "parking",
                contentScale = ContentScale.Crop
            )
        },
        modifier = Modifier.fillMaxSize()
    )
}