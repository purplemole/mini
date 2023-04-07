package com.clobot.mini

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.clobot.mini.model.MainViewModel
import com.clobot.mini.model.RobotViewModel
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.clobot.mini.util.network.NetworkOfflineDialog
import com.clobot.mini.util.network.NetworkState
import com.clobot.mini.util.robot.DockingState
import com.clobot.mini.view.common.BootCheck
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val viewRModel by viewModels<RobotViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // 참고 : https://copycoding.tistory.com/357
        val cameraPermissionCheck = ContextCompat.checkSelfPermission(
            this@MainActivity,
            android.Manifest.permission.CAMERA, //자체 사용 권한 선택(.CAMERA)
        )
        if (cameraPermissionCheck != PackageManager.PERMISSION_GRANTED) { // 권한이 없는 경우
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA), //권한 요청이 필요한 것들을 배열로 넘겨줌
                1000
            )
        }

        setContent {
            //val navController = rememberNavController()
            val dockingState by viewRModel.dockingState.collectAsState(initial = DockingState.None)
            val networkState by viewModel.networkState.collectAsState(initial = NetworkState.None)
            MiniTheme {
                NetworkOfflineDialog(networkState = networkState) {
                    viewModel.onRetry()
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //NavigationGraph()
                    BootCheck(dockingState, networkState)
                }
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