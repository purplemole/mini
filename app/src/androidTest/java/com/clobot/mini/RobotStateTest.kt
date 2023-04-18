package com.clobot.mini

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clobot.mini.repo.robot.AiniRobotRepository
import com.clobot.mini.view.common.MainApplication
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RobotStateTest {
    private lateinit var robot: AiniRobotRepository

    @Before
    fun setUp() {
    }

    @After
    fun delete() {

    }

    @Test
    fun testRobotConnect() = runBlocking {
        robot.checkDockingStation()
        assertEquals(true, robot.dockingState.last())
    }

    @Test
    fun robotOSConnect() {
        assertNotEquals(null, MainApplication.getInstance())
    }
}