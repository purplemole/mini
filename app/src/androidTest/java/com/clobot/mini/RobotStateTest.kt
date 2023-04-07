package com.clobot.mini

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clobot.mini.repo.RobotRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RobotStateTest {
    private lateinit var robot: RobotRepository

    @Before
    fun setUp() {
    }

    @Test
    fun testRobotConnect() = runBlocking {
        robot.checkDockingStation()
        assertEquals(true, robot.dockingState.first())
    }
}