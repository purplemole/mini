package com.clobot.mini

import com.clobot.mini.util.state.TextFieldState
import org.junit.Assert.assertEquals
import org.junit.Test

class TextFieldStateTest {
    @Test
    fun testSetText() {
        val textFieldState = TextFieldState()
        textFieldState.setText("test1")
        assertEquals("test1", textFieldState.text.value)
    }

    @Test
    fun testGetText(){
        val textFieldState = TextFieldState()
        textFieldState.setText("test2")
        val returnText = textFieldState.getText()
        assertEquals("test2", textFieldState.text.value, returnText)
    }
}