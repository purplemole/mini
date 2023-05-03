package com.clobot.mini

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.clobot.mini.model.MainViewModel
import com.clobot.mini.model.RobotViewModel
import com.clobot.mini.model.TTSViewModel
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.clobot.mini.util.LocalMainViewModel
import com.clobot.mini.util.LocalRobotViewModel
import com.clobot.mini.util.LocalTTSService
import com.clobot.mini.view.common.AlertDialog
import com.clobot.mini.view.navigation.NavigationGraph
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val robotViewModel: RobotViewModel by viewModels()

    // 필요 권한
    private val myPermission = listOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContent {
            val lifecycleOwner = LocalLifecycleOwner.current
            MiniTheme {
                val permissionsState = rememberMultiplePermissionsState(permissions = myPermission)
                val viewModel = hiltViewModel<MainViewModel>()
                val ttsService = hiltViewModel<TTSViewModel>()
                val alertState = viewModel.alertState.collectAsState()

                if (permissionsState.allPermissionsGranted) {
                    CompositionLocalProvider(
                        LocalMainViewModel provides viewModel,
                        LocalRobotViewModel provides robotViewModel,
                        LocalTTSService provides ttsService
                    ) {
                        val robotViewModel = LocalRobotViewModel.current
                        // 특정 페이지 에서만 확인 필요
//                    NetworkOfflineDialog(networkState = networkState) {
//                        viewModel.onRetry()
//                    }

                        // heartbeat 0.5 sec
                        LaunchedEffect(Unit) {
                            while (true) {
                                delay(500)
                                robotViewModel.checkChargingState()
                            }
                        }

                        AlertDialog(
                            alertState.value.title,
                            alertState.value.message,
                            alertState.value.visible
                        )

                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            NavigationGraph()
                        }
                    }
                }

                DisposableEffect(key1 = lifecycleOwner, effect = {
                    val observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_RESUME) {
                            permissionsState.launchMultiplePermissionRequest()
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)

                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                })
            }
        }
    }
}
//@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
//@Composable
//fun DefaultPreview() {
//    MiniTheme {
//
//    }
//}