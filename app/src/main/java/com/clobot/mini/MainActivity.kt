package com.clobot.mini

import android.content.pm.ActivityInfo
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
import com.clobot.mini.model.MainViewModel
import com.clobot.mini.view.common.ui.theme.MiniTheme
import com.clobot.mini.util.network.NetworkOfflineDialog
import com.clobot.mini.util.network.NetworkState
import com.clobot.mini.view.common.BootCheck
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContent {
            //val navController = rememberNavController()
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
                    BootCheck()
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