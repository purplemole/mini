package com.clobot.mini

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clobot.mini.util.network.NetworkChecker
import com.clobot.mini.util.network.NetworkState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NetworkCheckerTest {

    private lateinit var networkChecker: NetworkChecker

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        networkChecker = NetworkChecker(context)
    }

    // 아래 두 test case 각각 simulator 조작 필요 (Network on/off)
    @Test
    fun testNetworkConnectivityWhenConnected() = runBlocking {
        networkChecker.onAvailable(network = null)
        assertEquals(NetworkState.Connected, networkChecker.networkState.first())
    }

    @Test
    fun testNetworkConnectivityWhenNotConnected() = runBlocking {
        networkChecker.onLost(network = null)
        assertEquals(NetworkState.NotConnected, networkChecker.networkState.first())
    }
}