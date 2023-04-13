package com.clobot.mini.util.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class TextFieldState {
    private val _text = mutableStateOf("")
    val text: State<String> = _text

    fun setText(value: String) {
        _text.value = value
    }

    fun getText(): String {
        return text.value
    }
}