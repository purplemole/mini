package com.clobot.mini

import androidx.test.platform.app.InstrumentationRegistry
import com.clobot.mini.data.admin.StoreAdminSetting
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class StoreAdminSettingTest {

    private lateinit var storeAdminSetting: StoreAdminSetting

    @Before
    fun setUp() {
        storeAdminSetting = StoreAdminSetting(InstrumentationRegistry.getInstrumentation().targetContext)
    }

    @Test
    fun testSaveAndGetAdminSetting() = runBlocking{
        val promoteCycle = 5
        val forceCharging = 20

        storeAdminSetting.forTestCode(promoteCycle, forceCharging)

        val resultPromoteCycle = storeAdminSetting.getPromoteCycle.first()
        val resultForceCharging = storeAdminSetting.getForceCharging.first()

        assertEquals(resultPromoteCycle, promoteCycle)
        assertEquals(resultForceCharging, forceCharging)
    }

}