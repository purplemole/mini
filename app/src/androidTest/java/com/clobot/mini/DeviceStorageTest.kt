package com.clobot.mini

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clobot.mini.util.DeviceStorage
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * TODO (04.05.tk) Replace 0L with the expected total capacity of the device's storage
 *
 */
@RunWith(AndroidJUnit4::class)
class DeviceStorageTest {
    private val deviceStorage = DeviceStorage()

    @Test
    fun testGetTotalCapacity() {
        val totalCapacity = deviceStorage.getTotalCapacity()
        // assertEquals(0L, totalCapacity)
    }

    @Test
    fun testGetCapacityInUse() {
        val capacityInUse = deviceStorage.getCapacityInUse()
        //assertEquals(0L, capacityInUse)
    }
}