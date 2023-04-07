package com.clobot.mini

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.clobot.mini.util.network.NetworkChecker
import com.clobot.mini.data.network.NetworkState
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
        // test 레벨에서 context 얻어오기
        val context = InstrumentationRegistry.getInstrumentation().targetContext
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