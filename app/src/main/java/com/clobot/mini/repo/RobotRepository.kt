package com.clobot.mini.repo

import com.clobot.mini.util.robot.DockingState
import kotlinx.coroutines.flow.StateFlow

interface RobotRepository {
    val dockingState: StateFlow<DockingState>

    fun initialize(): Boolean
    fun checkDockingStation()

    fun getVersion(): String
}