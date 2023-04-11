package com.clobot.mini.repo.robot

import android.os.Handler
import android.os.Looper
import com.ainirobot.coreservice.client.RobotApi
import com.clobot.mini.repo.RobotRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AiniRobotRepository @Inject constructor() : RobotRepository {
//    val chargeStatus = RobotApi.getInstance().chargeStatus
    private val _dockingState = MutableStateFlow(false)
    override val dockingState: StateFlow<Boolean> = _dockingState

    private var checkTimes: Int = 0

    override fun initialize(): Boolean {
        checkTimes++
        return if (checkTimes > 10) {
            // Temporary code
            _dockingState.value = true
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
        _dockingState.value = RobotApi.getInstance().isChargePileExits
    }

    override fun getVersion(): String {
        return RobotApi.getInstance().version
    }
}