package com.clobot.mini.repo

import kotlinx.coroutines.flow.StateFlow

interface RobotRepository {
    val dockingState: StateFlow<Boolean>

    fun initialize(): Boolean

//    fun basicMove (vararg any: Any?)
}