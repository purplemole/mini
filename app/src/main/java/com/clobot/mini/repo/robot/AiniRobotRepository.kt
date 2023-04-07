package com.clobot.mini.repo.robot

import android.os.Handler
import android.os.Looper
import com.ainirobot.coreservice.client.RobotApi
import com.clobot.mini.repo.RobotRepository
import com.clobot.mini.util.robot.DockingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AiniRobotRepository @Inject constructor() : RobotRepository {
//    val isApiConnectedService = RobotApi.getInstance().isApiConnectedService
//    val isActive = RobotApi.getInstance().isActive
//    val test = RobotApi.getInstance().chargeStatus
    private val _dockingState = MutableStateFlow<DockingState>(DockingState.None)
    override val dockingState: StateFlow<DockingState> = _dockingState

    private var checkTimes: Int = 0

    override fun initialize(): Boolean {
        checkTimes++
        return if (checkTimes > 10) {
            // Temporary code
            _dockingState.value = DockingState.Connected
            false
        } else if (RobotApi.getInstance().isApiConnectedService
            && RobotApi.getInstance().isActive
        ) {
            true
        } else {
            Handler(Looper.getMainLooper()).postDelayed(Runnable { initialize() }, 300)
        }
    }

    override fun checkDockingStation() {
        if (RobotApi.getInstance().isChargePileExits) {
            _dockingState.value = DockingState.Connected
        } else {
            _dockingState.value = DockingState.NotConnected
        }
    }

    override fun getVersion(): String {
        return RobotApi.getInstance().version
    }
}