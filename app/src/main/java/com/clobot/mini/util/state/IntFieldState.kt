package com.clobot.mini.util.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class IntFieldState() {
    private val _int = mutableStateOf(1)
    val int: State<Int> = _int

    fun setInt(value: Int) {
        _int.value = value
    }

    fun getInt(): Int {
        return int.value
    }
}