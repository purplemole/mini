package com.clobot.mini

import com.clobot.mini.util.state.IntFieldState
import org.junit.Assert
import org.junit.Test

class IntFieldStateTest {
    @Test
    fun testSetInt() {
        val intFieldState = IntFieldState()
        intFieldState.setInt(99)
        Assert.assertEquals(99, intFieldState.int.value)
    }

    @Test
    fun testGetInt() {
        val intFieldState = IntFieldState()
        intFieldState.setInt(33)
        val returnText = intFieldState.getInt()
        Assert.assertEquals(33, returnText)
    }
}